package pondero.ui.testing.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.task.responses.KeyPressResponse;
import pondero.task.responses.MouseClickResponse;
import pondero.task.stimuli.VisualStimulus;
import pondero.tests.Test;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.testing.TestSceneComponent;

@SuppressWarnings("serial")
public class TestDrawableComponent extends TestSceneComponent {

    public TestDrawableComponent() {
        this(null);
    }

    public TestDrawableComponent(final Test test) {
        super(test);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                if (test != null) {
                    try {
                        test.doStep(new KeyPressResponse(evt));
                    } catch (final Exception e) {
                        ExceptionReporting.showExceptionMessage(null, e);
                    }
                }
            }

        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent evt) {
                if (test != null) {
                    try {
                        test.doStep(new MouseClickResponse(evt));
                    } catch (final Exception e) {
                        ExceptionReporting.showExceptionMessage(null, e);
                    }
                }
            }

        });
    }

    @Override
    public void reset() {
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        final int width = getWidth();
        final int height = getHeight();
        if (hasTest()) {
            getTest().drawScene(g2d, width, height);
        }
        if (hasController()) {
            g2d.setColor(getController().getScreenColor());
            g2d.fillRect(0, 0, width, height);
            for (final VisualStimulus stimulus : getVisualStimuli()) {
                stimulus.render(g2d, width, height);
            }
        }
    }

}
