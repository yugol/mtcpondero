package pondero.data.model.basic;

import pondero.data.model.PModel;
import pondero.data.model.PSheet;
import pondero.data.model.PType;

public class Participants extends PSheet {

    public static final String HASH             = "#";

    public static final String NAME             = "PARTICIPANTS";

    public static final String ATTR_ID          = "ID";
    public static final String ATTR_SURNAME     = "SURNAME";
    public static final String ATTR_NAME        = "NAME";
    public static final String ATTR_DOB         = "DOB";
    public static final String ATTR_GENDER      = "GENDER";
    public static final String ATTR_EDUCATION   = "EDUCATION";
    public static final String ATTR_DRIVING_AGE = "DRIVING_AGE";
    public static final String ATTR_MILEAGE     = "MILEAGE";

    Participants(final PModel model) throws Exception {
        super(model, NAME);
        addColumn(ATTR_ID, PType.STRING);
        addColumn(ATTR_SURNAME, PType.STRING);
        addColumn(ATTR_NAME, PType.STRING);
        addColumn(ATTR_DOB, PType.DATE);
        addColumn(ATTR_GENDER, PType.STRING);
        addColumn(ATTR_EDUCATION, PType.STRING);
        addColumn(ATTR_DRIVING_AGE, PType.DECIMAL);
        addColumn(ATTR_MILEAGE, PType.DECIMAL);
        lock();
    }

    @Override
    public Participant addRow() throws Exception {
        return (Participant) addRow(new Participant(this));
    }

    public String getNextPariciantId() {
        int maxIdx = 100;
        for (int rowIdx = 0; rowIdx < getRowCount(); ++rowIdx) {
            try {
                String idStr = getRow(rowIdx).getString(ATTR_ID);
                if (idStr.startsWith(HASH)) {
                    idStr = idStr.substring(1);
                }
                final int id = Integer.parseInt(idStr);
                if (id > maxIdx) {
                    maxIdx = id;
                }
            } catch (final Exception e) {
                // ignore: if cell content is not a number then no number will duplicate it
            }
        }
        return HASH + String.valueOf(maxIdx + 1);
    }

    @Override
    public Participant getRow(final int index) {
        return (Participant) super.getRow(index);
    }

    public Participant getRow(final String participantId) throws Exception {
        Participant participant = null;
        for (int rowIdx = 0; rowIdx < getRowCount(); ++rowIdx) {
            participant = getRow(rowIdx);
            if (participant.getId().equals(participantId)) { return participant; }
        }
        participant = new Participant(this);
        participant.setId(participantId);
        return (Participant) addRow(participant);
    }

}
