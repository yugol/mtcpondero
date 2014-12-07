package pondero.ui.tests;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import pondero.Context;
import pondero.L10n;
import pondero.tests.Test;
import pondero.ui.DialogSelectionListener;
import pondero.util.UiUtil;

@SuppressWarnings("serial")
public class TestSelector extends JComponent {

    private final JList<Test>                   list;
    private final List<DialogSelectionListener> selectionListeners = new ArrayList<DialogSelectionListener>();

    public TestSelector() {
        setLayout(new BorderLayout(0, 0));

        final JPanel panel = new JPanel();
        add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new TitledBorder(null, L10n.getString("lbl.choose-test") + ":", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        scrollPane.getViewport().setBackground(UiUtil.getListBackgroundColor());
        panel.add(scrollPane, BorderLayout.CENTER);

        list = new JList<Test>();
        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(final MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                    final int index = list.getSelectedIndex();
                    final ListSelectionEvent listEvet = new ListSelectionEvent(TestSelector.this, index, index, false);
                    for (final DialogSelectionListener listener : selectionListeners) {
                        listener.valueChosen(listEvet);
                    }
                }
            }

        });
        // list.setBackground(UiUtil.getListBackgroundColor());
        list.setCellRenderer(new TestCellRenderer());
        list.setModel(new DefaultListModel<Test>());
        list.setOpaque(false);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);

        addTests();
    }

    public void addListSelectionListener(final DialogSelectionListener listener) {
        selectionListeners.add(listener);
        list.addListSelectionListener(listener);
    }

    public Test getSelectedValue() {
        return list.getSelectedValue();
    }

    private void addTests() {
        final List<Test> registeredTests = new ArrayList<Test>(Context.REGISTERED_TESTS);
        Collections.sort(registeredTests, new TestComparator());
        final DefaultListModel<Test> model = (DefaultListModel<Test>) list.getModel();
        for (final Test test : registeredTests) {
            model.addElement(test);
        }
    }

}
