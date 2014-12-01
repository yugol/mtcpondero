package pondero.tests.test.launch;

import pondero.tests.test.Test;

public interface TaskLauncher {

    void onTaskEnded(Test task, TaskMonitor monitor);

    void onTaskStarted(Test task);

}
