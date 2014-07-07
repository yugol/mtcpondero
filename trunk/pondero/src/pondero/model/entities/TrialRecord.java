package pondero.model.entities;

import pondero.model.entities.base.Column;
import pondero.model.entities.base.Record;
import pondero.model.participants.Participant;
import pondero.util.DateUtil;

public class TrialRecord extends Record {

    private final long   startTime          = System.currentTimeMillis();
    private final String experimentName;

    @Column
    private final String experimentId;
    @Column
    private String       participantId      = "";
    @Column
    private String       participantSurname = "";
    @Column
    private String       participantName    = "";
    @Column
    private String       blockName          = "";
    @Column
    private String       trialName          = "";
    @Column
    private String       trialDate;
    @Column
    private String       trialTime;
    @Column
    private String       response;
    @Column
    private long         responseTime;
    @Column
    private boolean      responseCorrect;

    public TrialRecord(final String experimentName, final String experimentId) {
        this.experimentName = experimentName;
        this.experimentId = experimentId;
    }

    public String getBlockName() {
        return blockName;
    }

    public String getBlockNameString() {
        return blockName;
    }

    public String getExperimentIdString() {
        return experimentId;
    }

    public String getParticipantIdString() {
        return participantId;
    }

    public String getParticipantNameString() {
        return participantName;
    }

    public String getParticipantSurnameString() {
        return participantSurname;
    }

    public String getResponse() {
        return response;
    }

    public String getResponseCorrectString() {
        return responseCorrect ? "true" : "false";
    }

    public String getResponseString() {
        return response;
    }

    public String getResponseTimeString() {
        return String.valueOf(responseTime - startTime);
    }

    @Override
    public String getSheetName() {
        return experimentName;
    }

    public String getTrialDateString() {
        if (trialDate == null) {
            trialDate = DateUtil.toIsoDate(startTime);
        }
        return trialDate;
    }

    public String getTrialNameString() {
        return trialName;
    }

    public String getTrialTimeString() {
        if (trialTime == null) {
            trialTime = DateUtil.toIsoTime(startTime);
        }
        return trialTime;
    }

    public void setBlockName(final String blockName) {
        this.blockName = blockName;
    }

    public void setParticipant(final Participant participant) {
        participantId = participant.getId();
        participantSurname = participant.getSurname();
        participantName = participant.getName();
    }

    public void setResponse(final String response) {
        this.response = response;
    }

    public void setResponseCorrect(final boolean b) {
        responseCorrect = b;
    }

    public void setResponseTime(final long time) {
        responseTime = time;
    }

    public void setTrialName(final String trialName) {
        this.trialName = trialName;
    }

    @Override
    public String toCsv() {
        StringBuilder csv = new StringBuilder();
        csv.append(getExperimentIdString()).append(", ");
        csv.append(getParticipantIdString()).append(", ");
        csv.append(getParticipantSurnameString()).append(", ");
        csv.append(getParticipantNameString()).append(", ");
        csv.append(getBlockNameString()).append(", ");
        csv.append(getTrialDateString()).append(", ");
        csv.append(getTrialTimeString()).append(", ");
        csv.append(getResponseString()).append(", ");
        csv.append(getResponseTimeString()).append(", ");
        csv.append(getResponseCorrectString());
        return csv.toString();
    }

}
