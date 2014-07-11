package pondero.ui.actions;

import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import java.io.File;
import pondero.Context;
import pondero.L10n;
import pondero.data.drivers.excel.ExcelFileFilter;
import pondero.data.drivers.excel.templates.BasicTemplate;
import pondero.data.model.basic.BasicModel;
import pondero.ui.Ponderable;
import pondero.util.MsgUtil;
import pondero.util.OsUtil;

@SuppressWarnings("serial")
public class ViewDocumentAction extends PonderableAction {

    public ViewDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.open-externally"));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            final BasicModel model = getCurrentWorkbook().getModel();
            String modelFileName = model.getName();
            final int dotIndex = modelFileName.lastIndexOf(".");
            if (dotIndex >= 0) {
                modelFileName = modelFileName.substring(0, dotIndex);
            }
            modelFileName = modelFileName + "-" + System.currentTimeMillis() + ExcelFileFilter.DEFAULT_EXTENSION;
            final File modelFile = new File(Context.getFolderResultsTemp(), modelFileName);
            info("view model: ", modelFile.getCanonicalPath());

            final BasicTemplate viewDriver = new BasicTemplate(modelFile);
            viewDriver.pushModel(model);
            viewDriver.close();

            OsUtil.openFile(modelFile);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
