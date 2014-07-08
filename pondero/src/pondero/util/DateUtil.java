package pondero.util;

import static pondero.Logger.error;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import pondero.Globals;

public class DateUtil {

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static Date getDate(final Object value) {
        if (value instanceof Date) { return (Date) value; }
        if (value instanceof Number) { return new Date(((Number) value).longValue()); }
        return null;
    }

    public static Calendar parseIsoCalendar(final String str) {
        final Date date = parseIsoDate(str);
        if (date != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            return cal;
        }
        return null;
    }

    public synchronized static Date parseIsoDate(final String str) {
        try {
            if (StringUtil.isNullOrBlank(str)) { return null; }
            final Date date = ISO_DATE_FORMATTER.parse(str);
            return date;
        } catch (final Exception e) {
            error(e);
            return null;
        }
    }

    public static Calendar toCalendar(final Object value) {
        if (value instanceof Calendar) { return (Calendar) value; }
        if (value instanceof Number) {
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(((Number) value).longValue());
            return cal;
        }
        return null;
    }

    public synchronized static String toCompactDate(final Object value) {
        return COMPACT_DATE_FORMATTER.format(new Date(toMillis(value)));
    }

    public synchronized static String toCompactTime(final Object value) {
        return COMPACT_TIME_FORMATTER.format(new Date(toMillis(value)));
    }

    public synchronized static String toIsoDate(final Object value) {
        return ISO_DATE_FORMATTER.format(new Date(toMillis(value)));
    }

    public synchronized static String toIsoTime(final Object value) {
        return ISO_TIME_FORMATTER.format(new Date(toMillis(value)));
    }

    public synchronized static String toIsoTimestamp(final Object value) {
        return ISO_TIMESTAMP_FORMATTER.format(new Date(toMillis(value)));
    }

    public static Long toMillis(final Object value) {
        if (value instanceof Double) {
            final double oadate = (double) value;
            long num = (long) (oadate * 86400000.0 + (oadate >= 0.0 ? 0.5 : -0.5));
            if (num < 0L) {
                num -= num % 0x5265c00L * 2L;
            }
            num += 0x3680b5e1fc00L;
            num -= 62135596800000L;
            return num;
        }
        if (value instanceof Number) { return ((Number) value).longValue(); }
        if (value instanceof Calendar) { return ((Calendar) value).getTimeInMillis(); }
        if (value instanceof String) { return parseIsoDate(((String) value).trim()).getTime(); }
        throw new UnsupportedOperationException("toMillis for " + value.getClass().getName());
    }

    public synchronized static String toUiDate(final Calendar cal) {
        return DATE_UI_FORMATTER.format(cal.getTime());
    }

    private static final SimpleDateFormat ISO_DATE_FORMATTER      = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat ISO_TIME_FORMATTER      = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat ISO_TIMESTAMP_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat COMPACT_DATE_FORMATTER  = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat COMPACT_TIME_FORMATTER  = new SimpleDateFormat("HHmmss");
    private static final DateFormat       DATE_UI_FORMATTER       = DateFormat.getDateInstance(DateFormat.LONG, Globals.getLocale());

}
