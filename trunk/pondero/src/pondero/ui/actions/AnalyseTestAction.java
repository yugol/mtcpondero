package pondero.ui.actions;

import static pondero.Logger.action;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.Context;
import pondero.L10n;
import pondero.data.analysis.FillTestReport;
import pondero.data.drivers.excel.templates.test.TestTemplate;
import pondero.tests.Test;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.tests.TestSelectionDialog;
import pondero.util.ExcelFileFilter;
import pondero.util.OsUtil;

@SuppressWarnings("serial")
public class AnalyseTestAction extends PonderableAction {

    public AnalyseTestAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.test..."));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/application_go.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final TestSelectionDialog dlg = new TestSelectionDialog(getMainFrame());
            dlg.setLocationRelativeTo(getMainFrame());
            dlg.setModal(true);
            dlg.setVisible(true);
            if (dlg.getCloseOperation() == JOptionPane.YES_OPTION) {
                final Test test = dlg.getSelection();
                if (test != null) {
                    action("performing analysis of test ", test.getCodeName());
                    final TestTemplate template = new TestTemplate();
                    new FillTestReport().fill(template, test, getCurrentWorkbook().getModel());
                    final String reportFileName = TestTemplate.BASE_NAME + "-" + System.currentTimeMillis() + ExcelFileFilter.DEFAULT_EXTENSION;
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
