package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import pondero.L10n;
import pondero.task.Testable;
import pondero.tests.elements.other.Page;

@SuppressWarnings("serial")
public class TestCurtains extends JPanel {

    private static String getCharUiString(final Character ch) {
        if (ch == null) { return ""; }
        if (ch == ' ') { return L10n.getString("space-key-name"); }
        if (ch == '\n') { return L10n.getString("cr-key-name"); }
        return String.valueOf(ch);
    }

    private final JPanel           pnlCenter;
    private final JPanel           pnlNavigation;
    private final JButton          btnLeft;
    private final JButton          btnRight;
    private final JTextArea        message;
    private final JPanel           pnlMessage;

    private final Testable         test;
    private final TestKeyAdapter   defaultKeyAdapter;
    private final TestMouseAdapter defaultMouseAdapter;

    public TestCurtains(final Testable test) {
        defaultKeyAdapter = new TestKeyAdapter(test);
        defaultMouseAdapter = new TestMouseAdapter(test);
        this.test = test;
        setBorder(null);
        setLayout(new BorderLayout());

        pnlCenter = new JPanel();
        pnlCenter.addKeyListener(defaultKeyAdapter);
        pnlCenter.setBorder(new EmptyBorder(50, 75, 50, 75));
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(new BorderLayout(0, 0));

        pnlNavigation = new JPanel();
        pnlNavigation.addKeyListener(defaultKeyAdapter);
        pnlNavigation.setBorder(new EmptyBorder(6, 10, 5, 10));
        pnlCenter.add(pnlNavigation, BorderLayout.SOUTH);
        pnlNavigation.setLayout(new GridLayout(0, 2, 20, 0));

        btnLeft = new JButton("<<");
        btnLeft.addMouseListener(defaultMouseAdapter);
        btnLeft.addKeyListener(defaultKeyAdapter);
        pnlNavigation.add(btnLeft);

        btnRight = new JButton(">>");
        btnRight.addMouseListener(defaultMouseAdapter);
        btnRight.addKeyListener(defaultKeyAdapter);
        pnlNavigation.add(btnRight);

        pnlMessage = new JPanel();
        pnlMessage.setBorder(new EmptyBorder(4, 4, 4, 4));
        pnlMessage.setLayout(new BorderLayout(0, 0));
        pnlCenter.add(pnlMessage, BorderLayout.CENTER);

        message = new JTextArea();
        message.setOpaque(false);
        message.addKeyListener(defaultKeyAdapter);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(false);
        pnlMessage.add(message, BorderLayout.CENTER);
    }

    public void showInstructions(final Page page, final boolean first, final boolean last) {
        message.setFont(test.getInstructions().getFont());
        message.setText(page.$content());

        if (!last && test.getInstructions().getNextkey() != null) {
            btnRight.setText(L10n.getString("msg.press-key-for-next", getCharUiString(test.getInstructions().getNextkey())));
            btnRight.setVisible(true);
        } else {
            btnRight.setVisible(false);
        }
        if (!first && test.getInstructions().getPrevkey() != null) {
            btnLeft.setText(L10n.getString("msg.press-key-for-previous", getCharUiString(test.getInstructions().getPrevkey())));
            btnLeft.setVisible(true);
        } else {
            btnLeft.setVisible(false);
        }

        message.requestFocus();
    }

}
