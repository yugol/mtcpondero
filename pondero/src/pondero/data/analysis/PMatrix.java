package pondero.data.analysis;

import pondero.data.model.PRow;
import pondero.data.model.PSheet;

public class PMatrix extends PSheet {

    public class MRow extends PRow {

        protected MRow() {
            super(PMatrix.this);
        }

    }

    public PMatrix(final String name) {
        super(null, name);
    }

    @Override
    public PRow addRow() throws Exception {
        return addRow(new MRow());
    }

}
