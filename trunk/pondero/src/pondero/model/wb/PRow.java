package pondero.model.wb;

import pondero.engine.staples.DateUtil;
import pondero.engine.staples.NumberUtil;
import pondero.engine.staples.StringUtil;

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

    public Object set(final int index, final Object value) {
        final PType type = sheet.getColumn(index).getType();
        if (value == null || type == PType.ANY) { return data[index] = value; }
        switch (type) {
            case STRING:
                return data[index] = StringUtil.toString(value);
            case DATE:
                return data[index] = DateUtil.toSqlDate(value);
            case FIXED:
                return data[index] = NumberUtil.toFixed(value);
            case FLOAT:
                return data[index] = NumberUtil.toFloat(value);
            case TIME:
                return data[index] = DateUtil.toSqlTime(value);
            case TIMESTAMP:
                return data[index] = DateUtil.toSqlTimestamp(value);
            default:
                throw new IllegalArgumentException("Unsupported type " + value.getClass().getName());
        }
    }

    public Object set(final String name, final Object value) {
        return set(sheet.index(name), value);
    }

}
