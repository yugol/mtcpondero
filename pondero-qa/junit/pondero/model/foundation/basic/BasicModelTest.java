package pondero.model.foundation.basic;

import org.junit.Test;
import pondero.data.foundation.basic.BasicModel;
import pondero.data.foundation.basic.Participant;
import pondero.data.foundation.basic.Participants;
import pondero.data.foundation.basic.Records;
import pondero.data.foundation.basic.TrialRecord;

public class BasicModelTest {

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

        final Records rs = wb.getRecords("IC");
        for (int i = 0; i < 10; ++i) {
            final TrialRecord tr = rs.addRow();
            tr.randomize();
            tr.setExperimentId("dolor");
            System.out.println(tr.toCsv());
        }
        System.out.println(rs);
    }

}
