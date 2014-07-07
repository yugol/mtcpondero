package pondero.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import pondero.model.entities.base.Record;
import pondero.model.participants.Participant;

public interface Workbook {

    void add(Record record) throws Exception;

    void addWorkbookListener(ModelListener listener);

    void close() throws IOException;

    void deleteParticipants();

    List<? extends Record> getAll(Class<? extends Record> prototype) throws Exception;

    List<Participant> getAllParticipants() throws Exception;

    String getNewUniqueParticipantId();

    int getParticipantCount();

    String getWorkbookName();

    boolean isDirty();

    void save() throws IOException;

    void saveAs(File selectedFile) throws IOException;

    void view() throws Exception;

}