package pondero.task.launch;

import pondero.task.Task;
import pondero.ui.testing.TestFrame;

public class DefaultRenderer implements TaskRenderer {

    private Task      task;
    private TestFrame frame;

    @Override
    public void destroy() {
        frame.dispose();
    }

    @Override
    public void init() {
        frame.setVisible(true);
    }

    @Override
    public void setTask(final Task task) {
        this.task = task;
        frame = new TestFrame(task);
    }

}
