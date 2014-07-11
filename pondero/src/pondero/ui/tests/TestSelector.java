package pondero.ui.tests;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import pondero.L10n;
import pondero.tests.test.Test;
import pondero.util.UiUtil;

@SuppressWarnings("serial")
public class TestSelector extends JComponent {

    public static final List<Test> REGISTERED_TESTS = new ArrayList<Test>();

    private final JList<Test>      list;

    public TestSelector() {
        setLayout(new BorderLayout(0, 0));

        final JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, L10n.getString("lbl.choose-test") + ":", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.add(scrollPane, BorderLayout.CENTER);

        list = new JList<Test>();
        list.setBackground(UiUtil.getListBackgroundColor());
        list.setCellRenderer(new TestCellRenderer());
        list.setModel(new DefaultListModel<Test>());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);

        addTests();
    }

    private void addTests() {
        final DefaultListModel<Test> model = (DefaultListModel<Test>) list.getModel();
        for (final Test test : REGISTERED_TESTS) {
            model.addElement(test);
        }
    }

    public Test getSelectedValue() {
        return list.getSelectedValue();
    }

    public void addListSelectionListener(final ListSelectionListener listener) {
        list.addListSelectionListener(listener);
    }

}
