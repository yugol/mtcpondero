package pondero.model.foundation.basic;

import pondero.model.foundation.PModel;

public class BasicModel extends PModel {

    public BasicModel(final String name) {
        super(name);
        getParticipants(); // this must be the first sheet
    }

    public final Participants getParticipants() {
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
