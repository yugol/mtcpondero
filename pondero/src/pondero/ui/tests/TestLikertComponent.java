package pondero.ui.tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import pondero.engine.test.Test;
import pondero.engine.test.responses.KeyPressResponse;

@SuppressWarnings("serial")
public class TestLikertComponent extends TestVisualComponent {

    private static final float ANCHOR_LABEL_SIZE_FACTOR = 1.0f;

    private final JLabel       lblQuiz;
    private final JLabel       lbl1;
    private final JLabel       lbl2;
    private final JLabel       lbl3;
    private final JLabel       lbl4;
    private final JLabel       lbl5;
    private final JLabel       lbl6;
    private final JLabel       lbl7;
    private final JLabel       lbl8;
    private final JLabel       lbl9;
    private final JButton      btn1;
    private final JButton      btn2;
    private final JButton      btn3;
    private final JButton      btn4;
    private final JButton      btn5;
    private final JButton      btn6;
    private final JButton      btn7;
    private final JButton      btn8;
    private final JButton      btn9;
    private final JPanel       panel;

    private final Color        quizBackground           = new Color(79, 129, 189);

    public TestLikertComponent(final Test test) {
        super(test);

        setBorder(null);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 1, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        setLayout(gridBagLayout);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(quizBackground);
        final GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 10, 0);
        gbc_panel.weighty = 1.0;
        gbc_panel.gridwidth = 9;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(panel, gbc_panel);
        panel.setLayout(new BorderLayout(0, 0));

        lblQuiz = new JLabel();
        lblQuiz.setText("ABC");
        lblQuiz.setOpaque(true);
        lblQuiz.setBackground(quizBackground);
        lblQuiz.setForeground(Color.WHITE);
        lblQuiz.setFont(new Font("Dialog", Font.BOLD, 24));
        panel.add(lblQuiz, BorderLayout.CENTER);

        lbl1 = new JLabel("Anchor");
        lbl1.setFont(lbl1.getFont().deriveFont(Font.BOLD, lbl1.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl1 = new GridBagConstraints();
        gbc_lbl1.fill = GridBagConstraints.BOTH;
        gbc_lbl1.insets = new Insets(0, 3, 3, 5);
        gbc_lbl1.gridx = 0;
        gbc_lbl1.gridy = 1;
        add(lbl1, gbc_lbl1);

        lbl2 = new JLabel("Anchor");
        lbl2.setFont(lbl2.getFont().deriveFont(Font.BOLD, lbl2.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl2.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl2 = new GridBagConstraints();
        gbc_lbl2.fill = GridBagConstraints.BOTH;
        gbc_lbl2.insets = new Insets(0, 3, 3, 5);
        gbc_lbl2.gridx = 1;
        gbc_lbl2.gridy = 1;
        add(lbl2, gbc_lbl2);

        lbl3 = new JLabel("Anchor");
        lbl3.setFont(lbl3.getFont().deriveFont(Font.BOLD, lbl3.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl3.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl3 = new GridBagConstraints();
        gbc_lbl3.fill = GridBagConstraints.BOTH;
        gbc_lbl3.insets = new Insets(0, 3, 3, 5);
        gbc_lbl3.gridx = 2;
        gbc_lbl3.gridy = 1;
        add(lbl3, gbc_lbl3);

        lbl4 = new JLabel("Anchor");
        lbl4.setFont(lbl4.getFont().deriveFont(Font.BOLD, lbl4.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl4.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl4 = new GridBagConstraints();
        gbc_lbl4.fill = GridBagConstraints.BOTH;
        gbc_lbl4.insets = new Insets(0, 3, 3, 5);
        gbc_lbl4.gridx = 3;
        gbc_lbl4.gridy = 1;
        add(lbl4, gbc_lbl4);

        lbl5 = new JLabel("Anchor");
        lbl5.setFont(lbl5.getFont().deriveFont(Font.BOLD, lbl5.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl5.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl5 = new GridBagConstraints();
        gbc_lbl5.fill = GridBagConstraints.BOTH;
        gbc_lbl5.insets = new Insets(0, 3, 3, 5);
        gbc_lbl5.gridx = 4;
        gbc_lbl5.gridy = 1;
        add(lbl5, gbc_lbl5);

        lbl6 = new JLabel("Anchor");
        lbl6.setFont(lbl6.getFont().deriveFont(Font.BOLD, lbl6.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl6.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl6 = new GridBagConstraints();
        gbc_lbl6.fill = GridBagConstraints.BOTH;
        gbc_lbl6.insets = new Insets(0, 3, 3, 5);
        gbc_lbl6.gridx = 5;
        gbc_lbl6.gridy = 1;
        add(lbl6, gbc_lbl6);

        lbl7 = new JLabel("Anchor");
        lbl7.setFont(lbl7.getFont().deriveFont(Font.BOLD, lbl7.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl7.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl7 = new GridBagConstraints();
        gbc_lbl7.fill = GridBagConstraints.BOTH;
        gbc_lbl7.insets = new Insets(0, 3, 3, 5);
        gbc_lbl7.gridx = 6;
        gbc_lbl7.gridy = 1;
        add(lbl7, gbc_lbl7);

        lbl8 = new JLabel("Anchor");
        lbl8.setFont(lbl8.getFont().deriveFont(Font.BOLD, lbl8.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl8.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl8 = new GridBagConstraints();
        gbc_lbl8.fill = GridBagConstraints.BOTH;
        gbc_lbl8.insets = new Insets(0, 3, 3, 5);
        gbc_lbl8.gridx = 7;
        gbc_lbl8.gridy = 1;
        add(lbl8, gbc_lbl8);

        lbl9 = new JLabel("Anchor");
        lbl9.setFont(lbl9.getFont().deriveFont(Font.BOLD, lbl9.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lbl9.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lbl9 = new GridBagConstraints();
        gbc_lbl9.fill = GridBagConstraints.BOTH;
        gbc_lbl9.insets = new Insets(0, 3, 3, 5);
        gbc_lbl9.gridx = 8;
        gbc_lbl9.gridy = 1;
        add(lbl9, gbc_lbl9);

        btn1 = new JButton("[1]");
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("1"));
            }
        });
        final GridBagConstraints gbc_btn1 = new GridBagConstraints();
        gbc_btn1.weighty = 1.0;
        gbc_btn1.weightx = 1.0;
        gbc_btn1.insets = new Insets(0, 15, 10, 15);
        gbc_btn1.gridx = 0;
        gbc_btn1.gridy = 2;
        add(btn1, gbc_btn1);

        btn2 = new JButton("[2]");
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("2"));
            }
        });
        final GridBagConstraints gbc_btn2 = new GridBagConstraints();
        gbc_btn2.weighty = 1.0;
        gbc_btn2.weightx = 1.0;
        gbc_btn2.insets = new Insets(0, 15, 10, 15);
        gbc_btn2.gridx = 1;
        gbc_btn2.gridy = 2;
        add(btn2, gbc_btn2);

        btn3 = new JButton("[3]");
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("3"));
            }
        });
        final GridBagConstraints gbc_btn3 = new GridBagConstraints();
        gbc_btn3.weighty = 1.0;
        gbc_btn3.weightx = 1.0;
        gbc_btn3.insets = new Insets(0, 15, 10, 15);
        gbc_btn3.gridx = 2;
        gbc_btn3.gridy = 2;
        add(btn3, gbc_btn3);

        btn4 = new JButton("[4]");
        btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("4"));
            }
        });
        final GridBagConstraints gbc_btn4 = new GridBagConstraints();
        gbc_btn4.weighty = 1.0;
        gbc_btn4.weightx = 1.0;
        gbc_btn4.insets = new Insets(0, 15, 10, 15);
        gbc_btn4.gridx = 3;
        gbc_btn4.gridy = 2;
        add(btn4, gbc_btn4);

        btn5 = new JButton("[5]");
        btn5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("5"));
            }
        });
        final GridBagConstraints gbc_btn5 = new GridBagConstraints();
        gbc_btn5.weighty = 1.0;
        gbc_btn5.weightx = 1.0;
        gbc_btn5.insets = new Insets(0, 15, 10, 15);
        gbc_btn5.gridx = 4;
        gbc_btn5.gridy = 2;
        add(btn5, gbc_btn5);

        btn6 = new JButton("[6]");
        btn6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("6"));
            }
        });
        final GridBagConstraints gbc_btn6 = new GridBagConstraints();
        gbc_btn6.weighty = 1.0;
        gbc_btn6.weightx = 1.0;
        gbc_btn6.insets = new Insets(0, 15, 10, 15);
        gbc_btn6.gridx = 5;
        gbc_btn6.gridy = 2;
        add(btn6, gbc_btn6);

        btn7 = new JButton("[7]");
        btn7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("7"));
            }
        });
        final GridBagConstraints gbc_btn7 = new GridBagConstraints();
        gbc_btn7.weighty = 1.0;
        gbc_btn7.weightx = 1.0;
        gbc_btn7.insets = new Insets(0, 15, 10, 15);
        gbc_btn7.gridx = 6;
        gbc_btn7.gridy = 2;
        add(btn7, gbc_btn7);

        btn8 = new JButton("[8]");
        btn8.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("8"));
            }
        });
        final GridBagConstraints gbc_btn8 = new GridBagConstraints();
        gbc_btn8.weightx = 1.0;
        gbc_btn8.insets = new Insets(0, 15, 10, 15);
        gbc_btn8.gridx = 7;
        gbc_btn8.gridy = 2;
        add(btn8, gbc_btn8);

        btn9 = new JButton("[9]");
        btn9.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                test.doStep(new KeyPressResponse("9"));
            }
        });
        final GridBagConstraints gbc_btn9 = new GridBagConstraints();
        gbc_btn9.weighty = 1.0;
        gbc_btn9.weightx = 1.0;
        gbc_btn9.insets = new Insets(0, 15, 10, 15);
        gbc_btn9.gridx = 8;
        gbc_btn9.gridy = 2;
        add(btn9, gbc_btn9);
    }

    public void setAnchor(final int labelIndex, final String txt) {
        getLabel(labelIndex).setText("<html>" + txt + "</html>");
    }

    public void setPointCount(final int pc) {
        int i = 0;
        JLabel lbl = getLabel(i++);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));

        for (; i < pc - 1; ++i) {
            lbl = getLabel(i);
            lbl.setFont(lbl.getFont().deriveFont(Font.PLAIN));
            lbl.setVisible(true);
            getButton(i).setVisible(true);
        }

        lbl = getLabel(i);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        lbl.setVisible(true);
        getButton(i).setVisible(true);

        for (++i; i < maxAnchorCount(); ++i) {
            getLabel(i).setVisible(false);
            getButton(i).setVisible(false);
        }
    }

    public void setQuiz(final String quiz) {
        lblQuiz.setText("<html>" + quiz + "</html>");
    }

    private JButton getButton(final int index) {
        switch (index) {
            case 0:
                return btn1;
            case 1:
                return btn2;
            case 2:
                return btn3;
            case 3:
                return btn4;
            case 4:
                return btn5;
            case 5:
                return btn6;
            case 6:
                return btn7;
            case 7:
                return btn8;
            case 8:
                return btn9;
            default:
                return null;
        }
    }

    private JLabel getLabel(final int index) {
        switch (index) {
            case 0:
                return lbl1;
            case 1:
                return lbl2;
            case 2:
                return lbl3;
            case 3:
                return lbl4;
            case 4:
                return lbl5;
            case 5:
                return lbl6;
            case 6:
                return lbl7;
            case 7:
                return lbl8;
            case 8:
                return lbl9;
            default:
                return null;
        }
    }

    private int maxAnchorCount() {
        return 9;
    }

}
