package pondero.model.foundation.basic;

import org.junit.Test;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.Participants;
import pondero.model.foundation.basic.Records;
import pondero.model.foundation.basic.TrialRecord;

public class BasicModelTest {

    @Test
    public void testParticipants() {
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
            final TrialRecord tr = rs.addRow("dolor");
            tr.randomize();
            System.out.println(tr.toCsv());
        }
        System.out.println(rs);
    }

}
