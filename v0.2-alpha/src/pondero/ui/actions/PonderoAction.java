package pondero.ui.actions;

import javax.swing.AbstractAction;
import pondero.ui.Pondero;

@SuppressWarnings("serial")
public abstract class PonderoAction extends AbstractAction {

    private final Pondero app;

    public PonderoAction(Pondero app) {
        this.app = app;
    }

    Pondero getApp() {
        return app;
    }

}
