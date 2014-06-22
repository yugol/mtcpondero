package pondero.ui.update;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import pondero.engine.staples.DateUtil;
import pondero.ui.Messages;
import pondero.update.ArtifactDescriptor;

@SuppressWarnings("serial")
public class UpdateCellRenderer extends JPanel implements ListCellRenderer<ArtifactDescriptor> {

    private final List<Boolean> selection = new ArrayList<Boolean>();
    private final JLabel        lblId;
    private final JLabel        lblReleaseDate;
    private final JCheckBox     chckbxSelected;
    private final JLabel        lblIcon;

    public UpdateCellRenderer() {
        setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new EmptyBorder(5, 10, 10, 10)));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0 };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0 };
        setLayout(gridBagLayout);

        chckbxSelected = new JCheckBox("");
        chckbxSelected.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_chckbxSelected = new GridBagConstraints();
        gbc_chckbxSelected.insets = new Insets(0, 5, 5, 10);
        gbc_chckbxSelected.gridx = 0;
        gbc_chckbxSelected.gridy = 0;
        add(chckbxSelected, gbc_chckbxSelected);

        lblIcon = new JLabel("");
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblIcon = new GridBagConstraints();
        gbc_lblIcon.fill = GridBagConstraints.BOTH;
        gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
        gbc_lblIcon.gridx = 1;
        gbc_lblIcon.gridy = 0;
        add(lblIcon, gbc_lblIcon);

        lblId = new JLabel("id");
        GridBagConstraints gbc_lblId = new GridBagConstraints();
        gbc_lblId.anchor = GridBagConstraints.WEST;
        gbc_lblId.insets = new Insets(0, 0, 5, 0);
        gbc_lblId.gridx = 2;
        gbc_lblId.gridy = 0;
        add(lblId, gbc_lblId);

        lblReleaseDate = new JLabel("release");
        GridBagConstraints gbc_lblReleaseDate = new GridBagConstraints();
        gbc_lblReleaseDate.gridwidth = 2;
        gbc_lblReleaseDate.anchor = GridBagConstraints.WEST;
        gbc_lblReleaseDate.gridx = 1;
        gbc_lblReleaseDate.gridy = 1;
        add(lblReleaseDate, gbc_lblReleaseDate);
    }

    public void addElement(boolean downloadFlag) {
        selection.add(downloadFlag);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends ArtifactDescriptor> arg0,
            ArtifactDescriptor value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        if (selection.get(index)) {
            chckbxSelected.setSelected(true);
            setBackground(SystemColor.textHighlight);
            lblId.setForeground(SystemColor.textHighlightText);
            lblReleaseDate.setForeground(SystemColor.textHighlightText);
        } else {
            chckbxSelected.setSelected(false);
            setBackground(SystemColor.text);
            lblId.setForeground(SystemColor.textText);
            lblReleaseDate.setForeground(SystemColor.textText);
        }
        lblId.setText(value.getCodeName() + " (" + Messages.getString("lbl." + value.getType()) + ")");
        lblReleaseDate.setText(Messages.getString("lbl.release-date") + ":" + DateUtil.toUiDate(value.getReleaseDate()));
        if (value.isMandatory()) {
            lblIcon.setIcon(new ImageIcon(UpdateCellRenderer.class.getResource("/com/famfamfam/silk/exclamation.png")));
        } else {
            if (value.isProtected()) {
                lblIcon.setIcon(new ImageIcon(UpdateCellRenderer.class.getResource("/com/famfamfam/silk/key.png")));
            } else {
                lblIcon.setIcon(null);
            }
        }
        return this;
    }

    public int getSelectedCount() {
        int count = 0;
        for (boolean flag : selection) {
            if (flag) {
                ++count;
            }
        }
        return count;
    }

    public int getUpdateCount() {
        return selection.size();
    }

    public boolean isSelected(int index) {
        return selection.get(index);
    }

    public void removeElement(int index) {
        selection.remove(index);
    }

    public void toggle(int index) {
        boolean value = !selection.get(index);
        selection.set(index, value);
    }

}
