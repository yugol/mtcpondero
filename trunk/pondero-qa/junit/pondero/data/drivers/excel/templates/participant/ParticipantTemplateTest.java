package pondero.data.drivers.excel.templates.participant;

import org.junit.BeforeClass;
import org.junit.Test;
import pondero.Context;
import pondero.data.domains.Gender;

public class ParticipantTemplateTest {

    @BeforeClass
    public static void initContext() throws Exception {
        Context.initForTesting();
    }

    @Test
    public void testSetParticipantName() throws Exception {
        final ParticipantTemplate t = new ParticipantTemplate();
        t.setParticipantSurname("NUME");
        t.setParticipantName("PRENUME");
        t.setParticipantId("#1");
        t.setParticipantAge(10);
        t.setParticipantGender(Gender.FEMININE.toString());
        t.setParticipantDrivingAge(0);
        t.save();
    }

}
