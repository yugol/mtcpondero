package pondero.ui.actions;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import pondero.data.Workbook;
import pondero.data.model.basic.Participant;
import pondero.tests.Test;
import pondero.ui.Ponderable;

@SuppressWarnings("serial")
public abstract class PonderableAction extends AbstractAction {

    private final Ponderable app;

    public PonderableAction(final Ponderable app) {
        this.app = app;
    }

    protected Ponderable getApp() {
        return app;
    }

    protected Participant getCurrentParticipant() {
        return app.getCurrentParticipant();
    }

    protected Test getCurrentTask() {
        return app.getCurrentTask();
    }

    protected Workbook getCurrentWorkbook() {
        return app.getCurrentWorkbook();
    }

    protected JFrame getMainFrame() {
        return app.getMainFrame();
    }

}
