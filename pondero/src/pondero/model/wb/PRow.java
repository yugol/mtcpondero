package pondero.model.wb;

import java.util.Calendar;
import java.util.Date;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public abstract class PRow {

    private final PSheet   sheet;
    private final Object[] data;

    protected PRow(final PSheet sheet) {
        this.sheet = sheet;
        data = new Object[sheet.getColumnCount()];
    }

    public void randomize() {
        for (int i = 0; i < data.length; ++i) {
            final PType type = sheet.getColumn(i).getType();
            data[i] = type.next();
        }
    }

    public String toCsv() {
        return toString();
    }

    protected Object get(final int index) {
        return data[index];
    }

    protected Object get(final String name) {
        return data[sheet.index(name)];
    }

    protected Calendar getCalendar(final int index) {
        return DateUtil.toCalendar(get(index));
    }

    protected Calendar getCalendar(final String name) {
        return getCalendar(sheet.index(name));
    }

    protected Date getDate(final int index) {
        return DateUtil.getDate(get(index));
    }

    protected Date getDate(final String name) {
        return getDate(sheet.index(name));
    }

    protected Integer getInteger(final int index) {
        return NumberUtil.toInteger(get(index));
    }

    protected Integer getInteger(final String name) {
        return getInteger(sheet.index(name));
    }

    protected PSheet getSheet() {
        return sheet;
    }

    protected Object set(final int index, final Object value) {
        final PType type = sheet.getColumn(index).getType();
        if (value == null || type == PType.ANY) {
            data[index] = value;
        } else {
            switch (type) {
                case STRING:
                    data[index] = StringUtil.toString(value);
                    break;
                case DATE:
                case TIME:
                case TIMESTAMP:
                    data[index] = DateUtil.toMillis(value);
                    break;
                case FIXED:
                    data[index] = NumberUtil.toFixed(value);
                    break;
                case FLOAT:
                    data[index] = NumberUtil.toFloat(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported type " + value.getClass().getName());
            }
        }
        sheet.setDirty(true);
        return data[index];
    }

    protected Object set(final String name, final Object value) {
        return set(sheet.index(name), value);
    }

}
