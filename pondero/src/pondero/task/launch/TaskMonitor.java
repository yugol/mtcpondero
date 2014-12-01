package pondero.task.launch;

import pondero.tests.Test;

public interface TaskMonitor {

    void onTaskEnded(Test task, TaskData monitor);

    void onTaskStarted(Test task);

}
