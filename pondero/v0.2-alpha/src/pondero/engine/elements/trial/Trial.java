package pondero.engine.elements.trial;

import java.util.HashSet;
import java.util.Set;
import pondero.OsUtil;
import pondero.engine.elements.Element;
import pondero.engine.elements.interfaces.HasFeedback;
import pondero.engine.elements.interfaces.IsController;
import pondero.engine.elements.interfaces.IsStimulus;
import pondero.engine.elements.interfaces.IsVisualStimulus;
import pondero.engine.staples.Frame;
import pondero.engine.staples.FrameSequence;
import pondero.engine.staples.TimingUtil;
import pondero.engine.test.responses.KeyPressResponse;
import pondero.engine.test.responses.Response;
import pondero.engine.test.stimuli.Stimulus;
import pondero.engine.test.stimuli.VisualStimulus;
import pondero.ui.tests.TestCanvas;
import pondero.ui.tests.TestScene;

public class Trial extends Element implements HasFeedback, IsController {

    private class DoStatus {

        boolean stimulusPresented = false;

    }

    public static final String TYPENAME        = "trial";

    private final Set<String>  correctresponse = new HashSet<String>();
    private long               posttrialpause  = 0;
    private long               pretrialpause   = 0;
    private FrameSequence      stimulustimes   = new FrameSequence();
    private String             trialcode;
    private final Set<String>  validresponse   = new HashSet<String>();

    private DoStatus           doStatus;

    public Trial(final String name) {
        super(name);
        trialcode = name;
    }

    @Override
    public void _doBegin() {
        configureScene();
        test.resetStimuli();
        test.showScene();
        doStatus = new DoStatus();
        test.pushController(this);
        test.openRecord(this);
    }

    @Override
    public void _doEnd() {
        doStatus = null;
        test.closeRecord();
        test.popController();
    }

    @Override
    public void _doStep(final Response input) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                if (!doStatus.stimulusPresented) {
                    TimingUtil.pause(pretrialpause);
                    long prevFrameIndex = 0;
                    for (final Frame frame : stimulustimes) {
                        final long wait = frame.getIndex() - prevFrameIndex;
                        TimingUtil.pause(wait);
                        putVisualStimuli(frame);
                        prevFrameIndex = frame.getIndex();
                        test.presentStimuli();
                    }

                    doStatus.stimulusPresented = true;
                    return;
                }

                boolean passed = false;
                if (input != null) {
                    if (input instanceof KeyPressResponse) {
                        final String keyResponse = ((KeyPressResponse) input).getCharAsString();
                        if (validresponse.contains(keyResponse)) {
                            HasFeedback.FeedbackStimulus fb = null;
                            if (correctresponse.contains(keyResponse)) {
                                test.setCorrectResponse(input.getTime());
                                fb = test.getCorrectmessage();
                            } else {
                                test.setErrorResponse(input.getTime());
                                fb = test.getErrormessage();
                            }
                            test.setResponse(keyResponse);
                            passed = true;
                            if (fb != null) {
                                final IsStimulus eltStimulus = test.getStimulus(fb.getStimulusName());
                                Stimulus actualStimulus = null;
                                if (eltStimulus instanceof IsVisualStimulus) {
                                    actualStimulus = ((IsVisualStimulus) eltStimulus)._getStimulus();
                                    test.addVisualStimulus((VisualStimulus) actualStimulus);
                                }
                                test.presentStimuli();
                                TimingUtil.pause(fb.getDuration());
                                if (eltStimulus instanceof IsVisualStimulus) {
                                    test.removeVisualStimulus((VisualStimulus) actualStimulus);
                                }
                                test.presentStimuli();
                            }
                        }
                    }
                }

                if (passed) {
                    TimingUtil.pause(posttrialpause);
                    _doEnd();
                }
            }

        }).start();
    }

    @Override
    public FeedbackStimulus _getCorrectmessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FeedbackStimulus _getErrormessage() {
        // TODO Auto-generated method stub
        return null;
    }

    public String _getTrialcode() {
        return trialcode;
    }

    @Override
    public String $typename() {
        return TYPENAME;
    }

    @Override
    public void correctmessage(final boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void correctmessage(final String stimulusName, final long duration) {
        // TODO Auto-generated method stub

    }

    public void correctresponse(final String... correctresponse) {
        for (final String response : correctresponse) {
            this.correctresponse.add(response);
        }
    }

    @Override
    public void errormessage(final boolean flag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void errormessage(final String stimulusName, final long duration) {
        // TODO Auto-generated method stub

    }

    public void posttrialpause(final long posttrialpause) {
        this.posttrialpause = posttrialpause;
    }

    public void pretrialpause(final long pretrialpause) {
        this.pretrialpause = pretrialpause;
    }

    public void stimulusframes(final String expr) {
        stimulustimes = new FrameSequence(expr);
        for (final Frame frame : stimulustimes) {
            frame.setIndex(frame.getIndex() * OsUtil.getFrameRate());
        }
    }

    public void stimulustimes(final String expr) {
        stimulustimes = new FrameSequence(expr);
    }

    public void trialcode(final String trialcode) {
        this.trialcode = trialcode;
    }

    public void validresponse(final String... validresponse) {
        for (final String response : validresponse) {
            this.validresponse.add(response);
        }
    }

    private void putVisualStimuli(final Frame frame) {
        test.resetVisualStimuli();
        if (test.getBgstim() != null) {
            for (final String name : test.getBgstim()) {
                final IsStimulus stimulus = test.getStimulus(name);
                if (stimulus instanceof IsVisualStimulus) {
                    test.addVisualStimulus(((IsVisualStimulus) stimulus)._getStimulus());
                }
            }
        }
        for (final String name : frame.getContent()) {
            final IsStimulus stimulus = test.getStimulus(name);
            if (stimulus instanceof IsVisualStimulus) {
                test.addVisualStimulus(((IsVisualStimulus) stimulus)._getStimulus());
            }
        }
    }

    protected void configureScene() {
        final TestScene scene = _getTest().getTestWindow().getScene();
        scene.setNorth(null);
        scene.setSouth(null);
        scene.setEast(null);
        scene.setWest(null);
        if (!(scene.getCenter() instanceof TestCanvas)) {
            scene.setCenter(new TestCanvas(_getTest()));
        }
    }

}
