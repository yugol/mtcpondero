package pondero.data.foundation.basic;

import pondero.data.foundation.PModel;

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

    public Records getRecords(final String name) throws Exception {
        Records records = (Records) getSheet(name);
        if (records == null) {
            records = (Records) addSheet(new Records(this, name));
        }
        return records;
    }
}
