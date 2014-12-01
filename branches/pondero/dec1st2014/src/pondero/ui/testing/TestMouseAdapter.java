package pondero.ui.testing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.tests.test.Test;
import pondero.tests.test.responses.PrevNextResponse;

public class TestMouseAdapter extends MouseAdapter {

    private final Test test;

    public TestMouseAdapter(final Test test) {
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
