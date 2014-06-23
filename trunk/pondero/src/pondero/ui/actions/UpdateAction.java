package pondero.ui.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import pondero.ui.Messages;
import pondero.ui.Pondero;
import pondero.ui.update.UpdateDialog;

@SuppressWarnings("serial")
public class UpdateAction extends AbstractAction {

    private final Pondero app;

    public UpdateAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.update...")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/arrow_down.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateDialog dialog = new UpdateDialog(app.getFrame());
        dialog.setVisible(true);
        dialog.beginUpdate();
    }

}