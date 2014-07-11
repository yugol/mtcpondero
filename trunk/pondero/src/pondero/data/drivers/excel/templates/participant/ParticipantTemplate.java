package pondero.data.drivers.excel.templates.participant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.analysis.PMatrix;
import pondero.data.drivers.excel.ExcelDriver;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.data.model.PColumn;
import pondero.data.model.PType;

public class ParticipantTemplate {

    public static final String BASE_NAME = "participant-data";

    private final XSSFWorkbook template;
    private WorkbookLocation   responsesLocation;
    private WorkbookLocation   timesLocation;

    public ParticipantTemplate() throws Exception {
        final String path = "/" + ParticipantTemplate.class.getPackage().getName().replace(".", "/") + "/participant-data" + ExcelFileFilter.DEFAULT_EXTENSION;
        try (InputStream templateStream = ParticipantTemplate.class.getResourceAsStream(path)) {
            template = new XSSFWorkbook(templateStream);
        }
    }

    public void save(final File reportFile) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(reportFile)) {
            template.write(fileOut);
        }
    }

    public void setParticipantAge(final int value) {
        setFieldValue("participantAge", value);
    }

    public void setReportDate(final Date value) {
        setFieldValue("reportDate", value);
    }

    public void setParticipantDrivingAge(final int value) {
        setFieldValue("participantDrivingAge", value);
    }

    public void setParticipantGender(final String value) {
        setFieldValue("participantGender", value);
    }

    public void setParticipantId(final String value) {
        setFieldValue("participantId", value);
    }

    public void setParticipantName(final String value) {
        setFieldValue("participantName", value);
    }

    public void setParticipantSurname(final String value) {
        setFieldValue("participantSurname", value);
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

    public void addResponses(final PMatrix responseMatrix) {
        if (responsesLocation == null) {
            responsesLocation = new WorkbookLocation(template, "participantResponses");
        }
        renderTable(responsesLocation, responseMatrix);
    }

    public void addTimes(final PMatrix timeMatrix) {
        if (timesLocation == null) {
            timesLocation = new WorkbookLocation(template, "participantTimes");
        }
        renderTable(timesLocation, timeMatrix);
    }

    private void renderTable(final WorkbookLocation loc, final PMatrix matrix) {
        final Sheet sheet = loc.getSheet();
        final int dx = loc.getColIdx();
        int dy = loc.getRowIdx();

        // render heading
        Row sRow = sheet.getRow(dy);
        if (sRow == null) {
            sRow = sheet.createRow(dy);
        }
        for (int colIdx = 0; colIdx < matrix.getColumnCount(); ++colIdx) {
            final PColumn col = matrix.getColumn(colIdx);
            final int cellIdx = dx + 1 + colIdx;
            Cell cell = sRow.getCell(cellIdx);
            if (cell == null) {
                cell = sRow.createCell(cellIdx);
            }
            ExcelDriver.setCellValue(cell, col.getName(), PType.STRING);
        }

        // render table/test name
        dy++;
        sRow = sheet.getRow(dy);
        if (sRow == null) {
            sRow = sheet.createRow(dy);
        }
        Cell cell = sRow.getCell(dx);
        if (cell == null) {
            cell = sRow.createCell(dx);
        }
        ExcelDriver.setCellValue(cell, matrix.getName(), PType.STRING);

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
                ExcelDriver.setCellValue(cell, matrix.get(rowIdx, colIdx), col.getType());
            }
            dy++;
        }

        // set location for the next table
        loc.setRowIdx(dy + 1);
    }

}
