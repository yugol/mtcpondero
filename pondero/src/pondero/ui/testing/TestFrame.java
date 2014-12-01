package pondero.ui.testing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import pondero.task.Testable;
import pondero.task.controllers.PageController;
import pondero.tests.elements.other.Page;
import pondero.util.SwingUtil;

@SuppressWarnings("serial")
public class TestFrame extends JFrame {

    public static final String WINDOW_NAME = "testWindow";

    private final JPanel       contentPane;
    private final TestScene    scene;
    private final TestCurtains curtains;

    /**
     * Create the frame.
     */
    public TestFrame(final Testable test) {
        setName(WINDOW_NAME);
        setIconImage(Toolkit.getDefaultToolkit().getImage(TestFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(final WindowEvent e) {
                if (test != null) {
                    test.kill();
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

        scene = new TestScene(test);
        scene.setBorder(null);
        contentPane.add(scene);

        curtains = new TestCurtains(test);
        curtains.setBorder(null);
        contentPane.add(curtains);

        setTitle(test.getCodeName());

        SwingUtil.enableFullScreenMode(this);
        SwingUtil.showFractionedCentered(this);
    }

    public TestScene getScene() {
        return scene;
    }

    public void hideTestWindow() {
        setVisible(false);
    }

    public synchronized void invalidateScene() {
        scene.paintImmediately(0, 0, scene.getWidth(), scene.getHeight());
    }

    @Deprecated
    public synchronized void showCurtains(final Page instructPage, final boolean first, final boolean last) {
        scene.setVisible(false);
        curtains.setVisible(true);
        curtains.showInstructions(instructPage, first, last);
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
