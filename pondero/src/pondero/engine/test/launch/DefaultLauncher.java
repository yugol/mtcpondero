package pondero.engine.test.launch;

import pondero.engine.test.Test;

public class DefaultLauncher implements TaskLauncher {

    @Override
    public void onTaskEnded(final Test task, final TestReport report) {
        System.out.println("Test ended in " + report.getRunningTimeInSeconds() + " seconds");
    }

    @Override
    public void onTaskStarted(final Test task) {
        System.out.println("Started test " + task.getTestId());
    }

}
