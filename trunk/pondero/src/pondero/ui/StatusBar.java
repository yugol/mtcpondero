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
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(10, 23));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel(new AngledLinesWindowsCornerIcon()), BorderLayout.SOUTH);
        rightPanel.setOpaque(false);

        add(rightPanel, BorderLayout.EAST);
        setBackground(SystemColor.control);

        lblMessage = new JLabel("");
        add(lblMessage, BorderLayout.CENTER);
    }

    public void setMessage(int level, String message) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int y = 0;
        g.setColor(SystemColor.controlDkShadow);
        g.drawLine(0, y, getWidth(), y);
        y++;
        g.setColor(SystemColor.controlShadow);
        g.drawLine(0, y, getWidth(), y);
        y++;
        g.setColor(SystemColor.controlHighlight);
        g.drawLine(0, y, getWidth(), y);
    }

}

class AngledLinesWindowsCornerIcon implements Icon {

    private static final Color WHITE_LINE_COLOR = Color.white;
    private static final Color GRAY_LINE_COLOR  = SystemColor.controlShadow;
    private static final int   WIDTH            = 13;
    private static final int   HEIGHT           = 13;

    @Override
    public int getIconHeight() {
        return WIDTH;
    }

    @Override
    public int getIconWidth() {
        return HEIGHT;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {

        g.setColor(WHITE_LINE_COLOR);
        g.drawLine(0, 12, 12, 0);
        g.drawLine(5, 12, 12, 5);
        g.drawLine(10, 12, 12, 10);

        g.setColor(GRAY_LINE_COLOR);
        g.drawLine(1, 12, 12, 1);
        g.drawLine(2, 12, 12, 2);
        g.drawLine(3, 12, 12, 3);

        g.drawLine(6, 12, 12, 6);
        g.drawLine(7, 12, 12, 7);
        g.drawLine(8, 12, 12, 8);

        g.drawLine(11, 12, 12, 11);
        g.drawLine(12, 12, 12, 12);

    }
}
