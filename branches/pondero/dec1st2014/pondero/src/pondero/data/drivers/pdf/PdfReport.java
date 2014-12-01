package pondero.data.drivers.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import pondero.Constants;
import pondero.util.StreamUtil;

public abstract class PdfReport {

    private final PDDocument report;
    public final PDFont      AR;
    public final PDFont      AR_B;
    public final PDFont      AR_BI;
    public final PDFont      AR_I;
    public final PDFont      COU;
    public final PDFont      COU_B;
    public final PDFont      COU_BI;
    public final PDFont      COU_I;
    public final PDFont      TNR;
    public final PDFont      TNR_B;
    public final PDFont      TNR_BI;
    public final PDFont      TNR_I;

    public PdfReport() throws IOException {
        report = new PDDocument();
        AR = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/arl2.ttf", null));
        AR_B = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/arl2b.ttf", null));
        AR_BI = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/arl2bi.ttf", null));
        AR_I = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/arl2i.ttf", null));
        COU = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/cnl2.ttf", null));
        COU_B = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/cnl2b.ttf", null));
        COU_BI = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/cnl2bi.ttf", null));
        COU_I = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/cnl2i.ttf", null));
        TNR = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/tnrl2.ttf", null));
        TNR_B = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/tnrl2b.ttf", null));
        TNR_BI = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/tnrl2bi.ttf", null));
        TNR_I = PDTrueTypeFont.loadTTF(report, StreamUtil.getResourceStream("res/fonts/tnrl2i.ttf", null));
    }

    public PDPage addPage() throws IOException {
        final PDPage newPage = new PDPage(PDPage.PAGE_SIZE_A4);
        final PDPageContentStream contentStream = new PDPageContentStream(getReport(), newPage);
        contentStream.beginText();
        contentStream.setFont(AR, 5);
        contentStream.moveTextPositionByAmount(5, 5);
        contentStream.drawString(PdfUtil.ro("Generat de aplica\u021Ba 'Pondero': " + Constants.HOME_PAGE_ADDRESS));
        contentStream.endText();
        contentStream.close();
        report.addPage(newPage);
        return newPage;
    }

    public void close() throws IOException {
        report.close();
    }

    public abstract void generate() throws Exception;

    public PdfPageCanvas getCanvas(final PDPage page) throws IOException {
        return new PdfPageCanvas(report, page);
    }

    public PDFont getMono() {
        return COU;
    }

    public PDFont getMonoBold() {
        return COU_B;
    }

    public PDFont getMonoBoldItalic() {
        return COU_BI;
    }

    public PDFont getMonoItalic() {
        return COU_I;
    }

    public PDDocument getReport() {
        return report;
    }

    public PDFont getSansSerif() {
        return AR;
    }

    public PDFont getSansSerifBold() {
        return AR_B;
    }

    public PDFont getSansSerifBoldItalic() {
        return AR_BI;
    }

    public PDFont getSansSerifItalic() {
        return AR_I;
    }

    public PDFont getSerif() {
        return TNR;
    }

    public PDFont getSerifBold() {
        return TNR_B;
    }

    public PDFont getSerifBoldItalic() {
        return TNR_BI;
    }

    public PDFont getSerifItalic() {
        return TNR_I;
    }

    public void save(final File reportFile) throws Exception {
        report.save(reportFile);
    }

}
