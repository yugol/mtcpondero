package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.tests.test.Test;
import pondero.ui.Ponderable;
import pondero.ui.tests.TestSelectionDialog;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class AnalyseTestAction extends PonderableAction {

    public AnalyseTestAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.test"));
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
                    info(test.getCodeName());
                }
            }
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
