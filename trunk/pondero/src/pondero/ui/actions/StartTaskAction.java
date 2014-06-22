package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pondero.engine.test.Test;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class StartTaskAction extends AbstractAction {

    private final Pondero app;

    public StartTaskAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.start")); //$NON-NLS-1$
        putValue(SHORT_DESCRIPTION, Messages.getString("msg.start-test")); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Test task = app.getCurrentTask();
        task.setWorkbook(app.getCurrentWorkbook());
        task.setParticipant(app.getCurrentParticipant());
        task.start();
    }

}