package pondero.engine.test.launch;

import pondero.engine.test.Test;
import pondero.model.entities.TrialRecord;

public class DefaultLauncher implements TaskLauncher {

    @Override
    public void onTaskEnded(final Test task, final TaskMonitor report) {
        System.out.println("Test ended in " + report.getRunningTimeInSeconds() + " seconds");
        for (TrialRecord record : report.getRecords()) {
            System.out.println(record.toCsv());
        }
    }

    @Override
    public void onTaskStarted(final Test task) {
        System.out.println("Started test " + task.getTestId());
    }

}
