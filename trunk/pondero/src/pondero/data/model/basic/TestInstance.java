package pondero.data.model.basic;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class TestInstance extends ArrayList<TrialRecord> {

    private final String testName;
    private final String participantId;
    private final long   testTime;

    public TestInstance(final String testName, final String participantId, final Long testTime) {
        this.testName = testName;
        this.participantId = participantId;
        this.testTime = testTime;
    }

    public String getParticipantId() {
        return participantId;
    }

    public String getTestName() {
        return testName;
    }

    public Long getTestTime() {
        return testTime;
    }

    @Override
    public String toString() {
        return "TestInstance [testName=" + testName + ", participantId=" + participantId + ", testTime=" + testTime + "]";
    }

}
