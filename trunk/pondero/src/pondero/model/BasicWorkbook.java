package pondero.model;

import static pondero.Logger.info;
import java.io.File;
import java.util.Collection;
import pondero.Context;
import pondero.model.drivers.excel.ExcelFileFilter;
import pondero.model.drivers.excel.templates.BasicTemplate;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.Participants;
import pondero.model.foundation.basic.TrialRecord;

public class BasicWorkbook implements Workbook {

    private BasicModel    model;
    private BasicTemplate template;

    public BasicWorkbook(final File file) throws Exception {
        template = new BasicTemplate(file);
        template.open();
        model = template.fetchModel();
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
    public void addWorkbookListener(final ModelListener listener) {
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
    }

    @Override
    public void saveAs(final File selectedFile) throws Exception {
        template.close();
        template = new BasicTemplate(selectedFile.getCanonicalPath());
        template.open();
        template.pushModel(model);
        model = template.fetchModel();
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
