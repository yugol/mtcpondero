package pondero.model;

import java.io.File;
import java.util.Collection;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.Participants;
import pondero.model.foundation.basic.TrialRecord;

public interface Workbook {

    Participant addParticipant() throws Exception;

    TrialRecord addTrialRecord(String experimentId) throws Exception;

    void addWorkbookListener(ModelListener listener);

    void close() throws Exception;

    Participants getAllParticipants() throws Exception;

    String getNextPariciantId() throws Exception;

    int getParticipantCount() throws Exception;

    String getName();

    boolean isDirty();

    void removeRecords(String testId, Collection<TrialRecord> records) throws Exception;

    void save() throws Exception;

    void saveAs(File selectedFile) throws Exception;

    void view() throws Exception;

}
