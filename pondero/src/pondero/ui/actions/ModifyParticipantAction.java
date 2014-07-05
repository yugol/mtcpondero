package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.MsgUtil;
import pondero.model.participants.Participant;
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
    public void onParticipantSave(final Participant participant) {
        info("saving participant:", participant.getId());
        try {
            getApp().getCurrentWorkbook().add(participant);
            getApp().setCurrentParticipant(participant);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getFrame(), e);
        }
    }

    protected void showParticipantDialog(final Participant participant) {
        try {
            dlg = new ParticipantManagementDialog();
            dlg.addManagementListener(this);
            dlg.setLocationRelativeTo(getApp().getFrame());
            dlg.setParticipant(participant);
            dlg.setModal(true);
            dlg.setVisible(true);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getFrame(), e);
        }
    }

}
