package pondero.ui.actions;

import static pondero.Logger.action;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import pondero.L10n;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.participants.ParticipantManagementDialog;
import pondero.ui.participants.ParticipantManagementListener;

@SuppressWarnings("serial")
public class ModifyParticipantAction extends PonderableAction implements ParticipantManagementListener {

    private ParticipantManagementDialog dlg;

    public ModifyParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("html.modify-participant"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/page_edit.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        action("modifying participant");
        showParticipantDialog(getCurrentParticipant());
    }

    @Override
    public void onParticipantSave(final ParticipantManagementDialog source) {
        info("saving participant");
        try {
            final String participantId = source.getParticipantId();
            final Participant p = getCurrentWorkbook().getParticipant(participantId);
            source.getParticipant(p);
            getApp().setCurrentParticipant(p);
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
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
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
