package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.participants.ParticipantSelectionDialog;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class AnalyseParticipantAction extends PonderableAction {

    public AnalyseParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.participant"));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(getMainFrame(), getCurrentWorkbook());
            dlg.setLocationRelativeTo(getMainFrame());
            dlg.setModal(true);
            dlg.setVisible(true);
            if (dlg.getCloseOperation() == JOptionPane.YES_OPTION) {
                final Participant p = dlg.getSelection();
                if (p != null) {
                    info("Perform analysis for ", p.toString());
                }
            }
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
