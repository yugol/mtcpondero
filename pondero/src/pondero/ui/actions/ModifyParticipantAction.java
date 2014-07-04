package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.MessageUtil;
import pondero.model.entities.Participant;
import pondero.model.participants.Participants;
import pondero.ui.Ponderable;
import pondero.ui.PonderoOld;
import pondero.ui.participants.ParticipantManagementDialog;
import pondero.ui.participants.ParticipantManagementListener;

@SuppressWarnings("serial")
public class ModifyParticipantAction extends PonderoAction implements ParticipantManagementListener {

    private ParticipantManagementDialog dlg;

    public ModifyParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.modify-participant"));
        putValue(SMALL_ICON, new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/page_edit.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        showParticipantDialog(getApp().getCurrentParticipant());
    }

    @Override
    public void onParticipantSave(Participant participant) {
        info("saving participant:", participant.getId());
        try {
            Participants participants = getParticipants();
            participants.put(participant);
            participants.save();
            getApp().setCurrentParticipant(participant);
        } catch (final Exception e) {
            error(e);
            MessageUtil.showExceptionMessage(getFrame(), e);
        }
    }

    protected Participants getParticipants() {
        return new Participants(getApp().getCurrentWorkbook());
    }

    protected void showParticipantDialog(Participant participant) {
        try {
            dlg = new ParticipantManagementDialog();
            dlg.addManagementListener(this);
            dlg.setLocationRelativeTo(getApp().getFrame());
            dlg.setParticipant(participant);
            dlg.setModal(true);
            dlg.setVisible(true);
        } catch (final Exception e) {
            error(e);
            MessageUtil.showExceptionMessage(getFrame(), e);
        }
    }

}
