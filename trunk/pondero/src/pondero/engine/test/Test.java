package pondero.engine.test;

import static pondero.Logger.error;
import java.awt.EventQueue;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import pondero.MsgUtil;
import pondero.engine.elements.interfaces.HasFeedback.FeedbackStimulus;
import pondero.engine.elements.interfaces.HasScreencolor;
import pondero.engine.elements.interfaces.IsController;
import pondero.engine.elements.other.Block;
import pondero.engine.elements.trial.Trial;
import pondero.engine.test.launch.DefaultLauncher;
import pondero.engine.test.launch.TaskLauncher;
import pondero.engine.test.launch.TaskMonitor;
import pondero.engine.test.responses.Response;
import pondero.model.entities.TrialRecord;
import pondero.model.participants.Participant;

public abstract class Test extends TestRenderer implements IsController {

    private Participant               participant;
    private TaskLauncher              launcher;
    private TaskMonitor               monitor;
    private TrialRecord               record;

    private final Stack<IsController> controllerStack = new Stack<IsController>();
    private Block                     currentBlock;

    @Override
    public void _doBegin() {
        monitor = new TaskMonitor(UUID.randomUUID().toString().replace("-", ""));
        monitor.markStartTime();
        launcher.onTaskStarted(this);
        if (getExperiment() != null) {
            getExperiment()._doBegin();
        } else if (blocks.size() > 0) {
            blocks.values().iterator().next()._doBegin();
        } else if (trials.size() > 0) {
            trials.values().iterator().next()._doBegin();
        }
    }

    @Override
    public void _doEnd() {
        monitor.markStopTime(TaskMonitor.END_SUCCESS);
        getTestWindow().hideTestWindow();
        launcher.onTaskEnded(this, monitor);
    }

    @Override
    public void _doStep(final Response input) {
        final IsController controller = peekController();
        if (controller == null) {
            _doEnd();
        } else {
            controller._doStep(input);
        }
    }

    public void _recordClose() {
        if (record != null) {
            monitor.add(record);
            record = null;
        }
    }

    public TrialRecord _recordCreate(String runId) {
        return new TrialRecord(getTestId(), runId);
    }

    public void _recordOpen(final Trial trial) {
        record = _recordCreate(monitor.getRunId());
        if (participant != null) {
            record.setParticipant(participant);
        }
        if (currentBlock != null) {
            record.setBlockName(currentBlock.$name());
        }
        if (trial != null) {
            record.setTrialName(trial.$name());
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
        _doStep(null);
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
            record.setResponseTime(time);
            record.setResponseCorrect(true);
        }
    }

    public void recordErrorResponse(final long time) {
        if (record != null) {
            record.setResponseTime(time);
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
            _doBegin();
            _doStep(null);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(null, e);
            launcher.onTaskEnded(this, monitor);
        }
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void start(TaskLauncher launcher) {
        this.launcher = launcher == null ? new DefaultLauncher() : launcher;
        EventQueue.invokeLater(this);
    }

}
