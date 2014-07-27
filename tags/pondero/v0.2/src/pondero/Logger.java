package pondero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import pondero.engine.staples.DateUtil;

public class Logger {

    public static final int    NONE            = 0;
    public static final int    CRITICAL        = 10;
    public static final int    ERROR           = 20;
    public static final int    WARNING         = 30;
    public static final int    INFO            = 40;
    public static final int    DEBUG           = 50;
    public static final int    TRACE           = 60;

    public static int          maxConsoleLevel = INFO;
    public static int          maxFileLevel    = TRACE;

    private static final File  logFile         = new File(Globals.getFolderLogs(), buildLogFileName());
    private static PrintStream logFileOut;

    public static void critical(Object... obj) {
        log(CRITICAL, null, obj);
    }

    public static void critical(Throwable t, Object... obj) {
        log(CRITICAL, t, obj);
    }

    public static void debug(Object... obj) {
        log(DEBUG, null, obj);
    }

    public static void debug(Throwable t, Object... obj) {
        log(DEBUG, t, obj);
    }

    public static void error(Object... obj) {
        log(ERROR, null, obj);
    }

    public static void error(Throwable t, Object... obj) {
        log(ERROR, t, obj);
    }

    public static void info(Object... obj) {
        log(INFO, null, obj);
    }

    public static void info(Throwable t, Object... obj) {
        log(INFO, t, obj);
    }

    public static void trace(Object... obj) {
        log(TRACE, null, obj);
    }

    public static void trace(Throwable t, Object... obj) {
        log(TRACE, t, obj);
    }

    public static void warning(Object... obj) {
        log(WARNING, null, obj);
    }

    public static void warning(Throwable t, Object... obj) {
        log(WARNING, t, obj);
    }

    private static String buildLogFileName() {
        long timestamp = System.currentTimeMillis();
        return DateUtil.toIsoDate(timestamp) + "@" + DateUtil.toCompactTime(timestamp) + ".log";
    }

    private static int getMaxLoggableLevel() {
        return Math.max(maxConsoleLevel, maxFileLevel);
    }

    private static void justPrintTheDamnThing(PrintStream out, String message, Throwable t) {
        out.println(message);
        if (t != null) {
            t.printStackTrace(out);
        }
    }

    private static PrintStream log() {
        if (logFileOut == null) {
            try {
                logFileOut = new PrintStream(logFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return logFileOut;
    }

    private static void log(int level, Throwable t, Object... obj) {
        if (level <= getMaxLoggableLevel()) {
            if (obj == null) {
                println(level, "null", t);
            } else if (obj instanceof Object[]) {
                StringBuilder msg = new StringBuilder();
                for (Object item : obj) {
                    msg.append(item == null ? "null" : String.valueOf(item));
                }
                println(level, msg.toString(), t);
            } else {
                println(level, String.valueOf(obj), t);
            }
        }
    }

    private static void println(int level, String msg, Throwable t) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[4];
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
        StringBuilder logEntry = new StringBuilder();
        logEntry.append("[");
        logEntry.append(DateUtil.toIsoTime(System.currentTimeMillis()));
        logEntry.append("] ");
        logEntry.append(levelHint);
        logEntry.append(" (");
        logEntry.append(element.getFileName());
        logEntry.append(":");
        logEntry.append(element.getLineNumber());
        logEntry.append(")");
        logEntry.append(" ");
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
            PrintStream out = log();
            justPrintTheDamnThing(out, logEntry.toString(), t);
            out.flush();
        }
    }

}
