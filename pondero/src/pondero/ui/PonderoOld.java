package pondero.ui;

import static pondero.Logger.error;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import pondero.L10n;
import pondero.SysUtil;
import pondero.UiUtil;
import pondero.engine.test.Test;
import pondero.engine.test.launch.TaskLauncher;
import pondero.engine.test.launch.TestReport;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.participants.Participant;
import pondero.tests.TestLoader;
import pondero.ui.actions.TaskAction;
import pondero.ui.update.UpdateDialog;

public class PonderoOld implements TaskLauncher {

    private static final float START_BUTTON_SIZE_FACTOR = 1.2f;

    private static final int   DEFAULT                  = 0;
    private static final int   ERROR                    = 3;
    private static final int   SUCCESS                  = 1;
    private static final int   WARNING                  = 2;

    private static final Color DEFAULT_COLOR            = Color.BLACK;
    private static final Color ERROR_COLOR              = Color.RED;
    private static final Color SUCCESS_COLOR            = new Color(0, 128, 0);
    private static final Color WARNING_COLOR            = Color.BLUE;

    /**
     * Launch the application.
     * 
     * @throws Exception
     */
    public static void main(final String... args) throws Exception {
        String home = args.length >= 1 ? args[0] : null;
        Globals.loadPreferences(home);

        SysUtil.configure();
        UiUtil.setLaf();
        UiUtil.scaleUi(Globals.getUiScaleFactor());

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                final PonderoOld window = new PonderoOld();
                try {
                    TestLoader.registerTests(window);
                } catch (final Exception e) {
                    error(e);
                }
                try {
                    window.openWorkbook(WorkbookFactory.openWorkbook(Globals.getLastWorkbookFile()));
                } catch (final Exception e) {
                    error(e);
                }
                window.frmMain.setVisible(true);
                window.updateStatus(null);
                if (Globals.isUpdateOnStartup()) {
                    new UpdateDialog(window.frmMain).beginUpdate();
                }
            }

        });
    }

    // Essentials
    private Workbook     currentWorkbook          = null;
    private Participant  currentParticipant       = null;
    private Test         currentTask              = null;

    // Actions
    private final Action chooseParticipantAction  = null; // new ChooseParticipantAction(this);
    private final Action homePageAction           = null; // new HomePageAction(this);
    private final Action manageParticipantsAction = null; // new ManageParticipantsAction(this);
    private final Action openDocumentAction       = null; // new OpenDocumentAction(this);
    private final Action quitAction               = null; // new QuitAction(this);
    private final Action saveAsDocument           = null; // new SaveAsDocumentAction(this);
    private final Action saveDocument             = null; // new SaveDocumentAction(this);
    private final Action startDocument            = null; // new StartDocumentAction(this);
    private final Action startTaskAction          = null; // new StartTaskAction(this);
    private final Action updateAction             = null; // new UpdateAction(this);

    // Components
    private JButton      btnStartTask;
    private JFrame       frmMain;
    private JLabel       lblDocument;
    private JLabel       lblDocumentName;
    private JLabel       lblParticipant;
    private JLabel       lblParticipantName;
    private JLabel       lblTask;
    private JLabel       lblTaskName;
    private JLabel       lblTaskStatus;
    private JMenu        mnHelp;
    private JMenu        mnParticipants;
    private JMenu        mnTasks;
    private JMenuItem    mntmDocumentSave;
    private JMenuItem    mntmDocumentSaveAs;
    private JMenuItem    mntmDocumentStart;
    private JMenuItem    mntmHomepage;
    private JMenuItem    mntmParticipantSelect;

    public PonderoOld() {
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

    public Frame getFrame() {
        return frmMain;
    }

    @Override
    public void onTaskEnded(final Test task, final TestReport report) {
        switch (report.getEndCode()) {
            case TestReport.END_KILL:
                setStatusMessage(ERROR, L10n.getString("msg.test-interrupted", report.getEndCode())); //$NON-NLS-1$
                break;
            default:
                setStatusMessage(SUCCESS, L10n.getString("msg.test-completed", report.getRunningTimeInSeconds())); //$NON-NLS-1$
        }
    }

    @Override
    public void onTaskStarted(final Test task) {
        setStatusMessage(DEFAULT, L10n.getString("msg.test-in-progress")); //$NON-NLS-1$
    }

    public void openWorkbook(final Workbook workbook) throws Exception {
        currentWorkbook = workbook;
        lblDocumentName.setText(currentWorkbook.getWorkbookName());
        updateStatus(null);
    }

    public void registerTask(final Test task) {
        task.setLauncher(this);
        final JMenuItem mntmTask = new JMenuItem();
        mntmTask.setAction(new TaskAction(null, task));
        mnTasks.add(mntmTask);
    }

    public void setCurrentParticipant(final Participant currentParticipant) {
        this.currentParticipant = currentParticipant;
        if (currentParticipant == null) {
            lblParticipantName.setText(L10n.getString("lbl.n-a")); //$NON-NLS-1$
        } else {
            lblParticipantName.setText(currentParticipant.getName() + " " + currentParticipant.getSurname()); //$NON-NLS-1$
        }
        updateStatus(null);
    }

    public void setCurrentTask(final Test task) {
        currentTask = task;
        if (task != null) {
            lblTaskName.setText(task.getCodeName());
            btnStartTask.setEnabled(true);

        } else {
            lblTask.setForeground(Color.RED);
            lblTaskName.setText(L10n.getString("lbl.n-a")); //$NON-NLS-1$
            lblTaskName.setToolTipText(null);
            btnStartTask.setEnabled(false);

        }
        updateStatus(null);
    }

    /**
     * Initialise the contents of the frame.
     */
    private void initialize() {

        frmMain = new JFrame();
        frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage(PonderoOld.class.getResource("/pondero/res/pondero-48x48.png")));
        frmMain.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                quitAction.actionPerformed(null);
            }

        });
        frmMain.setTitle(L10n.getString("lbl.pondero")); //$NON-NLS-1$
        frmMain.setBounds(100, 100, 700, 320);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JMenuBar menuBar = new JMenuBar();
        frmMain.setJMenuBar(menuBar);

        final JMenu mnApp = new JMenu(" " + L10n.getString("lbl.application") + " "); //$NON-NLS-1$
        menuBar.add(mnApp);

        final JMenuItem mntmPreferences = new JMenuItem(L10n.getString("lbl.preferences")); //$NON-NLS-1$
        mntmPreferences.setEnabled(false);
        mnApp.add(mntmPreferences);

        mnApp.addSeparator();

        final JMenuItem mntmUpdate = new JMenuItem(L10n.getString("lbl.update...")); //$NON-NLS-1$
        mntmUpdate.setAction(updateAction);
        mnApp.add(mntmUpdate);

        mnApp.addSeparator();

        final JMenuItem mntmQuit = new JMenuItem();
        mntmQuit.setAction(quitAction);
        mnApp.add(mntmQuit);

        final JMenu mnDocuments = new JMenu(" " + L10n.getString("lbl.registers") + " "); //$NON-NLS-1$
        menuBar.add(mnDocuments);

        final JMenuItem mntmDocumentOpen = new JMenuItem();
        mntmDocumentOpen.setAction(openDocumentAction);
        mnDocuments.add(mntmDocumentOpen);

        mntmDocumentStart = new JMenuItem(L10n.getString("Pondero.mntmStartdocument.text")); //$NON-NLS-1$
        mntmDocumentStart.setAction(startDocument);
        mnDocuments.add(mntmDocumentStart);

        mnDocuments.addSeparator();

        mntmDocumentSave = new JMenuItem();
        mntmDocumentSave.setAction(saveDocument);
        mntmDocumentSave.setEnabled(false);
        mnDocuments.add(mntmDocumentSave);

        mntmDocumentSaveAs = new JMenuItem();
        mntmDocumentSaveAs.setAction(saveAsDocument);
        mntmDocumentSaveAs.setEnabled(false);
        mnDocuments.add(mntmDocumentSaveAs);

        mnParticipants = new JMenu(" " + L10n.getString("lbl.participants") + " "); //$NON-NLS-1$
        menuBar.add(mnParticipants);

        mntmParticipantSelect = new JMenuItem();
        mntmParticipantSelect.setAction(chooseParticipantAction);
        mnParticipants.add(mntmParticipantSelect);

        mnParticipants.addSeparator();

        final JMenuItem mntmParticipantUpdate = new JMenuItem();
        mntmParticipantUpdate.setAction(manageParticipantsAction);
        mnParticipants.add(mntmParticipantUpdate);

        mnTasks = new JMenu(" " + L10n.getString("lbl.tests") + " "); //$NON-NLS-1$
        menuBar.add(mnTasks);

        mnHelp = new JMenu(L10n.getString("lbl.help")); //$NON-NLS-1$
        menuBar.add(mnHelp);

        mntmHomepage = new JMenuItem(); //$NON-NLS-1$
        mntmHomepage.setAction(homePageAction);
        mnHelp.add(mntmHomepage);

        final JPanel pnlComposition = new JPanel();
        pnlComposition.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 0, 10), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
        frmMain.getContentPane().add(pnlComposition, BorderLayout.NORTH);
        final GridBagLayout gbl_pnlComposition = new GridBagLayout();
        gbl_pnlComposition.columnWidths = new int[] { 0, 0, 0 };
        gbl_pnlComposition.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_pnlComposition.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        gbl_pnlComposition.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        pnlComposition.setLayout(gbl_pnlComposition);

        lblDocument = new JLabel(L10n.getString("lbl.data-register") + ":"); //$NON-NLS-1$  //$NON-NLS-2$
        lblDocument.setFont(lblDocument.getFont().deriveFont(Font.BOLD));
        final GridBagConstraints gbc_lblDocument = new GridBagConstraints();
        gbc_lblDocument.anchor = GridBagConstraints.EAST;
        gbc_lblDocument.insets = new Insets(10, 10, 10, 5);
        gbc_lblDocument.gridx = 0;
        gbc_lblDocument.gridy = 0;
        pnlComposition.add(lblDocument, gbc_lblDocument);

        lblDocumentName = new JLabel(L10n.getString("lbl.n-a")); //$NON-NLS-1$
        lblDocumentName.setFont(lblDocumentName.getFont().deriveFont(Font.ITALIC));
        lblDocumentName.setHorizontalAlignment(SwingConstants.LEFT);
        final GridBagConstraints gbc_lblDocumentName = new GridBagConstraints();
        gbc_lblDocumentName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblDocumentName.weightx = 1.0;
        gbc_lblDocumentName.insets = new Insets(10, 5, 10, 0);
        gbc_lblDocumentName.gridx = 1;
        gbc_lblDocumentName.gridy = 0;
        pnlComposition.add(lblDocumentName, gbc_lblDocumentName);

        lblParticipant = new JLabel(L10n.getString("lbl.participant") + ":"); //$NON-NLS-1$  //$NON-NLS-2$
        lblParticipant.setFont(lblParticipant.getFont().deriveFont(Font.BOLD));
        final GridBagConstraints gbc_lblParticipant = new GridBagConstraints();
        gbc_lblParticipant.insets = new Insets(5, 0, 10, 5);
        gbc_lblParticipant.anchor = GridBagConstraints.EAST;
        gbc_lblParticipant.gridx = 0;
        gbc_lblParticipant.gridy = 1;
        pnlComposition.add(lblParticipant, gbc_lblParticipant);

        lblParticipantName = new JLabel(L10n.getString("lbl.n-a")); //$NON-NLS-1$
        lblParticipantName.setFont(lblParticipantName.getFont().deriveFont(Font.ITALIC));
        final GridBagConstraints gbc_lblParticipantName = new GridBagConstraints();
        gbc_lblParticipantName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblParticipantName.weightx = 1.0;
        gbc_lblParticipantName.insets = new Insets(5, 5, 10, 0);
        gbc_lblParticipantName.gridx = 1;
        gbc_lblParticipantName.gridy = 1;
        pnlComposition.add(lblParticipantName, gbc_lblParticipantName);

        lblTask = new JLabel(L10n.getString("lbl.test") + ":"); //$NON-NLS-1$  //$NON-NLS-2$
        lblTask.setFont(lblTask.getFont().deriveFont(Font.BOLD));
        final GridBagConstraints gbc_lblTask = new GridBagConstraints();
        gbc_lblTask.insets = new Insets(5, 0, 10, 5);
        gbc_lblTask.anchor = GridBagConstraints.EAST;
        gbc_lblTask.gridx = 0;
        gbc_lblTask.gridy = 2;
        pnlComposition.add(lblTask, gbc_lblTask);

        lblTaskName = new JLabel(L10n.getString("lbl.n-a")); //$NON-NLS-1$
        lblTaskName.setFont(lblTaskName.getFont().deriveFont(Font.ITALIC));
        final GridBagConstraints gbc_lblTaskName = new GridBagConstraints();
        gbc_lblTaskName.anchor = GridBagConstraints.NORTH;
        gbc_lblTaskName.insets = new Insets(5, 5, 10, 0);
        gbc_lblTaskName.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblTaskName.weightx = 1.0;
        gbc_lblTaskName.gridx = 1;
        gbc_lblTaskName.gridy = 2;
        pnlComposition.add(lblTaskName, gbc_lblTaskName);

        lblTaskStatus = new JLabel(L10n.getString("lbl.n-a"));//$NON-NLS-1$
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

        btnStartTask = new JButton(L10n.getString("lbl.start")); //$NON-NLS-1$
        btnStartTask.setFont(btnStartTask.getFont().deriveFont(Font.BOLD, btnStartTask.getFont().getSize() * START_BUTTON_SIZE_FACTOR));
        btnStartTask.setEnabled(false);
        btnStartTask.setAction(startTaskAction);
        btnStartTask.setIcon(new ImageIcon(PonderoOld.class.getResource("/com/famfamfam/silk/bullet_go.png"))); //$NON-NLS-1$
        final GridBagConstraints gbc_btnStartTask = new GridBagConstraints();
        gbc_btnStartTask.weightx = 1.0;
        gbc_btnStartTask.weighty = 1.0;
        gbc_btnStartTask.insets = new Insets(0, 15, 0, 15);
        gbc_btnStartTask.gridx = 0;
        gbc_btnStartTask.gridy = 0;
        pnlAction.add(btnStartTask, gbc_btnStartTask);
    }

    private void setStatusMessage(final int level, String message) {
        if (message == null) {
            message = ""; //$NON-NLS-1$
        }
        lblTaskStatus.setText(message + " "); //$NON-NLS-1$
        switch (level) {
            case WARNING:
                lblTaskStatus.setForeground(WARNING_COLOR);
                break;
            case ERROR:
                lblTaskStatus.setForeground(ERROR_COLOR);
                break;
            case SUCCESS:
                lblTaskStatus.setForeground(SUCCESS_COLOR);
                break;
            default:
                lblTaskStatus.setForeground(DEFAULT_COLOR);
                break;
        }
    }

    private void updateStatus(Object hint) {
        if (currentWorkbook == null) {
            lblDocument.setForeground(ERROR_COLOR);
        } else {
            lblDocument.setForeground(DEFAULT_COLOR);
        }
        if (currentParticipant == null) {
            lblParticipant.setForeground(Color.BLUE);
        } else {
            lblParticipant.setForeground(DEFAULT_COLOR);
        }
        if (currentTask == null) {
            lblTask.setForeground(ERROR_COLOR);
            btnStartTask.setEnabled(false);
        } else {
            lblTask.setForeground(DEFAULT_COLOR);
        }

        if (currentWorkbook == null) {
            setStatusMessage(ERROR, L10n.getString("msg.please-choose-workbook")); //$NON-NLS-1$
        } else if (currentTask == null) {
            setStatusMessage(ERROR, L10n.getString("msg.please-choose-test")); //$NON-NLS-1$
        } else if (currentParticipant == null) {
            setStatusMessage(WARNING, L10n.getString("msg.please-choose-participant")); //$NON-NLS-1$
        } else {
            setStatusMessage(DEFAULT, L10n.getString("msg.press-start-to-start", L10n.getString("lbl.start"))); //$NON-NLS-1$ //$NON-NLS-2$
        }

        mnParticipants.setEnabled(currentWorkbook != null);
        mnTasks.setEnabled(currentWorkbook != null);
        mntmDocumentSave.setEnabled(currentWorkbook != null);
        mntmDocumentSaveAs.setEnabled(currentWorkbook != null);
        mntmDocumentStart.setEnabled(currentWorkbook != null);

        btnStartTask.setEnabled(currentWorkbook != null && currentTask != null);
    }
}
