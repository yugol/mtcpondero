package pondero.data.analysis;

import java.util.Date;
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
                        reportRow.set(colIdx, record.getResponse());

                        reportRow = getTimeMatrixRow(record.getExperimentTimestamp());
                        colIdx = timeMatrix.index(dataColName, PType.INT);
                        reportRow.set(colIdx, record.getResponseTime());
                    }
                }
                template.addResponses(responseMatrix);
                template.addTimes(timeMatrix);
            }
        }
    }

}
