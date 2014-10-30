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
import pondero.tests.elements.interfaces.HasScreencolor;
import pondero.tests.elements.other.Page;
import pondero.tests.elements.stimulus.Text;
import pondero.tests.staples.Coordinates;
import pondero.tests.test.stimuli.VisualStimulus;
import pondero.ui.testing.TestFrame;

public abstract class TestRenderer extends TestBase {

    private final TestFrame             testFrame;

    private final Stack<HasScreencolor> screenColorStack = new Stack<HasScreencolor>();
    private final List<VisualStimulus>  visualStimuli    = new ArrayList<VisualStimulus>(); ;

    public TestRenderer() {
        super();
        testFrame = new TestFrame((Test) this);
    }

    public void addVisualStimulus(final VisualStimulus stimulus) {
        visualStimuli.add(stimulus);
    }

    public void drawScene(final Graphics2D g2d, final int surfaceWidth, final int surfaceHeight) {
        g2d.setColor(getScreencolor());
        g2d.fillRect(0, 0, surfaceWidth, surfaceHeight);
        for (final VisualStimulus stimulus : getVisualStimuli()) {
            stimulus.render(g2d, surfaceWidth, surfaceHeight);
        }
    }

    public Color getScreencolor() {
        return peekScreencolor()._getScreencolor();
    }

    public TestFrame getTestWindow() {
        return testFrame;
    }

    public Coordinates getTextSize(final Text text) {
        final Graphics g = testFrame.getGraphics();
        g.setFont(text._getFont());
        final FontMetrics fm = g.getFontMetrics();
        final Rectangle2D bounds = fm.getStringBounds("abc", g);
        return new Coordinates(bounds.getX(), bounds.getCenterY());
    }

    public List<VisualStimulus> getVisualStimuli() {
        return new ArrayList<VisualStimulus>(visualStimuli);
    }

    public HasScreencolor peekScreencolor() {
        return screenColorStack.peek();
    }

    public void presentStimuli() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                testFrame.invalidateScene();
            }

        });
    }

    public void removeVisualStimulus(final VisualStimulus visualStimulus) {
        visualStimuli.remove(visualStimulus);
    }

    public void resetStimuli() {
        resetVisualStimuli();
    }

    public void resetVisualStimuli() {
        visualStimuli.clear();
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
