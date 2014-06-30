package pondero.engine.test.launch;

public class TestReport {

    public static final int END_SUCCESS = 0;
    public static final int END_KILL    = -9;

    private long            startTime;
    private long            stopTime;
    private int             endCode;

    public int getEndCode() {
        return endCode;
    }

    public double getRunningTimeInSeconds() {
        return (getStopTime() - getStartTime()) / 1000.0;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setEndCode(final int endCode) {
        this.endCode = endCode;
    }

    public void setStartTime(final long startTime) {
        this.startTime = startTime;
    }

    public void setStopTime(final long stopTime) {
        this.stopTime = stopTime;
    }

}
