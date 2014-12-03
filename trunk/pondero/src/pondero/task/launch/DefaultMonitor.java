package pondero.task.launch;

import pondero.data.model.basic.TrialRecord;
import pondero.task.Task;

public class DefaultMonitor implements TaskMonitor {

    @Override
    public void onTaskEnded(final Task task, final TaskData report) {
        System.out.println("Test ended in " + report.getRunningTimeInSeconds() + " seconds");
        for (final TrialRecord record : report.getRecords()) {
            System.out.println(record.toCsv());
        }
    }

    @Override
    public void onTaskStarted(final Task task) {
        System.out.println("Started test " + task.getTest().getDescriptor().getId());
    }

}
