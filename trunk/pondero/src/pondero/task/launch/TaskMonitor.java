package pondero.task.launch;

import pondero.tests.Test;

public interface TaskMonitor {

    void onTaskEnded(Test test, TaskData data);

    void onTaskStarted(Test test);

}
