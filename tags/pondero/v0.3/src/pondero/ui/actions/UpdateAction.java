package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.update.UpdateDialog;

@SuppressWarnings("serial")
public class UpdateAction extends PonderableAction {

    public UpdateAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.update..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/world_add.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final UpdateDialog dlg = initUpdateDilaog();
        dlg.setVisible(true);
        dlg.beginUpdate();
    }

    public void actionPerformedInBackground() {
        final UpdateDialog dlg = initUpdateDilaog();
        dlg.setVisible(false);
        dlg.beginUpdate();
    }

    private UpdateDialog initUpdateDilaog() {
        action("initializing update dialog");
        final UpdateDialog dlg = new UpdateDialog(getMainFrame());
        dlg.setLocationRelativeTo(getMainFrame());
        dlg.setModal(false);
        return dlg;
    }

}
