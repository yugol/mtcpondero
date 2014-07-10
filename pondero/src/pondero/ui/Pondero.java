package pondero.ui;

import static pondero.Logger.error;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pondero.Globals;
import pondero.L10n;
import pondero.engine.test.Test;
import pondero.model.ModelListener;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.foundation.basic.Participant;
import pondero.tests.TestLoader;
import pondero.ui.actions.AddParticipantAction;
import pondero.ui.actions.ChooseParticipantAction;
import pondero.ui.actions.HomePageAction;
import pondero.ui.actions.ModifyParticipantAction;
import pondero.ui.actions.OpenDocumentAction;
import pondero.ui.actions.QuitAction;
import pondero.ui.actions.SaveAsDocumentAction;
import pondero.ui.actions.SaveDocumentAction;
import pondero.ui.actions.SetPreferencesAction;
import pondero.ui.actions.StartDocumentAction;
import pondero.ui.actions.StartTaskAction;
import pondero.ui.actions.UpdateAction;
import pondero.util.MsgUtil;
import pondero.util.SystemUtil;
import pondero.util.UiUtil;

public class Pondero implements Ponderable, ModelListener {

    public static final String BUTTON_ADD_NAME         = "addButton";
    public static final String BUTTON_BACK_NAME        = "backButton";
    public static final String BUTTON_MODIFY_NAME      = "modifyButton";
    public static final String BUTTON_NEXT_NAME        = "nextButton";
    public static final String BUTTON_SELECT_NAME      = "selectButton";
    public static final String BUTTON_START_NAME       = "startButton";
    public static final String MAIN_FRAME_NAME         = "ponderoMainFrame";
    public static final String MENU_ITEM_QUIT_NAME     = "quitMenuItem";
    public static final String REPORT_PARTICIPANT_NAME = "participantReport";

    /**
     * Launch the application.
     *
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        start(args);
    }

    public static Pondero start(final String[] args) throws Exception {
        // initialize context
        Globals.loadPreferences(args.length >= 1 ? args[0] : null);
        SystemUtil.configure();
        REGISTERED_TESTS.addAll(TestLoader.loadTests());
        UiUtil.getAvailableLafs();
        UiUtil.setLaf();
        UiUtil.scaleUi(Globals.getUiFontScaleFactor());

        // start application
        final Pondero app = new Pondero();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    app.mainFrame.setLocationRelativeTo(null);
                    app.mainFrame.setVisible(true);
                    app.setCurrentWorkbook(WorkbookFactory.openWorkbook(Globals.getLastWorkbookFile()));
                } catch (final Exception e) {
                    error(e);
                    MsgUtil.showExceptionMessage(null, e);
                }
            }

        });
        return app;
    }

    private static final List<Test> REGISTERED_TESTS        = new ArrayList<Test>();

    // Essentials
    private PonderoState            currentState;
    private Workbook                currentWorkbook;
    private Participant             currentParticipant;
    private Test                    currentTask;

    // Actions
    private final Action            addParticipantAction    = new AddParticipantAction(this);
    private final Action            chooseParticipantAction = new ChooseParticipantAction(this);
    private final Action            homePageAction          = new HomePageAction(this);
    private final Action            modifyParticipantAction = new ModifyParticipantAction(this);
    private final Action            openDocumentAction      = new OpenDocumentAction(this);
    private final Action            quitAction              = new QuitAction(this);
    private final Action            saveAsDocument          = new SaveAsDocumentAction(this);
    private final Action            saveDocument            = new SaveDocumentAction(this);
    private final Action            setPreferencesAction    = new SetPreferencesAction(this);
    private final Action            startDocument           = new StartDocumentAction(this);
    private final Action            startTaskAction         = new StartTaskAction(this);
    private final Action            updateAction            = new UpdateAction(this);

    // Widgets
    private JFrame                  mainFrame;
    private JButton                 btnAddParticipant;
    private JButton                 btnBack;
    private JButton                 btnModifyParticipant;
    private JButton                 btnNext;
    private JButton                 btnSelectParticipant;
    private JButton                 btnStart;
    private JEditorPane             epParticipantDescription;
    private JLabel                  lblPageHint;
    private JLabel                  lblPageTitle;
    private JList<Test>             lstTests;
    private JMenu                   mnAnalysis;
    private JMenuItem               mntmPreferences;
    private JMenuItem               mntmSave;
    private JMenuItem               mntmSaveas;
    private JMenuItem               mntmView;
    private JPanel                  stage;
    private StatusBar               statusBar;

    /**
     * Create the application.
     */
    public Pondero() {
        initialize();
        mainFrame.setMinimumSize(new Dimension(640, 480));
        mainFrame.setSize((int) (640 * Globals.getUiFontScaleFactor()), (int) (480 * Globals.getUiFontScaleFactor()));
        configureNavigationButton(btnNext);
        configureNavigationButton(btnBack);
        configureNavigationButton(btnStart);
        lblPageTitle.setFont(lblPageTitle.getFont().deriveFont(Font.BOLD, 2 * lblPageTitle.getFont().getSize()));
        addTests();
        setCurrentState(PonderoState.PARTICIPANT_SELECTION);
    }

    @Override
    public Participant getCurrentParticipant() {
        return currentParticipant;
    }

    @Override
    public Test getCurrentTask() {
        return currentTask;
    }

    @Override
    public Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    @Override
    public JFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public JFrame getTestFrame() {
        if (currentTask != null) { return currentTask.getTestWindow(); }
        return null;
    }

    @Override
    public void onDirtyFlagChanged(final boolean dirty) {
        updateCurrentState();
    }

    @Override
    public void setCurrentParticipant(final Participant participant) {
        currentParticipant = participant;
        updateCurrentState();
    }

    @Override
    public void setCurrentState(final PonderoState state) {
        mntmView.setEnabled(currentWorkbook != null);
        mntmSave.setEnabled(currentWorkbook != null);
        mntmSaveas.setEnabled(currentWorkbook != null);
        mnAnalysis.setEnabled(currentWorkbook != null);
        if (currentWorkbook == null) {
            stage.setVisible(false);
            statusBar.setMessage(StatusBar.ERROR, L10n.getString("msg.please-choose-workbook"));
        } else {
            stage.setVisible(true);
            if (PonderoState.PARTICIPANT_SELECTION == state) {
                final CardLayout cl = (CardLayout) stage.getLayout();
                cl.show(stage, "pnlParticipantSelection");
                lblPageTitle.setText(L10n.getString("lbl.CHOOSE-PARTICIPANT"));
                lblPageHint.setText(L10n.getString("msg.CHOOSE-PARTICIPANT"));
                epParticipantDescription.setEnabled(true);
                epParticipantDescription.setText(Participant.getHtml(currentParticipant));
                btnSelectParticipant.setEnabled(currentWorkbook != null && currentWorkbook.getParticipantCount() > 0);
                btnAddParticipant.setEnabled(currentWorkbook != null);
                btnModifyParticipant.setEnabled(currentWorkbook != null && currentParticipant != null);
                btnNext.setEnabled(currentWorkbook != null && (currentParticipant != null || Globals.isParticipantOptional()));
            } else if (PonderoState.TEST_SELECTION == state) {
                final CardLayout cl = (CardLayout) stage.getLayout();
                cl.show(stage, "pnlTestSelection");
                lblPageTitle.setText(L10n.getString("lbl.CHOOSE-TEST"));
                lblPageHint.setText(L10n.getString("msg.CHOOSE-TEST"));
                btnStart.setEnabled(currentWorkbook != null && (currentParticipant != null || Globals.isParticipantOptional()) && currentTask != null);
            }
            statusBar.setMessage(StatusBar.DEFAULT,
                    L10n.getString("lbl.data-register")
                    + ": " + currentWorkbook.getName()
                    + (currentWorkbook.isDirty() ? " *" : ""));
        }
        currentState = state;
    }

    @Override
    public void setCurrentTask(final Test task) {
        currentTask = task;
        updateCurrentState();
    }

    @Override
    public void setCurrentWorkbook(final Workbook workbook) {
        final int maxParticipantCount = 30;
        try {
            currentWorkbook = workbook;
            currentWorkbook.addWorkbookListener(this);
            currentParticipant = null;
            currentTask = null;
            if (currentWorkbook.getParticipantCount() <= 0) {
                final String decision = JOptionPane.showInputDialog(mainFrame, L10n.getString("msg.how-many-random-participants", maxParticipantCount), "0");
                try {
                    int count = Integer.parseInt(decision);
                    if (count < 0) {
                        count = 0;
                    }
                    if (count > maxParticipantCount) {
                        count = maxParticipantCount;
                    }
                    if (count > 0) {
                        mainFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        for (int i = 1; i <= count; ++i) {
                            final Participant p = currentWorkbook.addParticipant();
                            p.randomize();
                            p.setId(i);
                        }
                    }
                } catch (final NumberFormatException e) {
                } finally {
                    mainFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
            setCurrentState(PonderoState.PARTICIPANT_SELECTION);
        } catch (final Exception e) {
            error(e);
            MsgUtil.showExceptionMessage(mainFrame, e);
        }
    }

    @Override
    public void updateCurrentState() {
        setCurrentState(currentState);
    }

    private void addTests() {
        final DefaultListModel<Test> model = (DefaultListModel<Test>) lstTests.getModel();
        for (final Test test : REGISTERED_TESTS) {
            model.addElement(test);
        }
    }

    private void configureNavigationButton(final JButton btn) {
        btn.setMinimumSize(new Dimension(150, 40));
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mainFrame = new JFrame(L10n.getString("lbl.pondero"));
        mainFrame.setName(MAIN_FRAME_NAME);
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.setLocationByPlatform(true);
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(Pondero.class.getResource("/pondero/res/pondero-48x48.png")));
        mainFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                quitAction.actionPerformed(null);

            }

        });

        final JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        final JMenu mnApplication = new JMenu(" " + L10n.getString("lbl.application") + " ");
        menuBar.add(mnApplication);

        mntmPreferences = new JMenuItem("preferences");
        mntmPreferences.setAction(setPreferencesAction);
        mnApplication.add(mntmPreferences);

        mnApplication.addSeparator();

        final JMenuItem mntmUpdate = new JMenuItem("update");
        mntmUpdate.setAction(updateAction);
        mnApplication.add(mntmUpdate);

        mnApplication.addSeparator();

        final JMenuItem mntmQuit = new JMenuItem("quit");
        mntmQuit.setName(MENU_ITEM_QUIT_NAME);
        mntmQuit.setAction(quitAction);
        mnApplication.add(mntmQuit);

        final JMenu mnData = new JMenu(" " + L10n.getString("lbl.registers") + " ");
        menuBar.add(mnData);

        final JMenuItem mntmOpen = new JMenuItem("open");
        mntmOpen.setAction(openDocumentAction);
        mnData.add(mntmOpen);

        mntmView = new JMenuItem("view");
        mntmView.setAction(startDocument);
        mnData.add(mntmView);

        mnData.addSeparator();

        mntmSave = new JMenuItem("save");
        mntmSave.setAction(saveDocument);
        mnData.add(mntmSave);

        mntmSaveas = new JMenuItem("saveAs");
        mntmSaveas.setAction(saveAsDocument);
        mnData.add(mntmSaveas);

        mnAnalysis = new JMenu(" " + L10n.getString("lbl.analysis") + " ");
        menuBar.add(mnAnalysis);

        final JMenu mnHelp = new JMenu(" " + L10n.getString("lbl.help") + " ");
        menuBar.add(mnHelp);

        final JMenuItem mntmHomepage = new JMenuItem("homePage");
        mntmHomepage.setAction(homePageAction);
        mnHelp.add(mntmHomepage);

        final JPanel instructions = new JPanel();
        instructions.setBorder(new EmptyBorder(20, 20, 0, 100));
        mainFrame.getContentPane().add(instructions, BorderLayout.NORTH);
        instructions.setLayout(new BoxLayout(instructions, BoxLayout.Y_AXIS));

        lblPageTitle = new JLabel("   ");
        instructions.add(lblPageTitle);

        lblPageHint = new JLabel("  ");
        instructions.add(lblPageHint);

        stage = new JPanel();
        mainFrame.getContentPane().add(stage, BorderLayout.CENTER);
        stage.setLayout(new CardLayout(0, 0));

        final JPanel pnlParticipant = new JPanel();
        stage.add(pnlParticipant, "pnlParticipantSelection");
        pnlParticipant.setLayout(new BorderLayout(0, 0));

        final JPanel pnlParticipantContent = new JPanel();
        pnlParticipantContent.setBorder(new EmptyBorder(20, 20, 26, 20));
        pnlParticipant.add(pnlParticipantContent, BorderLayout.CENTER);
        final GridBagLayout gbl_pnlParticipantContent = new GridBagLayout();
        gbl_pnlParticipantContent.columnWidths = new int[] { 0, 0 };
        gbl_pnlParticipantContent.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_pnlParticipantContent.columnWeights = new double[] { 1.0, 0.0 };
        gbl_pnlParticipantContent.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
        pnlParticipantContent.setLayout(gbl_pnlParticipantContent);

        final JScrollPane pnlParticipantDescription = new JScrollPane();
        pnlParticipantDescription.setViewportBorder(new TitledBorder(null, L10n.getString("lbl.participant") + ":", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        final GridBagConstraints gbc_pnlParticipantDescription = new GridBagConstraints();
        gbc_pnlParticipantDescription.insets = new Insets(0, 0, 0, 10);
        gbc_pnlParticipantDescription.weightx = 1.0;
        gbc_pnlParticipantDescription.gridheight = 4;
        gbc_pnlParticipantDescription.fill = GridBagConstraints.BOTH;
        gbc_pnlParticipantDescription.gridx = 0;
        gbc_pnlParticipantDescription.gridy = 0;
        pnlParticipantContent.add(pnlParticipantDescription, gbc_pnlParticipantDescription);

        epParticipantDescription = new JEditorPane();
        epParticipantDescription.setName(REPORT_PARTICIPANT_NAME);
        epParticipantDescription.setEditable(false);
        epParticipantDescription.setContentType("text/html");
        epParticipantDescription.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        epParticipantDescription.setFont(new JLabel().getFont());
        pnlParticipantDescription.setViewportView(epParticipantDescription);

        btnSelectParticipant = new JButton("");
        btnSelectParticipant.setName(BUTTON_SELECT_NAME);
        btnSelectParticipant.setAction(chooseParticipantAction);
        final GridBagConstraints gbc_btnSelectParticipant = new GridBagConstraints();
        gbc_btnSelectParticipant.insets = new Insets(0, 0, 20, 0);
        gbc_btnSelectParticipant.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnSelectParticipant.anchor = GridBagConstraints.NORTH;
        gbc_btnSelectParticipant.gridx = 1;
        gbc_btnSelectParticipant.gridy = 0;
        pnlParticipantContent.add(btnSelectParticipant, gbc_btnSelectParticipant);

        btnAddParticipant = new JButton("");
        btnAddParticipant.setName(BUTTON_ADD_NAME);
        btnAddParticipant.setAction(addParticipantAction);
        final GridBagConstraints gbc_btnAddParticipant = new GridBagConstraints();
        gbc_btnAddParticipant.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnAddParticipant.insets = new Insets(0, 0, 5, 0);
        gbc_btnAddParticipant.gridx = 1;
        gbc_btnAddParticipant.gridy = 2;
        pnlParticipantContent.add(btnAddParticipant, gbc_btnAddParticipant);

        btnModifyParticipant = new JButton("");
        btnModifyParticipant.setName(BUTTON_MODIFY_NAME);
        btnModifyParticipant.setAction(modifyParticipantAction);
        final GridBagConstraints gbc_btnModifyParticipant = new GridBagConstraints();
        gbc_btnModifyParticipant.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnModifyParticipant.gridx = 1;
        gbc_btnModifyParticipant.gridy = 3;
        pnlParticipantContent.add(btnModifyParticipant, gbc_btnModifyParticipant);

        final JPanel pnlParticipantNavigation = new JPanel();
        pnlParticipantNavigation.setBackground(SystemColor.controlShadow);
        pnlParticipantNavigation.setBorder(new EmptyBorder(12, 50, 10, 50));
        pnlParticipant.add(pnlParticipantNavigation, BorderLayout.SOUTH);
        pnlParticipantNavigation.setLayout(new BorderLayout(0, 0));

        btnNext = new JButton(L10n.getString("lbl.next"));
        btnNext.setName(BUTTON_NEXT_NAME);
        btnNext.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                setCurrentState(PonderoState.TEST_SELECTION);
            }

        });
        pnlParticipantNavigation.add(btnNext, BorderLayout.EAST);

        final JPanel pnlTest = new JPanel();
        stage.add(pnlTest, "pnlTestSelection");
        pnlTest.setLayout(new BorderLayout(0, 0));

        final JPanel pnlTestContent = new JPanel();
        pnlTestContent.setBorder(new EmptyBorder(20, 20, 26, 20));
        pnlTest.add(pnlTestContent);
        final GridBagLayout gbl_pnlTestContent = new GridBagLayout();
        gbl_pnlTestContent.columnWidths = new int[] { 0 };
        gbl_pnlTestContent.rowHeights = new int[] { 0 };
        gbl_pnlTestContent.columnWeights = new double[] { 1.0 };
        gbl_pnlTestContent.rowWeights = new double[] { 1.0 };
        pnlTestContent.setLayout(gbl_pnlTestContent);

        final JScrollPane pnlTestList = new JScrollPane();
        pnlTestList.setViewportBorder(new TitledBorder(null, L10n.getString("lbl.choose-test") + ":", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        final GridBagConstraints gbc_pnlTestList = new GridBagConstraints();
        gbc_pnlTestList.fill = GridBagConstraints.BOTH;
        gbc_pnlTestList.gridx = 0;
        gbc_pnlTestList.gridy = 0;
        pnlTestContent.add(pnlTestList, gbc_pnlTestList);

        lstTests = new JList<Test>();
        lstTests.setBackground(UiUtil.getListBackgroundColor());
        lstTests.setCellRenderer(new TestCellRenderer());
        lstTests.setModel(new DefaultListModel<Test>());
        lstTests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstTests.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(final ListSelectionEvent arg0) {
                final Test newTask = lstTests.getSelectedValue();
                if (newTask != currentTask) {
                    setCurrentTask(newTask);
                }
            }

        });
        pnlTestList.setViewportView(lstTests);

        final JPanel pnlTestNavigation = new JPanel();
        pnlTestNavigation.setBackground(SystemColor.controlShadow);
        pnlTestNavigation.setBorder(new EmptyBorder(12, 50, 10, 50));
        pnlTest.add(pnlTestNavigation, BorderLayout.SOUTH);
        pnlTestNavigation.setLayout(new BorderLayout(0, 0));

        btnBack = new JButton(L10n.getString("lbl.back"));
        btnBack.setName(BUTTON_BACK_NAME);
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                setCurrentState(PonderoState.PARTICIPANT_SELECTION);
            }

        });
        pnlTestNavigation.add(btnBack, BorderLayout.WEST);

        btnStart = new JButton(L10n.getString("lbl.start"));
        btnStart.setName(BUTTON_START_NAME);
        btnStart.setAction(startTaskAction);
        pnlTestNavigation.add(btnStart, BorderLayout.EAST);

        statusBar = new StatusBar();
        mainFrame.getContentPane().add(statusBar, BorderLayout.SOUTH);
    }

}
