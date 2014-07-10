package pondero.engine.test.launch;

import java.util.ArrayList;
import java.util.List;
import pondero.data.model.basic.TrialRecord;

public class TaskMonitor {

    public static final int         END_SUCCESS = 0;
    public static final int         END_KILL    = -9;

    private final String            runId;
    private long                    startTime;
    private long                    stopTime;
    private int                     stopCode;
    private final List<TrialRecord> records     = new ArrayList<TrialRecord>();

    public TaskMonitor(final String runId) {
        this.runId = runId;
    }

    public void add(final TrialRecord record) {
        records.add(record);
    }

    public int getEndCode() {
        return stopCode;
    }

    public List<TrialRecord> getRecords() {
        return records;
    }

    public String getRunId() {
        return runId;
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

    public void markStartTime() {
        startTime = System.currentTimeMillis();
    }

    public void markStopTime(final int stopCode) {
        stopTime = System.currentTimeMillis();
        this.stopCode = stopCode;
    }

}
