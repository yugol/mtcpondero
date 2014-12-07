package pondero.ui.testing.components;

import java.awt.BorderLayout;
import java.awt.Font;
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
import pondero.Constants;
import pondero.task.controllers.TrialController;
import pondero.tests.elements.workflow.trials.LikertConfig;
import pondero.tests.interfaces.HasLikertConfig;
import pondero.ui.testing.SceneComponent;
import pondero.util.StringUtil;

@SuppressWarnings("serial")
public class LikertComponent extends SceneComponent {

    private static final int MAX_NUM_POINTS = 20;

    private final JPanel     pnlAnchors;
    private final JPanel     pnlInfo;
    private final JLabel     lblInfo;
    private final JLabel[]   labels;
    private final JButton[]  buttons;

    public LikertComponent() {
        addKeyListener(senzorKeyAdapter);
        setLayout(new BorderLayout());

        pnlInfo = new JPanel();
        pnlInfo.addKeyListener(senzorKeyAdapter);
        pnlInfo.setBorder(new EmptyBorder(5, 15, 5, 15));
        pnlInfo.setLayout(new BorderLayout(0, 0));
        add(pnlInfo, BorderLayout.CENTER);

        lblInfo = new JLabel();
        lblInfo.addKeyListener(senzorKeyAdapter);
        lblInfo.setText("Info:");
        lblInfo.setOpaque(true);
        lblInfo.setFont(lblInfo.getFont().deriveFont(Constants.H2_FONT_SIZE));
        pnlInfo.add(lblInfo, BorderLayout.CENTER);

        pnlAnchors = new JPanel();
        pnlAnchors.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlAnchors.addKeyListener(senzorKeyAdapter);
        pnlAnchors.setOpaque(true);
        add(pnlAnchors, BorderLayout.SOUTH);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[] { 1, 0, 0 };
        gridBagLayout.columnWidths = new int[MAX_NUM_POINTS];
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
        gridBagLayout.columnWeights = new double[MAX_NUM_POINTS];
        pnlAnchors.setLayout(gridBagLayout);

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

        pnlAnchors.setBackground(config.getAnswersBgColor());

        pnlInfo.setBackground(config.getInfoBgColor());
        lblInfo.setBackground(config.getInfoBgColor());
        lblInfo.setForeground(config.getInfoFgColor());
        if (StringUtil.isNullOrBlank(config.getInfo())) {
            pnlInfo.setVisible(false);
        } else {
            pnlInfo.setVisible(true);
            lblInfo.setText("<html>" + config.getInfo() + "</html>");
        }

        final int lastIndex = config.getNumPoints() - 1;
        for (int i = 0; i <= lastIndex; ++i) {
            final JLabel lblAnchor = labels[i];
            final String txt = StringUtil.isNullOrBlank(config.getAnchor(i)) ? "" : config.getAnchor(i);
            lblAnchor.setForeground(config.getAnswersFgColor());
            lblAnchor.setText("<html><center>" + txt + "</center></html>");
            if (i == 0 || i == lastIndex) {
                lblAnchor.setFont(lblAnchor.getFont().deriveFont(Font.BOLD));
            } else {
                lblAnchor.setFont(lblAnchor.getFont().deriveFont(Font.PLAIN));
            }
            lblAnchor.setVisible(true);

            final JButton btnAnchor = buttons[i];
            btnAnchor.setText(" " + (config.getStartIndex() + i) + " ");
            btnAnchor.setVisible(true);
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
        btnAnchor.setFont(btnAnchor.getFont().deriveFont(Constants.H3_FONT_SIZE));
        final GridBagConstraints gbc_btnAnchor = new GridBagConstraints();
        gbc_btnAnchor.weighty = 1.0;
        gbc_btnAnchor.weightx = 1.0;
        gbc_btnAnchor.insets = new Insets(0, 15, 10, 15);
        gbc_btnAnchor.gridx = index;
        gbc_btnAnchor.gridy = 2;
        pnlAnchors.add(btnAnchor, gbc_btnAnchor);
        return btnAnchor;
    }

    private JLabel createLabel(final int index) {
        final JLabel lblAnchor = new JLabel("Anchor");
        lblAnchor.addKeyListener(senzorKeyAdapter);
        lblAnchor.setFont(lblAnchor.getFont().deriveFont(Constants.H3_FONT_SIZE));
        lblAnchor.setHorizontalAlignment(SwingConstants.CENTER);
        final GridBagConstraints gbc_lblAnchor = new GridBagConstraints();
        gbc_lblAnchor.fill = GridBagConstraints.BOTH;
        gbc_lblAnchor.insets = new Insets(0, 3, 3, 5);
        gbc_lblAnchor.gridx = index;
        gbc_lblAnchor.gridy = 1;
        pnlAnchors.add(lblAnchor, gbc_lblAnchor);
        return lblAnchor;
    }

}
