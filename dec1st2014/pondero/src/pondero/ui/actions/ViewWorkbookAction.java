package pondero.ui.actions;

import static pondero.Logger.action;
import static pondero.Logger.info;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import pondero.Context;
import pondero.L10n;
import pondero.data.drivers.excel.templates.BasicTemplate;
import pondero.data.model.basic.BasicModel;
import pondero.ui.Ponderable;
import pondero.ui.Pondero;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.util.ExcelFileFilter;
import pondero.util.OsUtil;

@SuppressWarnings("serial")
public class ViewWorkbookAction extends PonderableAction {

    public ViewWorkbookAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.open-externally"));
        putValue(SMALL_ICON, new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/eye.png")));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        action("performing view on current registry");
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
            ExceptionReporting.showExceptionMessage(getMainFrame(), e);
        }
    }

}
