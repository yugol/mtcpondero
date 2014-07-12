package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.preferences.PreferencesDialog;

@SuppressWarnings("serial")
public class SetPreferencesAction extends PonderableAction {

    public SetPreferencesAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.preferences..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/wrench.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        action("opening preferences dialog");
        final PreferencesDialog dlg = new PreferencesDialog(getMainFrame());
        dlg.setLocationRelativeTo(getMainFrame());
        dlg.setModal(true);
        dlg.setVisible(true);
    }

}
