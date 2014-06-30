package pondero.ui.actions;

import java.awt.event.ActionEvent;
import pondero.engine.test.Test;
import pondero.ui.Messages;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class StartTaskAction extends PonderoAction {

    public StartTaskAction(final Pondero app) {
        super(app);
        putValue(NAME, Messages.getString("lbl.start")); //$NON-NLS-1$
        putValue(SHORT_DESCRIPTION, Messages.getString("msg.start-test")); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Test task = getApp().getCurrentTask();
        task.setWorkbook(getApp().getCurrentWorkbook());
        task.setParticipant(getApp().getCurrentParticipant());
        task.start();
    }

}