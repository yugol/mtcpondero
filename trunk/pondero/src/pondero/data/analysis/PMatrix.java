package pondero.data.analysis;

import pondero.data.model.PRow;
import pondero.data.model.PSheet;
import pondero.data.model.PType;

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
    public MRow addRow() throws Exception {
        return (MRow) addRow(new MRow());
    }

    public int index(final String name, final PType type) throws Exception {
        Integer colIdx = index(name);
        if (colIdx == null) {
            addColumn(name, type);
            colIdx = index(name);
        }
        return colIdx;
    }

}
