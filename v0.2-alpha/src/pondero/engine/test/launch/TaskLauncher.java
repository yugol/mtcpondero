package pondero.engine.test.launch;

import pondero.engine.test.Test;

public interface TaskLauncher {

    void onTaskEnded(Test task, TestReport report);

    void onTaskStarted(Test task);

}
