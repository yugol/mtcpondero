package pondero.engine.test;

import java.util.List;
import java.util.Stack;
import pondero.Globals;
import pondero.engine.elements.interfaces.HasFeedback.FeedbackStimulus;
import pondero.engine.elements.interfaces.HasScreencolor;
import pondero.engine.elements.interfaces.IsController;
import pondero.engine.elements.other.Block;
import pondero.engine.elements.trial.Trial;
import pondero.engine.test.launch.TestReport;
import pondero.engine.test.responses.Response;
import pondero.model.entities.TrialRecord;

public abstract class Test extends TestRenderer implements IsController {

    private final Stack<IsController> controllerStack = new Stack<IsController>();
    private Block                     currentBlock;

    @Override
    public void _doBegin() {
        startTimer();
        getLauncher().onTaskStarted(this);
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
        stopTimer();
        hideTestWindow();
        final TestReport report = new TestReport();
        report.setEndCode(TestReport.END_SUCCESS);
        report.setStartTime(getStartTime());
        report.setStopTime(getStopTime());
        if (workbook != null && Globals.isAutoSaveExperimentData()) {
            try {
                workbook.save();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        getLauncher().onTaskEnded(this, report);
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

    public void closeRecord() {
        if (record != null) {
            if (workbook != null) {
                try {
                    workbook.add(record);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
            record = null;
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
        final TestReport report = new TestReport();
        report.setEndCode(TestReport.END_KILL);
        getLauncher().onTaskEnded(this, report);
    }

    public void openRecord(final Trial trial) {
        record = new TrialRecord(getId());
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

    @Override
    public void run() {
        try {
            pushScreencolor(getDefaults());
            showTestWindow();
            _doBegin();
            _doStep(null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void setCorrectResponse(final long time) {
        if (record != null) {
            record.setResponseTime(time);
            record.setResponseCorrect(true);
        }
    }

    public void setErrorResponse(final long time) {
        if (record != null) {
            record.setResponseTime(time);
            record.setResponseCorrect(false);
        }
    }

    public void setResponse(final String keyResponse) {
        if (record != null) {
            record.setResponse(keyResponse);
        }
    }

}
