package pondero.model.drivers.excel.templates;

import java.io.File;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import pondero.model.drivers.excel.ExcelDriver;
import pondero.model.foundation.PModel;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participants;

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

}
