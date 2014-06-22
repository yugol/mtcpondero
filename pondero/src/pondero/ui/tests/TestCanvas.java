package pondero.ui.tests;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.engine.test.Test;
import pondero.engine.test.responses.KeyPressResponse;
import pondero.engine.test.responses.MouseClickResponse;

@SuppressWarnings("serial")
public class TestCanvas extends TestVisualComponent {

    public TestCanvas(final Test test) {
        super(test);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent e) {
                if (test != null) {
                    test._doStep(new KeyPressResponse(e));
                }
            }

        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (test != null) {
                    test._doStep(new MouseClickResponse(e));
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
