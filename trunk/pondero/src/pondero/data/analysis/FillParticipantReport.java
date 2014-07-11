package pondero.data.analysis;

import java.util.Date;
import pondero.data.drivers.excel.templates.participant.ParticipantTemplate;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;

public class FillParticipantReport {

    public static void fill(final ParticipantTemplate template, final Participant participant, final BasicModel model) throws Exception {
        template.setParticipantSurname(participant.getSurname());
        template.setParticipantName(participant.getName());
        template.setParticipantId(participant.getId());
        template.setReportDate(new Date());
        template.setParticipantAge(participant.getAge());
        template.setParticipantGender(participant.getGender().toString());
        template.setParticipantDrivingAge(participant.getDrivingAge());
    }

}
