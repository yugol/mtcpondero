package pondero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import pondero.util.DateUtil;

public final class Logger {

    public static final int    NONE            = 0;
    public static final int    CRITICAL        = 10;
    public static final int    ERROR           = 20;
    public static final int    WARNING         = 30;
    public static final int    INFO            = 40;
    public static final int    DEBUG           = 50;
    public static final int    TRACE           = 60;

    public static int          maxConsoleLevel = INFO;
    public static int          maxFileLevel    = TRACE;

    private static final File  LOG_FILE        = new File(Globals.getFolderLogs(), buildLogFileName());
    private static PrintStream logFileOut;

    public static void critical(final Object... obj) {
        log(CRITICAL, null, obj);
    }

    public static void critical(final Throwable t, final Object... obj) {
        log(CRITICAL, t, obj);
    }

    public static void debug(final Object... obj) {
        log(DEBUG, null, obj);
    }

    public static void debug(final Throwable t, final Object... obj) {
        log(DEBUG, t, obj);
    }

    public static void error(final Object... obj) {
        log(ERROR, null, obj);
    }

    public static void error(final Throwable t, final Object... obj) {
        log(ERROR, t, obj);
    }

    public static void info(final Object... obj) {
        log(INFO, null, obj);
    }

    public static void info(final Throwable t, final Object... obj) {
        log(INFO, t, obj);
    }

    public static void trace(final Object... obj) {
        log(TRACE, null, obj);
    }

    public static void trace(final Throwable t, final Object... obj) {
        log(TRACE, t, obj);
    }

    public static void warning(final Object... obj) {
        log(WARNING, null, obj);
    }

    public static void warning(final Throwable t, final Object... obj) {
        log(WARNING, t, obj);
    }

    private static String buildLogFileName() {
        final long timestamp = System.currentTimeMillis();
        return DateUtil.toIsoDate(timestamp) + "@" + DateUtil.toCompactTime(timestamp) + ".log";
    }

    private static int getMaxLoggableLevel() {
        return Math.max(maxConsoleLevel, maxFileLevel);
    }

    private static void justPrintTheDamnThing(final PrintStream destination, final String message, final Throwable t) {
        if (t == null) {
            destination.println(message);
        } else {
            destination.print(message);
            t.printStackTrace(destination);
        }
    }

    private static PrintStream log() {
        if (logFileOut == null) {
            try {
                logFileOut = new PrintStream(LOG_FILE);
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return logFileOut;
    }

    private static void log(final int level, final Throwable t, final Object... objs) {
        if (level <= getMaxLoggableLevel()) {
            if (objs == null) {
                println(level, "null", t);
            } else if (objs instanceof Object[]) {
                final StringBuilder msg = new StringBuilder();
                for (int i = 0; i < objs.length; ++i) {
                    final Object item = objs[i];
                    msg.append(item);
                }
                println(level, msg.toString(), t);
            } else {
                println(level, String.valueOf(objs), t);
            }
        }
    }

    private static void println(final int level, final String msg, final Throwable t) {
        final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        String levelHint;
        switch (level) {
            case CRITICAL:
                levelHint = "CRITIC";
                break;
            case ERROR:
                levelHint = "ERROR ";
                break;
            case WARNING:
                levelHint = "WARN  ";
                break;
            case INFO:
                levelHint = "INFO  ";
                break;
            case DEBUG:
                levelHint = "DEBUG ";
                break;
            case TRACE:
                levelHint = "TRACE ";
                break;
            default:
                levelHint = "      ";
                break;
        }
        final StringBuilder logEntry = new StringBuilder();
        logEntry.append("[");
        logEntry.append(DateUtil.toIsoTime(System.currentTimeMillis()));
        logEntry.append("] ");
        logEntry.append(levelHint);
        logEntry.append(" (");
        logEntry.append(element.getFileName());
        logEntry.append(":");
        logEntry.append(element.getLineNumber());
        logEntry.append(") ");
        logEntry.append(msg);
        if (maxConsoleLevel > NONE) {
            switch (level) {
                case ERROR:
                case CRITICAL:
                    justPrintTheDamnThing(System.err, logEntry.toString(), t);
                    break;
                default:
                    justPrintTheDamnThing(System.out, logEntry.toString(), t);
            }
        }
        if (maxFileLevel > NONE) {
            final PrintStream out = log();
            justPrintTheDamnThing(out, logEntry.toString(), t);
            out.flush();
        }
    }

    private Logger() {
    }

}
