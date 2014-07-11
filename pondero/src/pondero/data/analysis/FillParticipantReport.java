package pondero.data.analysis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import pondero.data.analysis.PMatrix.MRow;
import pondero.data.drivers.excel.templates.participant.ParticipantTemplate;
import pondero.data.model.PSheet;
import pondero.data.model.PType;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.TestRecords;
import pondero.data.model.basic.TrialRecord;

public class FillParticipantReport {

    private PMatrix         responseMatrix;
    private Map<Long, MRow> responseMatrixKeys;
    private PMatrix         timeMatrix;
    private Map<Long, MRow> timeMatrixKeys;

    public void fill(final ParticipantTemplate template, final Participant participant, final BasicModel model) throws Exception {
        fillParticipant(template, participant);
        for (final PSheet sheet : model) {
            if (sheet instanceof TestRecords) {
                responseMatrix = new PMatrix(sheet.getName());
                responseMatrixKeys = new HashMap<Long, MRow>();
                timeMatrix = new PMatrix(sheet.getName());
                timeMatrixKeys = new HashMap<Long, MRow>();
                for (int rowIdx = 0; rowIdx < sheet.getRowCount(); ++rowIdx) {
                    final TrialRecord record = (TrialRecord) sheet.getRow(rowIdx);
                    if (participant.getId().equals(record.getParticipantId())) {
                        final StringBuilder colName = new StringBuilder(record.getBlockId()).append(".").append(record.getTrialId());
                        MRow reportRow = getResponseMatrixRow(record.getExperimentTimestamp());
                        int colIdx = responseMatrix.index(colName.toString(), PType.STRING);
                        reportRow.set(colIdx, record.getResponse());

                        reportRow = getTimeMatrixRow(record.getExperimentTimestamp());
                        colIdx = timeMatrix.index(colName.toString(), PType.INT);
                        reportRow.set(colIdx, record.getResponseTime());
                    }
                }
                template.addResponses(responseMatrix);
                template.addResponses(responseMatrix);
                template.addTimes(timeMatrix);
            }
        }
    }

    private void fillParticipant(final ParticipantTemplate template, final Participant participant) throws Exception {
        template.setParticipantSurname(participant.getSurname());
        template.setParticipantName(participant.getName());
        template.setParticipantId(participant.getId());
        template.setReportDate(new Date());
        template.setParticipantAge(participant.getAge());
        template.setParticipantGender(participant.getGender().toString());
        template.setParticipantDrivingAge(participant.getDrivingAge());
    }

    private MRow getResponseMatrixRow(final Long key) throws Exception {
        MRow row = responseMatrixKeys.get(key);
        if (row == null) {
            row = responseMatrix.addRow();
            final int colIdx = responseMatrix.index("CHEIE", PType.INT);
            row.set(colIdx, key);
            responseMatrixKeys.put(key, row);
        }
        return row;
    }

    private MRow getTimeMatrixRow(final Long key) throws Exception {
        MRow row = timeMatrixKeys.get(key);
        if (row == null) {
            row = timeMatrix.addRow();
            final int colIdx = timeMatrix.index("CHEIE", PType.INT);
            row.set(colIdx, key);
            timeMatrixKeys.put(key, row);
        }
        return row;
    }

}
