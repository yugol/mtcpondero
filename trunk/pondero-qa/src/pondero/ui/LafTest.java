package pondero.ui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.junit.Test;

public class LafTest {

    @Test
    public void test() {
        for (final LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName());
        }
    }

}
