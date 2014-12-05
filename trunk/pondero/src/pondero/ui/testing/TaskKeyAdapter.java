package pondero.ui.testing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import pondero.Logger;
import pondero.task.controllers.PageController;
import pondero.task.controllers.TrialController;
import pondero.task.responses.KeyPressResponse;
import pondero.task.responses.PrevNextResponse;
import pondero.tests.interfaces.IsController;

public class TaskKeyAdapter extends KeyAdapter {

    private final Senzor senzor;

    public TaskKeyAdapter(final Senzor senzor) {
        this.senzor = senzor;
    }

    @Override
    public void keyPressed(final KeyEvent evt) {
        try {
            final IsController controller = senzor.getController();
            if (controller instanceof PageController) {
                final PageController pageController = (PageController) controller;
                final PrevNextResponse input = new PrevNextResponse();
                input.setNext(evt.getKeyChar() == pageController.getInstructNextKey());
                input.setPrev(evt.getKeyChar() == pageController.getInstructPrevKey());
                pageController.doStep(input);
            } else if (controller instanceof TrialController) {
                controller.doStep(new KeyPressResponse(evt));
            }
        } catch (final Exception e) {
            Logger.error(e);
        }
    }

}
