package pondero.ui.testing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.task.Testable;
import pondero.task.responses.PrevNextResponse;

@Deprecated
public class TestMouseAdapter extends MouseAdapter {

    private final Testable test;
    private final boolean  next;

    public TestMouseAdapter(final Testable test, final boolean next) {
        this.test = test;
        this.next = next;
    }

    @Override
    public void mouseClicked(final MouseEvent evt) {
        final PrevNextResponse input = new PrevNextResponse();
        input.setNext(next);
        try {
            test.doStep(input);
        } catch (final Exception e) {
        }
    }

}
