package pondero.data.analysis;

import java.util.HashMap;
import java.util.Map;
import pondero.data.analysis.PMatrix.MRow;
import pondero.data.model.PType;
import pondero.data.model.basic.TrialRecord;

public abstract class ReportFilling {

    public static final String KEY_NAME            = "CHEIE";
    public static final String PARTICIPANT_ID_NAME = "PARTICIPANT";

    protected PMatrix          responseMatrix;
    private Map<Long, MRow>    responseMatrixKeys;
    protected PMatrix          timeMatrix;
    private Map<Long, MRow>    timeMatrixKeys;

    protected String getDataColumName(final TrialRecord record) {
        return new StringBuilder(record.getBlockId()).append(".").append(record.getTrialId()).toString();
    }

    protected MRow getResponseMatrixRow(final Long key) throws Exception {
        MRow row = responseMatrixKeys.get(key);
        if (row == null) {
            row = responseMatrix.addRow();
            final int colIdx = responseMatrix.index(KEY_NAME, PType.INT);
            row.set(colIdx, key);
            responseMatrixKeys.put(key, row);
        }
        return row;
    }

    protected MRow getTimeMatrixRow(final Long key) throws Exception {
        MRow row = timeMatrixKeys.get(key);
        if (row == null) {
            row = timeMatrix.addRow();
            final int colIdx = timeMatrix.index(KEY_NAME, PType.INT);
            row.set(colIdx, key);
            timeMatrixKeys.put(key, row);
        }
        return row;
    }

    protected void reset(final String name) {
        responseMatrix = new PMatrix(name);
        responseMatrixKeys = new HashMap<Long, MRow>();
        timeMatrix = new PMatrix(name);
        timeMatrixKeys = new HashMap<Long, MRow>();
    }

}
