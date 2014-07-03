package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.model.entities.Participant;
import pondero.model.participants.Participants;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;
import pondero.ui.participants.ParticipantsManagementDialog;

@SuppressWarnings("serial")
public class AddParticipantAction extends PonderoAction {

    public AddParticipantAction(Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.add-participant"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/page_add.png")));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            Participants participants = new Participants(getApp().getCurrentWorkbook());
            Participant participant = new Participant();
            participant.setId(participants.generateNewId());

            final ParticipantsManagementDialog dlg = new ParticipantsManagementDialog();
            dlg.setLocationRelativeTo(getApp().getFrame());
            dlg.setParticipant(participant);
            dlg.setModal(true);
            dlg.setVisible(true);
        } catch (final Exception e) {
            error(e);
        }
    }

}
