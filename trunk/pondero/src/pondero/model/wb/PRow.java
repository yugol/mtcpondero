package pondero.model.wb;

import java.math.BigDecimal;
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
        return null;
    }

    protected Object get(final int index) {
        return data[index];
    }

    protected Object get(final String name) {
        return data[sheet.index(name)];
    }

    protected Calendar getCalendar(final int index) {
        final Object value = get(index);
        if (value instanceof Calendar) { return (Calendar) value; }
        if (value instanceof Number) {
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(((Number) value).longValue());
            return cal;
        }
        return null;
    }

    protected Calendar getCalendar(final String name) {
        return getCalendar(sheet.index(name));
    }

    protected Date getDate(final int index) {
        final Object value = get(index);
        if (value instanceof Date) { return (Date) value; }
        if (value instanceof Number) { return new Date(((Number) value).longValue()); }
        return null;
    }

    protected Date getDate(final String name) {
        return getDate(sheet.index(name));
    }

    protected Integer getInteger(final int index) {
        final BigDecimal value = (BigDecimal) get(index);
        if (value != null) { return value.intValue(); }
        return null;
    }

    protected Integer getInteger(final String name) {
        return getInteger(sheet.index(name));
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
            case TIME:
            case TIMESTAMP:
                return data[index] = DateUtil.toMillis(value);
            case FIXED:
                return data[index] = NumberUtil.toFixed(value);
            case FLOAT:
                return data[index] = NumberUtil.toFloat(value);
            default:
                throw new IllegalArgumentException("Unsupported type " + value.getClass().getName());
        }
    }

    protected Object set(final String name, final Object value) {
        return set(sheet.index(name), value);
    }

}
