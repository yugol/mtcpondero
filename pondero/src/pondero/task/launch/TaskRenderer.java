package pondero.task.launch;

import pondero.task.Task;

public interface TaskRenderer {

    void destroy();

    void init();

    void setTask(Task task);

}
