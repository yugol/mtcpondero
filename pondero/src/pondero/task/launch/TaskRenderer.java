package pondero.task.launch;

import pondero.task.Task;
import pondero.task.controllers.PageController;

public interface TaskRenderer {

    void doBegin();

    void doEnd();

    void setTask(Task task);

    void showCurtains(PageController pageController);

}
