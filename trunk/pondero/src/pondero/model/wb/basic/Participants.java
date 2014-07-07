package pondero.model.wb.basic;

import pondero.model.wb.PSheet;
import pondero.model.wb.PWorkbook;

public class Participants extends PSheet {

    public static final String NAME = "PARTICIPANTS";

    public Participants(final PWorkbook workbook) {
        super(workbook, NAME);
        lock();
    }

}
