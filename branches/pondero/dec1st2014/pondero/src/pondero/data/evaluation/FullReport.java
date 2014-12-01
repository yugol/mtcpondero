package pondero.data.evaluation;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import pondero.Context;
import pondero.data.drivers.pdf.PdfPageCanvas;
import pondero.data.evaluation.profile.ProfileReport;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.TestInstance;
import pondero.tests.test.Test;
import pondero.util.TimeUtil;

public class FullReport extends ProfileReport {

    static final float         FIRST_COLUMN  = 100;
    static final float         SECOND_COLUMN = 200;

    public static final String BASE_NAME     = "FullReport";

    public FullReport(final Participant participant, final BasicModel model) throws IOException {
        super(participant, model);
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
        final PdfPageCanvas canvas = getCanvas(addPage());
        canvas.setFont(AR, 14);
        canvas.drawString("Nume:", FIRST_COLUMN, 700);
        canvas.setFont(AR_B, 14);
        canvas.drawString(participant.getSurname(), SECOND_COLUMN, 700);
        canvas.setFont(AR, 14);
        canvas.drawString("Prenume:", FIRST_COLUMN, 675);
        canvas.setFont(AR_B, 14);
        canvas.drawString(participant.getName(), SECOND_COLUMN, 675);
        canvas.setFont(AR_I, 10);
        canvas.drawString("Nr. de ordine:", FIRST_COLUMN, 640);
        canvas.setFont(AR_I, 10);
        canvas.drawString(participant.getId(), SECOND_COLUMN, 640);
        canvas.setFont(AR_I, 10);
        canvas.drawString("Data raportului:", FIRST_COLUMN, 625);
        canvas.setFont(AR_I, 10);
        canvas.drawString(TimeUtil.toIsoDate(Calendar.getInstance().getTimeInMillis()), SECOND_COLUMN, 625);
        canvas.setFont(AR, 10);
        canvas.drawString("Vârsta:", FIRST_COLUMN, 600);
        canvas.setFont(AR, 10);
        canvas.drawString(String.valueOf(participant.getAge()) + " ani", SECOND_COLUMN, 600);
        canvas.setFont(AR, 10);
        canvas.drawString("Gen:", FIRST_COLUMN, 585);
        canvas.setFont(AR, 10);
        canvas.drawString(participant.getGender().toString(), SECOND_COLUMN, 585);
        canvas.setFont(AR, 10);
        canvas.drawString("Vechime permis :", FIRST_COLUMN, 570);
        canvas.setFont(AR, 10);
        canvas.drawString(String.valueOf(participant.getDrivingAge()) + " ani", SECOND_COLUMN, 570);
        canvas.close();
    }

}
