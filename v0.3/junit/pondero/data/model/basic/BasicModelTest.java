package pondero.data.model.basic;

import org.junit.Test;
import pondero.PonderoTest;

public class BasicModelTest extends PonderoTest {

    @Test
    public void testParticipants() throws Exception {
        final BasicModel wb = new BasicModel("My Workbook");

        final Participants ps = wb.getParticipants();
        for (int i = 1; i <= 10; ++i) {
            final Participant p = ps.addRow();
            p.randomize();
            p.setId(i);
            System.out.println(p.toCsv());
        }
        System.out.println(ps);

        final TestRecords rs = wb.getRecords("IC");
        for (int i = 0; i < 10; ++i) {
            final TrialRecord tr = rs.addRow();
            tr.randomize();
            tr.setExperimentId(System.currentTimeMillis());
            System.out.println(tr.toCsv());
        }
        System.out.println(rs);
    }

}
