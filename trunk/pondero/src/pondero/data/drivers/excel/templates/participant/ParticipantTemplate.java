package pondero.data.drivers.excel.templates.participant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParticipantTemplate {

    public static final String BASE_NAME = "participant-data";

    private final XSSFWorkbook template;

    public ParticipantTemplate() throws Exception {
        try (InputStream templateStream = ParticipantTemplate.class.getResourceAsStream("/pondero/data/drivers/excel/templates/participant/participant-data.xlsx")) {
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
        final Name namedCell = template.getNameAt(template.getNameIndex(fieldName));
        final AreaReference aref = new AreaReference(namedCell.getRefersToFormula());
        final CellReference cellReference = aref.getFirstCell();
        final Sheet sheet = template.getSheet(cellReference.getSheetName());
        Row row = sheet.getRow(cellReference.getRow());
        if (row == null) {
            row = sheet.createRow(cellReference.getRow());
        }
        Cell cell = row.getCell(cellReference.getCol());
        if (cell == null) {
            cell = row.createCell(cellReference.getCol());
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
