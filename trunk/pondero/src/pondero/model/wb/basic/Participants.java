package pondero.model.wb.basic;

import pondero.model.wb.PSheet;
import pondero.model.wb.PType;
import pondero.model.wb.PWorkbook;

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

    public Participants(final PWorkbook workbook) {
        super(workbook, NAME);
        addColumn(ATTR_ID, PType.STRING);
        addColumn(ATTR_SURNAME, PType.STRING);
        addColumn(ATTR_NAME, PType.STRING);
        addColumn(ATTR_DOB, PType.DATE);
        addColumn(ATTR_GENDER, PType.STRING);
        addColumn(ATTR_EDUCATION, PType.STRING);
        addColumn(ATTR_DRIVING_AGE, PType.FIXED);
        addColumn(ATTR_MILEAGE, PType.FIXED);
        lock();
    }

}
