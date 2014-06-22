package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pondero.engine.test.Test;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class TaskAction extends AbstractAction {

    private final Pondero app;
    private final Test    task;

    public TaskAction(final Pondero app, final Test task) {
        this.app = app;
        this.task = task;
        putValue(NAME, task.getCodeName());
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        app.setCurrentTask(task);
    }

}
