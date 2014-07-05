package pondero.ui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import pondero.UiUtil;
import pondero.engine.test.Test;

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
            JList<? extends Test> parent,
            Test value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        lblName.setText(value.getCodeName());
        if (isSelected) {
            setBackground(UiUtil.getListSelectedBackgroundColor());
            lblName.setForeground(UiUtil.getListSelectedForegroundColor());
        } else {
            if (index % 2 == 0) {
                setBackground(UiUtil.getListBackgroundEvenColor());
            } else {
                setBackground(UiUtil.getListBackgroundOddColor());
            }
            lblName.setForeground(UiUtil.getListForegroundColor());
        }
        return this;
    }

}
