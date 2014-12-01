package pondero.task.launch;

import pondero.task.Task;

public interface TaskRenderer {

    void doEnd();

    void doBegin();

    void setTask(Task task);

}
