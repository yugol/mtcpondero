package pondero.task.launch;

import pondero.task.Task;

public interface TaskMonitor {

    void onTaskEnded(Task test, TaskData data);

    void onTaskStarted(Task test);

}
