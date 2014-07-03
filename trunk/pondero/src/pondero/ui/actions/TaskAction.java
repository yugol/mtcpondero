package pondero.ui.actions;

import java.awt.event.ActionEvent;
import pondero.engine.test.Test;
import pondero.ui.Ponderable;

@SuppressWarnings("serial")
public class TaskAction extends PonderoAction {

    private final Test task;

    public TaskAction(final Ponderable app, final Test task) {
        super(app);
        this.task = task;
        putValue(NAME, task.getCodeName());
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        getApp().setCurrentTask(task);
    }

}
