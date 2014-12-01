package pondero.data.analysis;

import pondero.Constants;
import pondero.data.analysis.PMatrix.MRow;
import pondero.data.drivers.excel.templates.test.TestTemplate;
import pondero.data.model.PSheet;
import pondero.data.model.PType;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.TestRecords;
import pondero.data.model.basic.TrialRecord;
import pondero.tests.test.Test;

public class FillTestReport extends ReportFilling {

    public void fill(final TestTemplate template, final Test test, final BasicModel model) throws Exception {
        for (final PSheet sheet : model) {
            if (sheet instanceof TestRecords && test.getDescriptor().getId().equals(sheet.getName())) {
                reset(sheet.getName());
                for (int rowIdx = 0; rowIdx < sheet.getRowCount(); ++rowIdx) {
                    final TrialRecord record = (TrialRecord) sheet.getRow(rowIdx);
                    final String dataColName = getDataColumName(record);

                    MRow reportRow = getResponseMatrixRow(record.getExperimentTimestamp());
                    int colIdx = responseMatrix.index(PARTICIPANT_ID_NAME, PType.STRING);
                    reportRow.set(colIdx, record.getParticipantId());
                    colIdx = responseMatrix.index(dataColName, PType.STRING);
                    try {
                        reportRow.set(colIdx, record.getResponse());
                    } catch (final IndexOutOfBoundsException ex) {
                        reportRow.set(colIdx, Constants.NA);
                    }

                    reportRow = getTimeMatrixRow(record.getExperimentTimestamp());
                    colIdx = timeMatrix.index(PARTICIPANT_ID_NAME, PType.STRING);
                    reportRow.set(colIdx, record.getParticipantId());
                    colIdx = timeMatrix.index(dataColName, PType.STRING);
                    try {
                        final Integer responseTime = record.getResponseTime();
                        reportRow.set(colIdx, responseTime);
                    } catch (final NullPointerException ex) {
                        reportRow.set(colIdx, -1);
                    }
                }
                template.addResponses(responseMatrix);
                template.addTimes(timeMatrix);
            }
        }
    }

}
