package pondero.ui.testing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.tests.test.Test;
import pondero.tests.test.responses.KeyPressResponse;
import pondero.tests.test.responses.MouseClickResponse;
import pondero.ui.exceptions.ExceptionReporting;

@SuppressWarnings("serial")
public class TestCanvas extends TestVisualComponent {

    public TestCanvas(final Test test) {
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
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (hasTest()) {
            final Graphics2D g2d = (Graphics2D) g;
            getTest().drawScene(g2d, getWidth(), getHeight());
        }
    }

}
