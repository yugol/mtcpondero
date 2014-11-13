package pondero.ui.testing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import pondero.L10n;
import pondero.tests.elements.other.Page;
import pondero.tests.test.Test;
import pondero.tests.test.responses.PrevNextResponse;

@SuppressWarnings("serial")
public class TestCurtains extends JPanel {

    private static String getCharUiString(final Character ch) {
        if (ch == null) { return ""; }
        if (ch == ' ') { return L10n.getString("space-key-name"); }
        if (ch == '\n') { return L10n.getString("cr-key-name"); }
        return String.valueOf(ch);
    }

    protected final Test    test;
    private final JPanel    pnlCenter;
    private final JPanel    pnlNavigation;
    private final JButton   btnLeft;
    private final JButton   btnRight;
    private final JTextArea message;
    private final JPanel    pnlMessage;

    public TestCurtains(final Test test) {
        this.test = test;
        setBorder(null);
        setLayout(new BorderLayout());

        pnlCenter = new JPanel();
        pnlCenter.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                try {
                    handleKeyEvent(evt);
                } catch (final Exception e) {
                }
            }

        });
        pnlCenter.setBorder(new EmptyBorder(50, 75, 50, 75));
        add(pnlCenter, BorderLayout.CENTER);
        pnlCenter.setLayout(new BorderLayout(0, 0));

        pnlNavigation = new JPanel();
        pnlNavigation.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                try {
                    handleKeyEvent(evt);
                } catch (final Exception e) {
                }
            }

        });
        pnlNavigation.setBorder(new EmptyBorder(6, 10, 5, 10));
        pnlCenter.add(pnlNavigation, BorderLayout.SOUTH);
        pnlNavigation.setLayout(new GridLayout(0, 2, 20, 0));

        btnLeft = new JButton("<<"); //$NON-NLS-1$
        btnLeft.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent evt) {
                final PrevNextResponse input = new PrevNextResponse();
                input.setPrev(true);
                try {
                    test.doStep(input);
                } catch (final Exception e) {
                }
            }

        });
        btnLeft.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                try {
                    handleKeyEvent(evt);
                } catch (final Exception e) {
                }
            }

        });
        pnlNavigation.add(btnLeft);

        btnRight = new JButton(">>"); //$NON-NLS-1$
        btnRight.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent evt) {
                final PrevNextResponse input = new PrevNextResponse();
                input.setNext(true);
                try {
                    test.doStep(input);
                } catch (final Exception e) {
                }
            }

        });
        btnRight.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                try {
                    handleKeyEvent(evt);
                } catch (final Exception e) {
                }
            }
        });
        pnlNavigation.add(btnRight);

        pnlMessage = new JPanel();
        pnlMessage.setBorder(new EmptyBorder(4, 4, 4, 4));
        pnlMessage.setLayout(new BorderLayout(0, 0));
        pnlCenter.add(pnlMessage, BorderLayout.CENTER);

        message = new JTextArea();
        message.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent evt) {
                try {
                    handleKeyEvent(evt);
                } catch (final Exception e) {
                }
            }

        });
        message.setOpaque(false);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setEditable(false);
        pnlMessage.add(message, BorderLayout.CENTER);

    }

    public void showInstructions(final Page page, final boolean first, final boolean last) {
        setBackground(test.getScreenColor());
        pnlCenter.setBackground(test.getScreenColor());

        message.setBackground(test.getInstructions().getScreenColor());
        message.setForeground(test.getInstructions().getTextColor());
        message.setFont(test.getInstructions().getFont());
        message.setText(page.$content());

        pnlNavigation.setBackground(test.getInstructions().getScreenColor());
        if (!last && test.getInstructions().getNextkey() != null) {
            btnRight.setVisible(true);
            btnRight.setText(L10n.getString("msg.press-key-for-next", getCharUiString(test.getInstructions().getNextkey()))); //$NON-NLS-1$
        } else {
            btnRight.setVisible(false);
        }
        if (!first && test.getInstructions().getPrevkey() != null) {
            btnLeft.setVisible(true);
            btnLeft.setText(L10n.getString("msg.press-key-for-previous", getCharUiString(test.getInstructions().getPrevkey()))); //$NON-NLS-1$
        } else {
            btnLeft.setVisible(false);
        }

        message.requestFocus();
    }

    private void handleKeyEvent(final KeyEvent evt) throws Exception {
        final PrevNextResponse input = new PrevNextResponse();
        input.setNext(evt.getKeyChar() == test.getInstructions().getNextkey());
        input.setPrev(evt.getKeyChar() == test.getInstructions().getPrevkey());
        test.doStep(input);
    }

}
