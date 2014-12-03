package pondero.ui.tests;

import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import pondero.tests.Test;
import pondero.util.NumberUtil;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class TestCellRenderer extends JPanel implements ListCellRenderer<Test> {

    private final JLabel lblImage;
    private final JLabel lblName;

    public TestCellRenderer() {
        lblImage = new JLabel("");
        lblImage.setIcon(new ImageIcon(TestCellRenderer.class.getResource("/com/famfamfam/silk/bricks.png")));
        add(lblImage);

        lblName = new JLabel("N/A");
        lblName.setFont(lblName.getFont().deriveFont(Font.BOLD));
        add(lblName);
    }

    @Override
    public Component getListCellRendererComponent(
            final JList<? extends Test> parent,
            final Test value,
            final int index,
            final boolean isSelected,
            final boolean cellHasFocus) {
        lblName.setText(value.getDescriptor().getCodeName());
        if (isSelected) {
            setBackground(SwingUtil.getListSelectedBackgroundColor());
            lblName.setForeground(SwingUtil.getListSelectedForegroundColor());
        } else {
            if (NumberUtil.isOdd(index)) {
                setBackground(SwingUtil.getListBackgroundOddColor());
            } else {
                setBackground(SwingUtil.getListBackgroundEvenColor());
            }
            lblName.setForeground(SwingUtil.getListForegroundColor());
        }
        return this;
    }

}
