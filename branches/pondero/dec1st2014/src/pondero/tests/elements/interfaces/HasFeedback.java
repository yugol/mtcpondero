package pondero.tests.elements.interfaces;

public interface HasFeedback {

    // TODO <block> <expt> <likert> <openended> <trial>

    class FeedbackStimulus {

        private final String stimulusName;
        private final long   duration;

        public FeedbackStimulus(final String stimulusName, final long duration) {
            this.stimulusName = stimulusName;
            this.duration = duration;
        }

        public long getDuration() {
            return duration;
        }

        public String getStimulusName() {
            return stimulusName;
        }

    }

    public FeedbackStimulus getCorrectMessage();

    public FeedbackStimulus getErrorMessage();

    public void setCorrectMessage(boolean flag);

    public void setCorrectMessage(String stimulusName, long duration);

    public void setErrorMessage(boolean flag);

    public void setErrorMessage(String stimulusName, long duration);

}
