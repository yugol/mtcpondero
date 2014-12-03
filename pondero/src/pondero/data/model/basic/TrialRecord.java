package pondero.data.model.basic;

import java.math.BigDecimal;
import java.util.Date;
import pondero.data.model.PRow;
import pondero.data.model.PSheet;

public class TrialRecord extends PRow {

    TrialRecord(final PSheet sheet) throws Exception {
        super(sheet);
        setTrialTimestamp(System.currentTimeMillis());
    }

    public String getBlockId() {
        return getString(TestRecords.ATTR_BLOCK_ID);
    }

    public Long getExperimentTimestamp() {
        final BigDecimal millis = getDecimal(TestRecords.ATTR_EXPERIMNT_TIMESTAMP);
        if (millis != null) { return millis.longValue(); }
        return null;
    }

    public String getParticipantId() {
        return getString(TestRecords.ATTR_PARTICIPANT_ID);
    }

    public String getResponse() {
        return getString(TestRecords.ATTR_RESPONSE);
    }

    public Integer getResponseTime() {
        return getInteger(TestRecords.ATTR_RESPONSE_TIME);
    }

    public String getTrialId() {
        return getString(TestRecords.ATTR_TRIAL_ID);
    }

    public Date getTrialTimestamp() {
        return getDate(TestRecords.ATTR_TRIAL_TIMESTAMP);
    }

    public Boolean isResponseCorrect() {
        return getBoolean(TestRecords.ATTR_RESPONSE_CORRECT);
    }

    public void setBlockId(final String value) throws Exception {
        set(TestRecords.ATTR_BLOCK_ID, value);
    }

    public void setExperimentId(final long millis) throws Exception {
        set(TestRecords.ATTR_EXPERIMNT_TIMESTAMP, millis);
    }

    public void setParticipant(final Participant participant) throws Exception {
        set(TestRecords.ATTR_PARTICIPANT_ID, participant.getId());
    }

    public void setResponse(final String value) throws Exception {
        set(TestRecords.ATTR_RESPONSE, value);
    }

    public void setResponseCorrect(final boolean value) throws Exception {
        set(TestRecords.ATTR_RESPONSE_CORRECT, value);
    }

    public void setResponseTimestamp(final long responseTimestamp) throws Exception {
        set(TestRecords.ATTR_RESPONSE_TIME, responseTimestamp - (Long) get(TestRecords.ATTR_TRIAL_TIMESTAMP));
    }

    public void setTrialId(final String value) throws Exception {
        set(TestRecords.ATTR_TRIAL_ID, value);
    }

    public void setTrialTimestamp(final long timestamp) throws Exception {
        set(TestRecords.ATTR_TRIAL_TIMESTAMP, timestamp);
    }

    @Override
    public String toCsv() {
        final StringBuilder csv = new StringBuilder();
        csv.append(getExperimentTimestamp()).append(", ");
        csv.append(getParticipantId()).append(", ");
        csv.append(getBlockId()).append(", ");
        csv.append(getTrialId()).append(", ");
        csv.append(getTrialTimestamp()).append(", ");
        csv.append(getResponse()).append(", ");
        csv.append(getResponseTime()).append(", ");
        csv.append(isResponseCorrect());
        return csv.toString();
    }

}
