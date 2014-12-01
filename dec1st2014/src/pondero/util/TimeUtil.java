package pondero.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import pondero.Context;

public final class TimeUtil {

    private static final SimpleDateFormat ISO_DATE_FORMATTER      = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat ISO_TIME_FORMATTER      = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat ISO_TIMESTAMP_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat COMPACT_DATE_FORMATTER  = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat COMPACT_TIME_FORMATTER  = new SimpleDateFormat("HHmmss");
    private static final DateFormat       DATE_UI_FORMATTER       = DateFormat.getDateInstance(DateFormat.LONG, Context.getLocale());

    private static final long             ONE_DAY                 = 24L * 60 * 60 * 1000;

    public static Date fromOADate(final double d) {
        return fromOADate(d, TimeZone.getDefault());
    }

    public static Date fromOADate(final double d, final TimeZone tz) {
        final long wholeDays = (long) d;
        final double fracDays = Math.abs(d - wholeDays);
        final long offset = ONE_DAY * wholeDays + (long) (fracDays * ONE_DAY);
        final Date base = baseFor(tz);
        return new Date(base.getTime() + offset);
    }

    private static Date baseFor(final TimeZone tz) {
        final Calendar c = Calendar.getInstance(tz);
        c.clear();
        c.set(1899, 11, 30, 0, 0, 0);
        return c.getTime();
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static java.sql.Date getDate(final Object value) {
        if (value instanceof java.sql.Date) { return (java.sql.Date) value; }
        if (value instanceof Number) { return new java.sql.Date(((Number) value).longValue()); }
        return null;
    }

    public static Calendar parseIsoCalendar(final String str) throws ParseException {
        final Date date = parseIsoDate(str);
        if (date != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(date.getTime());
            return cal;
        }
        return null;
    }

    public static synchronized Date parseIsoDate(final String str) throws ParseException {
        return ISO_DATE_FORMATTER.parse(str);
    }

    public static synchronized Date parseIsoTime(final String str) throws ParseException {
        return ISO_TIME_FORMATTER.parse(str);
    }

    public static synchronized Date parseIsoTimestamp(final String str) throws ParseException {
        return ISO_TIMESTAMP_FORMATTER.parse(str);
    }

    public static Calendar toCalendar(final Object value) throws Exception {
        if (value != null) {
            final Calendar cal = Calendar.getInstance();
            if (value instanceof Calendar) {
                cal.setTimeInMillis(((Calendar) value).getTimeInMillis());
            } else if (value instanceof String) {
                final String str = (String) value;
                if (str.length() == 10) {
                    cal.setTimeInMillis(parseIsoDate(((String) value).trim()).getTime());
                } else if (str.length() == 8) {
                    cal.setTimeInMillis(parseIsoTime(((String) value).trim()).getTime());
                } else {
                    cal.setTimeInMillis(parseIsoTimestamp(((String) value).trim()).getTime());
                }
            } else {
                cal.setTimeInMillis(toMillis(value));
            }
            cal.set(Calendar.MILLISECOND, 0);
            return cal;
        }
        return null;
    }

    public static synchronized String toCompactDate(final Object value) {
        return COMPACT_DATE_FORMATTER.format(new Date(toMillis(value)));
    }

    public static synchronized String toCompactTime(final Object value) {
        return COMPACT_TIME_FORMATTER.format(new Date(toMillis(value)));
    }

    public static synchronized String toIsoDate(final Object value) {
        return ISO_DATE_FORMATTER.format(new Date(toMillis(value)));
    }

    public static synchronized String toIsoTime(final Object value) {
        return ISO_TIME_FORMATTER.format(new Date(toMillis(value)));
    }

    public static synchronized String toIsoTimestamp(final Object value) {
        return ISO_TIMESTAMP_FORMATTER.format(new Date(toMillis(value)));
    }

    private static long toMillis(final Object value) {
        if (value instanceof Double) {
            final double oadate = (double) value;
            long num = (long) (oadate * ONE_DAY + (oadate >= 0.0 ? 0.5 : -0.5));
            if (num < 0L) {
                num -= num % 0x5265c00L * 2L;
            }
            num += 0x3680b5e1fc00L;
            num -= 62135596800000L;
            return num;
        }
        if (value instanceof Number) { return ((Number) value).longValue(); }
        if (value instanceof Date) { return ((Date) value).getTime(); }
        throw new UnsupportedOperationException("toMillis for " + value.getClass().getName());
    }

    public static Long toDateMillis(final Object value) throws Exception {
        final Calendar cal = toCalendar(value);
        if (cal != null) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            return cal.getTimeInMillis();
        }
        return null;
    }

    public static Long toTimeMillis(final Object value) throws Exception {
        final Calendar cal = toCalendar(value);
        if (cal != null) {
            cal.set(Calendar.YEAR, 1900);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            return cal.getTimeInMillis();
        }
        return null;
    }

    public static Long toTimestampMillis(final Object value) throws Exception {
        final Calendar cal = toCalendar(value);
        if (cal != null) { return cal.getTimeInMillis(); }
        return null;
    }

    public static synchronized String toUiDate(final Calendar cal) {
        return DATE_UI_FORMATTER.format(cal.getTime());
    }

    private TimeUtil() {
    }

}
