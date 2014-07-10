package pondero.model.drivers.excel;

import static pondero.Logger.info;
import static pondero.Logger.warning;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.model.drivers.Driver;
import pondero.model.foundation.PRow;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.PType;
import pondero.util.BooleanUtil;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

//http://poi.apache.org/spreadsheet/quick-guide.html

public abstract class ExcelDriver extends Driver {

    private File         dataFile;
    private XSSFWorkbook workbook;

    private CellStyle    evenDateStyle;
    private CellStyle    evenStyle;
    private CellStyle    headerStyle;
    private CellStyle    oddDateStyle;
    private CellStyle    oddStyle;

    public ExcelDriver(final File dataFile) throws Exception {
        super(dataFile.getCanonicalPath());
    }

    public ExcelDriver(final String connectionString) {
        super(connectionString);
    }

    @Override
    public void open() throws Exception {
        dataFile = new File(getConnectionString());
        if (dataFile.exists()) {
            info("opening existing data file: ", getConnectionString());
            try (InputStream wbIn = new FileInputStream(dataFile)) {
                workbook = new XSSFWorkbook(wbIn);
            }
        } else {
            String filePath = dataFile.getCanonicalPath();
            if (!filePath.endsWith(ExcelFileFilter.DEFAULT_EXTENSION)) {
                filePath += ExcelFileFilter.DEFAULT_EXTENSION;
                dataFile = new File(filePath);
            }
            info("opening existing data file: ", getConnectionString());
            workbook = new XSSFWorkbook();
        }
        setConnectionString(dataFile.getCanonicalPath());

        final Font headerFont = getWorkbook().createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        final XSSFCreationHelper createHelper = getWorkbook().getCreationHelper();

        headerStyle = getWorkbook().createCellStyle();
        headerStyle.setWrapText(false);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(headerFont);

        oddStyle = getWorkbook().createCellStyle();
        oddStyle.setBorderRight(CellStyle.BORDER_THIN);
        oddStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        oddStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        evenStyle = getWorkbook().createCellStyle();
        evenStyle.setBorderRight(CellStyle.BORDER_THIN);
        evenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        evenStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        oddDateStyle = getWorkbook().createCellStyle();
        oddDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        oddDateStyle.setBorderRight(CellStyle.BORDER_THIN);
        oddDateStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        oddDateStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        evenDateStyle = getWorkbook().createCellStyle();
        evenDateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        evenDateStyle.setBorderRight(CellStyle.BORDER_THIN);
        evenDateStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        evenDateStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }

    @Override
    public void close() throws Exception {
        info("closing data file: ", getConnectionString());
        workbook = null;
        dataFile = null;
    }

    protected XSSFWorkbook getWorkbook() {
        return workbook;
    }

    protected Sheet getSheet(final String name) {
        Sheet sheet = workbook.getSheet(name);
        if (sheet == null) {
            sheet = workbook.createSheet(name);
        }
        return sheet;
    }

    protected void saveWorkbook() throws Exception {
        info("saving data file: ", getConnectionString());
        try (FileOutputStream fileOut = new FileOutputStream(dataFile)) {
            workbook.write(fileOut);
        }
    }

    protected boolean isHeadRow(final Row row, final PSheet pSheet) {
        for (int colIdx = row.getFirstCellNum(); colIdx < row.getLastCellNum(); ++colIdx) {
            final Cell cell = row.getCell(colIdx);
            if (cell != null && Cell.CELL_TYPE_STRING == cell.getCellType()) {
                final String value = cell.getStringCellValue();
                if (value != null) {
                    for (int i = 0; i < pSheet.getColumnCount(); ++i) {
                        final String colName = pSheet.getColumn(i).getName();
                        if (colName.equals(value)) { return true; }
                    }
                }
            }
        }
        return false;
    }

    protected void readSheet(final PSheet pSheet, final Sheet xSheet) throws Exception {
        Row headRow = null;
        PRow pRow = null;
        for (int rowIdx = xSheet.getFirstRowNum(); rowIdx <= xSheet.getLastRowNum(); ++rowIdx) {
            final Row xRow = xSheet.getRow(rowIdx);
            if (xRow != null) {
                if (headRow == null) {
                    if (isHeadRow(xRow, pSheet)) {
                        headRow = xRow;
                    }
                } else {
                    for (int colIdx = 0; colIdx < xRow.getLastCellNum(); ++colIdx) {
                        if (headRow.getFirstCellNum() <= colIdx && colIdx < headRow.getLastCellNum()) {
                            final Cell headCell = headRow.getCell(colIdx);
                            if (headCell != null && Cell.CELL_TYPE_STRING == headCell.getCellType()) {
                                final String xName = headCell.getStringCellValue();
                                final Integer pIdx = pSheet.index(xName);
                                if (pIdx != null) {
                                    final Cell valueCell = xRow.getCell(colIdx);
                                    if (valueCell != null) {
                                        if (pRow == null) {
                                            pRow = pSheet.addRow();
                                        }
                                        pRow.set(pIdx, getCellValue(valueCell, pSheet.getColumn(pIdx).getType()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            pRow = null;
        }
    }

    protected void commitSheet(final PSheet pSheet) {
        final int idx = getWorkbook().getSheetIndex(pSheet.getName());
        if (idx >= 0) {
            getWorkbook().removeSheetAt(idx);
        }
        final Sheet xSheet = getWorkbook().createSheet(pSheet.getName());
        for (final Row xRow : xSheet) {
            xSheet.removeRow(xRow);
        }
        Row xRow = xSheet.createRow(0);
        for (int colIdx = 0; colIdx < pSheet.getColumnCount(); ++colIdx) {
            final Cell cell = xRow.createCell(colIdx);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(pSheet.getColumn(colIdx).getName());
            cell.setCellStyle(headerStyle);
        }
        for (int rowIdx = 0; rowIdx < pSheet.getRowCount(); ++rowIdx) {
            xRow = xSheet.createRow(1 + rowIdx);
            for (int colIdx = 0; colIdx < pSheet.getColumnCount(); ++colIdx) {
                final PType pType = pSheet.getColumn(colIdx).getType();
                final Cell cell = xRow.createCell(colIdx);
                setCellValue(cell, pSheet.get(rowIdx, colIdx), pType);
                if (PType.DATE == pType) {
                    cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? evenDateStyle : oddDateStyle);
                } else {
                    cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? evenStyle : oddStyle);
                }
            }
        }
        if (pSheet.getRowCount() <= 1000) {
            for (int colIdx = 0; colIdx < pSheet.getColumnCount(); ++colIdx) {
                xSheet.autoSizeColumn(colIdx);
            }
        }
    }

    protected Object getCellValue(final Cell cell, final PType pType) throws Exception {
        final int xType = cell.getCellType();
        if (PType.STRING == pType) {
            if (Cell.CELL_TYPE_STRING == xType) { return StringUtil.toCellString(cell.getStringCellValue()); }
        } else if (PType.BOOLEAN == pType) {
            if (Cell.CELL_TYPE_STRING == xType) { return BooleanUtil.toBoolean(cell.getStringCellValue()); }
            if (Cell.CELL_TYPE_NUMERIC == xType) { return BooleanUtil.toBoolean(cell.getNumericCellValue()); }
        } else if (PType.DECIMAL == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return NumberUtil.toDecimal(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return NumberUtil.toDecimal(cell.getStringCellValue()); }
        } else if (PType.DATE == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return DateUtil.toDateMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return DateUtil.toDateMillis(cell.getStringCellValue()); }
        } else if (PType.TIME == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return DateUtil.toTimeMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return DateUtil.toTimeMillis(cell.getStringCellValue()); }
        } else if (PType.TIMESTAMP == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return DateUtil.toTimestampMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return DateUtil.toTimestampMillis(cell.getStringCellValue()); }
        }
        warning("getCellValue could not match ", pType, " with ", xType);
        return null;
    }

    protected void setCellValue(final Cell cell, final Object value, final PType pType) {
        if (value != null) {
            switch (pType) {
                case STRING:
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue((String) value);
                    break;
                case DECIMAL:
                    cell.setCellValue(((Number) value).doubleValue());
                    break;
                case DATE:
                case TIME:
                case TIMESTAMP:
                    cell.setCellValue(new Date((Long) value));
                    break;
                case BOOLEAN:
                    cell.setCellValue((Boolean) value);
                    break;
                default:
                    break;
            }
        }
    }

}
