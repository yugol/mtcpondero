package pondero.task.launch;

import pondero.tests.Test;

public interface TaskLauncher {

    void onTaskEnded(Test task, TaskMonitor monitor);

    void onTaskStarted(Test task);

}
