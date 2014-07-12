package pondero.data.drivers.excel.templates.participant;

import java.io.File;
import org.junit.AfterClass;
import org.junit.Test;
import pondero.PonderoTest;
import pondero.data.domains.Gender;
import pondero.data.drivers.excel.ExcelFileFilter;

public class ParticipantTemplateTest extends PonderoTest {

    @AfterClass
    public static void cleanupContext() {
        REPORT_FILE.deleteOnExit();
    }

    private static final File REPORT_FILE = new File(ParticipantTemplateTest.class.getSimpleName() + ExcelFileFilter.DEFAULT_EXTENSION);

    @Test
    public void testSetParticipantName() throws Exception {
        final ParticipantTemplate t = new ParticipantTemplate();
        t.setParticipantSurname("NUME");
        t.setParticipantName("PRENUME");
        t.setParticipantId("#1");
        t.setParticipantAge(10);
        t.setParticipantGender(Gender.FEMININE.toString());
        t.setParticipantDrivingAge(0);
        t.save(REPORT_FILE);
    }

}
