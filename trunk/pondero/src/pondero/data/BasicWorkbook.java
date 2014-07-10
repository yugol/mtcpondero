package pondero.data;

import static pondero.Logger.info;
import java.io.File;
import java.util.Collection;
import pondero.Context;
import pondero.data.drivers.excel.ExcelFileFilter;
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
        template.open();
        model = template.fetchModel();
        model.setDirty(false);
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
    public void addModelListener(final PModelListener listener) {
        model.addModelListener(listener);
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
    public String getNextPariciantId() throws Exception {
        return model.getParticipants().getNextPariciantId();
    }

    @Override
    public int getParticipantCount() throws Exception {
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
        template.open();
        template.pushModel(model);
        model = template.fetchModel();
        model.setDirty(false);
    }

    @Override
    public void view() throws Exception {
        final File tempFolder = Context.getFolderResultsTemp();
        String fileName = model.getName();
        final int dotIndex = fileName.indexOf(".");
        if (dotIndex >= 0) {
            fileName = fileName.substring(0, dotIndex);
        }
        final String tempFileName = fileName + "-" + System.currentTimeMillis() + ExcelFileFilter.DEFAULT_EXTENSION;
        final File tempFile = new File(tempFolder, tempFileName);
        info("view workbook: ", tempFile.getCanonicalPath());

        final BasicTemplate viewDriver = new BasicTemplate(tempFile);
        viewDriver.open();
        viewDriver.pushModel(model);
        viewDriver.close();

        String[] cmd = null;
        if (Context.isWindows()) {
            cmd = new String[] { "cmd.exe", "/c", tempFile.getCanonicalPath() };
        } else if (Context.isMacOSX()) {
            cmd = new String[] { "open", tempFile.getCanonicalPath() };
        } else {
            throw new UnsupportedOperationException("Your OS is not supported for view");
        }
        Runtime.getRuntime().exec(cmd);
    }

}
