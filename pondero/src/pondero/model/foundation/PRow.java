package pondero.model.foundation;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import pondero.util.BooleanUtil;
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

    public Boolean getBoolean(final int index) {
        return (Boolean) data[index];
    }

    public Boolean getBoolean(final String name) {
        return getBoolean(sheet.index(name));
    }

    public Date getDate(final int index) {
        if (data[index] == null) { return null; }
        return new Date((long) data[index]);
    }

    public Date getDate(final String name) {
        return getDate(sheet.index(name));
    }

    public BigDecimal getDecimal(final int index) {
        return (BigDecimal) data[index];
    }

    public BigDecimal getDecimal(final String name) {
        return getDecimal(sheet.index(name));
    }

    public String getString(final int index) {
        return (String) data[index];
    }

    public String getString(final String name) {
        return getString(sheet.index(name));
    }

    public Time getTime(final int index) {
        if (data[index] == null) { return null; }
        return new Time((long) data[index]);
    }

    public Time getTime(final String name) {
        return getTime(sheet.index(name));
    }

    public Timestamp getTimestamp(final int index) {
        if (data[index] == null) { return null; }
        return new Timestamp((long) data[index]);
    }

    public Timestamp getTimestamp(final String name) {
        return getTimestamp(sheet.index(name));
    }

    public void randomize() throws Exception {
        for (int i = 0; i < data.length; ++i) {
            final PType type = sheet.getColumn(i).getType();
            data[i] = type.next();
        }
    }

    public Object set(final int index, final Object value) throws Exception {
        if (value == null) {
            data[index] = null;
        } else {
            final PType type = sheet.getColumn(index).getType();
            switch (type) {
                case STRING:
                    data[index] = StringUtil.toCellString(value);
                    break;
                case DECIMAL:
                    data[index] = NumberUtil.toDecimal(value);
                    break;
                case BOOLEAN:
                    data[index] = BooleanUtil.toBoolean(value);
                    break;
                case DATE:
                    data[index] = DateUtil.toDateMillis(value);
                    break;
                case TIME:
                    data[index] = DateUtil.toTimeMillis(value);
                    break;
                case TIMESTAMP:
                    data[index] = DateUtil.toTimestampMillis(value);
                    break;
                case FORMULA:
                    data[index] = StringUtil.toCellString(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported type " + value.getClass().getName());
            }
        }
        sheet.setDirty(true);
        return data[index];
    }

    public Object set(final String name, final Object value) throws Exception {
        return set(sheet.index(name), value);
    }

    public String toCsv() throws Exception {
        return toString();
    }

    protected Object get(final int index) {
        return data[index];
    }

    protected Object get(final String name) {
        return data[sheet.index(name)];
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
