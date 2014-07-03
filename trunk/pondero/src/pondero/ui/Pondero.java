package pondero.ui;

import static pondero.Logger.error;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import pondero.Globals;
import pondero.L10n;
import pondero.engine.test.Test;
import pondero.model.Workbook;
import pondero.model.WorkbookFactory;
import pondero.model.entities.Participant;
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
import pondero.ui.participants.ParticipantReport;

public class Pondero {

    /**
     * Launch the application.
     * 
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String home = args.length >= 1 ? args[0] : null;
        Globals.loadPreferences(home);

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    Pondero window = new Pondero();
                    window.frame.setVisible(true);
                    try {
                        window.setWorkbook(WorkbookFactory.openWorkbook(Globals.getLastWorkbookFile()));
                    } catch (final Exception e) {
                        error(e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Essentials
    private PonderoState currentState;
    private Workbook     currentWorkbook;
    private Participant  currentParticipant;
    private Test         currentTask;

    // Actions
    private final Action addParticipantAction    = new AddParticipantAction(this);
    private final Action chooseParticipantAction = new ChooseParticipantAction(this);
    private final Action homePageAction          = new HomePageAction(this);
    private final Action modifyParticipantAction = new ModifyParticipantAction(this);
    private final Action openDocumentAction      = new OpenDocumentAction(this);
    private final Action quitAction              = new QuitAction(this);
    private final Action saveAsDocument          = new SaveAsDocumentAction(this);
    private final Action saveDocument            = new SaveDocumentAction(this);
    private final Action setPreferencesAction    = new SetPreferencesAction(this);
    private final Action startDocument           = new StartDocumentAction(this);
    private final Action startTaskAction         = new StartTaskAction(this);
    private final Action updateAction            = new UpdateAction(this);

    // Widgets
    private JFrame       frame;
    private JPanel       pnlStage;
    private JButton      btnSelectParticipant;
    private JButton      btnAddParticipant;
    private JButton      btnModifyParticipant;
    private JButton      btnNext;
    private JEditorPane  dtrpnEpparticipantdescription;
    private JMenuItem    mntmPreferences;
    private StatusBar    statusBar;
    private JMenu        mnAnalisys;
    private JMenuItem    mntmView;
    private JMenuItem    mntmSave;
    private JMenuItem    mntmSaveas;

    /**
     * Create the application.
     */
    public Pondero() {
        initialize();
        setCurrentState(PonderoState.PARTICIPANT_SELECTION);
        configureNavigationButton(btnNext);
        frame.setMinimumSize(new Dimension(640, 480));
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

    public JFrame getFrame() {
        return frame;
    }

    public void setCurrentParticipant(Participant participant) {
        currentParticipant = participant;
        updateCurrentState();
    }

    public void setCurrentState(PonderoState state) {
        mntmPreferences.setEnabled(false);
        mntmView.setEnabled(currentWorkbook != null);
        mntmSave.setEnabled(currentWorkbook != null);
        mntmSaveas.setEnabled(currentWorkbook != null);
        mnAnalisys.setEnabled(currentWorkbook != null);
        if (currentWorkbook == null) {
            pnlStage.setVisible(false);
            statusBar.setMessage(StatusBar.ERROR, L10n.getString("msg.please-choose-workbook"));
        } else {
            pnlStage.setVisible(true);
            if (PonderoState.PARTICIPANT_SELECTION == state) {
                CardLayout cl = (CardLayout) pnlStage.getLayout();
                cl.show(pnlStage, "pnlParticipant");
                dtrpnEpparticipantdescription.setEnabled(true);
                dtrpnEpparticipantdescription.setText(ParticipantReport.getHtml(currentParticipant));
                btnSelectParticipant.setEnabled(currentWorkbook != null);
                btnAddParticipant.setEnabled(currentWorkbook != null);
                btnModifyParticipant.setEnabled(currentWorkbook != null && currentParticipant != null);
                btnNext.setEnabled(currentWorkbook != null && currentParticipant != null);
            } else if (PonderoState.TEST_SELECTION == state) {
                CardLayout cl = (CardLayout) pnlStage.getLayout();
                cl.show(pnlStage, "pnlTestSelection");
            }
            statusBar.setMessage(StatusBar.DEFAULT, L10n.getString("lbl.data-register") + ": " + currentWorkbook.getName());
        }
        currentState = state;
    }

    public void setCurrentTask(Test task) {
        currentTask = task;
        updateCurrentState();
    }

    public void setWorkbook(Workbook workbook) {
        currentWorkbook = workbook;
        updateCurrentState();
    }

    public void updateCurrentState() {
        setCurrentState(currentState);
    }

    private void configureNavigationButton(JButton btn) {
        btn.setPreferredSize(new Dimension(150, 40));
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame(L10n.getString("lbl.pondero"));
        frame.setLocationByPlatform(true);
        frame.setSize(800, 600);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PonderoOld.class.getResource("/pondero/res/pondero-48x48.png")));
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                quitAction.actionPerformed(null);
            }

        });

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnApplication = new JMenu(" " + L10n.getString("lbl.application") + " ");
        menuBar.add(mnApplication);

        mntmPreferences = new JMenuItem("preferences");
        mntmPreferences.setAction(setPreferencesAction);
        mnApplication.add(mntmPreferences);

        mnApplication.addSeparator();

        JMenuItem mntmUpdate = new JMenuItem("update");
        mntmUpdate.setAction(updateAction);
        mnApplication.add(mntmUpdate);

        mnApplication.addSeparator();

        JMenuItem mntmQuit = new JMenuItem("quit");
        mntmQuit.setAction(quitAction);
        mnApplication.add(mntmQuit);

        JMenu mnData = new JMenu(" " + L10n.getString("lbl.registers") + " ");
        menuBar.add(mnData);

        JMenuItem mntmOpen = new JMenuItem("open");
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

        mnAnalisys = new JMenu(" " + L10n.getString("lbl.analisys") + " ");
        menuBar.add(mnAnalisys);

        JMenu mnHelp = new JMenu(" " + L10n.getString("lbl.help") + " ");
        menuBar.add(mnHelp);

        JMenuItem mntmHomepage = new JMenuItem("homePage");
        mntmHomepage.setAction(homePageAction);
        mnHelp.add(mntmHomepage);

        pnlStage = new JPanel();
        frame.getContentPane().add(pnlStage, BorderLayout.CENTER);
        pnlStage.setLayout(new CardLayout(0, 0));

        JPanel pnlParticipant = new JPanel();
        pnlStage.add(pnlParticipant, "pnlParticipantSelection");
        pnlParticipant.setLayout(new BorderLayout(0, 0));

        JPanel pnlParticipantContent = new JPanel();
        pnlParticipantContent.setBorder(new EmptyBorder(20, 20, 25, 20));
        pnlParticipant.add(pnlParticipantContent, BorderLayout.CENTER);
        GridBagLayout gbl_pnlParticipantContent = new GridBagLayout();
        gbl_pnlParticipantContent.columnWidths = new int[] { 0, 0 };
        gbl_pnlParticipantContent.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_pnlParticipantContent.columnWeights = new double[] { 1.0, 0.0 };
        gbl_pnlParticipantContent.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0 };
        pnlParticipantContent.setLayout(gbl_pnlParticipantContent);

        JScrollPane pnlParticipantDescription = new JScrollPane();
        pnlParticipantDescription.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), L10n.getString("lbl.participant") + ":", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_pnlParticipantDescription = new GridBagConstraints();
        gbc_pnlParticipantDescription.insets = new Insets(0, 0, 0, 10);
        gbc_pnlParticipantDescription.weightx = 1.0;
        gbc_pnlParticipantDescription.gridheight = 4;
        gbc_pnlParticipantDescription.fill = GridBagConstraints.BOTH;
        gbc_pnlParticipantDescription.gridx = 0;
        gbc_pnlParticipantDescription.gridy = 0;
        pnlParticipantContent.add(pnlParticipantDescription, gbc_pnlParticipantDescription);

        dtrpnEpparticipantdescription = new JEditorPane();
        dtrpnEpparticipantdescription.setEditable(false);
        dtrpnEpparticipantdescription.setContentType("text/html");
        pnlParticipantDescription.setViewportView(dtrpnEpparticipantdescription);

        btnSelectParticipant = new JButton("<html>\r\n<center>\r\nChoose<br/>\r\nparticipant<br/>\r\nfrom list\r\n</center>\r\n</html>");
        btnSelectParticipant.setAction(chooseParticipantAction);
        GridBagConstraints gbc_btnSelectParticipant = new GridBagConstraints();
        gbc_btnSelectParticipant.insets = new Insets(0, 0, 20, 0);
        gbc_btnSelectParticipant.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnSelectParticipant.anchor = GridBagConstraints.NORTH;
        gbc_btnSelectParticipant.gridx = 1;
        gbc_btnSelectParticipant.gridy = 0;
        pnlParticipantContent.add(btnSelectParticipant, gbc_btnSelectParticipant);

        btnAddParticipant = new JButton("Add...");
        btnAddParticipant.setAction(addParticipantAction);
        GridBagConstraints gbc_btnAddParticipant = new GridBagConstraints();
        gbc_btnAddParticipant.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnAddParticipant.insets = new Insets(0, 0, 5, 0);
        gbc_btnAddParticipant.gridx = 1;
        gbc_btnAddParticipant.gridy = 2;
        pnlParticipantContent.add(btnAddParticipant, gbc_btnAddParticipant);

        btnModifyParticipant = new JButton("Modify...");
        btnModifyParticipant.setAction(modifyParticipantAction);
        GridBagConstraints gbc_btnModifyParticipant = new GridBagConstraints();
        gbc_btnModifyParticipant.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnModifyParticipant.gridx = 1;
        gbc_btnModifyParticipant.gridy = 3;
        pnlParticipantContent.add(btnModifyParticipant, gbc_btnModifyParticipant);

        JPanel pnlParticipantNavigation = new JPanel();
        pnlParticipantNavigation.setBackground(Color.LIGHT_GRAY);
        pnlParticipantNavigation.setBorder(new CompoundBorder(null, new EmptyBorder(10, 20, 10, 20)));
        pnlParticipant.add(pnlParticipantNavigation, BorderLayout.SOUTH);
        pnlParticipantNavigation.setLayout(new BorderLayout(0, 0));

        btnNext = new JButton("CONTINUARE");
        pnlParticipantNavigation.add(btnNext, BorderLayout.EAST);

        JPanel pnlTest = new JPanel();
        pnlStage.add(pnlTest, "pnlTestSelection");
        pnlTest.setLayout(new BorderLayout(0, 0));

        JPanel pnlTestContent = new JPanel();
        pnlTest.add(pnlTestContent);
        GridBagLayout gbl_pnlTestContent = new GridBagLayout();
        gbl_pnlTestContent.columnWidths = new int[] { 0 };
        gbl_pnlTestContent.rowHeights = new int[] { 0 };
        gbl_pnlTestContent.columnWeights = new double[] { Double.MIN_VALUE };
        gbl_pnlTestContent.rowWeights = new double[] { Double.MIN_VALUE };
        pnlTestContent.setLayout(gbl_pnlTestContent);

        JPanel pnlTestNavigation = new JPanel();
        pnlTestNavigation.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTest.add(pnlTestNavigation, BorderLayout.SOUTH);
        pnlTestNavigation.setLayout(new BorderLayout(0, 0));

        JButton btnStart = new JButton("Start");
        pnlTestNavigation.add(btnStart, BorderLayout.EAST);

        JButton btnPrevious = new JButton("Back");
        pnlTestNavigation.add(btnPrevious, BorderLayout.WEST);

        statusBar = new StatusBar();
        frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
    }

}
