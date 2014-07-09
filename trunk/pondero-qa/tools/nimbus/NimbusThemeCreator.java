package nimbus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.Painter;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

//import com.sun.java.swing.Painter;

/**
 * UIManager.put("nimbusBase", new Color(...));
 * UIManager.put("nimbusBlueGrey", new Color(...));
 * UIManager.put("control", new Color(...));
 */

public class NimbusThemeCreator implements ActionListener, ChangeListener,
        ItemListener, PropertyChangeListener, TableModelListener {

    private static class BooleanChooser extends ValueChooser {

        JComponent   pane;

        JRadioButton tru;
        JRadioButton fal;

        @SuppressWarnings("unused")
        BooleanChooser() {
            tru = new JRadioButton(Boolean.TRUE.toString());
            tru.setFont(UIDefaultsRenderer.BOOLEAN_FONT);
            fal = new JRadioButton(Boolean.FALSE.toString());
            fal.setFont(UIDefaultsRenderer.BOOLEAN_FONT);
            final ButtonGroup group = new ButtonGroup();
            group.add(tru);
            group.add(fal);
            pane = new JPanel(null);
            final GroupLayout layout = new GroupLayout(pane);
            pane.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER, true)
                    .addGap(100, 100, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(8)
                            .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                    .addComponent(tru).addComponent(fal))
                            .addGap(8)));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGap(2).addComponent(tru).addComponent(fal).addGap(4));
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return Boolean.valueOf(tru.isSelected());
        }

        @Override
        void setValue(final Object value) {
            if (Boolean.TRUE.equals(value)) {
                tru.setSelected(true);
            } else {
                fal.setSelected(true);
            }
        }

    }

    private static class ColorChooser extends ValueChooser {

        JColorChooser chooser;

        @SuppressWarnings("unused")
        ColorChooser() {
            chooser = new JColorChooser();
        }

        @Override
        JComponent getComponent() {
            return chooser;
        }

        @Override
        Object getValue() {
            return chooser.getColor();
        }

        @Override
        void setValue(final Object value) {
            chooser.setColor((Color) value);
        }

    }

    private static class DimensionChooser extends ValueChooser implements ChangeListener {

        JComponent         pane;

        NumberChooser      width;
        NumberChooser      height;
        UIDefaultsRenderer renderer;

        @SuppressWarnings("unused")
        DimensionChooser() {
            width = new NumberChooser("Width:", 0, 2000);
            height = new NumberChooser("Height:", 0, 2000);
            renderer = new UIDefaultsRenderer();
            renderer.type = Type.Dimension;
            width.addChangeListener(this);
            height.addChangeListener(this);
            pane = NumberChooser.createComponent(renderer, 200, Short.MAX_VALUE, 200, 200, width, height);
        }

        @Override
        public void stateChanged(final ChangeEvent e) {
            if (renderer.value != null) {
                final Dimension d = (Dimension) renderer.value;
                d.width = width.getValue();
                d.height = height.getValue();
                renderer.repaint();
            }
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return renderer.value;
        }

        @Override
        void setValue(final Object value) {
            final Dimension d = (Dimension) value;
            renderer.value = null;
            width.setValue(d.width);
            height.setValue(d.height);
            renderer.value = d.clone();
        }

    }

    private class Exporter implements ActionListener, ChangeListener {

        private final JDialog     dialog;

        private final JTabbedPane tabs;
        private final JTextField  packageField;
        private final JTextField  classField;
        private final JTextField  methodField;
        private final JTextField  location;
        private final JButton     ok;
        private final JTextArea   text;
        private boolean           validTextArea;
        private File              browseDirectory;

        Exporter(final JFrame frame) {

            final JLabel pkgLabel = new JLabel("Package Name:");
            final JLabel clsLabel = new JLabel("Class Name:");
            final JLabel mtdLabel = new JLabel("Method Name:");
            packageField = new JTextField();
            classField = new JTextField("NimbusTheme");
            methodField = new JTextField("loadTheme");
            location = new JTextField(25);
            final JButton browse = new JButton("Browse...");
            browse.addActionListener(this);

            final JPanel options = titled(new JPanel(null), "Naming Options");
            GroupLayout layout = new GroupLayout(options);
            options.setLayout(layout);
            layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                            .addComponent(pkgLabel).addComponent(clsLabel).addComponent(mtdLabel))
                    .addGap(5)
                    .addGroup(layout.createParallelGroup()
                            .addComponent(packageField).addComponent(classField).addComponent(methodField)));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createBaselineGroup(false, true)
                            .addComponent(pkgLabel).addComponent(packageField))
                    .addGroup(layout.createBaselineGroup(false, true)
                            .addComponent(clsLabel).addComponent(classField))
                    .addGroup(layout.createBaselineGroup(false, true)
                            .addComponent(mtdLabel).addComponent(methodField)));
            final JComponent save = titled(createLocation(location, browse), "Save Location");
            final JPanel file = new JPanel(null);
            layout = new GroupLayout(file);
            file.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addComponent(options).addComponent(save));
            final int prf = GroupLayout.PREFERRED_SIZE;
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(options, prf, prf, prf).addComponent(save, prf, prf, prf));

            text = new JTextArea();
            text.setEditable(false);

            tabs = new JTabbedPane();
            tabs.addChangeListener(this);
            tabs.addTab("Export to File", file);
            tabs.addTab("Export to Text", new JScrollPane(text,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));

            ok = new JButton("OK");
            ok.addActionListener(this);
            final JButton cancel = new JButton("Cancel");
            cancel.addActionListener(this);

            dialog = new JDialog(frame, true);
            dialog.setContentPane(createContentPane(tabs, ok, cancel));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand() == "Browse...") {
                final JFileChooser browse = getFileChooser();
                browse.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                browse.setMultiSelectionEnabled(false);
                if (browseDirectory != null) {
                    browse.setSelectedFile(browseDirectory);
                }
                if (JFileChooser.APPROVE_OPTION == browse.showSaveDialog(null)) {
                    browseDirectory = browse.getSelectedFile();
                    location.setText(browseDirectory.getPath());
                }
            } else if (e.getActionCommand() == "OK") {
                if (tabs.getSelectedIndex() == 0) {
                    final String pkg = packageField.getText();
                    final String cls = classField.getText();
                    final String mtd = methodField.getText();
                    final File dir = new File(location.getText());
                    try {
                        if (!dir.isDirectory()) {
                            if (dir.isFile()) {
                                error("Invalid location:\n\t" + dir.getCanonicalPath() + "\nLocation must be a directory.");
                                return;
                            }
                            if (!confirm("Directory does not exist:\n\t" + dir.getCanonicalPath() + "\nCreate?")) { return; }
                            dir.mkdirs();
                            if (!dir.isDirectory()) {
                                error("Unable to create directory:\n\t" + dir.getCanonicalPath());
                                return;
                            }
                        }
                        final File file = new File(dir, cls.concat(".java"));
                        if (file.exists()) {
                            if (!confirm("File already exists:\n\t" + file.getCanonicalPath() + "\nOverwrite?")) { return; }
                        }
                        final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        if (pkg != null && !pkg.isEmpty()) {
                            writer.write(pkg);
                            writer.newLine();
                            writer.newLine();
                        }
                        writer.write("import javax.swing.*;");
                        writer.newLine();
                        writer.write("import javax.swing.plaf.*;");
                        writer.newLine();
                        writer.write("import java.awt.*;");
                        writer.newLine();
                        writer.newLine();
                        writer.write("public class ");
                        writer.write(cls);
                        writer.write(" {");
                        writer.newLine();
                        writer.write("\tpublic static void ");
                        writer.write(mtd);
                        writer.write(" {");
                        writer.newLine();
                        export(writer, "\t\t");
                        writer.write("\t}");
                        writer.newLine();
                        writer.write("}");
                        writer.flush();
                        writer.close();
                    } catch (final IOException x) {
                        error("IOException: " + x.getMessage());
                        return;
                    }
                }
                dialog.dispose();
            } else if (e.getActionCommand() == "Cancel") {
                dialog.dispose();
            }
        }

        @Override
        public void stateChanged(final ChangeEvent e) {
            if (!validTextArea && tabs.getSelectedIndex() == 1) {
                validTextArea = true;
                final StringWriter writer = new StringWriter();
                try {
                    export(writer, null);
                    text.setText(writer.toString());
                } catch (final IOException x) {
                }
            }
        }

        private void export(final Writer writer, final String prefix) throws IOException {
            try (BufferedWriter buf = writer instanceof BufferedWriter ? (BufferedWriter) writer : null) {
                final UIDefaults def = UIManager.getDefaults();
                for (final Map.Entry<Object, Object> entry : def.entrySet()) {
                    if (def.containsKey(entry.getKey())) {
                        if (prefix != null) {
                            writer.write(prefix);
                        }
                        writer.write("UIManager.put(\"");
                        writer.write(entry.getKey().toString());
                        final Object obj = entry.getValue();
                        final Type type = Type.getType(obj);
                        switch (type) {
                            case Color:
                                final Color color = (Color) obj;
                                writer.write("\", new ColorUIResource(0x");
                                writer.write(Integer.toHexString(color.getRGB() & 0xffffff));
                                writer.write("));");
                                break;
                            case Painter:
                                break;
                            case Insets:
                                final Insets insets = (Insets) obj;
                                writer.write("\", new InsetsUIResource(");
                                writer.write(Integer.toString(insets.top));
                                writer.write(", ");
                                writer.write(Integer.toString(insets.left));
                                writer.write(", ");
                                writer.write(Integer.toString(insets.bottom));
                                writer.write(", ");
                                writer.write(Integer.toString(insets.right));
                                writer.write("));");
                                break;
                            case Font:
                                final Font font = (Font) obj;
                                writer.write("\", new FontUIResource(\"");
                                writer.write(font.getFamily());
                                writer.write("\", ");
                                String style = font.isBold() ? "Font.BOLD" : null;
                                style = font.isItalic() ?
                                        style == null ? "Font.ITALIC" : style + " | " + "Font.ITALIC"
                                        : "Font.PLAIN";
                                writer.write(style);
                                writer.write(", ");
                                writer.write(font.getSize());
                                writer.write("));");
                                break;
                            case Boolean:
                                writer.write("\", Boolean.");
                                writer.write(obj == Boolean.TRUE ? "TRUE" : "FALSE");
                                writer.write(");");
                                break;
                            case Integer:
                                writer.write("\", new Integer(");
                                writer.write(obj.toString());
                                writer.write("));");
                                break;
                            case String:
                                writer.write("\", \"");
                                writer.write(obj.toString());
                                writer.write('"');
                                writer.write(");");
                                break;
                            case Icon:
                                break;
                            case Dimension:
                                final Dimension size = (Dimension) obj;
                                writer.write("\", new DimensionUIResource(");
                                writer.write(Integer.toString(size.width));
                                writer.write(", ");
                                writer.write(Integer.toString(size.height));
                                writer.write("));");
                                break;
                            case Object:
                                break;
                        }
                        if (buf != null) {
                            buf.newLine();
                        } else {
                            writer.write('\n');
                        }
                    }
                }
            }
        }

        void showDialog() {
            ok.getRootPane().setDefaultButton(ok);
            validTextArea = false;
            tabs.setSelectedIndex(0);
            dialog.setVisible(true);

        }

    }

    private class FiltersLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(final String name, final Component comp) {
        }

        @Override
        public void layoutContainer(final Container parent) {
            final TableColumnModel mdl = otherTable.getColumnModel();
            final int cw = mdl.getColumn(0).getWidth();
            Dimension size = keyFilterMethod.getPreferredSize();
            final int kfmw = size.width;
            final int kfw = cw - kfmw - 10;
            keyFilter.setBounds(0, 0, kfw, size.height);
            keyFilterMethod.setBounds(kfw, 0, kfmw, size.height);
            size = typeFilter.getPreferredSize();
            typeFilter.setBounds(cw, 0, size.width, size.height);
        }

        @Override
        public Dimension minimumLayoutSize(final Container parent) {
            return size(300);
        }

        @Override
        public Dimension preferredLayoutSize(final Container parent) {
            return size(TABLE_WIDTH);
        }

        @Override
        public void removeLayoutComponent(final Component comp) {
        }

        private Dimension size(final int width) {
            final Dimension size = keyFilter.getPreferredSize();
            size.width = width;
            return size;
        }

    }

    private static class FontChooser extends ValueChooser implements ChangeListener {

        JComponent         pane;

        SpinnerListModel   family;
        SpinnerNumberModel size;
        JToggleButton      bold;
        JToggleButton      italic;
        UIDefaultsRenderer renderer;

        @SuppressWarnings("unused")
        FontChooser() {
            family = new SpinnerListModel(new Object[] {
                    Font.DIALOG, Font.DIALOG_INPUT, Font.MONOSPACED, Font.SANS_SERIF, Font.SERIF
            });
            family.addChangeListener(this);
            final JSpinner familySpin = new JSpinner(family);
            size = new SpinnerNumberModel(32f, 8f, 32f, 2f);
            size.addChangeListener(this);
            final JSpinner sizeSpin = new JSpinner(size);
            bold = new JToggleButton("b");
            bold.addChangeListener(this);
            bold.setFont(bold.getFont().deriveFont(Font.BOLD));
            italic = new JToggleButton("i");
            italic.addChangeListener(this);
            italic.setFont(italic.getFont().deriveFont(Font.ITALIC));
            renderer = new UIDefaultsRenderer();
            renderer.type = Type.Font;
            pane = new JPanel(null);
            final GroupLayout layout = new GroupLayout(pane);
            pane.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER, true)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(8).addComponent(familySpin, 150, 150, 150).addGap(2)
                            .addComponent(sizeSpin).addGap(2).addComponent(bold)
                            .addGap(2).addComponent(italic).addGap(12))
                    .addComponent(renderer));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGap(2)
                    .addGroup(layout.createBaselineGroup(false, true)
                            .addComponent(familySpin).addComponent(sizeSpin)
                            .addComponent(bold).addComponent(italic))
                    .addComponent(renderer, 50, 50, 50));
        }

        @Override
        public void stateChanged(final ChangeEvent e) {
            Font font = (Font) renderer.value;
            if (font != null) {
                if (e.getSource() == size) {
                    renderer.value = font.deriveFont(size.getNumber().floatValue());
                } else if (e.getSource() == bold) {
                    renderer.value = font.deriveFont(bold.isSelected() ?
                            font.getStyle() | Font.BOLD : font.getStyle() & ~Font.BOLD);
                } else if (e.getSource() == italic) {
                    renderer.value = font.deriveFont(italic.isSelected() ?
                            font.getStyle() | Font.ITALIC : font.getStyle() & ~Font.ITALIC);
                } else if (e.getSource() == family) {
                    font = Font.decode(family.getValue().toString() + ' ' + size.getNumber().intValue());
                    int style = 0;
                    if (bold.isSelected()) {
                        style |= Font.BOLD;
                    }
                    if (italic.isSelected()) {
                        style |= Font.ITALIC;
                    }
                    if (style != 0) {
                        font = font.deriveFont(style);
                    }
                    renderer.value = font;
                }
                renderer.repaint();
            }
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return renderer.value;
        }

        @Override
        void setValue(final Object value) {
            final Font font = (Font) value;
            renderer.value = null;
            family.setValue(font.getFamily());
            size.setValue(font.getSize2D());
            bold.setSelected(font.isBold());
            italic.setSelected(font.isItalic());
            renderer.value = value;
        }

    }

    private class Importer implements ActionListener {

        JDialog     dialog;

        JTabbedPane tabs;
        JTextArea   text;
        JTextField  location;
        JButton     ok;
        File        browseFile;

        Importer(final JFrame frame) {

            location = new JTextField(25);
            final JButton browse = new JButton("Browse...");
            browse.addActionListener(this);
            final JPanel file = new JPanel(new BorderLayout());
            file.add(titled(createLocation(location, browse), "File Location"), BorderLayout.SOUTH);

            text = new JTextArea(10, 20);

            tabs = new JTabbedPane();
            tabs.addTab("Import from File", file);
            tabs.addTab("Import from Text", new JScrollPane(text,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));

            ok = new JButton("OK");
            ok.addActionListener(this);
            final JButton cancel = new JButton("Cancel");
            cancel.addActionListener(this);

            dialog = new JDialog(frame, true);
            dialog.setContentPane(createContentPane(tabs, ok, cancel));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand() == "Browse...") {
                final JFileChooser browse = getFileChooser();
                browse.setFileSelectionMode(JFileChooser.FILES_ONLY);
                browse.setMultiSelectionEnabled(false);
                if (browseFile != null) {
                    browse.setSelectedFile(browseFile);
                }
                if (JFileChooser.APPROVE_OPTION == browse.showOpenDialog(null)) {
                    browseFile = browse.getSelectedFile();
                    location.setText(browseFile.getPath());
                }
            } else if (e.getActionCommand() == "OK") {
                if (tabs.getSelectedIndex() == 0) {
                    try {
                        final File file = new File(location.getText());
                        if (!file.isFile()) {
                            error("Invalid File:\n\t" + file.getCanonicalPath());
                            return;
                        }
                        //TODO
                        error("Not Implemented.");
                    } catch (final IOException x) {
                        error("IOException: " + x.getMessage());
                        return;
                    }
                } else if (tabs.getSelectedIndex() == 1) {
                    final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                    if (compiler == null) {
                        error("No compiler available.");
                        return;
                    }
                    final String statements = text.getText();
                    final StringBuilder s = new StringBuilder(statements.length() + 200);
                    final String cls = "NimbusTheme";
                    s.append("import javax.swing.*;\n");
                    s.append("import javax.swing.plaf.*;\n");
                    s.append("import java.awt.*;\n");
                    s.append("public class ").append(cls).append(" {\n");
                    s.append("\tpublic static void loadTheme() {\n");
                    s.append(statements);
                    s.append("\t}\n}");

                    final CompilationTask task = compiler.getTask(null, null, null, null, null,
                            Arrays.asList(new MemoryFileObject(cls, s.toString())));
                    final boolean success = task.call();
                    if (!success) {
                        error("Unable to compile code.");
                        return;
                    } else {
                        try {
                            Class.forName(cls).getDeclaredMethod("loadTheme", (Class[]) null)
                                    .invoke(null, (Object[]) null);
                            final File file = new File(".", cls.replace('.', File.separatorChar).concat(".class"));
                            if (file.exists()) {
                                file.delete();
                            }
                        } catch (final Exception x) {
                            error(x.getClass().getSimpleName() + ": " + x.getMessage());
                            return;
                        }
                    }
                }
                dialog.dispose();
            } else if (e.getActionCommand() == "Cancel") {
                dialog.dispose();
            }
        }

        void showDialog() {
            ok.getRootPane().setDefaultButton(ok);
            dialog.setVisible(true);
        }

    }

    private static class InsetsChooser extends ValueChooser implements ChangeListener {

        JComponent         pane;

        NumberChooser      top;
        NumberChooser      left;
        NumberChooser      bottom;
        NumberChooser      right;
        UIDefaultsRenderer renderer;

        @SuppressWarnings("unused")
        InsetsChooser() {
            top = new NumberChooser("Top:", 0, 20);
            left = new NumberChooser("Left:", 0, 20);
            bottom = new NumberChooser("Bottom:", 0, 20);
            right = new NumberChooser("Right:", 0, 20);
            renderer = new UIDefaultsRenderer();
            renderer.type = Type.Insets;
            top.addChangeListener(this);
            left.addChangeListener(this);
            bottom.addChangeListener(this);
            right.addChangeListener(this);

            pane = NumberChooser.createComponent(renderer, 120, 120, 50, 50, top, left, bottom, right);
        }

        @Override
        public void stateChanged(final ChangeEvent e) {
            if (renderer.value != null) {
                final Insets in = (Insets) renderer.value;
                in.top = top.getValue();
                in.left = left.getValue();
                in.bottom = bottom.getValue();
                in.right = right.getValue();
                renderer.repaint();
            }
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return renderer.value;
        }

        @Override
        void setValue(final Object value) {
            final Insets i = (Insets) value;
            renderer.value = null;
            top.setValue(i.top);
            left.setValue(i.left);
            bottom.setValue(i.bottom);
            right.setValue(i.right);
            renderer.value = i.clone();
            renderer.repaint();
        }

    }

    private static class IntegerChooser extends ValueChooser {

        JComponent    pane;

        NumberChooser chooser;

        @SuppressWarnings("unused")
        IntegerChooser() {
            chooser = new NumberChooser(null, -10, 100);
            pane = NumberChooser.createComponent(null, -1, -1, -1, -1, chooser);
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return chooser.getValue();
        }

        @Override
        void setValue(final Object value) {
            chooser.setValue((Integer) value);
        }

    }

    private class MemoryFileObject extends SimpleJavaFileObject {

        final String code;

        MemoryFileObject(final String name, final String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(final boolean ignoreEncodingErrors) {
            return code;
        }
    }

    private static class NumberChooser implements ChangeListener {

        static JComponent createComponent(final JComponent preview, final int prefW, final int maxW, final int prefH, final int maxH, final NumberChooser... choosers) {
            final JComponent pane = new JPanel(null);
            final GroupLayout layout = new GroupLayout(pane);
            pane.setLayout(layout);
            GroupLayout.ParallelGroup labelX = null;
            final GroupLayout.ParallelGroup spinX = layout.createParallelGroup(Alignment.LEADING, false);
            final GroupLayout.ParallelGroup slideX = layout.createParallelGroup(Alignment.LEADING, false);
            final GroupLayout.SequentialGroup y = layout.createSequentialGroup().addGap(2);
            for (final NumberChooser chooser : choosers) {
                final JLabel label = chooser.name == null ? null : new JLabel(chooser.name);
                final JSpinner spin = new JSpinner(chooser.spin);
                final GroupLayout.ParallelGroup baseline = layout.createBaselineGroup(false, true);
                y.addGroup(baseline);
                if (label != null) {
                    if (labelX == null) {
                        labelX = layout.createParallelGroup(Alignment.TRAILING, false);
                    }
                    labelX.addComponent(label);
                    baseline.addComponent(label);
                }
                spinX.addComponent(spin);
                slideX.addComponent(chooser.slide);
                baseline.addComponent(spin).addComponent(chooser.slide);
            }
            GroupLayout.Group x = layout.createSequentialGroup().addGap(8);
            if (labelX != null) {
                x.addGroup(labelX).addGap(2);
            }
            x.addGroup(spinX).addGap(2).addGroup(slideX).addGap(8);
            y.addGap(4);
            if (preview != null) {
                y.addComponent(preview, prefH, prefH, maxH);
                y.addGap(4);
                x = layout.createParallelGroup(Alignment.CENTER, false)
                        .addGroup(x).addComponent(preview, prefW, prefW, maxW);
            }
            layout.setHorizontalGroup(x);
            layout.setVerticalGroup(y);
            return pane;
        }

        String             name;
        SpinnerNumberModel spin;
        JSlider            slide;

        NumberChooser(final String nam, final int min, final int max) {
            name = nam;
            spin = new SpinnerNumberModel(min, min, max, 1);
            spin.addChangeListener(this);
            slide = new JSlider(min, max);
            slide.setMinorTickSpacing((max - min) / 10);
            slide.setMajorTickSpacing((max - min) / 2);
            slide.setPaintTicks(true);
            slide.addChangeListener(this);
        }

        @Override
        public void stateChanged(final ChangeEvent e) {
            if (spin.getNumber().intValue() != slide.getValue()) {
                if (e.getSource() == slide) {
                    spin.setValue(slide.getValue());
                } else {
                    slide.setValue(spin.getNumber().intValue());
                }
            }
        }

        void addChangeListener(final ChangeListener l) {
            slide.addChangeListener(l);
        }

        int getValue() {
            return slide.getValue();
        }

        void setValue(final int value) {
            slide.setValue(value);
        }
    }

    //TODO
    @SuppressWarnings("unused")
    private static class PainterChooser extends ValueChooser implements ActionListener, ListSelectionListener, ChangeListener {

        JComponent         pane;

        JTabbedPane        tabs;
        JScrollPane        tablePane;
        JTable             table;
        JTextArea          editor;
        UIDefaultsRenderer renderer;
        Object             value;
        JDialog            dialog;

        PainterChooser() {
            tablePane = new JScrollPane();
            renderer = new UIDefaultsRenderer();
            renderer.type = Type.Painter;
            editor = new JTextArea(20, 80);
            editor.setText("Not Implemented");
            editor.setEnabled(false);
            editor.setFont(Font.decode(Font.MONOSPACED + ' ' + 12));
            final JScrollPane scroller = new JScrollPane(editor);
            final JButton update = new JButton("Update");
            update.addActionListener(this);
            update.setEnabled(false);
            final JButton toDialog = new JButton("Switch to Dialog");
            toDialog.addActionListener(this);
            final JPanel custom = new JPanel(null);
            GroupLayout layout = new GroupLayout(custom);
            custom.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addComponent(scroller)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(update).addGap(10, 100, Short.MAX_VALUE).addComponent(toDialog)));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(scroller)
                    .addGroup(layout.createBaselineGroup(false, true)
                            .addComponent(update).addComponent(toDialog)));

            tabs = new JTabbedPane();
            pane = new JPanel(null);
            layout = new GroupLayout(pane);
            pane.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup()
                    .addComponent(tabs)
                    .addComponent(renderer, Alignment.CENTER, 100, 100, Short.MAX_VALUE));
            layout.setVerticalGroup(layout.createSequentialGroup()
                    .addComponent(tabs).addComponent(renderer, 25, 25, 25));
            tabs.add("Custom", custom);
            tabs.add("Nimbus Painters", tablePane);
            tabs.addChangeListener(this);
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand() == "Update") {

            } else if (e.getActionCommand() == "Switch to Dialog") {
                if (dialog == null) {
                    dialog = new JDialog((JFrame) null, true);
                }
                dialog.add(pane, BorderLayout.CENTER);
                dialog.pack();
                dialog.setVisible(true);
            }
        }

        @Override
        public void stateChanged(final ChangeEvent e) {
            if (tabs.getSelectedComponent() == tablePane && table == null) {
                final DefaultTableColumnModel columns = new DefaultTableColumnModel();
                TableColumn column = new TableColumn(0, 400);
                column.setHeaderValue("Key");
                columns.addColumn(column);
                column = new TableColumn(2, 50, new UIDefaultsRenderer(), null);
                columns.addColumn(column);
                column.setHeaderValue(Type.Painter.name());
                table = new Table(new UITypeTableModel(painterKeys, Type.Painter, false), columns);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setRowHeight(25);
                table.setPreferredScrollableViewportSize(new Dimension(500, table.getRowHeight() * 10));
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table.getSelectionModel().addListSelectionListener(this);
                tablePane.getViewport().setView(table);
                tablePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            }
        }

        @Override
        public void valueChanged(final ListSelectionEvent e) {
            if (value == null || e.getValueIsAdjusting()) { return; }
            final int row = table.getSelectedRow();
            renderer.value = row < 0 ? value :
                    UIManager.getLookAndFeelDefaults().get(
                            table.getValueAt(row, 0));
            renderer.repaint();
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return value;
        }

        @Override
        void setValue(final Object value) {
            this.value = null;
            if (table != null) {
                table.changeSelection(-1, -1, false, false);
            }
            this.value = value;
            renderer.value = value;
        }

    }

    private class StackedTableLayout implements LayoutManager {

        int     minRows;

        int     prefRows;

        boolean fillHeight;

        @SuppressWarnings("unused")
        StackedTableLayout() {
            this(5, 15, true);
        }

        StackedTableLayout(final int minRows, final int prefRows, final boolean fillHeight) {
            this.minRows = minRows;
            this.prefRows = prefRows;
            this.fillHeight = fillHeight;
        }

        @Override
        public void addLayoutComponent(final String name, final Component comp) {
        }

        @Override
        public void layoutContainer(final Container parent) {
            final JScrollPane[] scrollers = scrollers(parent);
            if (scrollers == null) { return; }
            final int[] max = new int[scrollers.length];
            final int[] rowHeights = new int[scrollers.length];
            final int[] yInsets = new int[scrollers.length];
            int maxTot = 0;
            final Insets insets = parent.getInsets();
            int y = insets.top;
            final int x = insets.left;
            final int height = parent.getHeight() - y - insets.bottom;
            final int width = parent.getWidth() - x - insets.right;
            for (int i = scrollers.length; --i >= 0;) {
                final JTable table = (JTable) scrollers[i].getViewport().getView();
                Dimension size = scrollers[i].getPreferredSize();
                final int h = size.height;
                size = table.getPreferredScrollableViewportSize();
                yInsets[i] = h - size.height;
                rowHeights[i] = table.getRowHeight();
                max[i] = table.getRowHeight() * table.getRowCount();
                maxTot += max[i] + yInsets[i];
            }
            if (maxTot <= height) {
                for (int i = 0; i < scrollers.length; i++) {
                    final int h = max[i] + yInsets[i];
                    scrollers[i].setBounds(x, y, width, h);
                    y += h;
                }
            } else {
                int count = max.length;
                int availableHeight = height;
                while (count > 1) {
                    int min = Integer.MAX_VALUE;
                    int minIdx = -1;
                    for (int i = max.length; --i >= 0;) {
                        if (max[i] >= 0 && max[i] + yInsets[i] < min) {
                            min = max[i] + yInsets[i];
                            minIdx = i;
                        }
                    }
                    if (min > availableHeight / count) {
                        break;
                    }
                    availableHeight -= min;
                    max[minIdx] = -min;
                    count--;
                }
                int rem = availableHeight % count;
                availableHeight /= count;
                for (int i = scrollers.length; --i >= 0;) {
                    int h = max[i];
                    if (h < 0) {
                        continue;
                    }
                    if (h + yInsets[i] > availableHeight) {
                        h = availableHeight;
                        final int r = (h - yInsets[i]) % rowHeights[i];
                        h -= r;
                        rem += r;
                        max[i] = h;
                    } else {
                        max[i] = -h - yInsets[i];
                    }
                }
                for (int i = 0; i < scrollers.length; i++) {
                    int h = max[i];
                    if (h < 0) {
                        h = -h;
                    } else {
                        if (rem > rowHeights[i]) {
                            h += rowHeights[i];
                            rem -= rowHeights[i];
                        }
                    }
                    scrollers[i].setBounds(x, y, width, h);
                    y += h;
                }
            }
            if (fillHeight) {
                final JScrollPane s = scrollers[scrollers.length - 1];
                s.setSize(width, s.getHeight() + height - y);
            }
        }

        @Override
        public Dimension minimumLayoutSize(final Container parent) {
            return size(parent, true);
        }

        @Override
        public Dimension preferredLayoutSize(final Container parent) {
            return size(parent, false);
        }

        @Override
        public void removeLayoutComponent(final Component comp) {
        }

        private JScrollPane[] scrollers(final Container parent) {
            synchronized (parent.getTreeLock()) {
                int n = parent.getComponentCount();
                if (n == 0) { return null; }
                final JScrollPane[] scrollers = new JScrollPane[n];
                while (--n >= 0) {
                    scrollers[n] = (JScrollPane) parent.getComponent(n);
                }
                return scrollers;
            }
        }

        private Dimension size(final Container parent, final boolean min) {
            final JScrollPane[] scrollers = scrollers(parent);
            if (scrollers == null) { return new Dimension(0, 0); }
            final Insets insets = parent.getInsets();
            int height = insets.top + insets.bottom;
            final int xInsets = insets.left + insets.right;
            int maxWidth = 0;
            final int rows = min ? minRows : prefRows;
            for (int i = scrollers.length; --i >= 0;) {
                final JTable table = (JTable) scrollers[i].getViewport().getView();
                Dimension size = scrollers[i].getPreferredSize();
                int w = size.width;
                final int h = size.height;
                size = table.getPreferredScrollableViewportSize();
                height += h - size.height +
                        Math.min(rows, table.getRowCount()) * table.getRowHeight();
                w -= size.width;
                size = min ? table.getMinimumSize() : table.getPreferredSize();
                w += size.width;
                if (w > maxWidth) {
                    maxWidth = w;
                }
            }
            return new Dimension(maxWidth + xInsets, height);
        }

    }

    private static class StringChooser extends ValueChooser {

        JComponent pane;

        JTextField text;

        @SuppressWarnings("unused")
        StringChooser() {
            pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
            text = new JTextField(40);
            pane.add(text);
        }

        @Override
        JComponent getComponent() {
            return pane;
        }

        @Override
        Object getValue() {
            return text.getText();
        }

        @Override
        void setValue(final Object value) {
            text.setText(value.toString());
        }

    }

    @SuppressWarnings("serial")
    private static class Table extends JTable implements ComponentListener {

        Table(final TableModel mdl, final TableColumnModel clm) {
            super(mdl, clm);
        }

        @Override
        public void addNotify() {
            super.addNotify();
            final Container parent = getParent();
            if (parent instanceof JViewport) {
                parent.addComponentListener(this);
            }
        }

        @Override
        public void componentHidden(final ComponentEvent e) {
        }

        @Override
        public void componentMoved(final ComponentEvent e) {
        }

        @Override
        public void componentResized(final ComponentEvent e) {
            final JViewport port = (JViewport) e.getSource();
            final TableColumnModel columns = getColumnModel();
            int width = port.getWidth();
            final Insets in = port.getInsets();
            width -= in.left + in.right;
            for (int i = columns.getColumnCount(); --i > 0;) {
                width -= columns.getColumn(i).getWidth();
            }
            if (width < 210) {
                width = 210;
            }
            final TableColumn col = columns.getColumn(0);
            if (width != col.getPreferredWidth()) {
                col.setMinWidth(width);
                col.setPreferredWidth(width);
            }
        }

        @Override
        public void componentShown(final ComponentEvent e) {
        }

        /**
         * Overridden to supply hasFocus as false to the renderers
         * but still allow the table to be focusable.
         */
        @Override
        public Component prepareRenderer(final TableCellRenderer renderer,
                final int row, final int column) {
            final Object value = getValueAt(row, column);
            boolean isSelected = false;
            // Only indicate the selection and focused cell if not printing
            if (!isPaintingForPrint()) {
                isSelected = isCellSelected(row, column);
            }
            return renderer.getTableCellRendererComponent(
                    this, value, isSelected, false, row, column);
        }

        @Override
        public void removeNotify() {
            super.removeNotify();
            final Container parent = getParent();
            if (parent instanceof JViewport) {
                parent.removeComponentListener(this);
            }
        }

    }

    private enum Type {
        Color (ColorChooser.class),
        Painter (null),
        Insets (InsetsChooser.class),
        Font (FontChooser.class),
        Boolean (BooleanChooser.class),
        Integer (IntegerChooser.class),
        String (StringChooser.class),
        Icon (null),
        Dimension (DimensionChooser.class),
        Object (null);

        static Type getType(final Object obj) {
            if (obj instanceof Color) {
                return Color;
            } else if (obj instanceof Painter<?>) {
                return Painter;
            } else if (obj instanceof Insets) {
                return Insets;
            } else if (obj instanceof Font) {
                return Font;
            } else if (obj instanceof Boolean) {
                return Boolean;
            } else if (obj instanceof Integer) {
                return Integer;
            } else if (obj instanceof Icon) {
                return Icon;
            } else if (obj instanceof String) {
                return String;
            } else if (obj instanceof Dimension) {
                return Dimension;
            } else {
                return Object;
            }

        }

        ValueChooser                  chooser;
        Class<? extends ValueChooser> chooserClass;

        private Type(final Class<? extends ValueChooser> cls) {
            chooserClass = cls;
        }

        ValueChooser getValueChooser() {
            if (chooser == null) {
                if (chooserClass == null) { return null; }
                try {
                    chooser = chooserClass.newInstance();
                } catch (final Exception e) {
                    e.printStackTrace();
                    chooserClass = null;
                    return null;
                }
            }
            return chooser;
        }

    }

    @SuppressWarnings("serial")
    private static class UIDefaultsEditor extends AbstractCellEditor implements TableCellEditor, ActionListener, MouseListener {

        private static final String OK     = "OK";
        private static final String CANCEL = "Cancel";

        UIDefaultsRenderer          renderer;

        JPopupMenu                  popup;
        ValueChooser                currentChooser;

        public UIDefaultsEditor() {
            renderer = new UIDefaultsRenderer();
            renderer.addMouseListener(this);
            popup = new JPopupMenu();
            popup.setLayout(new BorderLayout());
            final JButton ok = new JButton(OK);
            ok.addActionListener(this);
            final JButton cancel = new JButton(CANCEL);
            cancel.addActionListener(this);
            final JPanel buttons = new JPanel(null);
            final GroupLayout layout = new GroupLayout(buttons);
            buttons.setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup(Alignment.CENTER, true)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(8).addComponent(ok).addGap(5).addComponent(cancel).addGap(8))
                    .addGap(100, 100, Short.MAX_VALUE));
            layout.setVerticalGroup(layout.createBaselineGroup(false, true)
                    .addComponent(ok).addComponent(cancel));
            layout.linkSize(SwingConstants.HORIZONTAL, ok, cancel);

            popup.add(buttons, BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand() == OK) {
                final Object value = currentChooser.getValue();
                if (!value.equals(renderer.value)) {
                    renderer.value = value;
                }
                currentChooser = null;
                popup.setVisible(false);
                fireEditingStopped();
            } else if (e.getActionCommand() == CANCEL) {
                currentChooser = null;
                popup.setVisible(false);
                fireEditingCanceled();
            }
        }

        @Override
        public Object getCellEditorValue() {
            return renderer.value;
        }

        @Override
        public Component getTableCellEditorComponent(final JTable table,
                final Object value, final boolean isSelected, final int row, final int column) {
            return renderer.getTableCellRendererComponent(table, value, true, false, row, column);
        }

        @Override
        public boolean isCellEditable(final EventObject e) {
            if (e instanceof MouseEvent) {
                final MouseEvent me = (MouseEvent) e;
                return (me.getModifiersEx() & (InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK |
                        InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)) == 0;
            }
            return super.isCellEditable(e);
        }

        @Override
        public void mouseClicked(final MouseEvent e) {
        }

        @Override
        public void mouseEntered(final MouseEvent e) {
        }

        @Override
        public void mouseExited(final MouseEvent e) {
        }

        @Override
        public void mousePressed(final MouseEvent e) {
        }

        @Override
        public void mouseReleased(final MouseEvent evt) {
            currentChooser = renderer.type.getValueChooser();
            if (currentChooser == null) { return; }
            currentChooser.setValue(renderer.value);
            final BorderLayout layout = (BorderLayout) popup.getLayout();
            final Component cur = layout.getLayoutComponent(BorderLayout.CENTER);
            if (cur != currentChooser.getComponent()) {
                if (cur != null) {
                    popup.remove(cur);
                }
                popup.add(currentChooser.getComponent(), BorderLayout.CENTER);
            }
            popup.show(renderer, 0, renderer.getHeight());
        }

    }

    @SuppressWarnings("serial")
    private static class UIDefaultsRenderer extends JComponent implements TableCellRenderer {

        private static final Font BOOLEAN_FONT = Font.decode("sansserif-bold");

        Object                    value;
        Type                      type;
        int                       row          = -1;
        boolean                   selected     = false;

        @Override
        public Component getTableCellRendererComponent(final JTable tbl,
                final Object val, final boolean isSelected, final boolean hasFocus, final int row,
                final int column) {
            final UITableModel mdl = (UITableModel) tbl.getModel();
            value = val;
            type = mdl.getType(tbl.convertRowIndexToModel(row));
            this.row = row;
            selected = isSelected;
            return this;
        }

        private void drawString(final Graphics g, String str, final Font font) {
            g.setColor(selected ?
                    UIManager.getColor("Table[Enabled+Selected].textForeground") :
                    UIManager.getColor("Table.textForeground"));
            g.setFont(font);
            ((Graphics2D) g).setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            final FontMetrics metrics = g.getFontMetrics();
            int w = metrics.stringWidth(str);
            final int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            int x;
            final int cw = getWidth();
            if (w > cw) {
                w = metrics.charWidth('.') * 3;
                int i = 0;
                while (w < cw) {
                    w += metrics.charWidth(str.charAt(i++));
                }
                str = str.substring(0, i - 1).concat("...");
                x = 0;
            } else {
                x = (cw - w) / 2;
            }
            g.drawString(str, x, y);
        }

        @Override
        protected void paintComponent(final Graphics g) {
            if (selected) {
                g.setColor(UIManager.getColor("Table[Enabled+Selected].textBackground"));
                g.fillRect(0, 0, getWidth(), getHeight());
            } else if (row % 2 == 0) {
                g.setColor(UIManager.getColor("Table.alternateRowColor"));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            switch (type) {
                case Color: {
                    final Color col = (Color) value;
                    g.setColor(col);
                    final Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 10, 10);
                }
                    break;
                case Painter: {
                    @SuppressWarnings("unchecked")
                    final Painter<JComponent> painter = (Painter<JComponent>) value;
                    g.translate((getWidth() - getHeight()) / 2, 0);
                    painter.paint((Graphics2D) g, this, getHeight(), getHeight());
                }
                    break;
                case Insets: {
                    final Insets in = (Insets) value;
                    g.setColor(Color.BLACK);
                    g.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
                    g.setColor(Color.GRAY);
                    g.drawRect(3 + in.left, 3 + in.top, getWidth() - 6 - in.right - in.left, getHeight() - 6 - in.bottom - in.top);
                }
                    break;
                case Font: {
                    final Font font = (Font) value;
                    drawString(g, font.getFamily(), font);
                }
                    break;
                case Boolean:
                    drawString(g, value.toString(), BOOLEAN_FONT);
                    break;
                case Integer:
                case String:
                    drawString(g, value.toString(), getFont());
                    break;
                case Icon: {
                    final Icon icn = (Icon) value;
                    final int x = (getWidth() - icn.getIconWidth()) / 2;
                    final int y = (getHeight() - icn.getIconHeight()) / 2;
                    icn.paintIcon(this, g, x, y);
                }
                    break;
                case Dimension: {
                    final Dimension d = (Dimension) value;
                    if (d.width < getWidth() - 2 && d.height < getHeight() - 2) {
                        g.setColor(Color.GRAY);
                        g.drawRect((getWidth() - d.width) / 2, (getHeight() - d.height) / 2, d.width, d.height);
                    } else {
                        drawString(g, d.width + " x " + d.height, getFont());
                    }
                }
                    break;
                case Object: {
                    System.out.println(value.getClass());
                }
                    break;
            }
        }
    }

    @SuppressWarnings("serial")
    private static class UITableModel extends AbstractTableModel {

        private final String[] keys;

        private final Type[]   types;

        UITableModel(final String[] kys) {
            this(kys, new Type[kys.length]);
        }

        UITableModel(final String[] kys, final Type[] tys) {
            keys = kys;
            types = tys;
        }

        @Override
        public Class<?> getColumnClass(final int col) {
            switch (col) {
                case 2:
                    return UIDefaults.class;
                case 3:
                    return Boolean.class;
            }
            return Object.class;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(final int col) {
            switch (col) {
                case 0:
                    return "Key";
                case 1:
                    return "Type";
                case 2:
                    return "Value";
                case 3:
                    return "Default";
            }
            throw new IllegalArgumentException();
        }

        @Override
        public int getRowCount() {
            return keys.length;
        }

        @Override
        public Object getValueAt(final int row, final int col) {
            switch (col) {
                case 0:
                    return keys[row];
                case 1:
                    return getType(row);
                case 2:
                    return UIManager.get(keys[row]);
                case 3:
                    return !UIManager.getDefaults().containsKey(keys[row]);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public boolean isCellEditable(final int row, final int col) {
            return col == 2 || col == 3;
        }

        @Override
        public void setValueAt(final Object aValue, final int row, final int col) {
            switch (col) {
                case 2:
                    final Object def = UIManager.getLookAndFeel().getDefaults().get(keys[row]);
                    if (getType(row) == Type.Font && fontEquals((Font) aValue, (Font) def) || aValue.equals(def)) {
                        UIManager.put(keys[row], null);
                    } else {
                        UIManager.put(keys[row], aValue);
                    }
                    fireTableCellUpdated(row, 3);
                    break;
                case 3:
                    if (aValue == Boolean.TRUE) {
                        UIManager.put(keys[row], null);
                        fireTableCellUpdated(row, 2);
                    }
                    break;
            }
        }

        // Font.equals() is too sensitive, so use this.
        private boolean fontEquals(final Font a, final Font b) {
            return a.getSize2D() == b.getSize2D() &&
                    a.getStyle() == b.getStyle() &&
                    a.getFamily().equals(b.getFamily());
        }

        Type getType(final int row) {
            if (types[row] == null) {
                types[row] = Type.getType(UIManager.get(keys[row]));
            }
            return types[row];
        }

    }

    @SuppressWarnings("serial")
    private static class UITypeTableModel extends UITableModel {

        private final Type    type;

        private final boolean editable;

        UITypeTableModel(final String[] keys, final Type typ, final boolean edt) {
            super(keys, null);
            type = typ;
            editable = edt;
        }

        @Override
        public String getColumnName(final int col) {
            return col == 2 ? type.name() : super.getColumnName(col);
        }

        @Override
        public boolean isCellEditable(final int row, final int col) {
            return editable ? super.isCellEditable(row, col) : false;
        }

        @Override
        Type getType(final int row) {
            return type;
        }

    }

    private static abstract class ValueChooser {

        abstract JComponent getComponent();

        abstract Object getValue();

        abstract void setValue(Object value);

    }

    private static final int TABLE_WIDTH   = 450;

    private static final int VALUE_WIDTH   = 100;

    private static final int DEFAULT_WIDTH = 50;

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                setNimbusLookAndFeel();
                //loadRandom();
                final JFrame frame = new JFrame(NimbusThemeCreator.class.getSimpleName());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                final NimbusThemeCreator creator = new NimbusThemeCreator();
                frame.add(creator.createBody(), BorderLayout.CENTER);
                frame.getRootPane().setDefaultButton(creator.defaultButton);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private static JPanel centered(final JComponent c) {
        final JPanel panel = new JPanel(new GridBagLayout());
        panel.add(c);
        return panel;
    }

    private static JOptionPane createOptionPane(final String message, final int type) {
        final JOptionPane pane = new JOptionPane(message, type);
        String title = message;
        if (type == JOptionPane.QUESTION_MESSAGE) {
            title = "Question Message";
            pane.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);
        }
        return titled(pane, title);
    }

    private static <T extends JComponent> T disabled(final T c) {
        c.setEnabled(false);
        return c;
    }

    private static String getPrototypeString(final int chars) {
        final char[] c = new char[chars];
        Arrays.fill(c, 'w');
        return new String(c);
    }

    @SuppressWarnings("unused")
    private static void loadRandom() {
        final Random rdm = new Random();
        for (final Map.Entry<Object, Object> entry : UIManager.getLookAndFeelDefaults().entrySet()) {
            final Type type = Type.getType(entry.getValue());
            switch (type) {
                case Color:
                    UIManager.put(entry.getKey(), new Color(rdm.nextInt(256), rdm.nextInt(256), rdm.nextInt(256)));
                    break;
                case Insets:
                    UIManager.put(entry.getKey(), new Insets(rdm.nextInt(10), rdm.nextInt(10), rdm.nextInt(10), rdm.nextInt(10)));
                    break;
                case Font:
                    final Font font = (Font) entry.getValue();
                    UIManager.put(entry.getKey(), font.deriveFont(font.getSize2D() + (rdm.nextBoolean() ? 1 : -1)));
                    break;
                case Boolean:
                    UIManager.put(entry.getKey(), Boolean.FALSE.equals(entry.getValue()));
                    break;
                case Integer:
                    final Integer i = (Integer) entry.getValue();
                    UIManager.put(entry.getKey(), i + rdm.nextInt(5));
                default:
                    break;
            }
        }
    }

    private static JProgressBar progress(final int value, final boolean paint) {
        final JProgressBar bar = new JProgressBar();
        bar.setValue(value);
        bar.setStringPainted(paint);
        return bar;
    }

    private static void setNimbusLookAndFeel() {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
            throw new ClassNotFoundException();
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static JSlider tickedSlider(final boolean paintLabels) {
        final JSlider s = new JSlider(0, 100);
        s.setMajorTickSpacing(25);
        s.setMinorTickSpacing(5);
        s.setPaintTicks(true);
        s.setPaintLabels(paintLabels);
        return s;
    }

    private static <T extends JComponent> T titled(final T c, final String title) {
        c.setBorder(BorderFactory.createTitledBorder(title));
        return c;
    }

    private final JButton   update;

    private final JCheckBox autoUpdate;

    private final JTable    primaryTable;

    private final JTable    secondaryTable;

    private final JTable    otherTable;

    @SuppressWarnings("rawtypes")
    private final JComboBox keyFilter;

    @SuppressWarnings("rawtypes")
    private final JComboBox keyFilterMethod;

    @SuppressWarnings("rawtypes")
    private final JComboBox typeFilter;

    private final JButton   defaultButton;

    private static String[] painterKeys;

    private static JPanel createPanel(final Class<?> cls, final int rows, final int cols, final Component... components) {
        final JPanel panel = new JPanel(new GridLayout(rows, cols, 5, 0));
        for (final Component c : components) {
            panel.add(c);
        }
        return titled(panel, cls.getSimpleName());
    }

    private static JTable createUITable(final boolean keyColumnResizable, final int typeWidth, final Type type,
            final List<String> lst) {
        final String[] keys = lst.toArray(new String[lst.size()]);
        Arrays.sort(keys);
        final TableModel mdl = type == null ? new UITableModel(keys) : new UITypeTableModel(keys, type, true);
        final JTable table = new Table(mdl, null);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(25);
        final TableColumnModel columns = table.getColumnModel();
        final int keyWidth = TABLE_WIDTH - typeWidth - VALUE_WIDTH - DEFAULT_WIDTH;
        columns.getColumn(0).setMinWidth(keyWidth);
        columns.getColumn(0).setPreferredWidth(keyWidth);
        columns.getColumn(0).setResizable(keyColumnResizable);
        setWidth(columns.getColumn(1), typeWidth);
        final TableColumn column = columns.getColumn(2);
        setWidth(column, VALUE_WIDTH);
        column.setCellRenderer(new UIDefaultsRenderer());
        column.setCellEditor(new UIDefaultsEditor());
        setWidth(columns.getColumn(3), DEFAULT_WIDTH);
        return table;
    }

    private static void setWidth(final TableColumn column, final int width) {
        column.setPreferredWidth(width);
        column.setResizable(false);
        column.setMinWidth(width);
        column.setMaxWidth(width);
    }

    private boolean[]    created;

    private JSplitPane   collections;

    private JPanel       options;

    private JSplitPane   texts;

    private JPanel       fileChooser;
    private JPanel       colorChooser;
    private JPanel       desktop;

    private Runnable     updater;

    private Importer     importer;

    private Exporter     exporter;

    private JFileChooser browse;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private NimbusThemeCreator() {
        final List<String> primary = new ArrayList<String>();
        final List<String> secondary = new ArrayList<String>();
        final List<String> other = new ArrayList<String>();
        final Set<String> filters = new HashSet<String>();
        final List<String> painters = new ArrayList<String>();
        for (final Map.Entry<Object, Object> entry : UIManager.getLookAndFeelDefaults().entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                continue;
            }
            final String str = (String) entry.getKey();
            if (Character.isLowerCase(str.charAt(0))) {
                if (entry.getValue() instanceof Color) {
                    if (entry.getValue() instanceof ColorUIResource) {
                        primary.add(str);
                    } else {
                        secondary.add(str);
                    }
                } else {
                    other.add(str);
                }
            } else {
                if (str.endsWith("Painter")) {
                    painters.add(str);
                }
                int i = str.indexOf('.');
                if (i < 0) {
                    continue;
                }
                other.add(str);
                if (Character.isLetter(str.charAt(0))) {
                    int j = str.indexOf('[');
                    if (j >= 0 && j < i) {
                        i = j;
                    }
                    j = str.indexOf(':');
                    if (j >= 0 && j < i) {
                        i = j;
                    }
                    filters.add(str.substring(0, i));
                }
            }
        }
        painterKeys = painters.toArray(new String[painters.size()]);
        Arrays.sort(painterKeys);
        primaryTable = createUITable(false, 0, Type.Color, primary);
        primaryTable.getModel().addTableModelListener(this);
        secondaryTable = createUITable(false, 0, Type.Color, secondary);
        otherTable = createUITable(true, 75, null, other);
        otherTable.setAutoCreateRowSorter(true);
        final DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) otherTable.getRowSorter();
        sorter.setSortable(2, false);

        final String[] filterArray = filters.toArray(new String[filters.size() + 1]);
        filterArray[filterArray.length - 1] = "";
        Arrays.sort(filterArray);
        keyFilter = new JComboBox(filterArray);
        keyFilter.setToolTipText("Filter Key Column");
        keyFilter.setEditable(true);
        keyFilter.addActionListener(this);
        keyFilterMethod = new JComboBox(new Object[] { "Starts With", "Ends With", "Contains", "Regex" });
        keyFilterMethod.addActionListener(this);
        final Object[] types = Type.values();
        final Object[] typeArray = new Object[types.length + 1];
        System.arraycopy(types, 0, typeArray, 1, types.length);
        typeArray[0] = "";
        typeFilter = new JComboBox(typeArray);
        typeFilter.setToolTipText("Filter Type Column");
        typeFilter.addActionListener(this);

        update = new JButton("Update UI");
        update.addActionListener(this);
        autoUpdate = new JCheckBox("Auto Update", false);
        autoUpdate.addItemListener(this);

        defaultButton = new JButton("Default");
        defaultButton.setDefaultCapable(true);

    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == update) {
            updateUI();
        } else if (e.getSource() == keyFilter ||
                e.getSource() == keyFilterMethod ||
                e.getSource() == typeFilter) {
            updateFilter();
        } else if (e.getActionCommand() == "Import") {
            if (importer == null) {
                importer = new Importer(
                        (JFrame) SwingUtilities.getWindowAncestor(defaultButton));
            }
            importer.showDialog();
        } else if (e.getActionCommand() == "Export") {
            if (exporter == null) {
                exporter = new Exporter(
                        (JFrame) SwingUtilities.getWindowAncestor(defaultButton));
            }
            exporter.showDialog();
        }
    }

    @Override
    public void itemStateChanged(final ItemEvent e) {
        final boolean b = autoUpdate.isSelected();
        update.setEnabled(!b);
        if (b) {
            secondaryTable.getModel().addTableModelListener(this);
            otherTable.getModel().addTableModelListener(this);
        } else {
            secondaryTable.getModel().removeTableModelListener(this);
            otherTable.getModel().removeTableModelListener(this);
        }
    }

    @Override
    public void propertyChange(final PropertyChangeEvent e) {
        if ("width".equals(e.getPropertyName())) {
            final JComponent c = (JComponent) keyFilter.getParent();
            if (c != null) {
                c.revalidate();
                c.repaint();
            }
        }
    }

    @Override
    public void stateChanged(final ChangeEvent e) {
        final JTabbedPane tabs = (JTabbedPane) e.getSource();
        final int idx = tabs.getSelectedIndex();
        if (idx >= 0 && !created[idx]) {
            created[idx] = true;
            switch (idx) {
                case 1:
                    createCollections();
                    break;
                case 2:
                    createOptions();
                    break;
                case 3:
                    createTexts();
                    break;
                case 4:
                    createFileChooser();
                    break;
                case 5:
                    createColorChooser();
                    break;
                case 6:
                    createDesktop();
                    break;
            }

        }
    }

    @Override
    public void tableChanged(final TableModelEvent e) {
        if (e.getSource() == primaryTable.getModel()) {
            final UITableModel mdl = (UITableModel) secondaryTable.getModel();
            mdl.fireTableRowsUpdated(0, mdl.getRowCount() - 1);
        }
        if (autoUpdate.isSelected() && updater == null) {
            updater = new Runnable() {

                @Override
                public void run() {
                    updater = null;
                    updateUI();
                }
            };
            SwingUtilities.invokeLater(updater);
        }
    }

    private boolean confirm(final String msg) {
        return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
                null, msg, "Confirm",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void createCollections() {
        final JList list = new JList(painterKeys);
        list.setPrototypeCellValue(getPrototypeString(50));
        final JTree tree = new JTree();
        for (int row = 0; row < tree.getRowCount(); row++) {
            tree.expandRow(row);
        }
        final TableColumnModel columns = new DefaultTableColumnModel();
        final TableColumn nameColumn = new TableColumn(0, 300);
        nameColumn.setHeaderValue("Name");
        columns.addColumn(nameColumn);
        final TableColumn typeColumn = new TableColumn(1, 100);
        typeColumn.setHeaderValue("Type");
        columns.addColumn(typeColumn);
        final JTable table = new JTable(otherTable.getModel(), columns);
        table.setPreferredScrollableViewportSize(new Dimension(400, table.getRowHeight() * 15));
        final JSplitPane hor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                titled(new JScrollPane(tree), "JTree"),
                titled(new JScrollPane(list), "JList"));
        collections.setTopComponent(hor);
        collections.setBottomComponent(titled(new JScrollPane(table), JTable.class.getSimpleName()));
        collections.validate();
        collections.setDividerLocation(0.55);
        hor.setDividerLocation(0.35);
    }

    private void createColorChooser() {
        colorChooser.add(titled(
                new JColorChooser(), JColorChooser.class.getSimpleName()));
    }

    private JComponent createContentPane(final JTabbedPane tabs, final JButton ok, final JButton cancel) {
        final JPanel content = new JPanel(null);
        final GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(tabs).addGroup(layout.createSequentialGroup()
                        .addGap(0, 200, Short.MAX_VALUE).addComponent(ok)
                        .addGap(3).addComponent(cancel).addGap(5)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(tabs, 0, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createBaselineGroup(false, true)
                        .addComponent(ok).addComponent(cancel))
                .addGap(5));
        layout.linkSize(SwingConstants.HORIZONTAL, ok, cancel);
        return content;
    }

    private void createDesktop() {
        final JDesktopPane desktop = new JDesktopPane();
        final JPopupMenu popup = new JPopupMenu();
        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JInternalFrame frame = new JInternalFrame(
                        JInternalFrame.class.getSimpleName(), true, true, true, true);
                frame.setVisible(true);
                frame.setBounds(50, 100, 600, 500);
                desktop.add(frame);
                desktop.moveToFront(frame);
                desktop.setSelectedFrame(frame);
            }
        };
        al.actionPerformed(null);
        popup.add("New Internal Frame").addActionListener(al);
        desktop.setComponentPopupMenu(popup);
        this.desktop.add(desktop, BorderLayout.CENTER);
    }

    private void createFileChooser() {
        fileChooser.add(titled(
                new JFileChooser(), JFileChooser.class.getSimpleName()));
    }

    private JComponent createLocation(final JTextField location, final JButton browse) {
        final JPanel panel = new JPanel(null);
        final GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(location).addComponent(browse));
        final int prf = GroupLayout.PREFERRED_SIZE;
        layout.setVerticalGroup(layout.createBaselineGroup(false, true)
                .addComponent(location, prf, prf, prf)
                .addComponent(browse, prf, prf, prf));
        return panel;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void createOptions() {
        options.add(createOptionPane("Plain Message", JOptionPane.PLAIN_MESSAGE));
        options.add(createOptionPane("Error Message", JOptionPane.ERROR_MESSAGE));
        options.add(createOptionPane("Information Message", JOptionPane.INFORMATION_MESSAGE));
        options.add(createOptionPane("Warning Message", JOptionPane.WARNING_MESSAGE));
        options.add(createOptionPane("Want to do something?", JOptionPane.QUESTION_MESSAGE));
        final JComboBox choiceCombo = new JComboBox(Type.values());
        options.add(titled(new JOptionPane(choiceCombo,
                JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION), "Question Message"));

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JComponent createPreview() {
        final JLabel label1 = new JLabel("Hover Here for Tooltip");
        label1.setToolTipText("Tooltip");
        final JLabel label2 = disabled(new JLabel("Disabled"));
        final JButton button1 = new JButton("Button");
        final JButton button2 = disabled(new JButton("Disabled"));
        final JToggleButton toggle1 = new JToggleButton("Toggle", true);
        final JToggleButton toggle2 = new JToggleButton("Toggle", false);
        final JToggleButton toggle3 = disabled(new JToggleButton("Disabled", true));
        final JToggleButton toggle4 = disabled(new JToggleButton("Disabled", false));
        final JRadioButton radio1 = new JRadioButton("Radio", true);
        final JRadioButton radio2 = new JRadioButton("Radio", false);
        final JRadioButton radio3 = disabled(new JRadioButton("Disabled", true));
        final JRadioButton radio4 = disabled(new JRadioButton("Disabled", false));
        final JCheckBox check1 = new JCheckBox("Check", true);
        final JCheckBox check2 = new JCheckBox("Check", false);
        final JCheckBox check3 = disabled(new JCheckBox("Disabled", true));
        final JCheckBox check4 = disabled(new JCheckBox("Disabled", false));
        final JPopupMenu popup = new JPopupMenu();
        final JMenu menu = new JMenu("Menu");
        menu.add("Item");
        popup.add(menu);
        popup.add(new JMenuItem("Item"));
        final JMenuItem item1 = new JMenuItem("Accelerator");
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        popup.add(item1);
        popup.add(disabled(new JMenuItem("Disabled")));
        popup.addSeparator();
        popup.add(new JRadioButtonMenuItem("Radio", true));
        popup.add(new JRadioButtonMenuItem("Radio", false));
        popup.add(disabled(new JRadioButtonMenuItem("Disabled", false)));
        popup.addSeparator();
        popup.add(new JCheckBoxMenuItem("Check", true));
        popup.add(new JCheckBoxMenuItem("Check", false));
        popup.add(disabled(new JCheckBoxMenuItem("Disabled", false)));
        final JTextField text1 = new JTextField("Click Here for Popup");
        text1.setComponentPopupMenu(popup);
        final JTextField text2 = disabled(new JTextField("Disabled"));
        final JSlider slider1 = new JSlider();
        final JSlider slider2 = disabled(new JSlider());
        final JSlider slider3 = tickedSlider(false);
        final JSlider slider4 = disabled(tickedSlider(false));
        final JSlider slider5 = tickedSlider(true);
        final JSlider slider6 = disabled(tickedSlider(true));
        final JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(100, 0, Short.MAX_VALUE, 100));
        final JSpinner spinner2 = disabled(new JSpinner(new SpinnerNumberModel(100, 0, Short.MAX_VALUE, 100)));
        final JSpinner spinner3 = new JSpinner(new SpinnerDateModel());
        final JSpinner spinner4 = disabled(new JSpinner(new SpinnerDateModel()));
        final Type[] values = Type.values();
        final JSpinner spinner5 = new JSpinner(new SpinnerListModel(values));
        final JSpinner spinner6 = disabled(new JSpinner(new SpinnerListModel(values)));
        final JComboBox combo1 = new JComboBox(values);
        final JComboBox combo2 = disabled(new JComboBox(values));
        final JComboBox combo3 = new JComboBox(values);
        combo3.setEditable(true);
        final JComboBox combo4 = disabled(new JComboBox(values));
        combo4.setEditable(true);
        final JProgressBar prog1 = progress(0, false);
        final JProgressBar prog2 = progress(50, false);
        final JProgressBar prog3 = progress(100, false);
        final JProgressBar progA = progress(0, true);
        final JProgressBar progB = progress(50, true);
        final JProgressBar progC = progress(100, true);
        final JProgressBar indeterminate = new JProgressBar();
        indeterminate.setIndeterminate(true);
        final JCheckBox hide = new JCheckBox("Hide Indeterminate Progress Bar:", false);
        hide.setHorizontalAlignment(SwingConstants.RIGHT);
        hide.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(final ItemEvent evt) {
                indeterminate.setVisible(evt.getStateChange() != ItemEvent.SELECTED);
            }
        });
        final JPanel other = new JPanel(null);
        final GroupLayout layout = new GroupLayout(other);
        other.setLayout(layout);
        final int prf = GroupLayout.PREFERRED_SIZE;

        final JPanel toggles = createPanel(JToggleButton.class, 2, 0,
                toggle1, toggle2, toggle3, toggle4);
        final JPanel buttons = createPanel(JButton.class, 1, 0,
                defaultButton, button1, button2);
        final JPanel combos = createPanel(JComboBox.class, 0, 2,
                combo1, combo2, combo3, combo4);
        final JPanel spinners = createPanel(JSpinner.class, 0, 2,
                spinner1, spinner2, spinner3, spinner4, spinner5, spinner6);
        final JPanel checks = createPanel(JCheckBox.class, 2, 0,
                check1, check2, check3, check4);
        final JPanel radios = createPanel(JRadioButton.class, 2, 0,
                radio1, radio2, radio3, radio4);
        final JPanel progs = createPanel(JProgressBar.class, 0, 2,
                prog1, progA, prog2, progB, prog3, progC, hide, indeterminate);
        final JPanel texts = createPanel(JTextField.class, 0, 1, text1, text2);
        final JPanel labels = createPanel(JLabel.class, 1, 0, label1, label2);
        final JPanel sliders = createPanel(JSlider.class, 0, 2,
                slider1, slider2, slider3, slider4, slider5, slider6);
        layout.linkSize(SwingConstants.HORIZONTAL, combos, spinners);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(buttons, prf, prf, prf)
                                .addComponent(toggles, prf, prf, prf)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup()
                                                .addComponent(radios)
                                                .addComponent(checks))
                                        .addGap(0, 0, 20)))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(texts)
                                .addComponent(combos, prf, prf, prf)
                                .addComponent(spinners, prf, prf, prf)))
                .addComponent(labels)
                .addComponent(sliders)
                .addComponent(progs));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttons, prf, prf, prf)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(toggles, prf, prf, prf)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(radios, prf, prf, prf)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(checks, prf, prf, prf))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(texts, prf, prf, prf)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(combos, prf, prf, prf)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(spinners, prf, prf, prf)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labels, prf, prf, prf)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sliders, prf, prf, prf)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(progs, prf, prf, prf));

        this.texts = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        collections = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        options = new JPanel(new GridLayout(0, 2));
        desktop = new JPanel(new BorderLayout());
        fileChooser = new JPanel(new GridBagLayout());
        colorChooser = new JPanel(new GridBagLayout());

        final JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Controls", other);
        tabs.addTab("Collections", collections);
        tabs.addTab("Options", centered(options));
        tabs.addTab("Texts", this.texts);
        tabs.addTab("File Chooser", fileChooser);
        tabs.addTab("Color Chooser", colorChooser);
        tabs.addTab("Desktop Pane", desktop);
        created = new boolean[tabs.getTabCount()];
        created[0] = true;
        tabs.addChangeListener(this);
        return tabs;
    }

    private void createTexts() {
        final JTextArea area = new JTextArea(10, 40);
        final Exception ex = new Exception("Little something for the Text Components");
        final StringWriter writer = new StringWriter();
        final PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        pw.flush();
        pw.close();
        final String str = writer.toString();
        area.setText(str);
        area.select(0, 0);
        final JEditorPane editor = new JEditorPane();
        editor.setText(str);
        texts.setTopComponent(titled(new JScrollPane(area), JTextArea.class.getSimpleName()));
        texts.setBottomComponent(titled(new JScrollPane(editor), JEditorPane.class.getSimpleName()));
        texts.setDividerLocation(0.5);
    }

    private void error(final String msg) {
        JOptionPane.showMessageDialog(
                null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private JFileChooser getFileChooser() {
        if (browse == null) {
            browse = new JFileChooser();
        }
        return browse;
    }

    @SuppressWarnings("unchecked")
    private void updateFilter() {
        final DefaultRowSorter<TableModel, Object> sorter = (DefaultRowSorter<TableModel, Object>) otherTable.getRowSorter();
        final String key = keyFilter.getSelectedItem().toString();
        RowFilter<TableModel, Object> filter = null;
        if (!key.isEmpty()) {
            final Object method = keyFilterMethod.getSelectedItem();
            if (method == "Starts With") {
                filter = RowFilter.regexFilter('^' + Pattern.quote(key), 0);
            } else if (method == "Ends With") {
                filter = RowFilter.regexFilter(Pattern.quote(key) + '$', 0);
            } else if (method == "Contains") {
                filter = RowFilter.regexFilter(Pattern.quote(key), 0);
            } else {
                filter = RowFilter.regexFilter(key, 0);
            }
        }
        final String type = typeFilter.getSelectedItem().toString();
        if (!type.isEmpty()) {
            final RowFilter<TableModel, Object> typeFilter = RowFilter.regexFilter('^' + type + '$', 1);
            filter = filter == null ? typeFilter : RowFilter.<TableModel, Object> andFilter(Arrays.asList(filter, typeFilter));
        }
        sorter.setRowFilter(filter);
    }

    private void updateUI() {
        for (final Window window : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }
        defaultButton.getRootPane().setDefaultButton(defaultButton);
    }

    JComponent createBody() {
        final JScrollPane primary = titled(new JScrollPane(
                primaryTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), "Primary");
        final JScrollPane secondary = titled(new JScrollPane(
                secondaryTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), "Secondary");

        final JPanel colors = new JPanel(new StackedTableLayout(3, 10, true));
        colors.add(primary);
        colors.add(secondary);
        final Dimension size = new Dimension(TABLE_WIDTH, primaryTable.getRowHeight() * 20);
        otherTable.setPreferredScrollableViewportSize(size);

        final JScrollPane other = new JScrollPane(otherTable);
        other.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        final JPanel otherPanel = new JPanel(null);

        final JPanel filters = new JPanel(new FiltersLayout());
        filters.add(keyFilter);
        filters.add(keyFilterMethod);
        filters.add(typeFilter);
        otherTable.getColumnModel().getColumn(0).addPropertyChangeListener(this);

        GroupLayout layout = new GroupLayout(otherPanel);
        otherPanel.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(2)
                .addGroup(layout.createParallelGroup()
                        .addComponent(filters).addComponent(other)));
        final int prf = GroupLayout.PREFERRED_SIZE;
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(2).addComponent(other).addComponent(filters, prf, prf, prf));

        final JTabbedPane options = new JTabbedPane();
        options.addTab("UI Base", colors);
        options.addTab("UI Controls", otherPanel);
        final JComponent preview = createPreview();

        final JButton imp = new JButton("Import");
        imp.addActionListener(this);
        final JButton exp = new JButton("Export");
        exp.addActionListener(this);

        final JPanel body = new JPanel(null);
        layout = new GroupLayout(body);
        body.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(options)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(4)
                                .addComponent(imp).addComponent(exp)
                                .addGap(0, 100, Short.MAX_VALUE)
                                .addComponent(autoUpdate).addGap(5).addComponent(update)))
                .addComponent(preview));
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(options)
                        .addGroup(layout.createBaselineGroup(false, true)
                                .addComponent(imp).addComponent(exp)
                                .addComponent(update).addComponent(autoUpdate))
                        .addGap(4))
                .addComponent(preview));
        return body;
    }

}
