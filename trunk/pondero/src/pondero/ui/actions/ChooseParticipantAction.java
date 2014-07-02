package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.model.entities.Participant;
import pondero.ui.Main;
import pondero.ui.Pondero;
import pondero.ui.participants.ParticipantSelectionDialog;

@SuppressWarnings("serial")
public class ChooseParticipantAction extends PonderoAction {

    public ChooseParticipantAction(final Main app) {
        super(app);
        putValue(NAME, L10n.getString("html.choose-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/find.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(getApp().getCurrentWorkbook());
            dlg.setModal(true);
            dlg.setVisible(true);
            final Participant selection = dlg.getSelection();
            if (selection != null) {
                getApp().setCurrentParticipant(selection);
            }
        } catch (final Exception e) {
            error(e);
        }
    }

}
