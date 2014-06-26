package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import pondero.model.entities.Participant;
import pondero.ui.Messages;
import pondero.ui.Pondero;
import pondero.ui.participants.ParticipantSelectionDialog;

@SuppressWarnings("serial")
public class ChooseParticipantAction extends AbstractAction {

    private final Pondero app;

    public ChooseParticipantAction(final Pondero app) {
        this.app = app;
        putValue(NAME, Messages.getString("lbl.choose...")); //$NON-NLS-1$
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/find.png"))); //$NON-NLS-1$
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(app.getCurrentWorkbook());
            dlg.setModal(true);
            dlg.setVisible(true);
            final Participant selection = dlg.getSelection();
            if (selection != null) {
                app.setCurrentParticipant(selection);
            }
        } catch (final Exception e) {
            error(e);
        }
    }

}
