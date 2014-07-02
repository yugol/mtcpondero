package pondero.ui.actions;

import javax.swing.AbstractAction;
import pondero.ui.Main;

@SuppressWarnings("serial")
public abstract class PonderoAction extends AbstractAction {

    private final Main app;

    public PonderoAction(Main app) {
        this.app = app;
    }

    Main getApp() {
        return app;
    }

}
