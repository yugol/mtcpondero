package pondero.model.wb.basic;

import pondero.model.wb.PWorkbook;

public class BasicWorkbook extends PWorkbook {

    public BasicWorkbook(final String name) {
        super(name);
    }

    public Participants getParticipants() {
        Participants participants = (Participants) getSheet(Participants.NAME);
        if (participants == null) {
            participants = (Participants) addSheet(new Participants(this));
        }
        return participants;
    }

    public Records getRecords(final String name) {
        Records records = (Records) getSheet(name);
        if (records == null) {
            records = new Records(this, name);
        }
        return records;
    }

}
