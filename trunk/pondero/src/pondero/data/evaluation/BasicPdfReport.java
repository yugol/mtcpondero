package pondero.data.evaluation;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import pondero.Constants;
import pondero.util.StreamUtil;

public abstract class BasicPdfReport {

    private final PDDocument report;

    protected final PDFont   AR;
    protected final PDFont   AR_B;
    protected final PDFont   AR_BI;
    protected final PDFont   AR_I;
    protected final PDFont   COU;
    protected final PDFont   COU_B;
    protected final PDFont   COU_BI;
    protected final PDFont   COU_I;
    protected final PDFont   TNR;
    protected final PDFont   TNR_B;
    protected final PDFont   TNR_BI;
    protected final PDFont   TNR_I;

    public BasicPdfReport() throws IOException {
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

    public void close() throws IOException {
        report.close();
    }

    public abstract void generate() throws Exception;

    public final String ro(String input) {
        input = input.replace('\u0103', '\u00e3');
        input = input.replace('â', '\u00e2');
        input = input.replace('î', '\u00ee');
        input = input.replace('\u0219', '\u00ba');
        input = input.replace('\u021B', '\u00fe');
        input = input.replace('\u0102', '\u00c3');
        input = input.replace('Â', '\u00c2');
        input = input.replace('Î', '\u00ce');
        input = input.replace('\u0218', '\u00aa');
        input = input.replace('\u021A', '\u00de');
        return input;
    }

    public void save(final File reportFile) throws Exception {
        report.save(reportFile);
    }

    protected PDPage addPage() throws Exception {
        final PDPage newPage = new PDPage();
        final PDPageContentStream contentStream = new PDPageContentStream(getReport(), newPage);
        contentStream.beginText();
        contentStream.setFont(AR, 5);
        contentStream.moveTextPositionByAmount(5, 5);
        contentStream.drawString(ro("Generat de aplica\u021Ba Pondero: " + Constants.HOME_PAGE_ADDRESS));
        contentStream.endText();
        contentStream.close();
        report.addPage(newPage);
        return newPage;
    }

    protected PDDocument getReport() {
        return report;
    }

}
