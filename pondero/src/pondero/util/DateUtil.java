package pondero.util;

import static pondero.Logger.error;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import pondero.Globals;

public class DateUtil {

    public static final SimpleDateFormat  ISO_DATE_FORMATTER      = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat  ISO_TIME_FORMATTER      = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat  ISO_TIMESTAMP_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat COMPACT_DATE_FORMATTER  = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat COMPACT_TIME_FORMATTER  = new SimpleDateFormat("HHmmss");
    private static final DateFormat       DATE_UI_FORMATTER       = DateFormat.getDateInstance(DateFormat.LONG, Globals.getLocale());

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Calendar parseIsoDate(final String dob) {
        try {
            if (StringUtil.isNullOrBlank(dob)) { return null; }
            final Date date = ISO_DATE_FORMATTER.parse(dob);
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            return cal;
        } catch (final Exception e) {
            error(e);
            return null;
        }
    }

    public static String toCompactDate(final long time) {
        return COMPACT_DATE_FORMATTER.format(new Date(time));
    }

    public static String toCompactTime(final long time) {
        return COMPACT_TIME_FORMATTER.format(new Date(time));
    }

    public static Calendar toDate(final Object value) {
        if (value instanceof Calendar) { return (Calendar) value; }
        throw new UnsupportedOperationException("toSqlDate for " + value.getClass().getName());
    }

    public static String toIsoDate(final Calendar cal) {
        return toIsoDate(cal.getTimeInMillis());
    }

    public static String toIsoDate(final long time) {
        return ISO_DATE_FORMATTER.format(new Date(time));
    }

    public static String toIsoTime(final Calendar cal) {
        return toIsoTime(cal.getTimeInMillis());
    }

    public static String toIsoTime(final long time) {
        return ISO_TIME_FORMATTER.format(new Date(time));
    }

    public static String toIsoTimestamp(final Calendar cal) {
        return toIsoTimestamp(cal.getTimeInMillis());
    }

    public static String toIsoTimestamp(final long time) {
        return ISO_TIMESTAMP_FORMATTER.format(new Date(time));
    }

    public static Calendar toTime(final Object value) {
        if (value instanceof Calendar) { return (Calendar) value; }
        throw new UnsupportedOperationException("toSqlTime for " + value.getClass().getName());
    }

    public static Calendar toTimestamp(final Object value) {
        if (value instanceof Calendar) { return (Calendar) value; }
        throw new UnsupportedOperationException("toSqlTimestamp for " + value.getClass().getName());
    }

    public static String toUiDate(final Calendar cal) {
        return DATE_UI_FORMATTER.format(cal.getTime());
    }

}
