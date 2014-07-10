package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.trace;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.tests.test.Test;
import pondero.tests.test.launch.TaskLauncher;
import pondero.tests.test.launch.TaskMonitor;
import pondero.ui.Ponderable;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class RunTaskAction extends PonderableAction implements TaskLauncher {

    public RunTaskAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.start"));
        putValue(SHORT_DESCRIPTION, L10n.getString("msg.start-test"));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Test task = getCurrentTask();
        task.setWorkbook(getCurrentWorkbook());
        task.setParticipant(getCurrentParticipant());
        task.start(this);
    }

    @Override
    public void onTaskEnded(final Test task, final TaskMonitor report) {
        trace("ended task: ", task.getCodeName());
        try {
            final StringBuilder html = new StringBuilder("<html>");
            if (TaskMonitor.END_SUCCESS == report.getEndCode()) {
                html.append(L10n.getString("msg.test-completed", report.getRunningTimeInSeconds()));
            } else {
                html.append(L10n.getString("msg.test-interrupted", report.getEndCode()));
            }
            html.append("<br/>");
            html.append(L10n.getString("msg.save-responses-?"));
            html.append("</html>");
            final int decision = JOptionPane.showConfirmDialog(getMainFrame(),
                    html, L10n.getString("lbl.pondero"),
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (JOptionPane.NO_OPTION == decision) {
                getCurrentWorkbook().removeRecords(task.getTestId(), report.getRecords());
            }
            getMainFrame().setVisible(true);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

    @Override
    public void onTaskStarted(final Test task) {
        trace("started task: ", task.getCodeName());
        getMainFrame().setVisible(false);
    }

}
