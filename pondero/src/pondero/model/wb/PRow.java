package pondero.model.wb;

import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public class PRow {

    private final PSheet   sheet;
    private final Object[] data;

    protected PRow(final PSheet sheet) {
        this.sheet = sheet;
        data = new Object[sheet.getColumnCount()];
    }

    public String toCsv() {
        return null;
    }

    protected Object get(final int index) {
        return data[index];
    }

    protected Object get(final String name) {
        return data[sheet.index(name)];
    }

    protected PSheet getSheet() {
        return sheet;
    }

    protected Object set(final int index, final Object value) {
        final PType type = sheet.getColumn(index).getType();
        if (value == null || type == PType.ANY) { return data[index] = value; }
        switch (type) {
            case STRING:
                return data[index] = StringUtil.toString(value);
            case DATE:
                return data[index] = DateUtil.toDate(value);
            case FIXED:
                return data[index] = NumberUtil.toFixed(value);
            case FLOAT:
                return data[index] = NumberUtil.toFloat(value);
            case TIME:
                return data[index] = DateUtil.toTime(value);
            case TIMESTAMP:
                return data[index] = DateUtil.toTimestamp(value);
            default:
                throw new IllegalArgumentException("Unsupported type " + value.getClass().getName());
        }
    }

    protected Object set(final String name, final Object value) {
        return set(sheet.index(name), value);
    }

}
