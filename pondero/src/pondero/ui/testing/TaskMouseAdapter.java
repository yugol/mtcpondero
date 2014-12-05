package pondero.ui.testing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pondero.Logger;
import pondero.task.controllers.PageController;
import pondero.task.controllers.TrialController;
import pondero.task.responses.MouseClickResponse;
import pondero.task.responses.PrevNextResponse;
import pondero.tests.interfaces.IsController;

public class TaskMouseAdapter extends MouseAdapter {

    private final Senzor  senzor;
    private final Boolean next;

    public TaskMouseAdapter(final Senzor senzor) {
        this(senzor, null);
    }

    public TaskMouseAdapter(final Senzor senzor, final Boolean next) {
        this.senzor = senzor;
        this.next = next;
    }

    @Override
    public void mouseClicked(final MouseEvent evt) {
        try {
            final IsController controller = senzor.getController();
            if (controller instanceof PageController) {
                final PageController pageController = (PageController) controller;
                final PrevNextResponse input = new PrevNextResponse();
                input.setNext(next);
                pageController.doStep(input);
            } else if (controller instanceof TrialController) {
                controller.doStep(new MouseClickResponse(evt));
            }
        } catch (final Exception e) {
            Logger.error(e);
        }
    }

}
