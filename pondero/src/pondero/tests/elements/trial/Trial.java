package pondero.tests.elements.trial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.Timer;
import pondero.Context;
import pondero.Logger;
import pondero.task.responses.KeyPressResponse;
import pondero.task.responses.KillResponse;
import pondero.task.responses.LikertResponse;
import pondero.task.responses.MouseClickResponse;
import pondero.task.responses.Response;
import pondero.task.stimuli.Stimulus;
import pondero.task.stimuli.VisualStimulus;
import pondero.tests.Test;
import pondero.tests.elements.Element;
import pondero.tests.elements.interfaces.HasFeedback;
import pondero.tests.elements.interfaces.IsAuditoryStimulus;
import pondero.tests.elements.interfaces.IsController;
import pondero.tests.elements.interfaces.IsStimulus;
import pondero.tests.elements.interfaces.IsVisualStimulus;
import pondero.tests.staples.Frame;
import pondero.tests.staples.FrameSequence;
import pondero.tests.staples.Timing;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.components.TestDrawableComponent;

public class Trial extends Element implements HasFeedback, IsController {

    private class DoStatus {

        Thread stimulusPresenter = null;
        Timer  trialTimer        = null;

        void kill() {
            if (trialTimer != null) {
                trialTimer.stop();
                trialTimer = null;
            }
            if (stimulusPresenter != null) {
                stimulusPresenter.interrupt();
                stimulusPresenter = null;
            }
        }

    }

    public static final String   TYPENAME         = "trial";

    private boolean              killerUserInput  = true;
    private final Set<String>    correctResponses = new HashSet<String>();
    private long                 postTrialPause   = 0;
    private long                 preTrialPause    = 0;
    private int                  timeout          = 0;
    private FrameSequence        stimulusTimes    = new FrameSequence();
    private String               trialCode;
    private final Set<String>    validResponses   = new HashSet<String>();
    private final List<Response> responses        = new ArrayList<Response>();

    private DoStatus             doStatus;

    public Trial(final String name) {
        super(name);
        trialCode = name;
    }

    @Override
    public void doBegin() throws Exception {
        Logger.trace("Trial: " + getName());
        configureScene();
        test.resetStimuli();
        test.showScene();
        doStatus = new DoStatus();
        test.pushController(this);
        test.openRecord(this);
        responses.clear();
    }

    @Override
    public void doEnd() throws Exception {
        doStatus = null;
        test.closeRecord();
        test.popController();
    }

    @Override
    public void doNext(final Response input) throws Exception {
        if (doStatus.stimulusPresenter == null) {
            doStatus.stimulusPresenter = new Thread(new Runnable() {

                @Override
                public void run() {
                    Timing.pause(preTrialPause);
                    if (timeout > 0) {
                        doStatus.trialTimer = new Timer(timeout, new ActionListener() {

                            @Override
                            public void actionPerformed(final ActionEvent e) {
                                try {
                                    Trial.this.doNext(new KillResponse());
                                } catch (final Exception ex) {
                                    ExceptionReporting.showExceptionMessage(null, ex);
                                }
                            }

                        });
                        doStatus.trialTimer.start();
                    }
                    long prevTimeIndex = 0;
                    for (final Frame frame : stimulusTimes) {
                        final long wait = frame.getIndex() - prevTimeIndex;
                        Timing.pause(wait);
                        prepareStimuli(frame);
                        prevTimeIndex = frame.getIndex();
                        test.presentStimuli();
                    }
                }

            });
            doStatus.stimulusPresenter.start();
        }

        boolean completed = false;

        if (input != null) {

            if (input instanceof KeyPressResponse) {
                final String keyResponse = ((KeyPressResponse) input).getCharAsString();
                if (validResponses.contains(keyResponse)) {
                    responses.add(input);
                    completed = killerUserInput;
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
                completed = killerUserInput;
            }

        }

        if (completed) {
            final HasFeedback.FeedbackStimulus fb = evaluateResponse();
            if (fb != null) {
                final IsStimulus eltStimulus = test.getStimulus(fb.getStimulusName());
                Stimulus actualStimulus = null;
                if (eltStimulus instanceof IsVisualStimulus) {
                    actualStimulus = ((IsVisualStimulus) eltStimulus).getStimulus();
                    test.addVisualStimulus((VisualStimulus) actualStimulus);
                }
                test.presentStimuli();
                Timing.pause(fb.getDuration());
                if (eltStimulus instanceof IsVisualStimulus) {
                    test.removeVisualStimulus((VisualStimulus) actualStimulus);
                }
                test.presentStimuli();
            }

            Timing.pause(postTrialPause);
            doStatus.kill();
            doEnd();
        }

    }

    @Override
    public FeedbackStimulus getCorrectMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    public Set<String> getCorrectResponses() {
        return correctResponses;
    }

    @Override
    public FeedbackStimulus getErrorMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getTrialCode() {
        return trialCode;
    }

    @Override
    public String getTypeName() {
        return TYPENAME;
    }

    public boolean isKillerUserInput() {
        return killerUserInput;
    }

    public void setCorrectAndValidResponses(final String... response) {
        setCorrectResponses(response);
        setValidResponses(response);
    }

    @Override
    public void setCorrectMessage(final boolean flag) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setCorrectMessage(final String stimulusName, final long duration) {
        // TODO Auto-generated method stub
    }

    public void setCorrectResponses(final String... correctresponse) {
        for (final String response : correctresponse) {
            correctResponses.add(response);
        }
    }

    @Override
    public void setErrorMessage(final boolean flag) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setErrorMessage(final String stimulusName, final long duration) {
        // TODO Auto-generated method stub
    }

    public void setKillerUserInput(final boolean killerUserInput) {
        this.killerUserInput = killerUserInput;
    }

    public void setPostTrialPause(final long posttrialpause) {
        postTrialPause = posttrialpause;
    }

    public void setPreTrialPause(final long pretrialpause) {
        preTrialPause = pretrialpause;
    }

    public void setStimulusFrames(final String expr) {
        stimulusTimes = new FrameSequence(expr);
        for (final Frame frame : stimulusTimes) {
            frame.setIndex(frame.getIndex() * Context.getFrameRate());
        }
    }

    public void setStimulusTimes(final String expr) {
        stimulusTimes = new FrameSequence(expr);
    }

    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }

    public void setTrialCode(final String trialcode) {
        trialCode = trialcode;
    }

    public void setValidResponses(final String... validresponse) {
        for (final String response : validresponse) {
            validResponses.add(response);
        }
    }

    private HasFeedback.FeedbackStimulus evaluateResponse() throws Exception {
        HasFeedback.FeedbackStimulus fb = null;
        final Long responseTime = getResponseTime(responses);
        if (isCorrectResponse(responses)) {
            test.recordCorrectResponse(responseTime);
            fb = test.getCorrectmessage();
        } else {
            test.recordErrorResponse(responseTime);
            fb = test.getErrormessage();
        }
        test.recordResponse(buildRecordedResponse(responses));
        return fb;
    }

    private void prepareStimuli(final Frame frame) {
        test.resetStimuli();
        if (test.getBgstim() != null) {
            for (final String name : test.getBgstim()) {
                final IsStimulus stimulus = test.getStimulus(name);
                if (stimulus instanceof IsVisualStimulus) {
                    test.addVisualStimulus(((IsVisualStimulus) stimulus).getStimulus());
                }
            }
        }
        for (final String name : frame.getContent()) {
            final IsStimulus stimulus = test.getStimulus(name);
            if (stimulus instanceof IsVisualStimulus) {
                test.addVisualStimulus(((IsVisualStimulus) stimulus).getStimulus());
            } else if (stimulus instanceof IsAuditoryStimulus) {
                test.addAuditoryStimulus(((IsAuditoryStimulus) stimulus).getStimulus());
            }
        }
    }

    protected String buildRecordedResponse(final List<Response> input) {
        final Response participantInput = input.get(0);
        return participantInput.toRecordedResponseString();
    }

    protected void configureScene() {
        final Test test = getTest();
        final TestScene scene = test.getTestWindow().getScene();
        if (!(scene.getCenter() instanceof TestDrawableComponent)) {
            scene.setCenter(new TestDrawableComponent(test));
        }
        scene.setNorth(null);
        scene.setEast(null);
        scene.setWest(null);
        scene.setSouth(null);
    }

    protected Long getResponseTime(final List<Response> input) {
        return input.get(0).getTime();
    }

    protected boolean isCorrectResponse(final List<Response> input) {
        final Response participantInput = input.get(0);
        if (participantInput instanceof KeyPressResponse) {
            final KeyPressResponse keyResponse = (KeyPressResponse) participantInput;
            return correctResponses.contains(keyResponse.getCharAsString());
        }
        return false;
    }

}
