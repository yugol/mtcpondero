package pondero.engine.test;

import static pondero.Logger.error;
import java.awt.EventQueue;
import java.util.List;
import java.util.Stack;
import pondero.engine.elements.interfaces.HasFeedback.FeedbackStimulus;
import pondero.engine.elements.interfaces.HasScreencolor;
import pondero.engine.elements.interfaces.IsController;
import pondero.engine.elements.other.Block;
import pondero.engine.elements.trial.Trial;
import pondero.engine.test.launch.DefaultLauncher;
import pondero.engine.test.launch.TaskLauncher;
import pondero.engine.test.launch.TaskMonitor;
import pondero.engine.test.responses.Response;
import pondero.model.Workbook;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.TrialRecord;
import pondero.util.MsgUtil;

public abstract class Test extends TestRenderer implements IsController {

    private Workbook                  workbook;
    private Participant               participant;
    private TaskLauncher              launcher;
    private TaskMonitor               monitor;
    private TrialRecord               record;

    private final Stack<IsController> controllerStack = new Stack<IsController>();
    private Block                     currentBlock;

    public void closeRecord() {
        if (record != null) {
            monitor.add(record);
            record = null;
        }
    }

    public TrialRecord createRecord(final String runId) {
        final TrialRecord record = workbook.addTrialRecord(getTestId());
        record.setExperimentId(runId);
        return record;
    }

    @Override
    public void doBegin() {
        monitor = new TaskMonitor(String.valueOf(System.currentTimeMillis()));
        monitor.markStartTime();
        launcher.onTaskStarted(this);
        if (getExperiment() != null) {
            getExperiment().doBegin();
        } else if (blocks.size() > 0) {
            blocks.values().iterator().next().doBegin();
        } else if (trials.size() > 0) {
            trials.values().iterator().next().doBegin();
        }
    }

    @Override
    public void doEnd() {
        monitor.markStopTime(TaskMonitor.END_SUCCESS);
        getTestWindow().hideTestWindow();
        launcher.onTaskEnded(this, monitor);
    }

    @Override
    public void doStep(final Response input) {
        final IsController controller = peekController();
        if (controller == null) {
            doEnd();
        } else {
            controller.doStep(input);
        }
    }

    public List<String> getBgstim() {
        if (currentBlock != null) { return currentBlock._getBgstim(); }
        return null;
    }

    public FeedbackStimulus getCorrectmessage() {
        if (currentBlock != null) { return currentBlock._getCorrectmessage(); }
        return null;
    }

    public FeedbackStimulus getErrormessage() {
        if (currentBlock != null) { return currentBlock._getErrormessage(); }
        return null;
    }

    public void kill() {
        monitor.markStopTime(TaskMonitor.END_KILL);
        launcher.onTaskEnded(this, monitor);
    }

    public void openRecord(final Trial trial) {
        record = createRecord(monitor.getRunId());
        if (participant != null) {
            record.setParticipant(participant);
        }
        if (currentBlock != null) {
            record.setBlockId(currentBlock.$name());
        }
        if (trial != null) {
            record.setTrialId(trial.$name());
        }
    }

    public IsController peekController() {
        IsController controller = null;
        if (!controllerStack.isEmpty()) {
            controller = controllerStack.peek();
        }
        return controller;
    }

    public void popController() {
        final IsController controller = controllerStack.pop();
        if (controller instanceof Block) {
            currentBlock = null;
        }
        if (controller instanceof HasScreencolor) {
            popScreencolor();
        }
        doStep(null);
    }

    public void pushController(final IsController controller) {
        controllerStack.push(controller);
        if (controller instanceof Block) {
            currentBlock = (Block) controller;
        }
        if (controller instanceof HasScreencolor) {
            pushScreencolor((HasScreencolor) controller);
        }
    }

    public void recordCorrectResponse(final long time) {
        if (record != null) {
            record.setResponseTimestamp(time);
            record.setResponseCorrect(true);
        }
    }

    public void recordErrorResponse(final long time) {
        if (record != null) {
            record.setResponseTimestamp(time);
            record.setResponseCorrect(false);
        }
    }

    public void recordResponse(final String keyResponse) {
        if (record != null) {
            record.setResponse(keyResponse);
        }
    }

    @Override
    public void run() {
        try {
            pushScreencolor(getDefaults());
            getTestWindow().showTestWindow();
            doBegin();
            doStep(null);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(null, e);
            launcher.onTaskEnded(this, monitor);
        }
    }

    public void setParticipant(final Participant participant) {
        this.participant = participant;
    }

    public void setWorkbook(final Workbook workbook) {
        this.workbook = workbook;
    }

    public void start(final TaskLauncher launcher) {
        this.launcher = launcher == null ? new DefaultLauncher() : launcher;
        EventQueue.invokeLater(this);
    }

}
