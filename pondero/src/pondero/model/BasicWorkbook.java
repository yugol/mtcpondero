package pondero.model;

import static pondero.Logger.info;
import java.io.File;
import java.util.Collection;
import pondero.Globals;
import pondero.model.drivers.excel.ExcelFileFilter;
import pondero.model.drivers.excel.templates.BasicTemplate;
import pondero.model.foundation.basic.BasicModel;
import pondero.model.foundation.basic.Participant;
import pondero.model.foundation.basic.Participants;
import pondero.model.foundation.basic.TrialRecord;
import pondero.util.SystemUtil;

public class BasicWorkbook implements Workbook {

    private BasicModel    model;
    private BasicTemplate driver;

    public BasicWorkbook(final File file) throws Exception {
        driver = new BasicTemplate(file);
        driver.open();
        model = driver.getModel();
    }

    @Override
    public Participant addParticipant() {
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
    public void saveAs(final File selectedFile) throws Exception {
        driver.close();
        driver = new BasicTemplate(selectedFile.getCanonicalPath());
        driver.open();
        driver.putModel(model);
        driver.save();
        model = driver.getModel();
    }

    @Override
    public void view() throws Exception {
        final File tempFolder = Globals.getFolderResultsTemp();
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
        viewDriver.putModel(model);
        viewDriver.save();
        viewDriver.close();

        String[] cmd = null;
        if (SystemUtil.isWindows()) {
            cmd = new String[] { "cmd.exe", "/c", tempFile.getCanonicalPath() };
        } else if (SystemUtil.isMacOSX()) {
            cmd = new String[] { "open", tempFile.getCanonicalPath() };
        } else {
            throw new UnsupportedOperationException("Your OS is not supported for view");
        }
        Runtime.getRuntime().exec(cmd);
    }

}
