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

    public FeedbackStimulus _getCorrectmessage();

    public FeedbackStimulus _getErrormessage();

    public void correctmessage(boolean flag);

    public void correctmessage(String stimulusName, long duration);

    public void errormessage(boolean flag);

    public void errormessage(String stimulusName, long duration);

}
