package pondero.data.model.basic;

import java.util.ArrayList;
import java.util.List;
import pondero.data.model.PModel;

public class BasicModel extends PModel {

    public BasicModel(final String name) throws Exception {
        super(name);
        getParticipants(); // this must be the first sheet
    }

    public final Participants getParticipants() throws Exception {
        Participants participants = (Participants) getSheet(Participants.NAME);
        if (participants == null) {
            participants = (Participants) addSheet(new Participants(this));
        }
        return participants;
    }

    public TestRecords getRecords(final String name) throws Exception {
        TestRecords records = (TestRecords) getSheet(name);
        if (records == null) {
            records = (TestRecords) addSheet(new TestRecords(this, name));
        }
        return records;
    }

    public List<String> getTestSheets() {
        final List<String> tests = new ArrayList<>();
        for (int i = 0; i < getSheetCount(); ++i) {
            final String sheetName = getSheet(i).getName();
            if (!Participants.NAME.equals(sheetName)) {
                tests.add(sheetName);
            }
        }
        return tests;
    }

}
