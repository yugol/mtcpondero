package pondero.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pondero.Context;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {

    public static final int    DEFAULT       = 0;
    public static final int    ERROR         = 3;
    public static final int    SUCCESS       = 1;
    public static final int    WARNING       = 2;

    private static final Color DEFAULT_COLOR = Color.BLACK;
    private static final Color ERROR_COLOR   = Color.RED;
    private static final Color SUCCESS_COLOR = new Color(0, 128, 0);
    private static final Color WARNING_COLOR = Color.BLUE;

    private final JLabel       lblMessage;

    public StatusBar() {
        setBackground(SystemColor.control);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(10, (int) (18 * Context.getUiFontScaleFactor())));

        lblMessage = new JLabel("");
        add(lblMessage, BorderLayout.CENTER);

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel(new AngledLinesWindowsCornerIcon()), BorderLayout.SOUTH);
        rightPanel.setOpaque(false);
        add(rightPanel, BorderLayout.EAST);
    }

    public void setMessage(final int level, String message) {
        if (message == null) {
            message = "";
        }
        lblMessage.setText(" " + message);
        switch (level) {
            case WARNING:
                lblMessage.setForeground(WARNING_COLOR);
                break;
            case ERROR:
                lblMessage.setForeground(ERROR_COLOR);
                break;
            case SUCCESS:
                lblMessage.setForeground(SUCCESS_COLOR);
                break;
            default:
                lblMessage.setForeground(DEFAULT_COLOR);
                break;
        }
    }

}

class AngledLinesWindowsCornerIcon implements Icon {

    private static final int WIDTH  = 12;
    private static final int HEIGHT = 12;

    @Override
    public int getIconHeight() {
        return WIDTH + 1;
    }

    @Override
    public int getIconWidth() {
        return HEIGHT + 1;
    }

    @Override
    public void paintIcon(final Component c, final Graphics g, final int _x, final int _y) {
        g.setColor(SystemColor.controlShadow);
        for (int x = 0; x < WIDTH; x += 4) {
            for (int y = 0; y < HEIGHT; y += 4) {
                if (x + y >= 8) {
                    g.fillRect(x, y, 3, 3);
                }
            }
        }
    }

}
