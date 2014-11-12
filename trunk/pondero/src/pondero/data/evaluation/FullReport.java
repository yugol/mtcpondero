package pondero.data.evaluation;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPage;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;

public class FullReport extends BasicPdfReport {

    public static final String BASE_NAME = "FullReport";

    private final BasicModel   model;
    private final Participant  participant;

    public FullReport(final Participant participant, final BasicModel model) throws IOException {
        this.model = model;
        this.participant = participant;
    }

    @Override
    public void generate() throws Exception {
        final PDPage firstPage = addPage();

    }

}
