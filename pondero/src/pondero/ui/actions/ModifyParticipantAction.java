package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.model.foundation.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.participants.ParticipantManagementDialog;
import pondero.ui.participants.ParticipantManagementListener;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class ModifyParticipantAction extends PonderoAction implements ParticipantManagementListener {

    private ParticipantManagementDialog dlg;

    public ModifyParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.modify-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/page_edit.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        showParticipantDialog(getCurrentParticipant());
    }

    @Override
    public void onParticipantSave(final ParticipantManagementDialog source) {
        info("saving participant");
        try {
            final Participant p = getCurrentWorkbook().addParticipant();
            source.getParticipant(p);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

    protected void showParticipantDialog(final Object participant) {
        try {
            dlg = new ParticipantManagementDialog(getMainFrame());
            dlg.addManagementListener(this);
            dlg.setLocationRelativeTo(getMainFrame());
            dlg.setParticipant(participant);
            dlg.setModal(true);
            dlg.setVisible(true);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
