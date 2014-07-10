package pondero.model.foundation;

import java.util.Calendar;
import java.util.Date;
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

    public void randomize() throws Exception {
        for (int i = 0; i < data.length; ++i) {
            final PType type = sheet.getColumn(i).getType();
            data[i] = type.next();
        }
    }

    public Object set(final int index, final Object value) throws Exception {
        final PType type = sheet.getColumn(index).getType();
        if (value == null) {
            data[index] = value;
        } else {
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

    protected Date getDate(final int index) {
        return DateUtil.getDate(get(index));
    }

    protected Date getDate(final String name) {
        return getDate(sheet.index(name));
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
