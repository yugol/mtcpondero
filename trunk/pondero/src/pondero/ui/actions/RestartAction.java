package pondero.ui.actions;

import java.awt.event.ActionEvent;
import pondero.L10n;
import pondero.ui.Ponderable;

@SuppressWarnings("serial")
public class RestartAction extends QuitAction {

    public RestartAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.restart"));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        closeApplication(true);
    }

}
