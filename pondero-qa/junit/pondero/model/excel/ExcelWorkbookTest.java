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
import pondero.model.participants.DefaultParticipants;
import pondero.model.participants.Participant;

public class ExcelWorkbookTest {

    private static final File WB_FILE = new File("implicit.xlsx");
    private static Workbook   WB;

    @AfterClass
    public static void afterClass() {
        try {
            WB.close();
            WB_FILE.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void beforeClass() {
        try {
            Globals.loadPreferences(null);
            WB = WorkbookFactory.openWorkbook(WB_FILE);
            WB.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddParticipant() throws Exception {
        int participantCount = WB.getAllParticipants().size();
        Participant participant = new Participant();
        participant.setId("10");
        participant.setName("Name");
        participant.setSurname("Surname");
        WB.add(participant);
        assertEquals(participantCount + 1, WB.getAllParticipants().size());
    }

    @Test
    public void testCreation() throws Exception {
        assertTrue(DefaultParticipants.getParticipants().size() <= WB.getAllParticipants().size());
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
        int participantCount = WB.getAllParticipants().size();
        Participant participant = new Participant();
        participant.setId("1");
        participant.setName("Name");
        participant.setSurname("Surname");
        WB.add(participant);
        assertEquals(participantCount, WB.getAllParticipants().size());
    }

}
