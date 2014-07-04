package pondero.ui.actions;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import pondero.ui.Ponderable;

@SuppressWarnings("serial")
public abstract class PonderoAction extends AbstractAction {

    private final Ponderable app;

    public PonderoAction(Ponderable app) {
        this.app = app;
    }

    protected Ponderable getApp() {
        return app;
    }

    protected JFrame getFrame() {
        return getApp().getFrame();
    }

}
