package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import pondero.ui.Messages;
import pondero.ui.Pondero;
import pondero.ui.participants.ParticipantsManagementDialog;

@SuppressWarnings("serial")
public class ManageParticipantsAction extends AbstractAction {

    private final Pondero app;

    public ManageParticipantsAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.add-modify")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/page_edit.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantsManagementDialog dlg = new ParticipantsManagementDialog(app.getCurrentWorkbook());
            dlg.setModal(true);
            dlg.setVisible(true);
        } catch (final Exception e) {
            error(e);
        }
    }

}
