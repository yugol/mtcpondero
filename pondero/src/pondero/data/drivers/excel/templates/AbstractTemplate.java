package pondero.data.drivers.excel.templates;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.drivers.excel.ExcelDriver;
import pondero.data.drivers.excel.templates.participant.WorkbookLocation;
import pondero.data.model.PType;

public abstract class AbstractTemplate {

    protected final XSSFWorkbook template;

    protected final CellStyle    headerStyle;
    protected final CellStyle    labelStyle;
    protected final CellStyle    keyStyle;

    protected AbstractTemplate(final XSSFWorkbook template) {
        this.template = template;

        final Font headerFont = template.createFont();
        headerFont.setItalic(true);
        headerStyle = template.createCellStyle();
        headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headerStyle.setFont(headerFont);
        headerStyle.setWrapText(false);

        final Font labelFont = template.createFont();
        labelFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        labelStyle = template.createCellStyle();
        labelStyle.setFillPattern(CellStyle.THIN_FORWARD_DIAG);
        labelStyle.setFont(labelFont);
        labelStyle.setWrapText(false);

        keyStyle = template.createCellStyle();
        keyStyle.setBorderLeft(CellStyle.BORDER_THIN);
        keyStyle.setDataFormat(template.getCreationHelper().createDataFormat().getFormat("0"));
        keyStyle.setWrapText(false);
    }

    public void save(final File reportFile) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(reportFile)) {
            template.write(fileOut);
        }
    }

    protected void setCellValue(final Cell cell, final Object value, final PType pType) {
        ExcelDriver.setCellValue(cell, value, pType);
    }

    protected void setFieldValue(final String fieldName, final Object fieldValue) {
        final WorkbookLocation loc = new WorkbookLocation(template, fieldName);
        Row row = loc.getSheet().getRow(loc.getRowIdx());
        if (row == null) {
            row = loc.getSheet().createRow(loc.getRowIdx());
        }
        Cell cell = row.getCell(loc.getColIdx());
        if (cell == null) {
            cell = row.createCell(loc.getColIdx());
        }
        if (fieldValue == null) {
            cell.setCellValue("");
        } else if (fieldValue instanceof String) {
            cell.setCellValue((String) fieldValue);
        } else if (fieldValue instanceof Number) {
            cell.setCellValue(((Number) fieldValue).doubleValue());
        } else if (fieldValue instanceof Date) {
            cell.setCellValue((Date) fieldValue);
        }
    }

}
