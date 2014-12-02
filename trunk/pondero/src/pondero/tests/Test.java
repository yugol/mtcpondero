package pondero.tests;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import pondero.data.drivers.pdf.PdfPageCanvas;
import pondero.data.drivers.pdf.PdfReport;
import pondero.data.evaluation.scoring.Evaluation;
import pondero.data.model.basic.TestInstance;
import pondero.tests.elements.stimulus.Text;
import pondero.tests.staples.Coordinates;
import pondero.ui.testing.TestFrame;
import pondero.util.TimeUtil;

public abstract class Test extends TestBase {

    private final TestFrame testFrame;

    public Test() {
        testFrame = new TestFrame(null);
    }

    public Evaluation getEvaluation(final TestInstance instance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Deprecated
    public Color getScreenColor() {
        // TODO Auto-generated method stub
        return null;
    }

    public TestFrame getTestWindow() {
        return testFrame;
    }

    public Coordinates getTextSize(final Text text) {
        final Graphics g = testFrame.getGraphics();
        g.setFont(text.getFont());
        final FontMetrics fm = g.getFontMetrics();
        final Rectangle2D bounds = fm.getStringBounds("abc", g);
        return new Coordinates(bounds.getX(), bounds.getCenterY());
    }

    public void renderReport(final PdfReport report, final TestInstance instance) throws Exception {
        final String title = getDescriptor().getId() + "   " + TimeUtil.toIsoTimestamp(instance.getTestTime());
        final PdfPageCanvas canvas = report.getCanvas(report.addPage());
        canvas.setFont(report.AR_B, 16);
        canvas.drawString(title, 100, 700);
        canvas.close();
    }

}
