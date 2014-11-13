package pondero.data.evaluation;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class PdfPageCanvas {

    private final PDDocument          document;
    private final PDPage              page;
    private final PDPageContentStream contentStream;
    private AffineTransform           matrix = new AffineTransform();

    public PdfPageCanvas(final PDDocument document, final PDPage page) throws IOException {
        this.document = document;
        this.page = page;
        contentStream = new PDPageContentStream(document, page, true, false, true);
    }

    public void close() throws IOException {
        contentStream.close();
    }

    public void drawString(final String str, final float x, final float y) throws IOException {
        contentStream.beginText();
        contentStream.saveGraphicsState();
        contentStream.setTextMatrix(matrix);
        contentStream.moveTextPositionByAmount(x, y);
        contentStream.drawString(PdfReport.ro(str));
        contentStream.restoreGraphicsState();
        contentStream.endText();
    }

    public AffineTransform getTransformation() {
        return matrix;
    }

    public void setFont(final PDFont font, final float fontSize) throws IOException {
        contentStream.setFont(font, fontSize);
    }

    public void setTransformation(final AffineTransform transformation) {
        matrix = transformation;
    }

    public void translate(final float x, final float y) {
        matrix.translate(x, y);
    }

}
