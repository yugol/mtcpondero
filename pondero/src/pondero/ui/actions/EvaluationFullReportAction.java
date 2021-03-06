package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.evaluation.FullReport;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.participants.ParticipantSelectionDialog;
import pondero.util.OsUtil;
import pondero.util.PdfFileFilter;

@SuppressWarnings("serial")
public class EvaluationFullReportAction extends PonderableAction {

    public EvaluationFullReportAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.full-report..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/report_user.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final ParticipantSelectionDialog dlg = new ParticipantSelectionDialog(getMainFrame(), getCurrentWorkbook());
            dlg.setLocationRelativeTo(getMainFrame());
            dlg.setModal(true);
            dlg.setVisible(true);
            if (dlg.getCloseOperation() == JOptionPane.YES_OPTION) {
                final Participant participant = dlg.getSelection();
                if (participant != null) {
                    action("creating full report for ", participant.getId());
                    final FullReport report = new FullReport(participant, getCurrentWorkbook().getModel());
                    report.generate();
                    final String reportFileName = FullReport.BASE_NAME + "-" + System.currentTimeMillis() + PdfFileFilter.DEFAULT_EXTENSION;
                    final File reportFile = new File(Context.getFolderResultsTemp(), reportFileName);
                    report.save(reportFile);
                    report.close();
                    OsUtil.openFile(reportFile);
                }
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
