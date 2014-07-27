package pondero.ui.update;

import static pondero.Logger.debug;
import static pondero.Logger.error;
import static pondero.Logger.info;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import pondero.Globals;
import pondero.engine.staples.StringUtil;
import pondero.ui.Messages;
import pondero.update.Artifact;
import pondero.update.UpdateEngine;
import pondero.update.UpdateListener;
import pondero.update.Updates;

@SuppressWarnings("serial")
public class UpdateDialog extends JDialog implements UpdateListener {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Globals.loadPreferences(null);
            UpdateDialog dialog = new UpdateDialog();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.beginUpdate();
        } catch (Exception e) {
            error(e);
        }
    }

    private final UpdateEngine       engine       = new UpdateEngine();
    private final UpdateCellRenderer cellRenderer = new UpdateCellRenderer();
    private boolean                  downloading  = false;

    private final JPanel             contentPanel = new JPanel();
    private JLabel                   lblTopStatus;
    private JProgressBar             progressBar;
    private JList<Artifact>          listUpdates;
    private JButton                  btnStart;
    private JScrollPane              scrollPane;

    public UpdateDialog() {
        this(null);
    }

    public UpdateDialog(Frame owner) {
        super(owner);
        engine.addListener(this);

        setTitle(Messages.getString("lbl.pondero-update"));
        setResizable(false);

        setBounds(100, 100, 450, 450);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(15, 15, 0, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 0, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        contentPanel.setLayout(gbl_contentPanel);
        {
            lblTopStatus = new JLabel("N/A");
            GridBagConstraints gbc_lblTopStatus = new GridBagConstraints();
            gbc_lblTopStatus.weightx = 1.0;
            gbc_lblTopStatus.insets = new Insets(0, 0, 5, 0);
            gbc_lblTopStatus.anchor = GridBagConstraints.EAST;
            gbc_lblTopStatus.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblTopStatus.gridx = 0;
            gbc_lblTopStatus.gridy = 0;
            contentPanel.add(lblTopStatus, gbc_lblTopStatus);
        }
        {
            progressBar = new JProgressBar();
            progressBar.setIndeterminate(true);

            GridBagConstraints gbc_progressBar = new GridBagConstraints();
            gbc_progressBar.insets = new Insets(5, 0, 5, 0);
            gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
            gbc_progressBar.gridx = 0;
            gbc_progressBar.gridy = 1;
            contentPanel.add(progressBar, gbc_progressBar);
        }
        {
            scrollPane = new JScrollPane();
            GridBagConstraints gbc_scrollPane = new GridBagConstraints();
            gbc_scrollPane.weightx = 1.0;
            gbc_scrollPane.weighty = 1.0;
            gbc_scrollPane.fill = GridBagConstraints.BOTH;
            gbc_scrollPane.gridx = 0;
            gbc_scrollPane.gridy = 2;
            contentPanel.add(scrollPane, gbc_scrollPane);
            {
                listUpdates = new JList<Artifact>();
                listUpdates.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if (listUpdates.isEnabled()) {
                            int index = listUpdates.locationToIndex(evt.getPoint());
                            Artifact update = listUpdates.getModel().getElementAt(index);
                            if (!update.isMandatory()) {
                                cellRenderer.toggle(index);
                                listUpdates.setSelectedIndices(new int[] {});
                                setUpdatesStatus();
                            }
                        }
                    }

                });
                listUpdates.setVisibleRowCount(4);
                listUpdates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listUpdates.setModel(new DefaultListModel<Artifact>());
                listUpdates.setCellRenderer(cellRenderer);
                scrollPane.setViewportView(listUpdates);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(5, 15, 10, 15));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnStart = new JButton(Messages.getString("lbl.start-update"));
                btnStart.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        beginDownload();
                    }

                });
                buttonPane.add(btnStart);
                getRootPane().setDefaultButton(btnStart);
            }
            {
                Component horizontalStrut = Box.createHorizontalStrut(1);
                buttonPane.add(horizontalStrut);
            }
            {
                JButton btnClose = new JButton(Messages.getString("lbl.close"));
                btnClose.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        UpdateDialog.this.setVisible(false);
                        if (!downloading) {
                            UpdateDialog.this.dispose();
                        }
                    }
                });
                buttonPane.add(btnClose);
            }
        }
    }

    public void beginUpdate() {
        engine.readUpdates();
    }

    @Override
    public void readRegistryEnded(Updates applicableUpdates) {
        for (Artifact update : applicableUpdates) {
            info("found update: " + update.getCodeName());
        }
        debug("reading update registry: ended");
        downloading = false;
        progressBar.setVisible(false);
        if (applicableUpdates.size() > 0) {
            setHeight(450);
            if (!isVisible()) {
                setVisible(true);
            }
            scrollPane.setVisible(true);
            listUpdates.setEnabled(true);
            populateList(applicableUpdates);
            btnStart.setVisible(true);
        }
        setUpdatesStatus();
        if (!isVisible()) {
            dispose();
        }
    }

    @Override
    public void readRegistryFailed(Exception e) {
        downloading = false;
        setTopStatusMessage(Messages.getString("msg.update-failed"));
        lblTopStatus.setForeground(Color.red);
        error(e);
    }

    @Override
    public void readRegistryStarted() {
        debug("reading update registry: started");
        downloading = true;
        setTopStatusMessage(Messages.getString("msg.downloading-update-list"));
        setHeight(150);
        progressBar.setVisible(true);
        scrollPane.setVisible(false);
        btnStart.setVisible(false);
    }

    @Override
    public void updateArtifactEnded(Artifact update) {
        DefaultListModel<Artifact> model = (DefaultListModel<Artifact>) listUpdates.getModel();
        int index = model.indexOf(update);
        model.remove(index);
        cellRenderer.removeElement(index);
    }

    @Override
    public void updateArtifactFailed(Artifact update, Exception e) {
        setTopStatusMessage(update.getCodeName() + " - " + Messages.getString("msg.update-failed"));
        lblTopStatus.setForeground(Color.red);
        error(e);
    }

    @Override
    public void updateArtifactStarted(Artifact update) {
        setTopStatusMessage(Messages.getString("msg.downloading-update") + ": " + update.getCodeName());
    }

    @Override
    public void updateProcessEnded() {
        downloading = false;
        setTopStatusMessage(Messages.getString("msg.downloading-updates-finished"));
        progressBar.setVisible(false);
        btnStart.setVisible(false);
        JOptionPane.showMessageDialog(this, Messages.getString("msg.update-process-finished"), getTitle(), JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    @Override
    public void updateProcessStarted(int updateCount) {
        downloading = true;
        listUpdates.setEnabled(false);
        progressBar.setVisible(true);
    }

    private void beginDownload() {
        Updates selectedUpdates = new Updates();
        for (int index = 0; index < cellRenderer.getUpdateCount(); ++index) {
            if (cellRenderer.isSelected(index)) {
                selectedUpdates.add(listUpdates.getModel().getElementAt(index));
            }
        }
        engine.downloadUpdates(selectedUpdates);
    }

    private void populateList(Updates applicableUpdates) {
        DefaultListModel<Artifact> listModel = (DefaultListModel<Artifact>) listUpdates.getModel();
        listModel.removeAllElements();
        for (Artifact update : applicableUpdates) {
            listModel.addElement(update);
            if (update.isMandatory()) {
                cellRenderer.addElement(true);
            } else {
                cellRenderer.addElement(!update.isProtected());
            }
        }
    }

    private void setHeight(int height) {
        setSize(getWidth(), height);
    }

    private void setTopStatusMessage(String message) {
        if (StringUtil.isNullOrBlank(message)) {
            message = "";
        }
        lblTopStatus.setText(message);
    }

    private void setUpdatesStatus() {
        if (cellRenderer.getUpdateCount() > 0) {
            setTopStatusMessage(Messages.getString("msg.selected-updates") + ": "
                    + cellRenderer.getSelectedCount() + "/" + cellRenderer.getUpdateCount());
        } else {
            setTopStatusMessage(Messages.getString("msg.application-is-up-to-date"));
        }
        btnStart.setEnabled(cellRenderer.getSelectedCount() > 0);
    }
}
