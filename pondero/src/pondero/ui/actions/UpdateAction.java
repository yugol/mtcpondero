package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.ui.Pondero;
import pondero.ui.update.UpdateDialog;

@SuppressWarnings("serial")
public class UpdateAction extends PonderoAction {

    public UpdateAction(final Pondero app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.update...")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/arrow_down.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateDialog dialog = new UpdateDialog(getApp().getFrame());
        dialog.setVisible(true);
        dialog.beginUpdate();
    }

}