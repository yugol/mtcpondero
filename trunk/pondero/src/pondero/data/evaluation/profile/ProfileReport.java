package pondero.data.evaluation.profile;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pondero.Context;
import pondero.L10n;
import pondero.Logger;
import pondero.data.drivers.pdf.PdfPageCanvas;
import pondero.data.drivers.pdf.PdfParagraph;
import pondero.data.drivers.pdf.PdfReport;
import pondero.data.drivers.pdf.PdfUtil;
import pondero.data.evaluation.scoring.Evaluation;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;
import pondero.data.model.basic.TestInstance;
import pondero.tests.Test;

public class ProfileReport extends PdfReport {

    private static String ani(final int age) {
        if (age == 1) { return " an"; }
        if (age < 20) { return " ani"; }
        return " de ani";
    }

    public static final String    BASE_NAME        = "Profile";
    private static final float    LEFT             = PdfUtil.mmToUnits(25);

    private static final float    WIDTH            = PdfUtil.A4_WIDTH - 2 * PdfUtil.mmToUnits(25);

    private static final float    TITLE_TOP        = PdfUtil.A4_HEIGHT - PdfUtil.mmToUnits(37.5f);
    private static final float    BIO_TOP          = TITLE_TOP - 25;
    private static final float    BIO_FIRST        = LEFT + 10;
    private static final float    BIO_SECOND       = BIO_FIRST + 180;
    private static final float    BIO_THIRD        = BIO_SECOND + 150;

    private static final float    BIO_HEIGHT       = 14;
    private static final float    TABLE_TOP        = BIO_TOP - BIO_HEIGHT * 3 - 10;
    private static final float    TABLE_FIRST      = LEFT;
    private static final float    TABLE_SECOND     = BIO_SECOND;                                                // (TABLE_FIRST + WIDTH) / 2 - 10;
    private static final float    TABLE_THIRD      = TABLE_SECOND + 40;
    private static final float    TABLE_LAST       = TABLE_FIRST + WIDTH;

    private static final float    TABLE_ROW_HEIGHT = 16;

    private static final String[] ROMAN            = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
    protected final BasicModel    model;

    protected final Participant   participant;

    public ProfileReport(final Participant participant, final BasicModel model) throws IOException {
        this.model = model;
        this.participant = participant;
    }

    @Override
    public void generate() throws Exception {

        // fetch evaluations
        final List<Evaluation> evaluations = new ArrayList<Evaluation>();
        for (final String testName : model.getTestSheets()) {
            final List<Long> instances = model.getRecords(testName).getTestTimes(participant.getId());
            if (!instances.isEmpty()) {
                final TestInstance testInstance = model.getRecords(testName).getInstance(participant.getId(), instances.get(instances.size() - 1));
                final Test test = Context.getTest(testInstance.getTestName());
                if (test != null) {
                    try {
                        final Evaluation evaluation = test.getEvaluation(testInstance);
                        evaluations.add(evaluation);
                    } catch (final UnsupportedOperationException ex) {
                        Logger.warning(ex, test.getDescriptor().getCodeName() + " does not support profiling");
                    }
                }
            }
        }
        Collections.sort(evaluations, new EvaluationsComparator());

        final List<ProfileEntry> entries = new ArrayList<>();
        for (final Evaluation eval : evaluations) {
            for (final ProfileEntry entry : eval.getProfileEntries()) {
                entries.add(entry);
            }
        }

        // draw profile
        final PdfPageCanvas canvas = getCanvas(addPage());
        drawTitle(canvas);
        drawInfo(canvas);
        drawEntries(canvas, entries, 7);
        canvas.close();
    }

    private void drawEntries(final PdfPageCanvas canvas, final List<ProfileEntry> entries, final int cellCount) throws IOException {
        final float[] cells = new float[cellCount];
        final float cellWidth = (TABLE_LAST - TABLE_THIRD) / cellCount;
        for (int i = 0; i < cellCount; ++i) {
            cells[i] = TABLE_THIRD + i * cellWidth;
        }

        for (int i = 0; i < entries.size(); ++i) {
            final ProfileEntry entry = entries.get(i);
            final int standardScore = entry.getStandardScore();
            final float rowTop = TABLE_TOP - (2 + i) * TABLE_ROW_HEIGHT;
            canvas.setNonStrokingColor(Color.LIGHT_GRAY);
            canvas.fillRect(cells[0], rowTop - TABLE_ROW_HEIGHT, standardScore * cellWidth, TABLE_ROW_HEIGHT);
            canvas.setNonStrokingColor(Color.GRAY);
            canvas.fillRect(cells[standardScore], rowTop - TABLE_ROW_HEIGHT, cellWidth, TABLE_ROW_HEIGHT);
        }

        canvas.setStrokingColor(Color.DARK_GRAY);

        final int rowCount = entries.size() + 2;
        canvas.drawLine(TABLE_THIRD, TABLE_TOP, TABLE_LAST, TABLE_TOP);
        for (int i = 1; i <= rowCount; ++i) {
            final float y = TABLE_TOP - i * TABLE_ROW_HEIGHT;
            canvas.drawLine(TABLE_FIRST, y, TABLE_LAST, y);
        }

        final float bottom = TABLE_TOP - rowCount * TABLE_ROW_HEIGHT;
        canvas.drawLine(TABLE_FIRST, TABLE_TOP - TABLE_ROW_HEIGHT, TABLE_FIRST, bottom);
        canvas.drawLine(TABLE_SECOND, TABLE_TOP - TABLE_ROW_HEIGHT, TABLE_SECOND, bottom);
        canvas.drawLine(TABLE_THIRD, TABLE_TOP, TABLE_THIRD, bottom);
        for (int i = 1; i < cellCount; ++i) {
            canvas.drawLine(cells[i], TABLE_TOP - TABLE_ROW_HEIGHT, cells[i], bottom);
        }
        canvas.drawLine(TABLE_LAST, TABLE_TOP, TABLE_LAST, bottom);

        canvas.setNonStrokingColor(Color.BLACK);

        canvas.setFont(getSerifBold(), 10);

        PdfParagraph par = canvas.createParagraph("NS");
        par.setWidth(TABLE_LAST - TABLE_THIRD);
        par.setHeight(TABLE_ROW_HEIGHT);
        canvas.drawParagraph(par, TABLE_THIRD, TABLE_TOP - TABLE_ROW_HEIGHT);

        par = canvas.createParagraph("INDICATORI PROBE");
        par.setWidth(TABLE_SECOND - TABLE_FIRST);
        par.setHeight(TABLE_ROW_HEIGHT);
        canvas.drawParagraph(par, TABLE_FIRST, TABLE_TOP - TABLE_ROW_HEIGHT * 2);

        par = canvas.createParagraph("NB");
        par.setWidth(TABLE_THIRD - TABLE_SECOND);
        par.setHeight(TABLE_ROW_HEIGHT);
        canvas.drawParagraph(par, TABLE_SECOND, TABLE_TOP - TABLE_ROW_HEIGHT * 2);

        canvas.setFont(getSerifItalic(), 10);

        for (int i = 0; i < cellCount; ++i) {
            canvas.drawLine(cells[i], TABLE_TOP - TABLE_ROW_HEIGHT, cells[i], TABLE_TOP - TABLE_ROW_HEIGHT * 2);
            par = canvas.createParagraph(ROMAN[i]);
            par.setWidth(cellWidth);
            par.setHeight(TABLE_ROW_HEIGHT);
            canvas.drawParagraph(par, cells[i], TABLE_TOP - TABLE_ROW_HEIGHT * 2);
        }

        canvas.setFont(getSerif(), 10);

        for (int i = 0; i < entries.size(); ++i) {
            final ProfileEntry entry = entries.get(i);

            final float rowTop = TABLE_TOP - (2 + i) * TABLE_ROW_HEIGHT;

            par = canvas.createParagraph("  " + entry.getEvaluation().getTest().getDescriptor().getId() + ": " + entry.getName());
            par.setWidth(TABLE_SECOND - TABLE_FIRST);
            par.setHeight(TABLE_ROW_HEIGHT);
            par.sethAlign(PdfParagraph.HAlign.LEFT);
            canvas.drawParagraph(par, TABLE_FIRST, rowTop - TABLE_ROW_HEIGHT);

            par = canvas.createParagraph(entry.getRawScore());
            par.setWidth(TABLE_THIRD - TABLE_SECOND);
            par.setHeight(TABLE_ROW_HEIGHT);
            canvas.drawParagraph(par, TABLE_SECOND, rowTop - TABLE_ROW_HEIGHT);
        }
    }

    private void drawInfo(final PdfPageCanvas canvas) throws IOException, Exception {
        canvas.setFont(getSansSerifBold(), 10);

        canvas.drawString("Nr. crt. " + participant.getId(), BIO_FIRST, BIO_TOP);

        canvas.drawString(L10n.getString("lbl.participant.surname") + ": " + participant.getSurname(), BIO_FIRST, BIO_TOP - BIO_HEIGHT);
        canvas.drawString(L10n.getString("lbl.participant.age") + ": " + participant.getAge() + ani(participant.getAge()), BIO_SECOND, BIO_TOP - BIO_HEIGHT);
        canvas.drawString(L10n.getString("lbl.participant.driving-age") + ": " + participant.getDrivingAge() + ani(participant.getDrivingAge()), BIO_THIRD, BIO_TOP - BIO_HEIGHT);

        canvas.drawString(L10n.getString("lbl.participant.name") + ": " + participant.getName(), BIO_FIRST, BIO_TOP - BIO_HEIGHT - BIO_HEIGHT);
        canvas.drawString(L10n.getString("lbl.participant.education") + ": " + participant.getEducation(), BIO_SECOND, BIO_TOP - BIO_HEIGHT - BIO_HEIGHT);
    }

    private void drawTitle(final PdfPageCanvas canvas) throws IOException {
        canvas.setFont(getSansSerifBold(), 12);
        final PdfParagraph par = canvas.createParagraph("PROFIL PSIHOLOGIC");
        par.setWidth(WIDTH);
        par.sethAlign(PdfParagraph.HAlign.CENTER);
        canvas.drawParagraph(par, LEFT, TITLE_TOP);
    }

}
