package pondero.ui.testing.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.task.responses.KeyPressResponse;
import pondero.task.responses.MouseClickResponse;
import pondero.tests.Test;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.testing.TestSceneComponent;

@SuppressWarnings("serial")
public class TestDrawableComponent extends TestSceneComponent {

    public TestDrawableComponent(final Test test) {
        super(test);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                if (test != null) {
                    try {
                        test.doNext(new KeyPressResponse(evt));
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
                        test.doNext(new MouseClickResponse(evt));
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
        if (hasTest()) {
            final Graphics2D g2d = (Graphics2D) g;
            getTest().drawScene(g2d, getWidth(), getHeight());
        }
    }

}
