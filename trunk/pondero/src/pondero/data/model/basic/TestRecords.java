package pondero.data.model.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pondero.data.model.PModel;
import pondero.data.model.PSheet;
import pondero.data.model.PType;

public class TestRecords extends PSheet {

    public static final String ATTR_BLOCK_ID            = "BLOCK_ID";
    public static final String ATTR_EXPERIMNT_TIMESTAMP = "PROBE_TIMESTAMP";
    public static final String ATTR_PARTICIPANT_ID      = "PART_ID";
    public static final String ATTR_RESPONSE            = "RESPONSE";
    public static final String ATTR_RESPONSE_CORRECT    = "RESPONSE_CORRECT";
    public static final String ATTR_RESPONSE_TIME       = "RESPONSE_TIME";
    public static final String ATTR_TRIAL_ID            = "TRIAL_ID";
    public static final String ATTR_TRIAL_TIMESTAMP     = "TRIAL_DATETIME";

    TestRecords(final PModel model, final String name) throws Exception {
        super(model, name);
        addColumn(ATTR_EXPERIMNT_TIMESTAMP, PType.INT);
        addColumn(ATTR_PARTICIPANT_ID, PType.STRING);
        addColumn(ATTR_BLOCK_ID, PType.STRING);
        addColumn(ATTR_TRIAL_ID, PType.STRING);
        addColumn(ATTR_TRIAL_TIMESTAMP, PType.TIMESTAMP);
        addColumn(ATTR_RESPONSE, PType.STRING);
        addColumn(ATTR_RESPONSE_TIME, PType.DECIMAL);
        addColumn(ATTR_RESPONSE_CORRECT, PType.BOOLEAN);
        lock();
    }

    @Override
    public TrialRecord addRow() throws Exception {
        return (TrialRecord) addRow(new TrialRecord(this));
    }

    public TestInstance getInstance(final String participantId, final long testTime) {
        final TestInstance instance = new TestInstance(getName(), participantId, testTime);
        for (int i = 0; i < getRowCount(); ++i) {
            final TrialRecord record = getRow(i);
            if (testTime == record.getExperimentTimestamp()) {
                if (participantId.equals(record.getParticipantId())) {
                    instance.add(record);
                }
            }
        }
        return instance;
    }

    @Override
    public TrialRecord getRow(final int index) {
        return (TrialRecord) super.getRow(index);
    }

    public List<Long> getTestTimes(final String participantId) {
        final Set<Long> instanceSet = new HashSet<>();
        for (int i = 0; i < getRowCount(); ++i) {
            final TrialRecord record = getRow(i);
            if (participantId.equals(record.getParticipantId())) {
                instanceSet.add(record.getExperimentTimestamp());
            }
        }
        final List<Long> instanceList = new ArrayList<>(instanceSet);
        Collections.sort(instanceList);
        return instanceList;
    }

}
