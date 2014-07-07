package pondero.model.wb.basic;

import org.junit.Test;

public class BasicWorkbookTest {

    @Test
    public void testBasicWorkbook() {
        final BasicWorkbook wb = new BasicWorkbook("My Workbook");
        final Participants ps = wb.getParticipants();
        for (int i = 1; i <= 100; ++i) {
            final Participant p = ps.addRow();
            p.setId(i);
            p.randomize();
        }
        System.out.println(ps);
    }

}
