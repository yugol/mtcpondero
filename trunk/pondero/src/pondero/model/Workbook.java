package pondero.model;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.Participants;
import pondero.model.foundation.basic.TrialRecord;

public interface Workbook {

    Participant addParticipant();

    TrialRecord addTrialRecord(String experimentId);

    void addWorkbookListener(ModelListener listener);

    void close() throws Exception;

    Participants getAllParticipants();

    String getNextPariciantId();

    int getParticipantCount();

    String getName();

    boolean isDirty();

    void removeRecords(String testId, Collection<TrialRecord> records);

    void save() throws IOException;

    void saveAs(File selectedFile) throws IOException;

    void view() throws Exception;

}
