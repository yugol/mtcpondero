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

}
