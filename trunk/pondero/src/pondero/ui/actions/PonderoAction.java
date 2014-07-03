package pondero.ui.actions;

import javax.swing.AbstractAction;
import pondero.ui.Ponderable;

@SuppressWarnings("serial")
public abstract class PonderoAction extends AbstractAction {

    private final Ponderable app;

    public PonderoAction(Ponderable app) {
        this.app = app;
    }

    Ponderable getApp() {
        return app;
    }

}
