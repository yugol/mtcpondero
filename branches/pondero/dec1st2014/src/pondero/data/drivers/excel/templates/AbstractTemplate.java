package pondero.data.drivers.excel.templates;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.analysis.PMatrix;
import pondero.data.drivers.excel.ExcelDriver;
import pondero.data.model.PColumn;
import pondero.data.model.PType;

public abstract class AbstractTemplate {

    private final XSSFWorkbook template;

    protected final CellStyle  headerStyle;
    protected final CellStyle  keyStyle;
    protected final CellStyle  labelStyle;

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

    public XSSFWorkbook getTemplate() {
        return template;
    }

    public void save(final File reportFile) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(reportFile)) {
            ExcelDriver.fillMetadate(template);
            template.write(fileOut);
        }
    }

    protected Sheet getSheet(final String name) {
        Sheet sheet = template.getSheet(name);
        if (sheet == null) {
            sheet = template.createSheet(name);
        }
        return sheet;
    }

    protected WbLocation renderTable(final WbLocation locIn, final PMatrix matrix) {
        final Sheet sheet = locIn.getSheet();
        final int dx = locIn.getColIdx();
        int dy = locIn.getRowIdx();

        // render heading
        Row sRow = sheet.getRow(dy);
        if (sRow == null) {
            sRow = sheet.createRow(dy);
        }
        Cell cell = sRow.getCell(dx);
        if (cell == null) {
            cell = sRow.createCell(dx);
        }
        cell.setCellStyle(headerStyle);
        setCellValue(cell, "", PType.STRING);
        for (int colIdx = 0; colIdx < matrix.getColumnCount(); ++colIdx) {
            final PColumn col = matrix.getColumn(colIdx);
            final int cellIdx = dx + 1 + colIdx;
            cell = sRow.getCell(cellIdx);
            if (cell == null) {
                cell = sRow.createCell(cellIdx);
            }
            cell.setCellStyle(headerStyle);
            setCellValue(cell, col.getName(), PType.STRING);
        }

        // render table/test name
        dy++;
        sRow = sheet.getRow(dy);
        if (sRow == null) {
            sRow = sheet.createRow(dy);
        }
        cell = sRow.getCell(dx);
        if (cell == null) {
            cell = sRow.createCell(dx);
        }
        cell.setCellStyle(labelStyle);
        setCellValue(cell, matrix.getName(), PType.STRING);

        // render responses
        for (int rowIdx = 0; rowIdx < matrix.getRowCount(); ++rowIdx) {
            sRow = sheet.getRow(dy);
            if (sRow == null) {
                sRow = sheet.createRow(dy);
            }
            for (int colIdx = 0; colIdx < matrix.getColumnCount(); ++colIdx) {
                final PColumn col = matrix.getColumn(colIdx);
                final int cellIdx = dx + 1 + colIdx;
                cell = sRow.getCell(cellIdx);
                if (cell == null) {
                    cell = sRow.createCell(cellIdx);
                }
                if (colIdx == 0) {
                    cell.setCellStyle(keyStyle);
                }
                setCellValue(cell, matrix.get(rowIdx, colIdx), col.getType());
            }
            dy++;
        }

        // calculate location for the next table
        final WbLocation locOut = new WbLocation();
        locOut.setSheet(locIn.getSheet());
        locOut.setRowIdx(dy + 1);
        locOut.setColIdx(locIn.getColIdx());
        return locOut;
    }

    protected void setCellValue(final Cell cell, final Object value, final PType pType) {
        ExcelDriver.setCellValue(cell, value, pType);
    }

    protected void setFieldValue(final String fieldName, final Object fieldValue) {
        final WbLocation loc = new WbLocation(template, fieldName);
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
