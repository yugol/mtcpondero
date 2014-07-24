package pondero.data;

import java.io.File;
import java.util.Collection;
import pondero.data.model.PModelListener;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.Participants;
import pondero.data.model.basic.TrialRecord;

public interface Workbook {

    void addModelListener(PModelListener listener);

    TrialRecord addTrialRecord(String experimentId) throws Exception;

    void close() throws Exception;

    Participants getAllParticipants() throws Exception;

    File getFile();

    BasicModel getModel();

    String getName();

    String getNextPariciantId() throws Exception;

    Participant getParticipant(String participantId) throws Exception;

    int getParticipantCount() throws Exception;

    boolean isDirty();

    void removeRecords(String testId, Collection<TrialRecord> records) throws Exception;

    void save() throws Exception;

    void saveAs(File selectedFile) throws Exception;

}
