package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.trace;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import pondero.L10n;
import pondero.engine.test.Test;
import pondero.engine.test.launch.TaskLauncher;
import pondero.engine.test.launch.TaskMonitor;
import pondero.model.entities.TrialRecord;
import pondero.ui.Ponderable;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class StartTaskAction extends PonderoAction implements TaskLauncher {

    public StartTaskAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.start"));
        putValue(SHORT_DESCRIPTION, L10n.getString("msg.start-test"));
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Test task = getApp().getCurrentTask();
        task.setParticipant(getApp().getCurrentParticipant());
        task.start(this);
    }

    @Override
    public void onTaskEnded(final Test task, final TaskMonitor report) {
        trace("ended task: ", task.getCodeName());
        final StringBuilder html = new StringBuilder("<html>");
        if (TaskMonitor.END_SUCCESS == report.getEndCode()) {
            html.append(L10n.getString("msg.test-completed", report.getRunningTimeInSeconds()));
        } else {
            html.append(L10n.getString("msg.test-interrupted", report.getEndCode()));
        }
        html.append("<br/>");
        html.append(L10n.getString("msg.save-responses-?"));
        html.append("</html>");
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(getFrame(),
                html, L10n.getString("lbl.pondero"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE)) {
            try {
                for (final TrialRecord record : report.getRecords()) {
                    getApp().getCurrentWorkbook().add(record);
                }
            } catch (final Exception e) {
                error(e);
                MsgUtil.showExceptionMessage(getFrame(), e);
            }
        }
        getFrame().setVisible(true);
    }

    @Override
    public void onTaskStarted(final Test task) {
        trace("started task: ", task.getCodeName());
        getFrame().setVisible(false);
    }

}