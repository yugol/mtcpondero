package pondero.model.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pondero.Globals;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.participants.Participant;

public class ExcelWorkbookTest {

    private static final File WB_FILE = new File("implicit.xlsx");
    private static Workbook   WB;

    @AfterClass
    public static void afterClass() {
        try {
            WB.close();
            WB_FILE.deleteOnExit();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void beforeClass() {
        try {
            Globals.loadPreferences(null);
            WB = WorkbookFactory.openWorkbook(WB_FILE);
            WB.save();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddParticipant() throws Exception {
        final int participantCount = WB.getAllParticipants().size();
        final Participant participant = new Participant();
        participant.setId("10");
        participant.setName("Name");
        participant.setSurname("Surname");
        WB.add(participant);
        assertEquals(participantCount + 1, WB.getAllParticipants().size());
    }

    @Test
    public void testCreation() throws Exception {
        assertTrue(WB.getAllParticipants().size() >= 1);
    }

    @Test
    public void testGetNewUniqueParticipantId() throws Exception {
        assertTrue(Integer.parseInt(WB.getNewUniqueParticipantId()) >= 101);
    }

    @Test
    public void testGetWorkbookName() throws Exception {
        assertEquals(WB_FILE.getName(), WB.getWorkbookName());
    }

    @Test
    public void testModifyParticipant() throws Exception {
        final int participantCount = WB.getAllParticipants().size();
        final Participant participant = new Participant();
        participant.setId("1");
        participant.setName("Name");
        participant.setSurname("Surname");
        WB.add(participant);
        assertEquals(participantCount, WB.getAllParticipants().size());
    }

}
