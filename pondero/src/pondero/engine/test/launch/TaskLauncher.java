package pondero.engine.test.launch;

import pondero.engine.test.Test;

public interface TaskLauncher {

    void onTaskEnded(Test task, TaskMonitor monitor);

    void onTaskStarted(Test task);

}
