package pondero.data.model.basic;

import java.util.Date;
import pondero.data.model.PRow;
import pondero.data.model.PSheet;

public class TrialRecord extends PRow {

    TrialRecord(final PSheet sheet) throws Exception {
        super(sheet);
        set(TestRecords.ATTR_TRIAL_TIMESTAMP, System.currentTimeMillis());
    }

    public String getBlockId() {
        return (String) get(TestRecords.ATTR_BLOCK_ID);
    }

    public String getExperimentId() {
        return (String) get(TestRecords.ATTR_EXPERIMNT_ID);
    }

    public String getParticipantId() {
        return (String) get(TestRecords.ATTR_PARTICIPANT_ID);
    }

    public String getParticipantName() {
        return (String) get(TestRecords.ATTR_PARTICIPANT_NAME);
    }

    public String getParticipantSurname() {
        return (String) get(TestRecords.ATTR_PARTICIPANT_SURNAME);
    }

    public String getResponse() {
        return (String) get(TestRecords.ATTR_RESPONSE);
    }

    public Integer getResponseTime() {
        return getInteger(TestRecords.ATTR_RESPONSE_TIME);
    }

    public String getTrialId() {
        return (String) get(TestRecords.ATTR_TRIAL_ID);
    }

    public Date getTrialTimestamp() {
        return getDate(TestRecords.ATTR_TRIAL_TIMESTAMP);
    }

    public Boolean isResponseCorrect() {
        return (Boolean) get(TestRecords.ATTR_RESPONSE_CORRECT);
    }

    public void setBlockId(final String value) throws Exception {
        set(TestRecords.ATTR_BLOCK_ID, value);
    }

    public void setExperimentId(final String id) throws Exception {
        set(TestRecords.ATTR_EXPERIMNT_ID, id);
    }

    public void setParticipant(final Participant participant) throws Exception {
        set(TestRecords.ATTR_PARTICIPANT_SURNAME, participant.getSurname());
        set(TestRecords.ATTR_PARTICIPANT_NAME, participant.getName());
        set(TestRecords.ATTR_PARTICIPANT_ID, participant.getId());
    }

    public void setResponse(final String value) throws Exception {
        set(TestRecords.ATTR_RESPONSE, value);
    }

    public void setResponseCorrect(final boolean value) throws Exception {
        set(TestRecords.ATTR_RESPONSE_CORRECT, value);
    }

    public void setResponseTimestamp(final long responseTimestamp) throws Exception {
        final double delta = responseTimestamp - (Long) get(TestRecords.ATTR_TRIAL_TIMESTAMP);
        set(TestRecords.ATTR_RESPONSE_TIME, delta / 1000);
    }

    public void setTrialId(final String value) throws Exception {
        set(TestRecords.ATTR_TRIAL_ID, value);
    }

    @Override
    public String toCsv() {
        final StringBuilder csv = new StringBuilder();
        csv.append(getParticipantSurname()).append(", ");
        csv.append(getParticipantName()).append(", ");
        csv.append(getParticipantId()).append(", ");
        csv.append(getExperimentId()).append(", ");
        csv.append(getBlockId()).append(", ");
        csv.append(getTrialId()).append(", ");
        csv.append(getTrialTimestamp()).append(", ");
        csv.append(getResponse()).append(", ");
        csv.append(getResponseTime()).append(", ");
        csv.append(isResponseCorrect());
        return csv.toString();
    }

}
