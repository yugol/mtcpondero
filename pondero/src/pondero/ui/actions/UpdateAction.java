package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;
import pondero.ui.update.UpdateDialog;

@SuppressWarnings("serial")
public class UpdateAction extends PonderoAction {

    public UpdateAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.update..."));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/arrow_down.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateDialog dlg = new UpdateDialog(getFrame());
        dlg.setLocationRelativeTo(getFrame());
        dlg.setModal(false);
        dlg.setVisible(true);
        dlg.beginUpdate();
    }

}