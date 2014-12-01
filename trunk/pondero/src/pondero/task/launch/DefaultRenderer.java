package pondero.task.launch;

import pondero.task.Task;
import pondero.task.controllers.PageController;
import pondero.ui.testing.TestFrame;

public class DefaultRenderer implements TaskRenderer {

    private Task      task;
    private TestFrame frame;

    @Override
    public void doBegin() {
        frame.setVisible(true);
    }

    @Override
    public void doEnd() {
        frame.dispose();
    }

    @Override
    public void setTask(final Task task) {
        this.task = task;
        frame = new TestFrame(task);
    }

    @Override
    public void showCurtains(final PageController pageController) {
        frame.showCurtains(pageController);
    }

}
