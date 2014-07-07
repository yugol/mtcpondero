package pondero.model.drivers.excel.templates;

import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import pondero.model.drivers.excel.ExcelDriver;
import pondero.model.foundation.PModel;
import pondero.model.foundation.PRow;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.PType;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participants;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public class BasicTemplate extends ExcelDriver {

    private CellStyle headerStyle;
    private CellStyle oddStyle;
    private CellStyle evenStyle;

    public BasicTemplate(final String connectionString) {
        super(connectionString);
    }

    @Override
    public BasicModel getModel() throws Exception {
        final BasicModel model = new BasicModel(new File(getConnectionString()).getName());
        for (int sheetIdx = 0; sheetIdx < getWorkbook().getNumberOfSheets(); ++sheetIdx) {
            final Sheet sheet = getWorkbook().getSheetAt(sheetIdx);
            final String sheetName = sheet.getSheetName();
            if (Participants.NAME.equals(sheetName)) {
                readSheet(model.getParticipants(), sheet);
            } else {
                readSheet(model.getRecords(sheetName), sheet);
            }
        }
        model.setDirty(false);
        return model;
    }

    @Override
    public void open() throws Exception {
        super.open();
        headerStyle = getWorkbook().createCellStyle();
        headerStyle.setWrapText(false);
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        final Font headerFont = getWorkbook().createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);

        oddStyle = getWorkbook().createCellStyle();
        oddStyle.setBorderRight(CellStyle.BORDER_THIN);

        evenStyle = getWorkbook().createCellStyle();
        evenStyle.setBorderRight(CellStyle.BORDER_THIN);
        evenStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        evenStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }

    @Override
    public void putModel(final PModel _model) throws Exception {
        final BasicModel model = (BasicModel) _model;
    }

    protected Object getCellValue(final Cell cell, final PType pType) {
        final int xType = cell.getCellType();
        if (PType.STRING == pType) {
            if (Cell.CELL_TYPE_STRING == xType) { return StringUtil.toString(cell.getStringCellValue()); }
        } else if (PType.FIXED == pType) {
            if (Cell.CELL_TYPE_STRING == xType) { return NumberUtil.toFixed(cell.getStringCellValue()); }
        } else if (PType.DATE == pType || PType.TIME == pType || PType.TIMESTAMP == pType) {
            if (Cell.CELL_TYPE_STRING == xType) { return DateUtil.toMillis(cell.getStringCellValue()); }
        }
        return null;
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

//    private Sheet getSheet(final String name, final PSheet pSheet) {
//        final Sheet sheet = super.getSheet(name);
//        if (sheet.getRow(0) == null) {
//            final Row row = sheet.createRow(0);
//            int columnIndex = 0;
//            for (final PColumn column : pSheet.getColumns()) {
//                final Cell cell = row.createCell(columnIndex++);
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                cell.setCellValue(columnName);
//                cell.setCellStyle(headerStyle);
//            }
//        }
//        return sheet;
//    }

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
