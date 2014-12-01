package pondero.task.launch;

import pondero.task.Task;
import pondero.task.controllers.PageController;
import pondero.task.controllers.StimulusFrame;
import pondero.task.controllers.TrialController;

public interface TaskRenderer {

    void doBegin();

    void doEnd();

    void presentStimuli(StimulusFrame frame);

    void setTask(Task task);

    void showCurtains(PageController controller);

    void showScene(TrialController controller);

}
