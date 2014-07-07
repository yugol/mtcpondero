package pondero.model.drivers.excel;

import static pondero.Logger.info;
import static pondero.Logger.warning;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.model.drivers.Driver;
import pondero.model.foundation.PRow;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.PType;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public abstract class ExcelDriver extends Driver {

    public static final String DEFAULT_FILE_EXTENSION = ".xlsx";

    private File               dataFile;
    private XSSFWorkbook       workbook;

    public ExcelDriver(final String connectionString) {
        super(connectionString);
    }

    @Override
    public void close() throws Exception {
        info("closing data file: ", getConnectionString());
        workbook = null;
        dataFile = null;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
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
            if (!filePath.endsWith(DEFAULT_FILE_EXTENSION)) {
                filePath += DEFAULT_FILE_EXTENSION;
                dataFile = new File(filePath);
            }
            info("opening existing data file: ", getConnectionString());
            workbook = new XSSFWorkbook();
        }
        setConnectionString(dataFile.getCanonicalPath());
    }

    @Override
    public void save() throws Exception {
        info("saving data file: ", getConnectionString());
        try (FileOutputStream fileOut = new FileOutputStream(dataFile)) {
            workbook.write(fileOut);
        }
    }

    protected Object getCellValue(final Cell cell, final PType pType) {
        final int xType = cell.getCellType();
        if (PType.STRING == pType) {
            if (Cell.CELL_TYPE_STRING == xType) { return StringUtil.toString(cell.getStringCellValue()); }
        } else if (PType.DECIMAL == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return NumberUtil.toDecimal(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return NumberUtil.toDecimal(cell.getStringCellValue()); }
        } else if (PType.DATE == pType || PType.TIME == pType || PType.TIMESTAMP == pType) {
            if (Cell.CELL_TYPE_NUMERIC == xType) { return DateUtil.toMillis(cell.getNumericCellValue()); }
            if (Cell.CELL_TYPE_STRING == xType) { return DateUtil.toMillis(cell.getStringCellValue()); }
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

    protected void readSheet(final PSheet pSheet, final Sheet xSheet) {
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

}
