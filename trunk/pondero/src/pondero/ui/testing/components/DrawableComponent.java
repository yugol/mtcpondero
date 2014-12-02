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
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.testing.TestSceneComponent;

@SuppressWarnings("serial")
public class DrawableComponent extends TestSceneComponent {

    public DrawableComponent() {

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                try {

                    if (hasController()) {
                        getController().doStep(new KeyPressResponse(evt));
                    }
                } catch (final Exception e) {
                    ExceptionReporting.showExceptionMessage(null, e);
                }
            }

        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent evt) {
                try {
                    if (hasController()) {
                        getController().doStep(new MouseClickResponse(evt));
                    }
                } catch (final Exception e) {
                    ExceptionReporting.showExceptionMessage(null, e);
                }
            }

        });
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
