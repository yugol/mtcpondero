package pondero.tests.elements.workflow;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pondero.Context;
import pondero.task.responses.KeyPressResponse;
import pondero.task.responses.Response;
import pondero.tests.elements.Element;
import pondero.tests.interfaces.HasFeedback;
import pondero.tests.staples.Frame;
import pondero.tests.staples.FrameSequence;
import pondero.ui.testing.components.DrawableComponent;

public class Trial extends Element implements HasFeedback {

    public static class TrialLayout {

        private String north;
        private String west;
        private String center;
        private String east;
        private String south;

        public String getCenter() {
            return center;
        }

        public String getEast() {
            return east;
        }

        public String getNorth() {
            return north;
        }

        public String getSouth() {
            return south;
        }

        public String getWest() {
            return west;
        }

        public void setCenter(final String center) {
            this.center = center;
        }

        public void setEast(final String east) {
            this.east = east;
        }

        public void setNorth(final String north) {
            this.north = north;
        }

        public void setSouth(final String south) {
            this.south = south;
        }

        public void setWest(final String west) {
            this.west = west;
        }

    }

    public static final String TYPENAME         = "trial";

    private boolean            killerUserInput  = true;
    private final Set<String>  correctResponses = new HashSet<String>();
    private long               postTrialPause   = 0;
    private long               preTrialPause    = 0;
    private int                timeout          = 0;
    private FrameSequence      stimulusTimes    = new FrameSequence();
    private String             trialCode;
    private final Set<String>  validResponses   = new HashSet<String>();
    private final TrialLayout  layout           = new TrialLayout();

    public Trial(final String name) {
        super(name);
        trialCode = name;
        getLayout().setCenter(DrawableComponent.class.getName());
    }

    public String buildRecordedResponse(final List<Response> input) {
        if (input.size() > 0) {
            final Response participantInput = input.get(0);
            return participantInput.toRecordedResponseString();
        }
        return "";
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

    public TrialLayout getLayout() {
        return layout;
    }

    public long getPostTrialPause() {
        return postTrialPause;
    }

    public long getPreTrialPause() {
        return preTrialPause;
    }

    public Long getResponseTime(final List<Response> input) {
        if (input.size() > 0) { return input.get(0).getTime(); }
        return 0L;
    }

    public FrameSequence getStimulusTimes() {
        return stimulusTimes;
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

    public Set<String> getValidResponses() {
        return validResponses;
    }

    public boolean isCorrectResponse(final List<Response> input) {
        if (input.size() > 0) {
            final Response participantInput = input.get(0);
            if (participantInput instanceof KeyPressResponse) {
                final KeyPressResponse keyResponse = (KeyPressResponse) participantInput;
                return correctResponses.contains(keyResponse.getCharAsString());
            }
        }
        return false;
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

}
