package pondero.data.drivers.excel;

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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.Context;
import pondero.data.drivers.Driver;
import pondero.data.model.PRow;
import pondero.data.model.PSheet;
import pondero.data.model.PType;
import pondero.tests.update.Artifact;
import pondero.util.BooleanUtil;
import pondero.util.TimeUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

//http://poi.apache.org/spreadsheet/quick-guide.html

public abstract class ExcelDriver extends Driver {

    public static void fillMetadate(final XSSFWorkbook workbook) {
        final Artifact art = Context.getPonderoArtifact();
        workbook.getProperties().getCoreProperties().setCreator(art.getCodeName());
    }

    public static void setCellValue(final Cell cell, final Object value, final PType pType) {
        if (value != null) {
            switch (pType) {
                case STRING:
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue((String) value);
                    break;
                case DECIMAL:
                case INT:
                    cell.setCellValue(((Number) value).doubleValue());
                    break;
                case DATE:
                case TIME:
                case TIMESTAMP:
                    cell.setCellValue(new Date((long) value));
                    break;
                case BOOLEAN:
                    cell.setCellValue((Boolean) value);
                    break;
                case FORMULA:
                    cell.setCellFormula((String) value);
                    break;
                default:
                    warning("setCellValue could not match ", pType, " with ", value);
                    break;
            }
        }
    }

    private static final String DATE_FORMAT      = "yyyy-MM-dd";
    private static final String DEFAULT_FORMAT   = "General";
    private static final String HEADER_FORMAT    = "General";
    private static final String INT_FORMAT       = "0";
    private static final String TIME_FORMAT      = "HH:mm:ss";

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private File                dataFile;

    private XSSFWorkbook        workbook;
    private final CellStyle     dateEvenStyle;
    private final CellStyle     dateOddStyle;
    private final CellStyle     defaultEvenStyle;
    private final CellStyle     defaultOddStyle;
    private final CellStyle     headerStyle;
    private final CellStyle     intEvenStyle;
    private final CellStyle     intOddStyle;
    private final CellStyle     timeEvenStyle;
    private final CellStyle     timeOddStyle;
    private final CellStyle     timestampEvenStyle;

    private final CellStyle     timestampOddStyle;

    public ExcelDriver(final File dataFile) throws Exception {
        this(dataFile.getCanonicalPath());
    }

    public ExcelDriver(final String connectionString) throws Exception {
        super(connectionString);
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

        final Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle = workbook.createCellStyle();
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(HEADER_FORMAT));
        headerStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(headerFont);
        headerStyle.setWrapText(false);

        dateEvenStyle = createCellStyle(DATE_FORMAT, false);
        dateOddStyle = createCellStyle(DATE_FORMAT, true);
        defaultEvenStyle = createCellStyle(DEFAULT_FORMAT, false);
        defaultOddStyle = createCellStyle(DEFAULT_FORMAT, true);
        intEvenStyle = createCellStyle(INT_FORMAT, false);
        intOddStyle = createCellStyle(INT_FORMAT, true);
        timeEvenStyle = createCellStyle(TIME_FORMAT, false);
        timeOddStyle = createCellStyle(TIME_FORMAT, true);
        timestampEvenStyle = createCellStyle(TIMESTAMP_FORMAT, false);
        timestampOddStyle = createCellStyle(TIMESTAMP_FORMAT, true);
    }

    @Override
    public void close() throws Exception {
        info("closing data file: ", getConnectionString());
        workbook = null;
        dataFile = null;
    }

    private CellStyle createCellStyle(final String format, final boolean odd) {
        final CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(format));
        cellStyle.setFillForegroundColor((odd ? IndexedColors.WHITE : IndexedColors.LIGHT_GREEN).getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        return cellStyle;
    }

    protected void commitSheet(final PSheet pSheet) {
        final int idx = workbook.getSheetIndex(pSheet.getName());
        if (idx >= 0) {
            workbook.removeSheetAt(idx);
        }
        final Sheet xSheet = workbook.createSheet(pSheet.getName());
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
                switch (pType) {
                    case INT:
                        cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? intEvenStyle : intOddStyle);
                        break;
                    case DATE:
                        cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? dateEvenStyle : dateOddStyle);
                        break;
                    case TIME:
                        cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? timeEvenStyle : timeOddStyle);
                        break;
                    case TIMESTAMP:
                        cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? timestampEvenStyle : timestampOddStyle);
                        break;
                    default:
                        cell.setCellStyle(NumberUtil.isOdd(rowIdx) ? defaultEvenStyle : defaultOddStyle);
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
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        } else if (PType.BOOLEAN == pType) {
            if (Cell.CELL_TYPE_BOOLEAN == xType) { return cell.getBooleanCellValue(); }
            if (Cell.CELL_TYPE_NUMERIC == xType) { return BooleanUtil.toBoolean(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return BooleanUtil.toBoolean(cell.getStringCellValue()); }
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        } else if (PType.DECIMAL == pType || PType.INT == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return NumberUtil.toDecimal(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return NumberUtil.toDecimal(cell.getStringCellValue()); }
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        } else if (PType.DATE == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return TimeUtil.toDateMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return TimeUtil.toDateMillis(cell.getStringCellValue()); }
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        } else if (PType.TIME == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return TimeUtil.toTimeMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return TimeUtil.toTimeMillis(cell.getStringCellValue()); }
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        } else if (PType.TIMESTAMP == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return TimeUtil.toTimestampMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return TimeUtil.toTimestampMillis(cell.getStringCellValue()); }
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        } else if (PType.FORMULA == pType) {
            if (Cell.CELL_TYPE_FORMULA == xType) { return cell.getCellFormula(); }
            if (Cell.CELL_TYPE_BLANK == xType) { return null; }
        }
        warning("getCellValue could not match ", pType, " with ", xType);
        return null;
    }

    protected Sheet getSheet(final String name) {
        Sheet sheet = workbook.getSheet(name);
        if (sheet == null) {
            sheet = workbook.createSheet(name);
        }
        return sheet;
    }

    protected XSSFWorkbook getWorkbook() {
        return workbook;
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
        if (!pSheet.isLocked()) { throw new UnsupportedOperationException("Reading unlocked sheets is not yet supported"); }
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

    protected void saveWorkbook() throws Exception {
        info("saving data file: ", getConnectionString());
        fillMetadate(workbook);
        try (FileOutputStream fileOut = new FileOutputStream(dataFile)) {
            workbook.write(fileOut);
        }
    }

}
