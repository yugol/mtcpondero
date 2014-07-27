package pondero.data.drivers.excel.templates.participant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pondero.data.analysis.PMatrix;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.data.drivers.excel.templates.AbstractTemplate;
import pondero.data.drivers.excel.templates.WbLocation;

public class ParticipantTemplate extends AbstractTemplate {

    private static XSSFWorkbook createTemplate() throws IOException {
        final String path = "/" + ParticipantTemplate.class.getPackage().getName().replace(".", "/") + "/participant-data" + ExcelFileFilter.DEFAULT_EXTENSION;
        try (InputStream templateStream = ParticipantTemplate.class.getResourceAsStream(path)) {
            return new XSSFWorkbook(templateStream);
        }
    }

    public static final String BASE_NAME = "participant-data";

    private WbLocation         responsesLocation;
    private WbLocation         timesLocation;

    public ParticipantTemplate() throws Exception {
        super(createTemplate());
    }

    public void addResponses(final PMatrix responseMatrix) {
        if (responsesLocation == null) {
            responsesLocation = new WbLocation(getTemplate(), "participantResponses");
        }
        responsesLocation = renderTable(responsesLocation, responseMatrix);
    }

    public void addTimes(final PMatrix timeMatrix) {
        if (timesLocation == null) {
            timesLocation = new WbLocation(getTemplate(), "participantTimes");
        }
        timesLocation = renderTable(timesLocation, timeMatrix);
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

}
