package pondero.engine.test;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.SwingUtilities;
import pondero.engine.elements.interfaces.HasScreencolor;
import pondero.engine.elements.other.Page;
import pondero.engine.elements.stimulus.Text;
import pondero.engine.staples.Coordinates;
import pondero.engine.test.stimuli.VisualStimulus;
import pondero.ui.tests.TestWindow;

public abstract class TestRenderer extends TestBase {

    private final TestWindow            testWindow;

    private final Stack<HasScreencolor> screenColorStack = new Stack<HasScreencolor>();
    private final List<VisualStimulus>  visualStimuli    = new ArrayList<VisualStimulus>(); ;

    public TestRenderer() {
        super();
        testWindow = new TestWindow((Test) this);
    }

    public void addVisualStimulus(final VisualStimulus stimulus) {
        visualStimuli.add(stimulus);
    }

    public void drawScene(final Graphics2D g2d, final int surfaceWidth, final int surfaceHeight) {
        g2d.setColor(getScreencolor());
        g2d.fillRect(0, 0, surfaceWidth, surfaceHeight);
        final List<VisualStimulus> localStimuli = new ArrayList<VisualStimulus>(visualStimuli);
        for (final VisualStimulus stimulus : localStimuli) {
            stimulus.render(g2d, surfaceWidth, surfaceHeight);
        }
    }

    public Color getScreencolor() {
        return peekScreencolor()._getScreencolor();
    }

    public TestWindow getTestWindow() {
        return testWindow;
    }

    public Coordinates getTextSize(final Text text) {
        final Graphics g = testWindow.getGraphics();
        g.setFont(text._getFont());
        final FontMetrics fm = g.getFontMetrics();
        final Rectangle2D bounds = fm.getStringBounds("abc", g);
        return new Coordinates(bounds.getX(), bounds.getCenterY());
    }

    public HasScreencolor peekScreencolor() {
        return screenColorStack.peek();
    }

    public void presentStimuli() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                testWindow.invalidateScene();
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

    public void showInstructions(final Page page, final boolean first, final boolean last) {
        testWindow.showInstructions(page, first, last);
    }

    public void showScene() {
        testWindow.showScene();
    }

    protected void popScreencolor() {
        screenColorStack.pop();
    }

    protected void pushScreencolor(final HasScreencolor screenColor) {
        screenColorStack.push(screenColor);
    }

}
