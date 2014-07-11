package pondero.data.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import pondero.util.BooleanUtil;
import pondero.util.DateUtil;
import pondero.util.NumberUtil;
import pondero.util.StringUtil;

public abstract class PRow {

    private final PSheet       sheet;
    private final List<Object> data;

    protected PRow(final PSheet sheet) {
        this.sheet = sheet;
        data = new ArrayList<Object>(sheet.getColumnCount());
    }

    public Boolean getBoolean(final int index) {
        return (Boolean) data.get(index);
    }

    public Boolean getBoolean(final String name) {
        return getBoolean(sheet.index(name));
    }

    public Date getDate(final int index) {
        if (data.get(index) == null) { return null; }
        return new Date((long) data.get(index));
    }

    public Date getDate(final String name) {
        return getDate(sheet.index(name));
    }

    public BigDecimal getDecimal(final int index) {
        return (BigDecimal) data.get(index);
    }

    public BigDecimal getDecimal(final String name) {
        return getDecimal(sheet.index(name));
    }

    public String getString(final int index) {
        return (String) data.get(index);
    }

    public String getString(final String name) {
        return getString(sheet.index(name));
    }

    public Time getTime(final int index) {
        if (data.get(index) == null) { return null; }
        return new Time((long) data.get(index));
    }

    public Time getTime(final String name) {
        return getTime(sheet.index(name));
    }

    public Timestamp getTimestamp(final int index) {
        if (data.get(index) == null) { return null; }
        return new Timestamp((long) data.get(index));
    }

    public Timestamp getTimestamp(final String name) {
        return getTimestamp(sheet.index(name));
    }

    public void randomize() throws Exception {
        for (int i = 0; i < sheet.getColumnCount(); ++i) {
            final PType type = sheet.getColumn(i).getType();
            data.set(i, type.next());
        }
    }

    public Object set(final int index, final Object value) throws Exception {
        if (value == null) {
            setRaw(index, null);
        } else {
            final PType type = sheet.getColumn(index).getType();
            switch (type) {
                case STRING:
                    setRaw(index, StringUtil.toCellString(value));
                    break;
                case DECIMAL:
                case INT:
                    setRaw(index, NumberUtil.toDecimal(value));
                    break;
                case BOOLEAN:
                    setRaw(index, BooleanUtil.toBoolean(value));
                    break;
                case DATE:
                    setRaw(index, DateUtil.toDateMillis(value));
                    break;
                case TIME:
                    setRaw(index, DateUtil.toTimeMillis(value));
                    break;
                case TIMESTAMP:
                    setRaw(index, DateUtil.toTimestampMillis(value));
                    break;
                case FORMULA:
                    setRaw(index, StringUtil.toCellString(value));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported type " + value.getClass().getName());
            }
        }
        sheet.setDirty(true);
        return get(index);
    }

    public Object set(final String name, final Object value) throws Exception {
        return set(sheet.index(name), value);
    }

    public String toCsv() throws Exception {
        return toString();
    }

    private void ensureIndex(final int index) {
        while (data.size() <= index) {
            data.add(null);
        }
    }

    private void setRaw(final int index, final Object value) {
        ensureIndex(index);
        data.set(index, value);
    }

    protected Object get(final int index) {
        ensureIndex(index);
        return data.get(index);
    }

    protected Object get(final String name) {
        return get(sheet.index(name));
    }

    protected Calendar getCalendar(final int index) throws Exception {
        return DateUtil.toCalendar(get(index));
    }

    protected Calendar getCalendar(final String name) throws Exception {
        return getCalendar(sheet.index(name));
    }

    protected Integer getInteger(final int index) {
        return NumberUtil.toDecimal(get(index)).intValue();
    }

    protected Integer getInteger(final String name) {
        return getInteger(sheet.index(name));
    }

    protected PSheet getSheet() {
        return sheet;
    }

}
