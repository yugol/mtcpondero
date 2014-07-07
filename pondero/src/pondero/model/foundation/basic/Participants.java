package pondero.model.foundation.basic;

import pondero.model.foundation.PModel;
import pondero.model.foundation.PSheet;
import pondero.model.foundation.PType;

public class Participants extends PSheet {

    public static final String NAME             = "PARTICIPANTS";

    public static final String ATTR_ID          = "ID";
    public static final String ATTR_SURNAME     = "SURNAME";
    public static final String ATTR_NAME        = "NAME";
    public static final String ATTR_DOB         = "DOB";
    public static final String ATTR_GENDER      = "GENDER";
    public static final String ATTR_EDUCATION   = "EDUCATION";
    public static final String ATTR_DRIVING_AGE = "DRIVING_AGE";
    public static final String ATTR_MILEAGE     = "MILEAGE";

    Participants(final PModel model) {
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

    public Participant addRow() {
        return (Participant) addRow(new Participant(this));
    }

    @Override
    public Participant getRow(final int index) {
        return (Participant) super.getRow(index);
    }

}
