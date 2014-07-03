package pondero.ui.participants;

import static pondero.Logger.trace;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pondero.Globals;
import pondero.L10n;
import pondero.model.entities.Participant;
import pondero.model.entities.domains.Education;
import pondero.model.entities.domains.Gender;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class ParticipantsManagementDialog extends JDialog {

    private final JTextField           valId;
    private final JTextField           valSurname;
    private final JTextField           valName;
    private final JDateChooser         valDob;
    private final JSpinner             valAge;
    private final JComboBox<Gender>    valGender;
    private final JComboBox<Education> valEducation;
    private final JSpinner             valDrivingAge;
    private final JSpinner             valMileage;

    private boolean                    dirty          = false;
    private final JButton              btnSave        = new JButton(L10n.getString("lbl.save"));

    private final DocumentListener     txtDocListener = new DocumentListener() {

                                                          @Override
                                                          public void changedUpdate(final DocumentEvent e) {
                                                              setDirtyFlag();
                                                          }

                                                          @Override
                                                          public void insertUpdate(final DocumentEvent e) {
                                                              setDirtyFlag();
                                                          }

                                                          @Override
                                                          public void removeUpdate(final DocumentEvent e) {
                                                              setDirtyFlag();
                                                          }

                                                      };

    private final ItemListener         itemListener   = new ItemListener() {

                                                          @Override
                                                          public void itemStateChanged(ItemEvent arg0) {
                                                              setDirtyFlag();
                                                          }

                                                      };

    private final ChangeListener       changeListener = new ChangeListener() {

                                                          @Override
                                                          public void stateChanged(ChangeEvent arg0) {
                                                              setDirtyFlag();
                                                          }

                                                      };

    public ParticipantsManagementDialog() throws Exception {
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
                onClosing();
            }

        });
        setIconImage(Toolkit.getDefaultToolkit().getImage(ParticipantsManagementDialog.class.getResource("/com/famfamfam/silk/group.png")));

        setTitle(L10n.getString("lbl.manage-participants"));
        setBounds(100, 100, 400, 400);

        final JPanel background = new JPanel();
        background.setBorder(new EmptyBorder(15, 15, 15, 15));
        getContentPane().add(background, BorderLayout.CENTER);
        background.setLayout(new BorderLayout(0, 0));

        final JPanel pnlParticipant = new JPanel();
        background.add(pnlParticipant, BorderLayout.CENTER);
        final GridBagLayout gbl_pnlParticipant = new GridBagLayout();
        gbl_pnlParticipant.columnWidths = new int[] { 0, 0, 0 };
        gbl_pnlParticipant.rowHeights = new int[] { 0, 0, 0 };
        gbl_pnlParticipant.columnWeights = new double[] { 0.0, 0.0, 1.0 };
        gbl_pnlParticipant.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
        pnlParticipant.setLayout(gbl_pnlParticipant);

        final JLabel lblLblid = new JLabel(L10n.getString("lbl.participant.id"));
        final GridBagConstraints gbc_lblLblid = new GridBagConstraints();
        gbc_lblLblid.anchor = GridBagConstraints.WEST;
        gbc_lblLblid.insets = new Insets(0, 0, 5, 5);
        gbc_lblLblid.gridx = 0;
        gbc_lblLblid.gridy = 0;
        pnlParticipant.add(lblLblid, gbc_lblLblid);

        final JLabel lblColumn_01 = new JLabel(":");
        final GridBagConstraints gbc_lblColumn_01 = new GridBagConstraints();
        gbc_lblColumn_01.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_01.gridx = 1;
        gbc_lblColumn_01.gridy = 0;
        pnlParticipant.add(lblColumn_01, gbc_lblColumn_01);

        valId = new JTextField();
        valId.setEnabled(false);
        valId.setText("");
        final GridBagConstraints gbc_valId = new GridBagConstraints();
        gbc_valId.anchor = GridBagConstraints.WEST;
        gbc_valId.weightx = 1.0;
        gbc_valId.insets = new Insets(0, 0, 5, 5);
        gbc_valId.gridx = 2;
        gbc_valId.gridy = 0;
        pnlParticipant.add(valId, gbc_valId);
        valId.setColumns(10);

        final JLabel lblLblsurname = new JLabel(L10n.getString("lbl.participant.surname"));
        final GridBagConstraints gbc_lblLblsurname = new GridBagConstraints();
        gbc_lblLblsurname.anchor = GridBagConstraints.WEST;
        gbc_lblLblsurname.insets = new Insets(0, 0, 5, 5);
        gbc_lblLblsurname.gridx = 0;
        gbc_lblLblsurname.gridy = 1;
        pnlParticipant.add(lblLblsurname, gbc_lblLblsurname);

        final JLabel lblColumn_02 = new JLabel(":");
        lblColumn_02.setForeground(Color.RED);
        final GridBagConstraints gbc_lblColumn_02 = new GridBagConstraints();
        gbc_lblColumn_02.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_02.gridx = 1;
        gbc_lblColumn_02.gridy = 1;
        pnlParticipant.add(lblColumn_02, gbc_lblColumn_02);

        valSurname = new JTextField();
        valSurname.getDocument().addDocumentListener(txtDocListener);
        valSurname.setText("");
        final GridBagConstraints gbc_valSurname = new GridBagConstraints();
        gbc_valSurname.anchor = GridBagConstraints.WEST;
        gbc_valSurname.weightx = 1.0;
        gbc_valSurname.insets = new Insets(0, 0, 5, 5);
        gbc_valSurname.gridx = 2;
        gbc_valSurname.gridy = 1;
        pnlParticipant.add(valSurname, gbc_valSurname);
        valSurname.setColumns(20);

        final JLabel lblLblname = new JLabel(L10n.getString("lbl.participant.name"));
        final GridBagConstraints gbc_lblLblname = new GridBagConstraints();
        gbc_lblLblname.anchor = GridBagConstraints.WEST;
        gbc_lblLblname.insets = new Insets(0, 0, 5, 5);
        gbc_lblLblname.gridx = 0;
        gbc_lblLblname.gridy = 2;
        pnlParticipant.add(lblLblname, gbc_lblLblname);

        final JLabel lblColumn_03 = new JLabel(":");
        lblColumn_03.setForeground(Color.RED);
        final GridBagConstraints gbc_lblColumn_03 = new GridBagConstraints();
        gbc_lblColumn_03.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_03.gridx = 1;
        gbc_lblColumn_03.gridy = 2;
        pnlParticipant.add(lblColumn_03, gbc_lblColumn_03);

        valName = new JTextField();
        valName.getDocument().addDocumentListener(txtDocListener);
        valName.setText("");
        final GridBagConstraints gbc_valName = new GridBagConstraints();
        gbc_valName.anchor = GridBagConstraints.WEST;
        gbc_valName.weightx = 1.0;
        gbc_valName.insets = new Insets(0, 0, 5, 5);
        gbc_valName.gridx = 2;
        gbc_valName.gridy = 2;
        pnlParticipant.add(valName, gbc_valName);
        valName.setColumns(20);

        final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
        final GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
        gbc_tabbedPane.insets = new Insets(10, 0, 0, 0);
        gbc_tabbedPane.gridwidth = 3;
        gbc_tabbedPane.fill = GridBagConstraints.BOTH;
        gbc_tabbedPane.gridx = 0;
        gbc_tabbedPane.gridy = 3;
        pnlParticipant.add(tabbedPane, gbc_tabbedPane);

        final JPanel tabPersonal = new JPanel();
        tabPersonal.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab(L10n.getString("lbl.personal-data"), null, tabPersonal, null);
        final GridBagLayout gbl_tabPersonal = new GridBagLayout();
        gbl_tabPersonal.columnWidths = new int[] { 0, 0, 0, 0 };
        gbl_tabPersonal.rowHeights = new int[] { 0, 0, 0, 0, 0 };
        gbl_tabPersonal.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_tabPersonal.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        tabPersonal.setLayout(gbl_tabPersonal);

        final JLabel lblDob = new JLabel(L10n.getString("lbl.participant.dob"));
        lblDob.setVisible(false);
        final GridBagConstraints gbc_lblDob = new GridBagConstraints();
        gbc_lblDob.anchor = GridBagConstraints.WEST;
        gbc_lblDob.insets = new Insets(0, 0, 5, 5);
        gbc_lblDob.gridx = 0;
        gbc_lblDob.gridy = 0;
        tabPersonal.add(lblDob, gbc_lblDob);

        final JLabel lblColumn_04 = new JLabel(":");
        lblColumn_04.setVisible(false);
        final GridBagConstraints gbc_lblColumn_04 = new GridBagConstraints();
        gbc_lblColumn_04.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_04.gridx = 1;
        gbc_lblColumn_04.gridy = 0;
        tabPersonal.add(lblColumn_04, gbc_lblColumn_04);

        valDob = new JDateChooser();
        valDob.setVisible(false);
        valDob.setLocale(Globals.getLocale());
        final GridBagConstraints gbc_valDob = new GridBagConstraints();
        gbc_valDob.anchor = GridBagConstraints.WEST;
        gbc_valDob.insets = new Insets(0, 0, 5, 0);
        gbc_valDob.gridx = 2;
        gbc_valDob.gridy = 0;
        tabPersonal.add(valDob, gbc_valDob);

        final JLabel lblAge = new JLabel(L10n.getString("lbl.participant.age"));
        final GridBagConstraints gbc_lblAge = new GridBagConstraints();
        gbc_lblAge.anchor = GridBagConstraints.WEST;
        gbc_lblAge.insets = new Insets(0, 0, 5, 5);
        gbc_lblAge.gridx = 0;
        gbc_lblAge.gridy = 1;
        tabPersonal.add(lblAge, gbc_lblAge);

        final JLabel lblColumn_05 = new JLabel(":");
        final GridBagConstraints gbc_lblColumn_05 = new GridBagConstraints();
        gbc_lblColumn_05.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_05.gridx = 1;
        gbc_lblColumn_05.gridy = 1;
        tabPersonal.add(lblColumn_05, gbc_lblColumn_05);

        valAge = new JSpinner();
        valAge.addChangeListener(changeListener);
        valAge.setModel(new SpinnerNumberModel(0, 0, 200, 1));
        final GridBagConstraints gbc_valAge = new GridBagConstraints();
        gbc_valAge.anchor = GridBagConstraints.WEST;
        gbc_valAge.insets = new Insets(0, 0, 5, 0);
        gbc_valAge.gridx = 2;
        gbc_valAge.gridy = 1;
        tabPersonal.add(valAge, gbc_valAge);

        final JLabel lblGender = new JLabel(L10n.getString("lbl.participant.gender"));
        final GridBagConstraints gbc_lblGender = new GridBagConstraints();
        gbc_lblGender.anchor = GridBagConstraints.WEST;
        gbc_lblGender.insets = new Insets(0, 0, 5, 5);
        gbc_lblGender.gridx = 0;
        gbc_lblGender.gridy = 2;
        tabPersonal.add(lblGender, gbc_lblGender);

        final JLabel lblColumn_06 = new JLabel(":");
        final GridBagConstraints gbc_lblColumn_06 = new GridBagConstraints();
        gbc_lblColumn_06.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_06.gridx = 1;
        gbc_lblColumn_06.gridy = 2;
        tabPersonal.add(lblColumn_06, gbc_lblColumn_06);

        valGender = new JComboBox<Gender>();
        valGender.addItemListener(itemListener);
        for (Gender item : Gender.values()) {
            valGender.addItem(item);
        }
        final GridBagConstraints gbc_valGender = new GridBagConstraints();
        gbc_valGender.anchor = GridBagConstraints.WEST;
        gbc_valGender.insets = new Insets(0, 0, 5, 0);
        gbc_valGender.gridx = 2;
        gbc_valGender.gridy = 2;
        tabPersonal.add(valGender, gbc_valGender);

        final JLabel lblEducation = new JLabel(L10n.getString("lbl.participant.education"));
        final GridBagConstraints gbc_lblEducation = new GridBagConstraints();
        gbc_lblEducation.anchor = GridBagConstraints.WEST;
        gbc_lblEducation.insets = new Insets(0, 0, 0, 5);
        gbc_lblEducation.gridx = 0;
        gbc_lblEducation.gridy = 3;
        tabPersonal.add(lblEducation, gbc_lblEducation);

        final JLabel lblColumn_07 = new JLabel(":");
        final GridBagConstraints gbc_lblColumn_07 = new GridBagConstraints();
        gbc_lblColumn_07.insets = new Insets(0, 0, 0, 5);
        gbc_lblColumn_07.gridx = 1;
        gbc_lblColumn_07.gridy = 3;
        tabPersonal.add(lblColumn_07, gbc_lblColumn_07);

        valEducation = new JComboBox<Education>();
        valEducation.addItemListener(itemListener);
        for (Education item : Education.values()) {
            valEducation.addItem(item);
        }
        final GridBagConstraints gbc_valEducation = new GridBagConstraints();
        gbc_valEducation.anchor = GridBagConstraints.WEST;
        gbc_valEducation.gridx = 2;
        gbc_valEducation.gridy = 3;
        tabPersonal.add(valEducation, gbc_valEducation);

        final JPanel tabAuto = new JPanel();
        tabAuto.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab(L10n.getString("lbl.driving-history"), null, tabAuto, null);
        final GridBagLayout gbl_tabAuto = new GridBagLayout();
        gbl_tabAuto.columnWidths = new int[] { 0, 0, 0, 0 };
        gbl_tabAuto.rowHeights = new int[] { 0, 0, 0 };
        gbl_tabAuto.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_tabAuto.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
        tabAuto.setLayout(gbl_tabAuto);

        final JLabel lblDrivinglicenceage = new JLabel(L10n.getString("lbl.participant.driving-age"));
        final GridBagConstraints gbc_lblDrivinglicenceage = new GridBagConstraints();
        gbc_lblDrivinglicenceage.anchor = GridBagConstraints.WEST;
        gbc_lblDrivinglicenceage.insets = new Insets(0, 0, 5, 5);
        gbc_lblDrivinglicenceage.gridx = 0;
        gbc_lblDrivinglicenceage.gridy = 0;
        tabAuto.add(lblDrivinglicenceage, gbc_lblDrivinglicenceage);

        final JLabel lblColumn_08 = new JLabel(":");
        final GridBagConstraints gbc_lblColumn_08 = new GridBagConstraints();
        gbc_lblColumn_08.insets = new Insets(0, 0, 5, 5);
        gbc_lblColumn_08.gridx = 1;
        gbc_lblColumn_08.gridy = 0;
        tabAuto.add(lblColumn_08, gbc_lblColumn_08);

        valDrivingAge = new JSpinner();
        valDrivingAge.addChangeListener(changeListener);
        valDrivingAge.setModel(new SpinnerNumberModel(0, 0, 99, 1));
        final GridBagConstraints gbc_valDrivingAge = new GridBagConstraints();
        gbc_valDrivingAge.anchor = GridBagConstraints.WEST;
        gbc_valDrivingAge.insets = new Insets(0, 0, 5, 0);
        gbc_valDrivingAge.gridx = 2;
        gbc_valDrivingAge.gridy = 0;
        tabAuto.add(valDrivingAge, gbc_valDrivingAge);

        final JLabel lblMileage = new JLabel(L10n.getString("lbl.paricipant.mileage"));
        final GridBagConstraints gbc_lblMileage = new GridBagConstraints();
        gbc_lblMileage.anchor = GridBagConstraints.WEST;
        gbc_lblMileage.insets = new Insets(0, 0, 0, 5);
        gbc_lblMileage.gridx = 0;
        gbc_lblMileage.gridy = 1;
        tabAuto.add(lblMileage, gbc_lblMileage);

        final JLabel lblColumn_09 = new JLabel(":");
        final GridBagConstraints gbc_lblColumn_09 = new GridBagConstraints();
        gbc_lblColumn_09.anchor = GridBagConstraints.EAST;
        gbc_lblColumn_09.insets = new Insets(0, 0, 0, 5);
        gbc_lblColumn_09.gridx = 1;
        gbc_lblColumn_09.gridy = 1;
        tabAuto.add(lblColumn_09, gbc_lblColumn_09);

        valMileage = new JSpinner();
        valMileage.addChangeListener(changeListener);
        valMileage.setModel(new SpinnerNumberModel(0, 0, 10000000, 1));
        GridBagConstraints gbc_valMileage = new GridBagConstraints();
        gbc_valMileage.anchor = GridBagConstraints.WEST;
        gbc_valMileage.gridx = 2;
        gbc_valMileage.gridy = 1;
        tabAuto.add(valMileage, gbc_valMileage);

        final JPanel pnlControls = new JPanel();
        pnlControls.setBorder(null);
        final FlowLayout flowLayout = (FlowLayout) pnlControls.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        background.add(pnlControls, BorderLayout.SOUTH);

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                saveParticipant();
            }

        });
        pnlControls.add(btnSave);

        final Component horizontalStrut = Box.createHorizontalStrut(0);
        pnlControls.add(horizontalStrut);

        final JButton btnClose = new JButton(L10n.getString("lbl.close"));
        btnClose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                onClosing();
            }

        });
        pnlControls.add(btnClose);
    }

    public Participant getParticipant() {
        Participant participant = new Participant();
        participant.setId(valId.getText());
        participant.setSurname(valSurname.getText());
        participant.setName(valName.getText());
        participant.setAge((int) valAge.getValue());
        participant.setGender((Gender) valGender.getSelectedItem());
        participant.setEducation((Education) valEducation.getSelectedItem());
        participant.setDrivingAge((int) valDrivingAge.getValue());
        participant.setMileage((int) valMileage.getValue());
        return participant;
    }

    public void setParticipant(Participant participant) {
        trace("set participant: ", participant.getId());
        valId.setText(participant.getId());
        valSurname.setText(participant.getSurname());
        valName.setText(participant.getName());
        valDob.setDate(participant.getDob().getTime());
        valAge.setValue(participant.getAge());
        valGender.setSelectedItem(participant.getGender());
        valEducation.setSelectedItem(participant.getEducation());
        valDrivingAge.setValue(participant.getDrivingAge());
        valMileage.setValue(participant.getMileage());
        clearDirtyFlag();
    }

    private boolean checkDirtyAndContinue() {
        if (dirty) {
            int option = JOptionPane.showConfirmDialog(
                    ParticipantsManagementDialog.this,
                    L10n.getString("msg.participant-was-changed", valId.getText()),
                    L10n.getString("lbl.pondero"),
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            switch (option) {
                case JOptionPane.CANCEL_OPTION:
                    trace("check dirty and continue: ", "CANCEL");
                    return false;
                case JOptionPane.YES_OPTION:
                    trace("check dirty and continue: ", "YES");
                    saveParticipant();
                    break;
                case JOptionPane.NO_OPTION:
                    trace("check dirty and continue: ", "NO");
                    clearDirtyFlag();
                    break;
                default:
                    trace("check dirty and continue: ", "?????");
                    break;
            }
        }
        return true;
    }

    private void clearDirtyFlag() {
        trace("clear dirty flag");
        setTitle(L10n.getString("lbl.manage-participants"));
        btnSave.setEnabled(false);
        dirty = false;
    }

    private void onClosing() {
        trace("user event: close dialog");
        if (checkDirtyAndContinue()) {
            ParticipantsManagementDialog.this.dispose();
        }
    }

    private void saveParticipant() {
        trace("user event: save participant");
        clearDirtyFlag();
    }

    private void setDirtyFlag() {
        trace("set dirty flag");
        dirty = true;
        btnSave.setEnabled(true);
        setTitle(L10n.getString("lbl.manage-participants") + "*");
    }

}
