package pondero.ui.actions;

import java.awt.event.ActionEvent;
import pondero.L10n;
import pondero.engine.test.Test;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public class StartTaskAction extends PonderoAction {

    public StartTaskAction(final Pondero app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.start"));
        putValue(SHORT_DESCRIPTION, L10n.getString("msg.start-test"));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Test task = getApp().getCurrentTask();
        task.setWorkbook(getApp().getCurrentWorkbook());
        task.setParticipant(getApp().getCurrentParticipant());
        task.start();
    }

}