package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.analysis.FillParticipantReport;
import pondero.data.drivers.excel.templates.participant.ParticipantTemplate;
import pondero.data.model.basic.Participant;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.participants.ParticipantSelectionDialog;
import pondero.util.ExcelFileFilter;
import pondero.util.OsUtil;

@SuppressWarnings("serial")
public class AnalyseParticipantAction extends PonderableAction {

    public AnalyseParticipantAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.participant..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/user_gray.png")));
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
                    action("performing analysis of participant ", participant.getId());
                    final ParticipantTemplate template = new ParticipantTemplate();
                    new FillParticipantReport().fill(template, participant, getCurrentWorkbook().getModel());
                    final String reportFileName = ParticipantTemplate.BASE_NAME + "-" + System.currentTimeMillis() + ExcelFileFilter.DEFAULT_EXTENSION;
                    final File reportFile = new File(Context.getFolderResultsTemp(), reportFileName);
                    template.save(reportFile);
                    OsUtil.openFile(reportFile);
                }
            }
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
