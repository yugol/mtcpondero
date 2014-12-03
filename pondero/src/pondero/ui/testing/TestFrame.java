package pondero.ui.testing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import pondero.task.Task;
import pondero.task.controllers.PageController;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

    public static final String WINDOW_NAME = "testWindow";

    private final JPanel       contentPane;
    private final TestScene    scene;
    private final TestCurtains curtains;

    public TestFrame(final Task task) {
        setName(WINDOW_NAME);
        setIconImage(Toolkit.getDefaultToolkit().getImage(TestFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                task.kill();
            }

        });

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(1024, 768));
        contentPane = new JPanel();
        contentPane.setBorder(null);
        contentPane.setFocusable(false);
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        scene = new TestScene();
        scene.setBorder(null);
        contentPane.add(scene);

        curtains = new TestCurtains(task);
        curtains.setBorder(null);
        contentPane.add(curtains);

        setTitle(task.getTest().getDescriptor().getCodeName());

        SwingUtil.enableFullScreenMode(this);
        SwingUtil.showFractionedCentered(this);
    }

    public TestScene getScene() {
        return scene;
    }

    public synchronized void invalidateScene() {
        scene.paintImmediately(0, 0, scene.getWidth(), scene.getHeight());
    }

    public synchronized void showCurtains(final PageController pageController) {
        scene.setVisible(false);
        curtains.setVisible(true);
        curtains.showInstructions(pageController);
    }

    public synchronized void showScene() {
        curtains.setVisible(false);
        scene.setVisible(true);
        scene.focalize();
    }

    public void showTestWindow() {
        setVisible(true);
        SwingUtil.showCentered(this);
    }

}
