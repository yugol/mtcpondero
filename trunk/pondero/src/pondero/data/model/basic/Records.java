package pondero.data.model.basic;

import pondero.data.model.PModel;
import pondero.data.model.PSheet;
import pondero.data.model.PType;

public class Records extends PSheet {

    public static final String ATTR_PARTICIPANT_SURNAME = "PART_SURNAME";
    public static final String ATTR_PARTICIPANT_NAME    = "PART_NAME";
    public static final String ATTR_PARTICIPANT_ID      = "PART_ID";
    public static final String ATTR_EXPERIMNT_ID        = "PROBE_ID";
    public static final String ATTR_BLOCK_ID            = "BLOCK_ID";
    public static final String ATTR_TRIAL_ID            = "TRIAL_ID";
    public static final String ATTR_TRIAL_TIMESTAMP     = "TRIAL_DATETIME";
    public static final String ATTR_RESPONSE            = "RESPONSE";
    public static final String ATTR_RESPONSE_TIME       = "RESPONSE_TIME";
    public static final String ATTR_RESPONSE_CORRECT    = "RESPONSE_CORRECT";

    Records(final PModel model, final String name) throws Exception {
        super(model, name);
        addColumn(ATTR_EXPERIMNT_ID, PType.STRING);
        addColumn(ATTR_PARTICIPANT_ID, PType.STRING);
        addColumn(ATTR_PARTICIPANT_SURNAME, PType.STRING);
        addColumn(ATTR_PARTICIPANT_NAME, PType.STRING);
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

    @Override
    public TrialRecord getRow(final int index) {
        return (TrialRecord) super.getRow(index);
    }

}
