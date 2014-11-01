package pondero.data.analysis;

import java.util.Date;
import pondero.Constants;
import pondero.data.analysis.PMatrix.MRow;
import pondero.data.drivers.excel.templates.participant.ParticipantTemplate;
import pondero.data.model.PSheet;
import pondero.data.model.PType;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.TestRecords;
import pondero.data.model.basic.TrialRecord;

public class FillParticipantReport extends ReportFilling {

    public void fill(final ParticipantTemplate template, final Participant participant, final BasicModel model) throws Exception {
        template.setParticipantSurname(participant.getSurname());
        template.setParticipantName(participant.getName());
        template.setParticipantId(participant.getId());
        template.setReportDate(new Date());
        template.setParticipantAge(participant.getAge());
        template.setParticipantGender(participant.getGender().toString());
        template.setParticipantDrivingAge(participant.getDrivingAge());

        for (final PSheet sheet : model) {
            if (sheet instanceof TestRecords) {
                reset(sheet.getName());
                for (int rowIdx = 0; rowIdx < sheet.getRowCount(); ++rowIdx) {
                    final TrialRecord record = (TrialRecord) sheet.getRow(rowIdx);
                    if (participant.getId().equals(record.getParticipantId())) {
                        final String dataColName = getDataColumName(record);

                        MRow reportRow = getResponseMatrixRow(record.getExperimentTimestamp());
                        int colIdx = responseMatrix.index(dataColName, PType.STRING);
                        try {
                            reportRow.set(colIdx, record.getResponse());
                        } catch (final IndexOutOfBoundsException ex) {
                            reportRow.set(colIdx, Constants.NA);
                        }

                        reportRow = getTimeMatrixRow(record.getExperimentTimestamp());
                        colIdx = timeMatrix.index(dataColName, PType.INT);
                        try {
                            final Integer responseTime = record.getResponseTime();
                            reportRow.set(colIdx, responseTime);
                        } catch (final NullPointerException ex) {
                            reportRow.set(colIdx, -1);
                        }
                    }
                }
                template.addResponses(responseMatrix);
                template.addTimes(timeMatrix);
            }
        }
    }

}
