package pondero.data.drivers.excel.templates.participant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.analysis.PMatrix;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.data.drivers.excel.templates.AbstractTemplate;
import pondero.data.model.PColumn;
import pondero.data.model.PType;

public class ParticipantTemplate extends AbstractTemplate {

    public static final String BASE_NAME = "participant-data";

    private static XSSFWorkbook createTemplate() throws IOException {
        final String path = "/" + ParticipantTemplate.class.getPackage().getName().replace(".", "/") + "/participant-data" + ExcelFileFilter.DEFAULT_EXTENSION;
        try (InputStream templateStream = ParticipantTemplate.class.getResourceAsStream(path)) {
            return new XSSFWorkbook(templateStream);
        }
    }

    private WorkbookLocation responsesLocation;
    private WorkbookLocation timesLocation;

    public ParticipantTemplate() throws Exception {
        super(createTemplate());
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

    @Override
    public void save(final File reportFile) throws Exception {
        if (responsesLocation != null) {
            responsesLocation.getSheet().autoSizeColumn(responsesLocation.getColIdx() + 1);
        }
        if (timesLocation != null) {
            timesLocation.getSheet().autoSizeColumn(timesLocation.getColIdx() + 1);
        }
        super.save(reportFile);
    }

    public void setParticipantAge(final int value) {
        setFieldValue("participantAge", value);
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

    public void setReportDate(final Date value) {
        setFieldValue("reportDate", value);
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

        // set location for the next table
        loc.setRowIdx(dy + 1);
    }

}
