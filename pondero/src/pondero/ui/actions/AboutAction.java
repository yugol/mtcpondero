package pondero.ui.actions;

import java.awt.event.ActionEvent;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.about.AboutDialog;

@SuppressWarnings("serial")
public class AboutAction extends PonderableAction {

    public AboutAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.about..."));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final AboutDialog dlg = new AboutDialog(getMainFrame());
        dlg.setLocationRelativeTo(getMainFrame());
        dlg.setModal(true);
        dlg.setVisible(true);
    }

}
