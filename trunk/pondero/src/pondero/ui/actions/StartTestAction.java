package pondero.ui.actions;

import static pondero.Logger.action;
import static pondero.Logger.trace;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.task.launch.TaskLauncher;
import pondero.task.launch.TaskMonitor;
import pondero.tests.Test;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;

@SuppressWarnings("serial")
public class StartTestAction extends PonderableAction implements TaskLauncher {

    public StartTestAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.start"));
        putValue(SHORT_DESCRIPTION, L10n.getString("msg.start-test"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/application_go.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        action("running test ", getCurrentTask().getCodeName(), " on ", getCurrentParticipant().getId());
        final Test test = getCurrentTask();
        test.setWorkbook(getCurrentWorkbook());
        test.setParticipant(getCurrentParticipant());
        test.start(this);
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
                action("cancelling test results");
                getCurrentWorkbook().removeRecords(task.getTestId(), report.getRecords());
            } else {
                action("retaining test results");
            }
            getMainFrame().setVisible(true);
        } catch (final Exception e) {
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

    @Override
    public void onTaskStarted(final Test task) {
        trace("started task: ", task.getCodeName());
        getMainFrame().setVisible(false);
    }

}
