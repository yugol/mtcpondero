package pondero.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import pondero.Globals;
import pondero.OsUtil;
import pondero.engine.test.Test;
import pondero.engine.test.launch.TaskLauncher;
import pondero.engine.test.launch.TestReport;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.entities.Participant;
import pondero.tests.TestLoader;
import pondero.ui.actions.ChooseParticipantAction;
import pondero.ui.actions.ManageParticipantsAction;
import pondero.ui.actions.OpenDocumentAction;
import pondero.ui.actions.QuitAction;
import pondero.ui.actions.SaveDocumentAction;
import pondero.ui.actions.StartTaskAction;
import pondero.ui.actions.TaskAction;
import pondero.ui.update.UpdateDialog;

public class Pondero implements TaskLauncher {

    private static final float START_BUTTON_SIZE_FACTOR = 1.2f;
    private static final int   INFO                     = 0;
    private static final int   WARNING                  = 1;
    private static final int   ERROR                    = 2;
    private static final int   SUCCESS                  = 3;

    /**
     * Launch the application.
     * 
     * @throws Exception
     */
    public static void main(final String... args) throws Exception {
        String home = args.length >= 1 ? args[0] : null;
        Globals.loadPreferences(home);
        OsUtil.setupMainWindow(Messages.getString("lbl.pondero"));//$NON-NLS-1$
        OsUtil.setLaf();
        OsUtil.factorFontSize(Globals.getUiScaleFactor());
        OsUtil.localizeJOptionPaneButtons();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    final Pondero window = new Pondero();
                    TestLoader.registerTests(window);
                    OsUtil.factorFontSize(Globals.getUiScaleFactor());
                    window.openWorkbook(Globals.getLastWorkbookFile());
                    window.frmMain.setVisible(true);
                    new UpdateDialog(window.frmMain).beginUpdate();
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private final Action chooseParticipantAction  = new ChooseParticipantAction(this);
    private final Action manageParticipantsAction = new ManageParticipantsAction(this);
    private final Action mnApplication            = new SaveDocumentAction(this);
    private final Action openDocumentAction       = new OpenDocumentAction(this);
    private final Action quitAction               = new QuitAction(this);
    private final Action startTaskAction          = new StartTaskAction(this);

    private Participant  currentParticipant       = null;
    private Test         currentTask              = null;
    private Workbook     currentWorkbook          = null;

    private JButton      btnStartTask;
    private JFrame       frmMain;
    private JLabel       lblDocumentName;
    private JLabel       lblParticipant;
    private JLabel       lblParticipantName;
    private JLabel       lblTask;
    private JLabel       lblTaskName;
    private JLabel       lblTaskStatus;
    private JMenu        mnTasks;
    private JMenuItem    mntmDocumentSave;
    private JMenuItem    mntmDocumentSaveAs;
    private JMenuItem    mntmParticipantSelect;

    public Pondero() {
        initialize();
        setCurrentParticipant(null);
        setCurrentTask(null);
        btnStartTask.setMargin(new Insets(5, 10, 6, 14));
    }

    public Participant getCurrentParticipant() {
        return currentParticipant;
    }

    public Test getCurrentTask() {
        return currentTask;
    }

    public Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    public Component getFrame() {
        return frmMain;
    }

    @Override
    public void onTaskEnded(final Test task, final TestReport report) {
        switch (report.getEndCode()) {
            case TestReport.END_KILL:
                setStatus(Messages.getString("msg.test-interrupted", report.getEndCode()), ERROR); //$NON-NLS-1$
                break;
            default:
                setStatus(Messages.getString("msg.test-completed", report.getRunningTimeInSeconds()), SUCCESS); //$NON-NLS-1$
        }
    }

    @Override
    public void onTaskStarted(final Test task) {
        setStatus(Messages.getString("msg.test-in-progress"), INFO); //$NON-NLS-1$
    }

    public void openWorkbook(final File workbookFile) throws Exception {
        currentWorkbook = WorkbookFactory.openWorkbook(workbookFile);
        mntmParticipantSelect.setEnabled(true);
        mntmDocumentSave.setEnabled(true);
        // mntmDocumentSaveAs.setEnabled(true);
        lblDocumentName.setText(workbookFile.getName());
    }

    public void registerTask(final Test task) {
        task.setLauncher(this);
        final JMenuItem mntmTask = new JMenuItem();
        mntmTask.setAction(new TaskAction(this, task));
        mnTasks.add(mntmTask);
    }

    public void setCurrentParticipant(final Participant currentParticipant) {
        this.currentParticipant = currentParticipant;
        if (currentParticipant == null) {
            lblParticipant.setForeground(Color.BLUE);
            lblParticipantName.setText(Messages.getString("lbl.n-a")); //$NON-NLS-1$
        } else {
            lblParticipant.setForeground(Color.BLACK);
            lblParticipantName.setText(currentParticipant.getName() + " " + currentParticipant.getSurname()); //$NON-NLS-1$
        }
    }

    public void setCurrentTask(final Test task) {
        currentTask = task;
        if (task != null) {
            lblTask.setForeground(Color.BLACK);
            lblTaskName.setText(task.getCodeName());
            btnStartTask.setEnabled(true);
            setStatus(Messages.getString("msg.press-start-to-start", Messages.getString("lbl.start")), INFO); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            lblTask.setForeground(Color.RED);
            lblTaskName.setText(Messages.getString("lbl.n-a")); //$NON-NLS-1$
            lblTaskName.setToolTipText(null);
            btnStartTask.setEnabled(false);
            setStatus(Messages.getString("msg.please-choose-test"), ERROR); //$NON-NLS-1$
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frmMain = new JFrame();
        frmMain.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                quitAction.actionPerformed(null);
            }

        });
        frmMain.setTitle(Messages.getString("lbl.pondero")); //$NON-NLS-1$
        frmMain.setBounds(100, 100, 700, 350);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JMenuBar menuBar = new JMenuBar();
        frmMain.setJMenuBar(menuBar);

        final JMenu mnApp = new JMenu(" " + Messages.getString("lbl.application") + " "); //$NON-NLS-1$
        menuBar.add(mnApp);

        final JMenuItem mntmPreferences = new JMenuItem(Messages.getString("lbl.preferences")); //$NON-NLS-1$
        mntmPreferences.setEnabled(false);
        mnApp.add(mntmPreferences);

        mnApp.addSeparator();

        final JMenuItem mntmQuit = new JMenuItem();
        mntmQuit.setAction(quitAction);
        mnApp.add(mntmQuit);

        final JMenu mnDocuments = new JMenu(" " + Messages.getString("lbl.registers") + " "); //$NON-NLS-1$
        menuBar.add(mnDocuments);

        final JMenuItem mntmDocumentOpen = new JMenuItem();
        mntmDocumentOpen.setAction(openDocumentAction);
        mnDocuments.add(mntmDocumentOpen);

        mnDocuments.addSeparator();

        mntmDocumentSave = new JMenuItem();
        mntmDocumentSave.setAction(mnApplication);
        mntmDocumentSave.setEnabled(false);
        mnDocuments.add(mntmDocumentSave);

        mntmDocumentSaveAs = new JMenuItem(Messages.getString("lbl.save-as...")); //$NON-NLS-1$
        mntmDocumentSaveAs.setEnabled(false);
        mnDocuments.add(mntmDocumentSaveAs);

        final JMenu mnParticipants = new JMenu(" " + Messages.getString("lbl.participants") + " "); //$NON-NLS-1$
        menuBar.add(mnParticipants);

        mntmParticipantSelect = new JMenuItem();
        mntmParticipantSelect.setAction(chooseParticipantAction);
        mnParticipants.add(mntmParticipantSelect);

        mnParticipants.addSeparator();

        final JMenuItem mntmParticipantUpdate = new JMenuItem();
        mntmParticipantUpdate.setAction(manageParticipantsAction);
        mnParticipants.add(mntmParticipantUpdate);

        mnTasks = new JMenu(" " + Messages.getString("lbl.tests") + " "); //$NON-NLS-1$
        menuBar.add(mnTasks);

        final JPanel pnlComposition = new JPanel();
        pnlComposition.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 0, 10), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
        frmMain.getContentPane().add(pnlComposition, BorderLayout.NORTH);
        final GridBagLayout gbl_pnlComposition = new GridBagLayout();
        gbl_pnlComposition.columnWidths = new int[] { 0, 0, 0 };
        gbl_pnlComposition.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_pnlComposition.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_pnlComposition.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        pnlComposition.setLayout(gbl_pnlComposition);

        final JLabel lblDocument = new JLabel(Messages.getString("lbl.data-register") + ":"); //$NON-NLS-1$  //$NON-NLS-2$
        lblDocument.setFont(lblDocument.getFont().deriveFont(Font.BOLD));
        final GridBagConstraints gbc_lblDocument = new GridBagConstraints();
        gbc_lblDocument.anchor = GridBagConstraints.EAST;
        gbc_lblDocument.insets = new Insets(10, 10, 10, 5);
        gbc_lblDocument.gridx = 0;
        gbc_lblDocument.gridy = 0;
        pnlComposition.add(lblDocument, gbc_lblDocument);

        lblDocumentName = new JLabel(Messages.getString("lbl.n-a")); //$NON-NLS-1$
        lblDocumentName.setFont(lblDocumentName.getFont().deriveFont(Font.ITALIC));
        lblDocumentName.setHorizontalAlignment(SwingConstants.LEFT);
        final GridBagConstraints gbc_lblDocumentName = new GridBagConstraints();
        gbc_lblDocumentName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblDocumentName.weightx = 1.0;
        gbc_lblDocumentName.insets = new Insets(10, 5, 10, 0);
        gbc_lblDocumentName.gridx = 1;
        gbc_lblDocumentName.gridy = 0;
        pnlComposition.add(lblDocumentName, gbc_lblDocumentName);

        lblParticipant = new JLabel(Messages.getString("lbl.participant") + ":"); //$NON-NLS-1$  //$NON-NLS-2$
        lblParticipant.setFont(lblParticipant.getFont().deriveFont(Font.BOLD));
        final GridBagConstraints gbc_lblParticipant = new GridBagConstraints();
        gbc_lblParticipant.insets = new Insets(5, 0, 10, 5);
        gbc_lblParticipant.anchor = GridBagConstraints.EAST;
        gbc_lblParticipant.gridx = 0;
        gbc_lblParticipant.gridy = 1;
        pnlComposition.add(lblParticipant, gbc_lblParticipant);

        lblParticipantName = new JLabel(Messages.getString("lbl.n-a")); //$NON-NLS-1$
        lblParticipantName.setFont(lblParticipantName.getFont().deriveFont(Font.ITALIC));
        final GridBagConstraints gbc_lblParticipantName = new GridBagConstraints();
        gbc_lblParticipantName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblParticipantName.weightx = 1.0;
        gbc_lblParticipantName.insets = new Insets(5, 5, 10, 0);
        gbc_lblParticipantName.gridx = 1;
        gbc_lblParticipantName.gridy = 1;
        pnlComposition.add(lblParticipantName, gbc_lblParticipantName);

        lblTask = new JLabel(Messages.getString("lbl.test") + ":"); //$NON-NLS-1$  //$NON-NLS-2$
        lblTask.setFont(lblTask.getFont().deriveFont(Font.BOLD));
        final GridBagConstraints gbc_lblTask = new GridBagConstraints();
        gbc_lblTask.insets = new Insets(5, 0, 10, 5);
        gbc_lblTask.anchor = GridBagConstraints.EAST;
        gbc_lblTask.gridx = 0;
        gbc_lblTask.gridy = 2;
        pnlComposition.add(lblTask, gbc_lblTask);

        lblTaskName = new JLabel(Messages.getString("lbl.n-a")); //$NON-NLS-1$
        lblTaskName.setFont(lblTaskName.getFont().deriveFont(Font.ITALIC));
        final GridBagConstraints gbc_lblTaskName = new GridBagConstraints();
        gbc_lblTaskName.anchor = GridBagConstraints.NORTH;
        gbc_lblTaskName.insets = new Insets(5, 5, 10, 0);
        gbc_lblTaskName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblTaskName.weightx = 1.0;
        gbc_lblTaskName.gridx = 1;
        gbc_lblTaskName.gridy = 2;
        pnlComposition.add(lblTaskName, gbc_lblTaskName);

        lblTaskStatus = new JLabel(Messages.getString("lbl.n-a"));//$NON-NLS-1$
        lblTaskStatus.setFont(lblTaskStatus.getFont().deriveFont(Font.ITALIC));
        lblTaskStatus.setHorizontalAlignment(SwingConstants.TRAILING);
        final GridBagConstraints gbc_lblTaskEndingStatus = new GridBagConstraints();
        gbc_lblTaskEndingStatus.insets = new Insets(20, 0, 10, 10);
        gbc_lblTaskEndingStatus.weightx = 1.0;
        gbc_lblTaskEndingStatus.gridwidth = 2;
        gbc_lblTaskEndingStatus.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblTaskEndingStatus.gridx = 0;
        gbc_lblTaskEndingStatus.gridy = 3;
        pnlComposition.add(lblTaskStatus, gbc_lblTaskEndingStatus);

        final JPanel pnlAction = new JPanel();
        frmMain.getContentPane().add(pnlAction, BorderLayout.CENTER);
        final GridBagLayout gbl_pnlAction = new GridBagLayout();
        gbl_pnlAction.columnWidths = new int[] { 0, 0, 0 };
        gbl_pnlAction.rowHeights = new int[] { 0, 0 };
        gbl_pnlAction.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_pnlAction.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        pnlAction.setLayout(gbl_pnlAction);

        btnStartTask = new JButton(Messages.getString("lbl.start")); //$NON-NLS-1$
        btnStartTask.setFont(btnStartTask.getFont().deriveFont(Font.BOLD, btnStartTask.getFont().getSize() * START_BUTTON_SIZE_FACTOR));
        btnStartTask.setEnabled(false);
        btnStartTask.setAction(startTaskAction);
        btnStartTask.setIcon(new ImageIcon(Pondero.class.getResource("/com/famfamfam/silk/bullet_go.png"))); //$NON-NLS-1$
        final GridBagConstraints gbc_btnStartTask = new GridBagConstraints();
        gbc_btnStartTask.weightx = 1.0;
        gbc_btnStartTask.weighty = 1.0;
        gbc_btnStartTask.insets = new Insets(0, 15, 0, 15);
        gbc_btnStartTask.gridx = 0;
        gbc_btnStartTask.gridy = 0;
        pnlAction.add(btnStartTask, gbc_btnStartTask);
    }

    private void setStatus(String message, final int level) {
        if (message == null) {
            message = ""; //$NON-NLS-1$
        }
        lblTaskStatus.setText(message + " "); //$NON-NLS-1$
        switch (level) {
            case WARNING:
                lblTaskStatus.setForeground(Color.BLUE);
                break;
            case ERROR:
                lblTaskStatus.setForeground(Color.RED);
                break;
            case SUCCESS:
                lblTaskStatus.setForeground(new Color(0, 128, 0));
                break;
            default:
                lblTaskStatus.setForeground(Color.BLACK);
                break;
        }
    }

}