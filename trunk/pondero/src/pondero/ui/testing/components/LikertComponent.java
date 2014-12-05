package pondero.ui.testing.components;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import pondero.task.controllers.TrialController;
import pondero.tests.elements.workflow.trials.LikertConfig;
import pondero.tests.interfaces.HasLikertConfig;
import pondero.ui.testing.TestSceneComponent;
import pondero.util.StringUtil;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class LikertComponent extends TestSceneComponent {

    private static final int MAX_NUM_POINTS = 20;

    private final float      topFontSize    = SwingUtil.getUiScaledDefaultFontSize();

    private final JPanel     pnlMain;
    private final JPanel     pnlInfo;
    private final JLabel     lblInfo;
    private final JLabel[]   labels;
    private final JButton[]  buttons;

    public LikertComponent() {
        addKeyListener(senzorKeyAdapter);
        setLayout(new BorderLayout());

        pnlMain = new JPanel();
        pnlMain.addKeyListener(senzorKeyAdapter);
        pnlMain.setOpaque(true);
        add(pnlMain, BorderLayout.CENTER);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[] { 1, 0, 0 };
        gridBagLayout.columnWidths = new int[MAX_NUM_POINTS];
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        gridBagLayout.columnWeights = new double[MAX_NUM_POINTS];
        pnlMain.setLayout(gridBagLayout);

        pnlInfo = new JPanel();
        pnlInfo.addKeyListener(senzorKeyAdapter);
        pnlInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlInfo.setLayout(new BorderLayout(0, 0));
        final GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 10, 0);
        gbc_panel.weighty = 1.0;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        gbc_panel.gridwidth = MAX_NUM_POINTS;
        pnlMain.add(pnlInfo, gbc_panel);

        lblInfo = new JLabel();
        lblInfo.addKeyListener(senzorKeyAdapter);
        lblInfo.setText("Info:");
        lblInfo.setOpaque(true);
        lblInfo.setFont(lblInfo.getFont().deriveFont(2 * topFontSize / 3));
        pnlInfo.add(lblInfo, BorderLayout.CENTER);

        labels = new JLabel[MAX_NUM_POINTS];
        buttons = new JButton[MAX_NUM_POINTS];

        for (int i = 0; i < MAX_NUM_POINTS; ++i) {
            labels[i] = createLabel(i);
            buttons[i] = createButton(i);
        }
    }

    @Override
    public void setController(final TrialController controller) {
        super.setController(controller);
        final LikertConfig config = ((HasLikertConfig) controller.getElement()).getLikertConfig();

        pnlMain.setBackground(config.getAnswersBgColor());

        pnlInfo.setBackground(config.getInfoBgColor());
        lblInfo.setBackground(config.getInfoBgColor());
        lblInfo.setForeground(config.getInfoFgColor());
        if (StringUtil.isNullOrBlank(config.getInfo())) {
            pnlInfo.setVisible(false);
        } else {
            pnlInfo.setVisible(true);
            lblInfo.setText("<html>" + config.getInfo() + "</html>");
        }

        for (int i = 0; i < config.getNumPoints(); ++i) {
            final String txt = StringUtil.isNullOrBlank(config.getAnchor(i)) ? "" : config.getAnchor(i);
            labels[i].setForeground(config.getAnswersFgColor());
            labels[i].setText("<html><center>" + txt + "</center></html>");
            labels[i].setVisible(true);
            buttons[i].setText(" " + (config.getStartIndex() + i) + " ");
            buttons[i].setVisible(true);
        }
        for (int i = config.getNumPoints(); i < MAX_NUM_POINTS; ++i) {
            labels[i].setVisible(false);
            buttons[i].setVisible(false);
        }
    }

    private JButton createButton(final int index) {
        final JButton btnAnchor = new JButton(" * ");
        btnAnchor.addKeyListener(senzorKeyAdapter);
        btnAnchor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent evt) {
                final KeyEvent kEvt = new KeyEvent(LikertComponent.this, 0, 0, 0, 0, btnAnchor.getText().trim().charAt(0));
                senzorKeyAdapter.keyPressed(kEvt);
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

    private JLabel createLabel(final int index) {
        final JLabel lblAnchor = new JLabel("Anchor");
        lblAnchor.addKeyListener(senzorKeyAdapter);
        lblAnchor.setFont(lblAnchor.getFont().deriveFont(topFontSize / 2));
        lblAnchor.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lblAnchor = new GridBagConstraints();
        gbc_lblAnchor.fill = GridBagConstraints.BOTH;
        gbc_lblAnchor.insets = new Insets(0, 3, 3, 5);
        gbc_lblAnchor.gridx = index;
        gbc_lblAnchor.gridy = 1;
        pnlMain.add(lblAnchor, gbc_lblAnchor);
        return lblAnchor;
    }

}
