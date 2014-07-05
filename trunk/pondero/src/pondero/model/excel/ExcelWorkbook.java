package pondero.model.excel;

import static pondero.Logger.info;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.Globals;
import pondero.SysUtil;
import pondero.engine.staples.DateUtil;
import pondero.engine.staples.StringUtil;
import pondero.model.Workbook;
import pondero.model.WorkbookListener;
import pondero.model.entities.base.Record;
import pondero.model.participants.DefaultParticipants;
import pondero.model.participants.Participant;
import pondero.model.participants.SurnameNameIdComparator;

// http://poi.apache.org/spreadsheet/quick-guide.html

public class ExcelWorkbook implements Workbook {

    private static final SurnameNameIdComparator PARTICIPANT_COMPARATOR = new SurnameNameIdComparator();

    private File                                 workbookFile;
    private final XSSFWorkbook                   workbook;
    private boolean                              dirty;

    private final CellStyle                      headerStyle;
    private final CellStyle                      oddStyle;
    private final CellStyle                      evenStyle;

    private final List<WorkbookListener>         workbookListeners      = new ArrayList<WorkbookListener>();

    public ExcelWorkbook() throws Exception {
        this("implicit.xlsx");
    }

    public ExcelWorkbook(final File wbFile) throws Exception {
        workbook = openWorkbook(wbFile);
        Globals.setLastWorkbookFile(workbookFile);

        headerStyle = workbook.createCellStyle();
        headerStyle.setWrapText(false);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        final Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);

        oddStyle = workbook.createCellStyle();
        oddStyle.setBorderRight(CellStyle.BORDER_THIN);

        evenStyle = workbook.createCellStyle();
        evenStyle.setBorderRight(CellStyle.BORDER_THIN);
        evenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        evenStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        if (!workbookFile.exists()) {
            for (final Participant p : DefaultParticipants.getParticipants()) {
                add(p);
            }
        }

        setDirty(false);
    }

    public ExcelWorkbook(final String workbookPath) throws Exception {
        this(new File(workbookPath));
    }

    @Override
    public void add(final Record record) throws Exception {
        info("add record ", record.getClass().getSimpleName(), " : ", record.toCsv());
        setDirty(true);
        int recordRowIdx = 0;
        Row recordRow = null;
        final Sheet sheet = getSheet(record);
        if (record instanceof Participant) {
            int inColIdx = getIdColumnIndex(sheet);
            for (int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); ++rowIdx) {
                final Row row = sheet.getRow(rowIdx);
                String id = row.getCell(inColIdx).getStringCellValue();
                if (id.equals(((Participant) record).getId())) {
                    recordRowIdx = rowIdx;
                    recordRow = row;
                    break;
                }
            }
        }
        if (recordRowIdx == 0) {
            recordRowIdx = sheet.getLastRowNum() + 1;
            recordRow = sheet.createRow(recordRowIdx);
        }
        final Row firstRow = sheet.getRow(0);
        for (int colIdx = 0; colIdx < firstRow.getLastCellNum(); ++colIdx) {
            final String cellName = firstRow.getCell(colIdx).getStringCellValue();
            final String getterName = Record.getGetterName(cellName);
            final Method getter = record.getClass().getMethod(getterName, (Class<?>[]) null);
            final String val = (String) getter.invoke(record, (Object[]) null);
            final Cell cell = recordRow.createCell(colIdx);
            cell.setCellValue(val);
            cell.setCellStyle(recordRowIdx % 2 == 0 ? evenStyle : oddStyle);
            sheet.autoSizeColumn(colIdx);
        }
    }

    @Override
    public void addWorkbookListener(WorkbookListener listener) {
        if (!workbookListeners.contains(listener)) {
            workbookListeners.add(listener);
        }
    }

    @Override
    public void close() throws IOException {
        info("close workbook: ", workbookFile.getCanonicalPath());
    }

    @Override
    public void deleteParticipants() {
        info("delete participants: ");
        final Sheet sheet = getSheet(new Participant());
        for (int rowIdx = sheet.getLastRowNum(); rowIdx >= 1; --rowIdx) {
            sheet.removeRow(sheet.getRow(rowIdx));
            setDirty(true);
        }
    }

    @Override
    public List<? extends Record> getAll(final Class<? extends Record> prototype) throws Exception {
        final List<Record> records = new ArrayList<Record>();
        final Sheet sheet = getSheet(prototype.newInstance());
        final Row firstRow = sheet.getRow(0);
        outer: for (int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); ++rowIdx) {
            final Row row = sheet.getRow(rowIdx);
            final Record record = prototype.newInstance();
            for (int colIdx = 0; colIdx < firstRow.getLastCellNum(); ++colIdx) {
                final String cellName = firstRow.getCell(colIdx).getStringCellValue();
                if (StringUtil.isNullOrBlank(cellName)) {
                    break;
                }
                final String setterName = Record.getSetterName(cellName);
                final Method setter = prototype.getMethod(setterName, String.class);
                final Cell cell = row.getCell(colIdx);
                final String cellValue = cell.getStringCellValue();
                if (colIdx == 0 && StringUtil.isNullOrBlank(cellValue)) {
                    break outer;
                }
                setter.invoke(record, cellValue);
            }
            records.add(record);
        }
        return records;
    }

    @Override
    public List<Participant> getAllParticipants() throws Exception {
        @SuppressWarnings("unchecked")
        List<Participant> buffer = (List<Participant>) getAll(Participant.class);
        Collections.sort(buffer, PARTICIPANT_COMPARATOR);
        return buffer;
    }

    @Override
    public String getNewUniqueParticipantId() {
        int maxIdx = 100;
        final Sheet sheet = getSheet(new Participant());
        int idColIdx = getIdColumnIndex(sheet);
        for (int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); ++rowIdx) {
            final Row row = sheet.getRow(rowIdx);
            int id = Integer.parseInt(row.getCell(idColIdx).getStringCellValue());
            if (id > maxIdx) {
                maxIdx = id;
            }
        }
        return String.valueOf(maxIdx + 1);
    }

    @Override
    public String getWorkbookName() {
        return workbookFile.getName();
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void save() throws IOException {
        info("save workbook: ", workbookFile.getCanonicalPath());
        saveWorkbook(workbookFile);
    }

    @Override
    public void saveAs(final File selectedFile) throws IOException {
        info("save workbook as: ", selectedFile.getCanonicalPath());
        workbookFile = normalizeWorkbookFile(selectedFile);
        saveWorkbook(workbookFile);
        Globals.setLastWorkbookFile(workbookFile);
    }

    @Override
    public String toString() {
        return workbookFile.getName();
    }

    @Override
    public void view() throws Exception {
        final File tempFile = viewWorkbookFile();
        info("view workbook: ", tempFile.getCanonicalPath());
        final FileOutputStream tempOut = new FileOutputStream(tempFile);
        workbook.write(tempOut);
        tempOut.close();
        String[] cmd = null;
        if (SysUtil.isWindows()) {
            cmd = new String[] { "cmd.exe", "/c", tempFile.getCanonicalPath() };
        } else if (SysUtil.isMacOSX()) {
            cmd = new String[] { "open", tempFile.getCanonicalPath() };
        }
        Runtime.getRuntime().exec(cmd);
    }

    private void backupWorkbook(final File wbFile) throws IOException {
        final long now = System.currentTimeMillis();
        final String timestamp = DateUtil.toCompactDate(now) + DateUtil.toCompactTime(now);
        String backupFileName = wbFile.getName();
        final int dotIndex = backupFileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            backupFileName = backupFileName.substring(0, dotIndex) + "-" + timestamp + backupFileName.substring(dotIndex);
        } else {
            backupFileName += "-" + timestamp;
        }
        final File backupFile = new File(Globals.getFolderResultsBackup(), backupFileName);
        FileUtils.copyFile(wbFile, backupFile, true);
        info("workbook backup: ", backupFile.getCanonicalPath());
    }

    private int getIdColumnIndex(Sheet sheet) {
        final Row firstRow = sheet.getRow(0);
        int inColIdx = 0;
        for (; inColIdx < firstRow.getLastCellNum(); ++inColIdx) {
            final String cellName = firstRow.getCell(inColIdx).getStringCellValue();
            if ("ID".equals(cellName)) {
                break;
            }
        }
        return inColIdx;
    }

    private Sheet getSheet(final Record record) {
        Sheet sheet = workbook.getSheet(record.getSheetName());
        if (sheet == null) {
            sheet = workbook.createSheet(record.getSheetName());
        }
        if (sheet.getRow(0) == null) {
            final Row row = sheet.createRow(0);
            int columnIndex = 0;
            for (final String columnName : record.getColumnNames()) {
                final Cell cell = row.createCell(columnIndex++);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(columnName);
                cell.setCellStyle(headerStyle);
            }
        }
        return sheet;
    }

    private File normalizeWorkbookFile(File wbFile) throws IOException {
        String filePath = wbFile.getCanonicalPath();
        if (!filePath.endsWith(ExcelWorkbookFilter.EXT)) {
            filePath += ExcelWorkbookFilter.EXT;
            wbFile = new File(filePath);
        }
        return wbFile;
    }

    private XSSFWorkbook openWorkbook(File wbFile) throws IOException {
        setDirty(false);
        if (wbFile.exists()) {
            info("open existing workbook: ", wbFile.getCanonicalPath());
            backupWorkbook(wbFile);
            workbookFile = wbFile;
            try (InputStream wbIn = new FileInputStream(wbFile)) {
                return new XSSFWorkbook(wbIn);
            }
        } else {
            wbFile = normalizeWorkbookFile(wbFile);
            info("open new workbook: ", wbFile.getCanonicalPath());
            workbookFile = wbFile;
            return new XSSFWorkbook();
        }
    }

    private void saveWorkbook(final File wbFile) throws FileNotFoundException, IOException {
        final FileOutputStream fileOut = new FileOutputStream(wbFile);
        workbook.write(fileOut);
        fileOut.close();
        setDirty(false);
    }

    private void setDirty(boolean dirty) {
        if (this.dirty != dirty) {
            this.dirty = dirty;
            for (WorkbookListener listener : workbookListeners) {
                listener.onDirtyFlagChanged(dirty);
            }
        }
    }

    private File viewWorkbookFile() {
        final File tempFolder = Globals.getFolderResultsTemp();
        String fileName = workbookFile.getName();
        final int dotIndex = fileName.indexOf(".");
        if (dotIndex >= 0) {
            fileName = fileName.substring(0, dotIndex);
        }
        final String tempFileName = fileName + "-" + UUID.randomUUID().toString().replace("-", "") + ExcelWorkbookFilter.EXT;
        final File tempFile = new File(tempFolder, tempFileName);
        return tempFile;
    }

}
