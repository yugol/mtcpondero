package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import pondero.L10n;
import pondero.task.Task;
import pondero.task.controllers.PageController;

@SuppressWarnings("serial")
public class TestCurtains extends JPanel {

    private static String getCharUiString(final Character ch) {
        if (ch == null) { return ""; }
        if (ch == ' ') { return L10n.getString("space-key-name"); }
        if (ch == '\n') { return L10n.getString("cr-key-name"); }
        return String.valueOf(ch);
    }

    private final JPanel    pnlCenter;
    private final JPanel    pnlNavigation;
    private final JButton   btnLeft;
    private final JButton   btnRight;
    private final JTextArea message;
    private final JPanel    pnlMessage;

    public TestCurtains(final Task task) {
        setBorder(null);
        setLayout(new BorderLayout());

        pnlCenter = new JPanel();
        pnlCenter.setBorder(new EmptyBorder(50, 75, 50, 75));
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(new BorderLayout(0, 0));

        pnlNavigation = new JPanel();
        pnlNavigation.setBorder(new EmptyBorder(6, 10, 5, 10));
        pnlCenter.add(pnlNavigation, BorderLayout.SOUTH);
        pnlNavigation.setLayout(new GridLayout(0, 2, 20, 0));

        btnLeft = new JButton("<<");
        pnlNavigation.add(btnLeft);

        btnRight = new JButton(">>");
        pnlNavigation.add(btnRight);

        pnlMessage = new JPanel();
        pnlMessage.setBorder(new EmptyBorder(4, 4, 4, 4));
        pnlMessage.setLayout(new BorderLayout(0, 0));
        pnlCenter.add(pnlMessage, BorderLayout.CENTER);

        message = new JTextArea();
        message.setOpaque(false);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(false);
        pnlMessage.add(message, BorderLayout.CENTER);
    }

    public void showInstructions(final PageController pageController) {
        message.setFont(pageController.getInstructFont());
        message.setText(pageController.getElement().getContent());
        pnlCenter.setBackground(pageController.getInstructScreenColor());
        pnlMessage.setBackground(pageController.getInstructScreenColor());
        pnlNavigation.setBackground(pageController.getInstructScreenColor());
        message.setForeground(pageController.getInstructTextColor());

        if (!pageController.getElement().isLast() && pageController.getInstructNextKey() != null) {
            btnRight.setText(L10n.getString("msg.press-key-for-next", getCharUiString(pageController.getInstructNextKey())));
            btnRight.setVisible(true);
        } else {
            btnRight.setVisible(false);
        }
        if (!pageController.getElement().isFirst() && pageController.getInstructPrevKey() != null) {
            btnLeft.setText(L10n.getString("msg.press-key-for-previous", getCharUiString(pageController.getInstructPrevKey())));
            btnLeft.setVisible(true);
        } else {
            btnLeft.setVisible(false);
        }

        setEventHandlers(new TaskKeyAdapter(pageController), new TaskMouseAdapter(pageController, false), new TaskMouseAdapter(pageController, true));
        message.requestFocus();
    }

    private void removeKeyListeners(final Component component) {
        for (final KeyListener listener : component.getKeyListeners()) {
            if (listener instanceof TaskKeyAdapter) {
                component.removeKeyListener(listener);
            }
        }
    }

    private void removeMouseListeners(final Component component) {
        for (final MouseListener listener : component.getMouseListeners()) {
            if (listener instanceof TaskMouseAdapter) {
                component.removeMouseListener(listener);
            }
        }
    }

    private void setEventHandlers(final KeyAdapter keyAdapter, final MouseAdapter prevMouseAdapter, final MouseAdapter nextMouseAdapter) {
        removeKeyListeners(pnlCenter);
        removeKeyListeners(pnlNavigation);
        removeKeyListeners(message);
        removeKeyListeners(btnLeft);
        removeKeyListeners(btnRight);

        pnlCenter.addKeyListener(keyAdapter);
        pnlNavigation.addKeyListener(keyAdapter);
        message.addKeyListener(keyAdapter);
        btnLeft.addKeyListener(keyAdapter);
        btnRight.addKeyListener(keyAdapter);

        removeMouseListeners(btnLeft);
        removeMouseListeners(btnRight);

        btnLeft.addMouseListener(prevMouseAdapter);
        btnRight.addMouseListener(nextMouseAdapter);
    }

}
