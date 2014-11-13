package pondero.tests.test;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.SwingUtilities;
import pondero.data.evaluation.PdfPageCanvas;
import pondero.data.evaluation.PdfReport;
import pondero.data.model.basic.TestInstance;
import pondero.tests.elements.interfaces.HasScreencolor;
import pondero.tests.elements.other.Page;
import pondero.tests.elements.stimulus.Text;
import pondero.tests.staples.Coordinates;
import pondero.tests.test.stimuli.AuditoryStimulus;
import pondero.tests.test.stimuli.VisualStimulus;
import pondero.ui.testing.TestFrame;
import pondero.util.TimeUtil;

public abstract class TestRenderer extends TestBase {

    private final TestFrame              testFrame;

    private final List<AuditoryStimulus> auditoryStimuli  = new ArrayList<AuditoryStimulus>();
    private final List<VisualStimulus>   visualStimuli    = new ArrayList<VisualStimulus>();
    private final Stack<HasScreencolor>  screenColorStack = new Stack<HasScreencolor>();

    public TestRenderer() {
        testFrame = new TestFrame((Test) this);
    }

    public void addAuditoryStimulus(final AuditoryStimulus stimulus) {
        auditoryStimuli.add(stimulus);
    }

    public void addVisualStimulus(final VisualStimulus stimulus) {
        visualStimuli.add(stimulus);
    }

    public void drawScene(final Graphics2D g2d, final int surfaceWidth, final int surfaceHeight) {
        g2d.setColor(getScreenColor());
        g2d.fillRect(0, 0, surfaceWidth, surfaceHeight);
        for (final VisualStimulus stimulus : getVisualStimuli()) {
            stimulus.render(g2d, surfaceWidth, surfaceHeight);
        }
    }

    public List<AuditoryStimulus> getAuditoryStimuli() {
        return new ArrayList<AuditoryStimulus>(auditoryStimuli);
    }

    public Color getScreenColor() {
        return peekScreenColor().getScreenColor();
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

    public List<VisualStimulus> getVisualStimuli() {
        return new ArrayList<VisualStimulus>(visualStimuli);
    }

    public HasScreencolor peekScreenColor() {
        return screenColorStack.peek();
    }

    public synchronized void presentStimuli() {
        for (final AuditoryStimulus stimulus : getAuditoryStimuli()) {
            stimulus.play();
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                testFrame.invalidateScene();
            }

        });
    }

    public synchronized void removeVisualStimulus(final VisualStimulus visualStimulus) {
        visualStimuli.remove(visualStimulus);
    }

    public void renderReport(final PdfReport report, final TestInstance instance) throws Exception {
        final String title = getArtifactDescriptor().getId() + "   " + TimeUtil.toIsoTimestamp(instance.getTestTime());
        final PdfPageCanvas canvas = report.getCanvas(report.addPage());
        canvas.setFont(report.AR_B, 16);
        canvas.drawString(title, 100, 700);
        canvas.close();
    }

    public synchronized void resetStimuli() {
        visualStimuli.clear();
        auditoryStimuli.clear();
    }

    public void showCurtains(final Page page, final boolean first, final boolean last) {
        testFrame.showCurtains(page, first, last);
    }

    public void showScene() {
        testFrame.showScene();
    }

    protected void popScreencolor() {
        screenColorStack.pop();
    }

    protected void pushScreencolor(final HasScreencolor screenColor) {
        screenColorStack.push(screenColor);
    }

}
