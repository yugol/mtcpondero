package pondero.data.model;

public class TestSheet extends PSheet {

    public static final String NAME           = "PTypes";

    public static final String ATTR_BOOLEAN   = "BOOLEAN";
    public static final String ATTR_DATE      = "DATE";
    public static final String ATTR_DECIMAL   = "DECIMAL";
    public static final String ATTR_INT       = "INT";
    public static final String ATTR_STRING    = "STRING";
    public static final String ATTR_TIME      = "TIME";
    public static final String ATTR_TIMESTAMP = "TIMESTAMP";
    public static final String ATTR_FORMULA   = "FORMULA";

    TestSheet(final PModel model, final boolean lock) throws Exception {
        super(model, NAME);
        if (lock) {
            addColumn(ATTR_STRING, PType.STRING);
            addColumn(ATTR_BOOLEAN, PType.BOOLEAN);
            addColumn(ATTR_INT, PType.INT);
            addColumn(ATTR_DECIMAL, PType.DECIMAL);
            addColumn(ATTR_DATE, PType.DATE);
            addColumn(ATTR_TIME, PType.TIME);
            addColumn(ATTR_TIMESTAMP, PType.TIMESTAMP);
            addColumn(ATTR_FORMULA, PType.FORMULA);
            lock();
        }
    }

    @Override
    public TestRow addRow() throws Exception {
        return (TestRow) super.addRow(new TestRow(this));
    }

    @Override
    public TestRow getRow(final int index) {
        return (TestRow) super.getRow(index);
    }

}
