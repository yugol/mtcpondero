package pondero.model;

import java.io.IOException;
import java.util.List;
import pondero.model.entities.base.Record;

public interface Workbook {

    public abstract void add(Record record) throws Exception;

    public abstract void close() throws IOException;

    public abstract void deleteParticipants();

    public abstract List<? extends Record> getAll(Class<? extends Record> prototype) throws Exception;

    public abstract String getName();

    public abstract boolean isDirty();

    public abstract void save() throws IOException;

    public abstract void view() throws Exception;

}