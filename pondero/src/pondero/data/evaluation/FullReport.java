package pondero.data.evaluation;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import pondero.Context;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.TestInstance;
import pondero.tests.test.Test;
import pondero.util.TimeUtil;

public class FullReport extends BasicPdfReport {

    static final float         FIRST_COLUMN  = 100;
    static final float         SECOND_COLUMN = 200;

    static final float         WIDTH         = 595.27563f;
    static final float         HEIGHT        = 841.8898f;

    public static final String BASE_NAME     = "FullReport";

    private final BasicModel   model;
    private final Participant  participant;

    public FullReport(final Participant participant, final BasicModel model) throws IOException {
        this.model = model;
        this.participant = participant;
    }

    @Override
    public void generate() throws Exception {
        buildFistPage();
        for (final String testName : model.getTestSheets()) {
            final List<Long> instances = model.getRecords(testName).getTestTimes(participant.getId());
            if (!instances.isEmpty()) {
                final TestInstance instance = model.getRecords(testName).getInstance(participant.getId(), instances.get(instances.size() - 1));
                final Test test = Context.getTest(instance.getTestName());
                if (test != null) {
                    test.renderReport(this, instance);
                }
            }
        }
    }

    private void buildFistPage() throws Exception, IOException {
        final PDPage firstPage = addPage();
        final PDPageContentStream cs = getContentStream(firstPage);
        cs.beginText();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 700);
        cs.setFont(AR, 14);
        cs.drawString("Nume:");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 700);
        cs.setFont(AR_B, 14);
        cs.drawString(participant.getSurname());
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 675);
        cs.setFont(AR, 14);
        cs.drawString("Prenume:");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 675);
        cs.setFont(AR_B, 14);
        cs.drawString(participant.getName());
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 640);
        cs.setFont(AR_I, 10);
        cs.drawString("Nr. de ordine:");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 640);
        cs.setFont(AR_I, 10);
        cs.drawString(participant.getId());
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 625);
        cs.setFont(AR_I, 10);
        cs.drawString("Data raportului:");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 625);
        cs.setFont(AR_I, 10);
        cs.drawString(TimeUtil.toIsoDate(Calendar.getInstance().getTimeInMillis()));
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 600);
        cs.setFont(AR, 10);
        cs.drawString("Vârsta:");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 600);
        cs.setFont(AR, 10);
        cs.drawString(String.valueOf(participant.getAge()) + " ani");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 585);
        cs.setFont(AR, 10);
        cs.drawString("Gen:");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 585);
        cs.setFont(AR, 10);
        cs.drawString(participant.getGender().toString());
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(FIRST_COLUMN, 570);
        cs.setFont(AR, 10);
        cs.drawString("Vechime permis :");
        cs.restoreGraphicsState();

        cs.saveGraphicsState();
        cs.moveTextPositionByAmount(SECOND_COLUMN, 570);
        cs.setFont(AR, 10);
        cs.drawString(String.valueOf(participant.getDrivingAge()) + " ani");
        cs.restoreGraphicsState();

        cs.endText();
        cs.close();
    }

}
