package pondero.data.evaluation;

import java.io.IOException;
import pondero.L10n;
import pondero.data.drivers.pdf.PdfPageCanvas;
import pondero.data.drivers.pdf.PdfParagraph;
import pondero.data.drivers.pdf.PdfReport;
import pondero.data.drivers.pdf.PdfUtil;
import pondero.data.model.basic.BasicModel;
import pondero.data.model.basic.Participant;

public class ProfileReport extends PdfReport {

    public static final String    BASE_NAME        = "Profile";

    private static final float    LEFT             = PdfUtil.mmToUnits(25);
    private static final float    WIDTH            = PdfUtil.A4_WIDTH - 2 * PdfUtil.mmToUnits(25);

    private static final float    TITLE_TOP        = PdfUtil.A4_HEIGHT - PdfUtil.mmToUnits(37.5f);

    private static final float    BIO_TOP          = TITLE_TOP - 25;
    private static final float    BIO_FIRST        = LEFT + 10;
    private static final float    BIO_SECOND       = BIO_FIRST + 200;
    private static final float    BIO_THIRD        = BIO_SECOND + 140;
    private static final float    BIO_HEIGHT       = 14;

    private static final float    TABLE_TOP        = BIO_TOP - BIO_HEIGHT * 3 - 10;
    private static final float    TABLE_FIRST      = LEFT;
    private static final float    TABLE_SECOND     = TABLE_FIRST + WIDTH / 2;
    private static final float    TABLE_THIRD      = TABLE_SECOND + 30;
    private static final float    TABLE_LAST       = TABLE_FIRST + WIDTH;
    private static final float    TABLE_ROW_HEIGHT = 20;

    private static final String[] ROMAN            = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

    protected final BasicModel    model;
    protected final Participant   participant;

    public ProfileReport(final Participant participant, final BasicModel model) throws IOException {
        this.model = model;
        this.participant = participant;
    }

    @Override
    public void generate() throws Exception {
        final PdfPageCanvas canvas = getCanvas(addPage());
        drawTitle(canvas);
        drawInfo(canvas);

        final float[] divisions = drawTableHeader(canvas, TABLE_TOP, 7);

        canvas.close();
    }

    private void drawInfo(final PdfPageCanvas canvas) throws IOException, Exception {
        canvas.setFont(getSansSerifBold(), 10);

        canvas.drawString("Nr. crt. " + participant.getId(), BIO_FIRST, BIO_TOP);

        canvas.drawString(L10n.getString("lbl.participant.surname") + ": " + participant.getSurname(), BIO_FIRST, BIO_TOP - BIO_HEIGHT);
        canvas.drawString(L10n.getString("lbl.participant.age") + ": " + participant.getAge(), BIO_SECOND, BIO_TOP - BIO_HEIGHT);
        canvas.drawString(L10n.getString("lbl.participant.driving-age") + ": " + participant.getDrivingAge(), BIO_THIRD, BIO_TOP - BIO_HEIGHT);

        canvas.drawString(L10n.getString("lbl.participant.name") + ": " + participant.getName(), BIO_FIRST, BIO_TOP - BIO_HEIGHT - BIO_HEIGHT);
        canvas.drawString(L10n.getString("lbl.participant.education") + ": " + participant.getEducation(), BIO_SECOND, BIO_TOP - BIO_HEIGHT - BIO_HEIGHT);
    }

    private float[] drawTableHeader(final PdfPageCanvas canvas, final float tableTop, final int n) throws IOException {
        canvas.drawLine(TABLE_FIRST, tableTop, TABLE_LAST, tableTop);
        canvas.drawLine(TABLE_FIRST, tableTop - TABLE_ROW_HEIGHT, TABLE_LAST, tableTop - TABLE_ROW_HEIGHT);
        canvas.drawLine(TABLE_FIRST, tableTop - TABLE_ROW_HEIGHT * 2, TABLE_LAST, tableTop - TABLE_ROW_HEIGHT * 2);

        canvas.drawLine(TABLE_FIRST, tableTop, TABLE_FIRST, tableTop - TABLE_ROW_HEIGHT * 2);
        canvas.drawLine(TABLE_SECOND, tableTop - TABLE_ROW_HEIGHT, TABLE_SECOND, tableTop - TABLE_ROW_HEIGHT * 2);
        canvas.drawLine(TABLE_THIRD, tableTop, TABLE_THIRD, tableTop - TABLE_ROW_HEIGHT);
        canvas.drawLine(TABLE_LAST, tableTop, TABLE_LAST, tableTop - TABLE_ROW_HEIGHT);

        canvas.setFont(getSerif(), 10);
        PdfParagraph par = canvas.createParagraph("NS");
        par.setWidth(TABLE_LAST - TABLE_THIRD);
        par.setHeight(TABLE_ROW_HEIGHT);
        canvas.drawParagraph(par, TABLE_THIRD, tableTop - TABLE_ROW_HEIGHT);

        par = canvas.createParagraph("INDICATORI PROBE");
        par.setWidth(TABLE_SECOND - TABLE_FIRST);
        par.setHeight(TABLE_ROW_HEIGHT);
        canvas.drawParagraph(par, TABLE_FIRST, tableTop - TABLE_ROW_HEIGHT * 2);

        par = canvas.createParagraph("NB");
        par.setWidth(TABLE_THIRD - TABLE_SECOND);
        par.setHeight(TABLE_ROW_HEIGHT);
        canvas.drawParagraph(par, TABLE_SECOND, tableTop - TABLE_ROW_HEIGHT * 2);

        final float[] divisions = new float[n];
        final float increment = (TABLE_LAST - TABLE_THIRD) / n;
        for (int i = 0; i < n; ++i) {
            divisions[i] = TABLE_THIRD + i * increment;
            canvas.drawLine(divisions[i], tableTop - TABLE_ROW_HEIGHT, divisions[i], tableTop - TABLE_ROW_HEIGHT * 2);
            par = canvas.createParagraph(ROMAN[i]);
            par.setWidth(increment);
            par.setHeight(TABLE_ROW_HEIGHT);
            canvas.drawParagraph(par, divisions[i], tableTop - TABLE_ROW_HEIGHT * 2);
        }
        canvas.drawLine(TABLE_LAST, tableTop - TABLE_ROW_HEIGHT, TABLE_LAST, tableTop - TABLE_ROW_HEIGHT * 2);

        return divisions;
    }

    private void drawTitle(final PdfPageCanvas canvas) throws IOException {
        canvas.setFont(getSansSerifBold(), 12);
        final PdfParagraph par = canvas.createParagraph("PROFIL PSIHOLOGIC");
        par.setWidth(WIDTH);
        par.sethAlign(PdfParagraph.HAlign.CENTER);
        canvas.drawParagraph(par, LEFT, TITLE_TOP);
    }

}
