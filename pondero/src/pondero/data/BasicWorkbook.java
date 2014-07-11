package pondero.data;

import java.io.File;
import java.util.Collection;
import pondero.data.drivers.excel.templates.BasicTemplate;
import pondero.data.model.PModelListener;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.Participants;
import pondero.data.model.basic.TrialRecord;

public class BasicWorkbook implements Workbook {

    private BasicModel    model;
    private BasicTemplate template;

    public BasicWorkbook(final File file) throws Exception {
        template = new BasicTemplate(file);
        model = template.fetchModel();
        model.setDirty(false);
    }

    @Override
    public void addModelListener(final PModelListener listener) {
        model.addModelListener(listener);
    }

    @Override
    public Participant addParticipant() throws Exception {
        return model.getParticipants().addRow();
    }

    @Override
    public TrialRecord addTrialRecord(final String testId) throws Exception {
        return model.getRecords(testId).addRow();
    }

    @Override
    public void close() throws Exception {
        template.close();
    }

    @Override
    public Participants getAllParticipants() throws Exception {
        return model.getParticipants();
    }

    @Override
    public BasicModel getModel() {
        return model;
    }

    @Override
    public String getName() {
        return model.getName();
    }

    @Override
    public String getNextPariciantId() throws Exception {
        return model.getParticipants().getNextPariciantId();
    }

    @Override
    public int getParticipantCount() throws Exception {
        return model.getParticipants().getRowCount();
    }

    @Override
    public boolean isDirty() {
        return model.isDirty();
    }

    @Override
    public void removeRecords(final String testId, final Collection<TrialRecord> records) throws Exception {
        model.getRecords(testId).removeRows(records);
    }

    @Override
    public void save() throws Exception {
        template.pushModel(model);
        model.setDirty(false);
    }

    @Override
    public void saveAs(final File selectedFile) throws Exception {
        template.close();
        template = new BasicTemplate(selectedFile.getCanonicalPath());
        template.pushModel(model);
        model = template.fetchModel();
        model.setDirty(false);
    }

}
