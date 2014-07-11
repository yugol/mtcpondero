package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.analysis.FillParticipantReport;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.data.drivers.excel.templates.participant.ParticipantTemplate;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.participants.ParticipantSelectionDialog;
import pondero.util.MsgUtil;
import pondero.util.OsUtil;

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
                    final ParticipantTemplate pTemplate = new ParticipantTemplate();
                    FillParticipantReport.fill(pTemplate, p, getCurrentWorkbook().getModel());
                    final String reportFileName = ParticipantTemplate.BASE_NAME + "-" + System.currentTimeMillis() + ExcelFileFilter.DEFAULT_EXTENSION;
                    final File reportFile = new File(Context.getFolderResultsReports(), reportFileName);
                    pTemplate.save(reportFile);
                    OsUtil.openFile(reportFile);
                }
            }
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
