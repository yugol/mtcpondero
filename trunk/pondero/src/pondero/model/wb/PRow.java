package pondero.model.wb;

public class PRow {

    private final PSheet   sheet;
    private final Object[] data;

    public PRow(final PSheet sheet) {
        this.sheet = sheet;
        data = new Object[sheet.getColumnCount()];
    }

    public Object get(final int index) {
        return data[index];
    }

    public Object get(final String name) {
        return data[sheet.index(name)];
    }

    public PSheet getSheet() {
        return sheet;
    }

}
