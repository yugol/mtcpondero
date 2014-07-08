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
import pondero.L10n;
import pondero.update.Artifact;
import pondero.update.UpdateEngine;
import pondero.update.UpdateListener;
import pondero.update.Updates;
import pondero.util.StringUtil;
import pondero.util.UiUtil;

@SuppressWarnings("serial")
public class UpdateDialog extends JDialog implements UpdateListener {

    public static final String DIALOG_NAME = "updateDialog";

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        try {
            Globals.loadPreferences(null);
            final UpdateDialog dialog = new UpdateDialog();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.beginUpdate();
        } catch (final Exception e) {
            error(e);
        }
    }

    private final UpdateEngine         engine       = new UpdateEngine();
    private final ArtifactCellRenderer cellRenderer = new ArtifactCellRenderer();
    private boolean                    downloading  = false;

    private final JPanel               contentPanel = new JPanel();
    private JLabel                     lblTopStatus;
    private JProgressBar               progressBar;
    private JList<Artifact>            listUpdates;
    private JButton                    btnStart;
    private JScrollPane                scrollPane;

    public UpdateDialog() {
        this(null);
    }

    public UpdateDialog(final Frame owner) {
        super(owner);
        setName(DIALOG_NAME);
        setType(Type.UTILITY);
        engine.addListener(this);

        setTitle(L10n.getString("lbl.pondero-update"));
        setResizable(false);

        setBounds(100, 100, (int) (450 * Globals.getUiFontScaleFactor()), (int) (450 * Globals.getUiFontScaleFactor()));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(15, 15, 0, 15));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        final GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] { 0, 0 };
        gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        contentPanel.setLayout(gbl_contentPanel);
        {
            lblTopStatus = new JLabel("N/A");
            final GridBagConstraints gbc_lblTopStatus = new GridBagConstraints();
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

            final GridBagConstraints gbc_progressBar = new GridBagConstraints();
            gbc_progressBar.insets = new Insets(5, 0, 5, 0);
            gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
            gbc_progressBar.gridx = 0;
            gbc_progressBar.gridy = 1;
            contentPanel.add(progressBar, gbc_progressBar);
        }
        {
            scrollPane = new JScrollPane();
            final GridBagConstraints gbc_scrollPane = new GridBagConstraints();
            gbc_scrollPane.weightx = 1.0;
            gbc_scrollPane.weighty = 1.0;
            gbc_scrollPane.fill = GridBagConstraints.BOTH;
            gbc_scrollPane.gridx = 0;
            gbc_scrollPane.gridy = 2;
            contentPanel.add(scrollPane, gbc_scrollPane);
            {
                listUpdates = new JList<Artifact>();
                listUpdates.setBackground(UiUtil.getListBackgroundColor());
                listUpdates.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(final MouseEvent evt) {
                        if (listUpdates.isEnabled()) {
                            final int index = listUpdates.locationToIndex(evt.getPoint());
                            final Artifact update = listUpdates.getModel().getElementAt(index);
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
            final JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EmptyBorder(5, 15, 10, 15));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnStart = new JButton(L10n.getString("lbl.start-update"));
                btnStart.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent evt) {
                        beginDownload();
                    }

                });
                buttonPane.add(btnStart);
                getRootPane().setDefaultButton(btnStart);
            }
            {
                final Component horizontalStrut = Box.createHorizontalStrut(1);
                buttonPane.add(horizontalStrut);
            }
            {
                final JButton btnClose = new JButton(L10n.getString("lbl.close"));
                btnClose.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent evt) {
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
    public void readRegistryEnded(final Updates applicableUpdates) {
        for (final Artifact update : applicableUpdates) {
            info("found update: " + update.getCodeName());
        }
        debug("reading update registry: ended");
        downloading = false;
        progressBar.setVisible(false);
        if (applicableUpdates.size() > 0) {
            setHeight((int) (450 * Globals.getUiFontScaleFactor()));
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
    public void readRegistryFailed(final Exception e) {
        downloading = false;
        setTopStatusMessage(L10n.getString("msg.update-failed"));
        lblTopStatus.setForeground(Color.red);
        error(e);
    }

    @Override
    public void readRegistryStarted() {
        debug("reading update registry: started");
        downloading = true;
        setTopStatusMessage(L10n.getString("msg.downloading-update-list"));
        setHeight((int) (150 * Globals.getUiFontScaleFactor()));
        progressBar.setVisible(true);
        scrollPane.setVisible(false);
        btnStart.setVisible(false);
    }

    @Override
    public void updateArtifactEnded(final Artifact update) {
        final DefaultListModel<Artifact> model = (DefaultListModel<Artifact>) listUpdates.getModel();
        final int index = model.indexOf(update);
        model.remove(index);
        cellRenderer.removeElement(index);
    }

    @Override
    public void updateArtifactFailed(final Artifact update, final Exception e) {
        setTopStatusMessage(update.getCodeName() + " - " + L10n.getString("msg.update-failed"));
        lblTopStatus.setForeground(Color.red);
        error(e);
    }

    @Override
    public void updateArtifactStarted(final Artifact update) {
        setTopStatusMessage(L10n.getString("msg.downloading-update") + ": " + update.getCodeName());
    }

    @Override
    public void updateProcessEnded() {
        downloading = false;
        setTopStatusMessage(L10n.getString("msg.downloading-updates-finished"));
        progressBar.setVisible(false);
        btnStart.setVisible(false);
        JOptionPane.showMessageDialog(this, L10n.getString("msg.update-process-finished"), getTitle(), JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    @Override
    public void updateProcessStarted(final int updateCount) {
        downloading = true;
        listUpdates.setEnabled(false);
        progressBar.setVisible(true);
    }

    private void beginDownload() {
        final Updates selectedUpdates = new Updates();
        for (int index = 0; index < cellRenderer.getUpdateCount(); ++index) {
            if (cellRenderer.isSelected(index)) {
                selectedUpdates.add(listUpdates.getModel().getElementAt(index));
            }
        }
        engine.downloadUpdates(selectedUpdates);
    }

    private void populateList(final Updates applicableUpdates) {
        final DefaultListModel<Artifact> listModel = (DefaultListModel<Artifact>) listUpdates.getModel();
        listModel.removeAllElements();
        for (final Artifact update : applicableUpdates) {
            listModel.addElement(update);
            if (update.isMandatory()) {
                cellRenderer.addElement(true);
            } else {
                cellRenderer.addElement(!update.isProtected());
            }
        }
    }

    private void setHeight(final int height) {
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
            setTopStatusMessage(L10n.getString("msg.selected-updates") + ": "
                    + cellRenderer.getSelectedCount() + "/" + cellRenderer.getUpdateCount());
        } else {
            setTopStatusMessage(L10n.getString("msg.application-is-up-to-date"));
        }
        btnStart.setEnabled(cellRenderer.getSelectedCount() > 0);
    }

}
