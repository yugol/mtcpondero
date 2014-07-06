package pondero.ui.tests;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import pondero.UiUtil;
import pondero.engine.elements.other.Page;
import pondero.engine.test.Test;

@SuppressWarnings("serial")
public class TestWindow extends JFrame {

    private final JPanel           contentPane;
    private final TestScene        scene;
    private final TestInstructions instructions;

    public TestWindow() {
        this(null);
    }

    /**
     * Create the frame.
     */
    public TestWindow(final Test task) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(TestWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                if (task != null) {
                    task.kill();
                }
            }

        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 768));
        contentPane = new JPanel();
        contentPane.setBorder(null);
        contentPane.setFocusable(false);
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        scene = new TestScene(task);
        scene.setBorder(null);
        contentPane.add(scene);

        instructions = new TestInstructions(task);
        instructions.setBorder(null);
        contentPane.add(instructions);

        setTitle(task.getCodeName());

        UiUtil.enableFullScreenMode(this);
        UiUtil.showFractionedCentered(this);
    }

    public TestScene getScene() {
        return scene;
    }

    public void hideTestWindow() {
        setVisible(false);
    }

    public void invalidateScene() {
        scene.paintImmediately(0, 0, scene.getWidth(), scene.getHeight());
    }

    public void showInstructions(final Page instructPage, final boolean first, final boolean last) {
        scene.setVisible(false);
        instructions.setVisible(true);
        instructions.showInstructions(instructPage, first, last);
    }

    public void showScene() {
        instructions.setVisible(false);
        scene.setVisible(true);
        scene.focalize();
    }

    public void showTestWindow() {
        setVisible(true);
        UiUtil.showCentered(this);
    }

}
