package pondero.model;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import pondero.model.drivers.excel.templates.BasicTemplate;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participants;
import pondero.model.foundation.basic.TrialRecord;

public class BasicWorkbook implements Workbook {

    private final BasicModel    model;
    private final BasicTemplate driver;

    public BasicWorkbook(final File file) throws Exception {
        driver = new BasicTemplate(file.getCanonicalPath());
        driver.open();
        model = driver.getModel();
    }

    @Override
    public pondero.model.foundation.basic.Participant addParticipant() {
        return model.getParticipants().addRow();
    }

    @Override
    public TrialRecord addTrialRecord(final String testId) {
        return model.getRecords(testId).addRow();
    }

    @Override
    public void addWorkbookListener(final ModelListener listener) {
        model.addModelListener(listener);
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    @Override
    public Participants getAllParticipants() {
        return model.getParticipants();
    }

    @Override
    public String getNextPariciantId() {
        return model.getParticipants().getNextPariciantId();
    }

    @Override
    public int getParticipantCount() {
        return model.getParticipants().getRowCount();
    }

    @Override
    public String getName() {
        return model.getName();
    }

    @Override
    public boolean isDirty() {
        return model.isDirty();
    }

    @Override
    public void removeRecords(final String testId, final Collection<TrialRecord> records) {
        model.getRecords(testId).removeRows(records);
    }

    @Override
    public void save() throws Exception {
        driver.putModel(model);
        driver.save();
    }

    @Override
    public void saveAs(final File selectedFile) throws IOException {
        throw new UnsupportedOperationException("saveAs");
    }

    @Override
    public void view() throws Exception {
        throw new UnsupportedOperationException("view");
    }
}
