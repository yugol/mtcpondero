package pondero.ui.actions;

import static pondero.Logger.error;
import java.awt.event.ActionEvent;
import pondero.L10n;
import pondero.ui.Ponderable;
import pondero.util.MsgUtil;

@SuppressWarnings("serial")
public class StartDocumentAction extends PonderoAction {

    public StartDocumentAction(final Ponderable app) {
        super(app);
        putValue(NAME, L10n.getString("lbl.open-externally"));
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
            getCurrentWorkbook().view();
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(getMainFrame(), e);
        }
    }

}
