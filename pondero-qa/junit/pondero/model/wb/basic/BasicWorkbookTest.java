package pondero.model.wb.basic;

import org.junit.Test;

public class BasicWorkbookTest {

    @Test
    public void testBasicWorkbook() {
        final BasicWorkbook wb = new BasicWorkbook("My Workbook");
        final Participants ps = wb.getParticipants();
        final Participant p = ps.addRow();
        p.randomize();
        System.out.println(ps);
    }

}
