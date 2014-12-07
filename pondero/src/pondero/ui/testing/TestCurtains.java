package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import pondero.Constants;
import pondero.L10n;
import pondero.task.Task;
import pondero.task.controllers.PageController;
import pondero.tests.interfaces.IsController;

@SuppressWarnings("serial")
public class TestCurtains extends JPanel implements Senzor {

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

    private PageController  pageController;

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
        btnLeft.setFont(btnLeft.getFont().deriveFont(Constants.H3_FONT_SIZE));
        pnlNavigation.add(btnLeft);

        btnRight = new JButton(">>");
        btnRight.setFont(btnRight.getFont().deriveFont(Constants.H3_FONT_SIZE));
        pnlNavigation.add(btnRight);

        pnlMessage = new JPanel();
        pnlMessage.setBorder(new EmptyBorder(4, 4, 4, 4));
        pnlMessage.setLayout(new BorderLayout(0, 0));
        pnlCenter.add(pnlMessage, BorderLayout.CENTER);

        message = new JTextArea();
        message.setBorder(null);
        message.setOpaque(false);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(false);
        pnlMessage.add(message, BorderLayout.CENTER);

        // event handlers
        final KeyAdapter keyAdapter = new TaskKeyAdapter(this);
        pnlCenter.addKeyListener(keyAdapter);
        pnlNavigation.addKeyListener(keyAdapter);
        message.addKeyListener(keyAdapter);
        btnLeft.addKeyListener(keyAdapter);
        btnRight.addKeyListener(keyAdapter);

        final MouseAdapter prevMouseAdapter = new TaskMouseAdapter(this, false);
        btnLeft.addMouseListener(prevMouseAdapter);

        final MouseAdapter nextMouseAdapter = new TaskMouseAdapter(this, true);
        btnRight.addMouseListener(nextMouseAdapter);
    }

    @Override
    public IsController getController() {
        return pageController;
    }

    public void showInstructions(final PageController pageController) {
        this.pageController = pageController;
        message.setFont(pageController.getInstructFont());
        message.setText(pageController.getElement().getContent());
        pnlCenter.setBackground(pageController.getInstructScreenColor());
        pnlMessage.setBackground(pageController.getInstructScreenColor());
        pnlNavigation.setBackground(pageController.getInstructScreenColor());
        message.setBackground(pageController.getInstructScreenColor());
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

        btnRight.requestFocus();
    }

}
