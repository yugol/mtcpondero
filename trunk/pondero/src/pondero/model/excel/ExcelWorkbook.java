package pondero.model.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.engine.staples.StringUtil;
import pondero.model.Workbook;
import pondero.model.entities.Participant;
import pondero.model.entities.base.Record;
import pondero.model.participants.DefaultParticipants;

// http://poi.apache.org/spreadsheet/quick-guide.html

public class ExcelWorkbook implements Workbook {

    public static Participant getAnonymousParticipant() {
        final Participant anonymous = new Participant();
        anonymous.setId("#0");
        anonymous.setName("George");
        anonymous.setSurname("Nemo");
        return anonymous;
    }

    private final File         workbookFile;
    private final XSSFWorkbook workbook;
    private boolean            dirty = false;

    private final CellStyle    headerStyle;
    private final CellStyle    oddStyle;
    private final CellStyle    evenStyle;

    public ExcelWorkbook() throws Exception {
        this("implicit.xlsx");
    }

    public ExcelWorkbook(final File workbookFile) throws Exception {
        this.workbookFile = workbookFile;
        workbook = workbookFile.exists() ? new XSSFWorkbook(new FileInputStream(workbookFile)) : new XSSFWorkbook();

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
            for (Participant p : DefaultParticipants.getParticipants()) {
                add(p);
            }
        }
    }

    public ExcelWorkbook(final String workbookPath) throws Exception {
        this(new File(workbookPath));
    }

    @Override
    public void add(final Record record) throws Exception {
        final Sheet sheet = getSheet(record);
        final Row firstRow = sheet.getRow(0);
        final int rowIdx = sheet.getLastRowNum() + 1;
        final Row newRow = sheet.createRow(rowIdx);
        for (int colIdx = 0; colIdx < firstRow.getLastCellNum(); ++colIdx) {
            final String cellName = firstRow.getCell(colIdx).getStringCellValue();
            final String getterName = Record.getGetterName(cellName);
            final Method getter = record.getClass().getMethod(getterName, (Class<?>[]) null);
            final String val = (String) getter.invoke(record, (Object[]) null);
            final Cell cell = newRow.createCell(colIdx);
            cell.setCellValue(val);
            cell.setCellStyle(rowIdx % 2 == 0 ? evenStyle : oddStyle);
            sheet.autoSizeColumn(colIdx);
        }
        dirty = true;
    }

    @Override
    public void deleteParticipants() {
        Sheet sheet = getSheet(new Participant());
        for (int rowIdx = sheet.getLastRowNum(); rowIdx >= 0; --rowIdx) {
            sheet.removeRow(sheet.getRow(rowIdx));
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
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void save() throws IOException {
        final FileOutputStream fileOut = new FileOutputStream(workbookFile);
        workbook.write(fileOut);
        fileOut.close();
        dirty = false;
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

}
