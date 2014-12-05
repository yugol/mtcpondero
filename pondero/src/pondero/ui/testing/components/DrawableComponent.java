package pondero.ui.testing.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import pondero.task.stimuli.VisualStimulus;
import pondero.ui.testing.TestSceneComponent;

@SuppressWarnings("serial")
public class DrawableComponent extends TestSceneComponent {

    public DrawableComponent() {
        addKeyListener(senzorKeyAdapter);
        addMouseListener(senzorMouseAdapter);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        final int width = getWidth();
        final int height = getHeight();

        if (hasController()) {
            g2d.setColor(getController().getScreenColor());
            g2d.fillRect(0, 0, width, height);
            for (final VisualStimulus stimulus : getVisualStimuli()) {
                stimulus.render(g2d, width, height);
            }
        }
    }

}
