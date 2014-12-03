package pondero.task.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import pondero.data.model.basic.TrialRecord;
import pondero.task.Task;
import pondero.task.responses.KeyPressResponse;
import pondero.task.responses.KillResponse;
import pondero.task.responses.LikertResponse;
import pondero.task.responses.MouseClickResponse;
import pondero.task.responses.Response;
import pondero.tests.Test;
import pondero.tests.elements.workflow.Trial;
import pondero.tests.interfaces.HasFeedback;
import pondero.tests.interfaces.IsStimulus;
import pondero.tests.staples.Frame;
import pondero.ui.exceptions.ExceptionReporting;

public class TrialController extends TaskController {

    private final List<StimulusFrame> frames    = new ArrayList<>();
    private final List<Response>      responses = new ArrayList<>();
    private Thread                    stimulusPresenter;
    private Timer                     trialTimer;
    private TrialRecord               record;

    public TrialController(final Task task, final Trial trial) {
        super(task, trial);
    }

    @Override
    public void doBegin() throws Exception {
        frames.clear();
        final Test test = getTask().getTest();
        if (getBgstim() != null) {
            final StimulusFrame frame = new StimulusFrame(0);
            for (final String name : getBgstim()) {
                final IsStimulus stimulus = test.getStimulus(name);
                frame.addStimulus(stimulus.getStimulus());
            }
            frames.add(frame);
        }
        for (final Frame timeFrame : getElement().getStimulusTimes()) {
            final StimulusFrame frame = new StimulusFrame(timeFrame.getIndex());
            if (getBgstim() != null) {
                for (final String name : getBgstim()) {
                    final IsStimulus stimulus = test.getStimulus(name);
                    frame.addStimulus(stimulus.getStimulus());
                }
            }
            for (final String name : timeFrame.getContent()) {
                final IsStimulus stimulus = test.getStimulus(name);
                frame.addStimulus(stimulus.getStimulus());
            }
            frames.add(frame);
        }

        if (record == null && getTask().getWorkbook() != null) {
            record = getTask().getWorkbook().addTrialRecord(getTask().getTest().getDescriptor().getId());
            record.setExperimentId(getTask().getData().getRunId());
            record.setParticipant(getTask().getParticipant());
            record.setBlockId(getParent().getElement().getName());
            record.setTrialId(getElement().getName());
        }
        if (record != null) {
            record.setTrialTimestamp(System.currentTimeMillis());
        }

        responses.clear();
        stimulusPresenter = null;
        trialTimer = null;

        getTask().showScene(this);
    }

    @Override
    public void doEnd() throws Exception {
        if (trialTimer != null) {
            trialTimer.stop();
            trialTimer = null;
        }
        if (stimulusPresenter != null) {
            stimulusPresenter.interrupt();
            stimulusPresenter = null;
        }
    }

    @Override
    public void doStep(final Response input) throws Exception {
        Timing.pause(getElement().getPreTrialPause());

        if (stimulusPresenter == null) {
            stimulusPresenter = new Thread(new Runnable() {

                @Override
                public void run() {
                    if (getElement().getTimeout() > 0) {
                        trialTimer = new Timer(getElement().getTimeout(), new ActionListener() {

                            @Override
                            public void actionPerformed(final ActionEvent e) {
                                try {
                                    TrialController.this.doStep(new KillResponse());
                                } catch (final Exception ex) {
                                    ExceptionReporting.showExceptionMessage(null, ex);
                                }
                            }

                        });
                        trialTimer.start();
                    }
                    long prevTimeIndex = 0;
                    for (final StimulusFrame frame : frames) {
                        final long wait = frame.getTimeIndex() - prevTimeIndex;
                        Timing.pause(wait);
                        prevTimeIndex = frame.getTimeIndex();
                        getTask().presentStimuli(frame);
                    }
                }

            });
            stimulusPresenter.start();
        }

        boolean completed = false;
        final boolean goForward = true;

        if (input != null) {
            if (input instanceof KeyPressResponse) {
                final String keyResponse = ((KeyPressResponse) input).getCharAsString();
                if (getElement().getValidResponses().contains(keyResponse)) {
                    responses.add(input);
                    completed = getElement().isKillerUserInput();
                }
            } else if (input instanceof LikertResponse) {
                responses.add(input);
                completed = true;
            } else if (input instanceof MouseClickResponse) {
                // ignore for now
            } else if (input instanceof KillResponse) {
                responses.add(input);
                completed = true;
            } else {
                responses.add(input);
                completed = getElement().isKillerUserInput();
            }
        }

        if (completed) {
            final HasFeedback.FeedbackStimulus fb = evaluateResponse();
            if (fb != null) {
//                final IsStimulus eltStimulus = test.getStimulus(fb.getStimulusName());
//                Stimulus actualStimulus = null;
//                if (eltStimulus instanceof IsVisualStimulus) {
//                    actualStimulus = ((IsVisualStimulus) eltStimulus).getStimulus();
//                    test.addVisualStimulus((VisualStimulus) actualStimulus);
//                }
//                test.presentStimuli();
//                Timing.pause(fb.getDuration());
//                if (eltStimulus instanceof IsVisualStimulus) {
//                    test.removeVisualStimulus((VisualStimulus) actualStimulus);
//                }
//                test.presentStimuli();
            }

            Timing.pause(getElement().getPostTrialPause());
            doEnd();
            if (goForward) {
                getTask().goNext();
            }
        }
    }

    @Override
    public Trial getElement() {
        return (Trial) super.getElement();
    }

    public TrialRecord getRecord() {
        return record;
    }

    private HasFeedback.FeedbackStimulus evaluateResponse() throws Exception {
        final boolean correct = getElement().isCorrectResponse(responses);
        if (record != null) {
            record.setResponse(getElement().buildRecordedResponse(responses));
            record.setResponseTimestamp(getElement().getResponseTime(responses));
            record.setResponseCorrect(correct);
        }
        return correct ? getCorrectMessage() : getErrorMessage();
    }

}
