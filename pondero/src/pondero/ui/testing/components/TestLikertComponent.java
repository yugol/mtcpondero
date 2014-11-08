package pondero.ui.testing.components;

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
import pondero.tests.test.Test;
import pondero.tests.test.responses.LikertResponse;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.testing.TestSceneComponent;

@SuppressWarnings("serial")
public class TestLikertComponent extends TestSceneComponent {

    private static final Color QUIZ_BACKGROUND          = new Color(79, 129, 189);
    private static final float ANCHOR_LABEL_SIZE_FACTOR = 1.0f;

    private final JLabel       lblQuiz;
    private final JLabel[]     labels;
    private final JButton[]    buttons;
    private final JPanel       panel;

    public TestLikertComponent(final Test test, final int anchorCount, final int startIndex) {
        super(test);
        setBorder(null);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[anchorCount];
        gridBagLayout.rowHeights = new int[] { 1, 0, 0 };
        gridBagLayout.columnWeights = new double[anchorCount];
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        setLayout(gridBagLayout);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setBackground(QUIZ_BACKGROUND);
        final GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 10, 0);
        gbc_panel.weighty = 1.0;
        gbc_panel.gridwidth = anchorCount;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        add(panel, gbc_panel);
        panel.setLayout(new BorderLayout(0, 0));

        lblQuiz = new JLabel();
        lblQuiz.setText("ABC");
        lblQuiz.setOpaque(true);
        lblQuiz.setBackground(QUIZ_BACKGROUND);
        lblQuiz.setForeground(Color.WHITE);
        lblQuiz.setFont(new Font("Dialog", Font.BOLD, 24));
        panel.add(lblQuiz, BorderLayout.CENTER);

        labels = new JLabel[anchorCount];
        buttons = new JButton[anchorCount];

        for (int i = 0; i < anchorCount; ++i) {
            labels[i] = createLabel(i);
            buttons[i] = createButton(test, startIndex, i);
        }
    }

    @Override
    public void reset() {
    }

    public void setAnchor(final int labelIndex, final String txt) {
        labels[labelIndex].setText("<html>" + txt + "</html>");
    }

    public void setQuiz(final String quiz) {
        lblQuiz.setText("<html>" + quiz + "</html>");
    }

    private JButton createButton(final Test test, final int startIndex, final int index) {
        final JButton btnAnchor = new JButton("[" + (startIndex + index) + "]");
        final GridBagConstraints gbc_btnAnchor = new GridBagConstraints();
        gbc_btnAnchor.weighty = 1.0;
        gbc_btnAnchor.weightx = 1.0;
        gbc_btnAnchor.insets = new Insets(0, 15, 10, 15);
        gbc_btnAnchor.gridx = index;
        gbc_btnAnchor.gridy = 2;
        add(btnAnchor, gbc_btnAnchor);
        btnAnchor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    test.doStep(new LikertResponse(String.valueOf(startIndex + index)));
                } catch (final Exception e) {
                    ExceptionReporting.showExceptionMessage(null, e);
                }
            }

        });
        return btnAnchor;
    }

    private JLabel createLabel(final int index) {
        final JLabel lblAnchor = new JLabel("Anchor");
        final GridBagConstraints gbc_lblAnchor = new GridBagConstraints();
        gbc_lblAnchor.fill = GridBagConstraints.BOTH;
        gbc_lblAnchor.insets = new Insets(0, 3, 3, 5);
        gbc_lblAnchor.gridx = index;
        gbc_lblAnchor.gridy = 1;
        add(lblAnchor, gbc_lblAnchor);
        lblAnchor.setFont(lblAnchor.getFont().deriveFont(Font.BOLD, lblAnchor.getFont().getSize() * ANCHOR_LABEL_SIZE_FACTOR));
        lblAnchor.setHorizontalAlignment(SwingConstants.CENTER);
        return lblAnchor;
    }

}
