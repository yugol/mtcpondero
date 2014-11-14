package pondero.data.drivers.pdf;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Stack;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class PdfPageCanvas {

    // private final PDDocument             document;
    // private final PDPage                 page;
    private final PDPageContentStream    contentStream;
    private AffineTransform              matrix      = new AffineTransform();
    private final Stack<AffineTransform> matrixStack = new Stack<>();
    private PDFont                       lastFont;
    private float                        lastFontSize;

    public PdfPageCanvas(final PDDocument document, final PDPage page) throws IOException {
        // this.document = document;
        // this.page = page;
        contentStream = new PDPageContentStream(document, page, true, false, true);
    }

    public void close() throws IOException {
        contentStream.close();
    }

    public PdfParagraph createParagraph(final String text) {
        return new PdfParagraph(this, text);
    }

    public void drawLine(final float x1, final float y1, final float x2, final float y2) throws IOException {
        final Point2D start = transform(x1, y1);
        final Point2D end = transform(x2, y2);
        contentStream.drawLine((float) start.getX(), (float) start.getY(), (float) end.getX(), (float) end.getY());
    }

    public void drawOpenPolygon(final float[] x, final float[] y) throws IOException {
        final float[] xTmp = new float[x.length];
        final float[] yTmp = new float[y.length];
        for (int i = 0; i < x.length; ++i) {
            final Point2D xy = transform(x[i], y[i]);
            xTmp[i] = (float) xy.getX();
            yTmp[i] = (float) xy.getY();
        }
        for (int i = 1; i < x.length; ++i) {
            contentStream.drawLine(xTmp[i - 1], yTmp[i - 1], xTmp[i], yTmp[i]);
        }
    }

    public void drawParagraph(final PdfParagraph paragraph, final float x, final float y) throws IOException {
        contentStream.beginText();
        for (final PdfParagraphLine line : paragraph) {
            contentStream.saveGraphicsState();
            final Point2D xy = transform(x + line.getxDelta(), y + line.getyDelta());
            contentStream.moveTextPositionByAmount((float) xy.getX(), (float) xy.getY());
            contentStream.drawString(line.getText());
            contentStream.restoreGraphicsState();
        }
        contentStream.endText();
    }

    public void drawString(final String str, final float x, final float y) throws IOException {
        contentStream.beginText();
        contentStream.saveGraphicsState();
        final Point2D xy = transform(x, y);
        contentStream.moveTextPositionByAmount((float) xy.getX(), (float) xy.getY());
        contentStream.drawString(PdfUtil.ro(str));
        contentStream.restoreGraphicsState();
        contentStream.endText();
    }

    public float getStringHeight() {
        return lastFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * lastFontSize;
    }

    public float getStringWidth(final String text) throws IOException {
        return lastFont.getStringWidth(text) / 1000f * lastFontSize;
    }

    public AffineTransform getTransformation() {
        return matrix;
    }

    public void popMatrix() {
        matrix = matrixStack.pop();
    }

    public void pushMatrix() {
        matrixStack.push(matrix);
        matrix = new AffineTransform(matrix);
    }

    public void rotate(final double theta) {
        matrix.rotate(theta);
    }

    public void rotate(final double theta, final double anchorx, final double anchory) {
        matrix.rotate(theta, anchorx, anchory);
    }

    public void scale(final double sx, final double sy) {
        matrix.scale(sx, sy);
    }

    public void setFont(final PDFont font, final float fontSize) throws IOException {
        contentStream.setFont(font, fontSize);
        lastFont = font;
        lastFontSize = fontSize;
    }

    public void setNonStrokingColor(final Color color) throws IOException {
        contentStream.setNonStrokingColor(color);
    }

    public void setStrokingColor(final Color color) throws IOException {
        contentStream.setStrokingColor(color);
    }

    public void setTransformation(final AffineTransform transformation) {
        matrix = transformation;
    }

    public void translate(final double x, final double y) {
        matrix.translate(x, y);
    }

    private Point2D transform(final float x, final float y) {
        final Point2D transformed = new Point2D.Float();
        matrix.transform(new Point2D.Float(x, y), transformed);
        return transformed;
    }

}
