package pondero.engine.test.launch;

import pondero.data.foundation.basic.TrialRecord;
import pondero.engine.test.Test;

public class DefaultLauncher implements TaskLauncher {

    @Override
    public void onTaskEnded(final Test task, final TaskMonitor report) {
        System.out.println("Test ended in " + report.getRunningTimeInSeconds() + " seconds");
        for (final TrialRecord record : report.getRecords()) {
            System.out.println(record.toCsv());
        }
    }

    @Override
    public void onTaskStarted(final Test task) {
        System.out.println("Started test " + task.getTestId());
    }

}
