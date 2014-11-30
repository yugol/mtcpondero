package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.evaluation.profile.ProfileReport;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.participants.ParticipantSelectionDialog;
import pondero.util.OsUtil;
import pondero.util.PdfFileFilter;

@SuppressWarnings("serial")
public class EvaluationProfileAction extends PonderableAction {

    public EvaluationProfileAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.profile..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/table.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            Participant participant = getCurrentParticipant();
            if (participant == null) {
                final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(getMainFrame(), getCurrentWorkbook());
                dlg.setLocationRelativeTo(getMainFrame());
                dlg.setModal(true);
                dlg.setVisible(true);
                if (dlg.getCloseOperation() == JOptionPane.YES_OPTION) {
                    participant = dlg.getSelection();
                }
            }
            if (participant != null) {
                action("creating full report for ", participant.getId());
                final ProfileReport report = new ProfileReport(participant, getCurrentWorkbook().getModel());
                report.generate();
                final String reportFileName = ProfileReport.BASE_NAME + "-" + System.currentTimeMillis() + PdfFileFilter.DEFAULT_EXTENSION;
                final File reportFile = new File(Context.getFolderResultsTemp(), reportFileName);
                report.save(reportFile);
                report.close();
                OsUtil.openFile(reportFile);
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
