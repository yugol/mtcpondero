package pondero.ui.testing.components;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import pondero.Constants;
import pondero.tests.Test;
import pondero.ui.testing.TestSceneComponent;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class ItemQuestionComponent extends TestSceneComponent {

    private final JLabel lblQuestion = new JLabel("1. Question?"); ;

    public ItemQuestionComponent(final Test test) {
        super(test);
        setLayout(new BorderLayout(0, 0));

        final JPanel pnlMain = new JPanel();
        pnlMain.setOpaque(true);
        pnlMain.setBackground(Constants.DEFAULT_QUESTION_BG_COLOR);
        add(pnlMain, BorderLayout.CENTER);

        lblQuestion.setOpaque(true);
        lblQuestion.setBackground(Constants.DEFAULT_QUESTION_BG_COLOR);
        lblQuestion.setForeground(Constants.DEFAULT_QUESTION_FG_COLOR);
        lblQuestion.setFont(lblQuestion.getFont().deriveFont(Font.BOLD, SwingUtil.getUiScaledDefaultFontSize()));
        lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        pnlMain.setLayout(gridBagLayout);

        final GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(40, 40, 40, 40);
        gbc_panel.weightx = 1.0;
        gbc_panel.anchor = GridBagConstraints.CENTER;
        gbc_panel.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        pnlMain.add(lblQuestion, gbc_panel);
    }

    @Override
    public void reset() {
    }

    public void setQuestion(final String question) {
        lblQuestion.setText("<html><center>" + question + "</center></html>");
    }

}
