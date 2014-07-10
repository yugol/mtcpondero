package pondero.data;

import java.io.File;
import java.util.Collection;
import pondero.data.foundation.basic.Participant;
import pondero.data.foundation.basic.Participants;
import pondero.data.foundation.basic.TrialRecord;

public interface Workbook {

    void addModelListener(PModelListener listener);

    Participant addParticipant() throws Exception;

    TrialRecord addTrialRecord(String experimentId) throws Exception;

    void close() throws Exception;

    Participants getAllParticipants() throws Exception;

    String getName();

    String getNextPariciantId() throws Exception;

    int getParticipantCount() throws Exception;

    boolean isDirty();

    void removeRecords(String testId, Collection<TrialRecord> records) throws Exception;

    void save() throws Exception;

    void saveAs(File selectedFile) throws Exception;

    void view() throws Exception;

}
