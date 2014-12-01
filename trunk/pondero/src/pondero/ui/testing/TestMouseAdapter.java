package pondero.ui.testing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.task.Testable;
import pondero.task.responses.PrevNextResponse;

public class TestMouseAdapter extends MouseAdapter {

    private final Testable test;

    public TestMouseAdapter(final Testable test) {
        this.test = test;
    }

    @Override
    public void mouseClicked(final MouseEvent evt) {
        final PrevNextResponse input = new PrevNextResponse();
        input.setNext(true);
        try {
            test.doStep(input);
        } catch (final Exception e) {
        }
    }

}
