package pondero.data.analysis;

import java.util.Date;
import pondero.data.drivers.excel.templates.participant.ParticipantTemplate;
import pondero.data.model.PSheet;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.TestRecords;

public class FillParticipantReport {

    public void fill(final ParticipantTemplate template, final Participant participant, final BasicModel model) throws Exception {
        fillParticipant(template, participant);
        for (final PSheet sheet : model) {
            if (sheet instanceof TestRecords) {

                final PMatrix matrix = new PMatrix(sheet.getName());
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

}
