package pondero.model.drivers.excel.templates;

import java.io.File;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import pondero.model.drivers.excel.ExcelDriver;
import pondero.model.foundation.PModel;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.PType;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participants;

public class BasicTemplate extends ExcelDriver {

    private CellStyle headerStyle;
    private CellStyle oddStyle;
    private CellStyle evenStyle;
    private CellStyle oddDateStyle;
    private CellStyle evenDateStyle;

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
    public void putModel(final PModel model) throws Exception {
        for (final PSheet pSheet : model) {
            writeSheet(pSheet);
        }
    }

    private void setCellValue(final Cell cell, final Object value, final PType pType) {
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

    private void writeSheet(final PSheet pSheet) {
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
                    cell.setCellStyle(rowIdx % 2 == 1 ? evenDateStyle : oddDateStyle);
                } else {
                    cell.setCellStyle(rowIdx % 2 == 1 ? evenStyle : oddStyle);
                }
            }
        }
        for (int colIdx = 0; colIdx < pSheet.getColumnCount(); ++colIdx) {
            xSheet.autoSizeColumn(colIdx);
        }
    }

}
