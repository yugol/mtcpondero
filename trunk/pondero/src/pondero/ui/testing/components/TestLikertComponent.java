package pondero.ui.testing.components;

import java.awt.BorderLayout;
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
import pondero.task.responses.LikertResponse;
import pondero.tests.Test;
import pondero.tests.elements.trial.LikertConfig;
import pondero.ui.exceptions.ExceptionReporting;
import pondero.ui.testing.TestSceneComponent;
import pondero.util.StringUtil;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class TestLikertComponent extends TestSceneComponent {

    private final float     topFontSize = SwingUtil.getUiScaledDefaultFontSize();

    private final JPanel    pnlMain;
    private final JPanel    pnlInfo;
    private final JLabel    lblInfo;
    private final JLabel[]  labels;
    private final JButton[] buttons;

    public TestLikertComponent(final Test test, final LikertConfig config) {
        super(test);
        setLayout(new BorderLayout(0, 0));

        pnlMain = new JPanel();
        pnlMain.setOpaque(true);
        pnlMain.setBackground(config.getAnswersBgColor());
        add(pnlMain, BorderLayout.CENTER);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[config.getNumPoints()];
        gridBagLayout.rowHeights = new int[] { 1, 0, 0 };
        gridBagLayout.columnWeights = new double[config.getNumPoints()];
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        pnlMain.setLayout(gridBagLayout);

        pnlInfo = new JPanel();
        pnlInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlInfo.setBackground(config.getInfoBgColor());
        pnlInfo.setLayout(new BorderLayout(0, 0));
        final GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 10, 0);
        gbc_panel.weighty = 1.0;
        gbc_panel.gridwidth = config.getNumPoints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        pnlMain.add(pnlInfo, gbc_panel);

        lblInfo = new JLabel();
        lblInfo.setText("Info:");
        lblInfo.setOpaque(true);
        lblInfo.setBackground(config.getInfoBgColor());
        lblInfo.setForeground(config.getInfoFgColor());
        lblInfo.setFont(lblInfo.getFont().deriveFont(2 * topFontSize / 3));
        pnlInfo.add(lblInfo, BorderLayout.CENTER);

        labels = new JLabel[config.getNumPoints()];
        buttons = new JButton[config.getNumPoints()];

        setInfo(config.getInfo());
        for (int i = 0; i < config.getNumPoints(); ++i) {
            labels[i] = createLabel(i, config);
            setAnchor(i, config.getAnchor(i));
            buttons[i] = createButton(test, config.getStartIndex(), i);
        }
    }

    @Override
    public void reset() {
    }

    public void setAnchor(final int labelIndex, String txt) {
        if (StringUtil.isNullOrBlank(txt)) {
            txt = "";
        }
        labels[labelIndex].setText("<html><center>" + txt + "</center></html>");
    }

    public void setInfo(final String quiz) {
        if (StringUtil.isNullOrBlank(quiz)) {
            pnlInfo.setVisible(false);
        } else {
            pnlInfo.setVisible(true);
            lblInfo.setText("<html>" + quiz + "</html>");
        }
    }

    private JButton createButton(final Test test, final int startIndex, final int index) {
        final JButton btnAnchor = new JButton(" " + (startIndex + index) + " ");
        btnAnchor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    test.doNext(new LikertResponse(String.valueOf(startIndex + index)));
                } catch (final Exception e) {
                    ExceptionReporting.showExceptionMessage(null, e);
                }
            }

        });
        btnAnchor.setFont(btnAnchor.getFont().deriveFont(2 * topFontSize / 3));
        final GridBagConstraints gbc_btnAnchor = new GridBagConstraints();
        gbc_btnAnchor.weighty = 1.0;
        gbc_btnAnchor.weightx = 1.0;
        gbc_btnAnchor.insets = new Insets(0, 15, 10, 15);
        gbc_btnAnchor.gridx = index;
        gbc_btnAnchor.gridy = 2;
        pnlMain.add(btnAnchor, gbc_btnAnchor);
        return btnAnchor;
    }

    private JLabel createLabel(final int index, final LikertConfig config) {
        final JLabel lblAnchor = new JLabel("Anchor");
        final int fontStyle = index == 0 || index == labels.length - 1 ? Font.BOLD : Font.PLAIN;
        lblAnchor.setFont(lblAnchor.getFont().deriveFont(fontStyle, topFontSize / 2));
        lblAnchor.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnchor.setForeground(config.getAnswersFgColor());
        final GridBagConstraints gbc_lblAnchor = new GridBagConstraints();
        gbc_lblAnchor.fill = GridBagConstraints.BOTH;
        gbc_lblAnchor.insets = new Insets(0, 3, 3, 5);
        gbc_lblAnchor.gridx = index;
        gbc_lblAnchor.gridy = 1;
        pnlMain.add(lblAnchor, gbc_lblAnchor);
        return lblAnchor;
    }

}
