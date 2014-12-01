package pondero.ui.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import pondero.Constants;
import pondero.Logger;
import pondero.task.controllers.TrialController;
import pondero.task.stimuli.VisualStimulus;
import pondero.tests.Test;
import pondero.ui.testing.components.ItemQuestionComponent;
import pondero.ui.testing.components.TestDrawableComponent;
import pondero.ui.testing.components.TestLikertComponent;

@SuppressWarnings("serial")
public abstract class TestSceneComponent extends JComponent {

    public static final TestSceneComponent newInstance(final String name, final TrialController controller) {
        final Class<? extends TestSceneComponent> componentClass = REGISTERED_COMPONENTS.get(name);
        try {
            final TestSceneComponent component = componentClass.newInstance();
            component.setController(controller);
            return component;
        } catch (InstantiationException | IllegalAccessException e) {
            Logger.error(e);
            return null;
        }
    }

    public static final void registerComponent(final String name, final Class<? extends TestSceneComponent> componentClass) {
        REGISTERED_COMPONENTS.put(name, componentClass);
    }

    private static final Map<String, Class<? extends TestSceneComponent>> REGISTERED_COMPONENTS = new HashMap<>();

    static {
        registerComponent(Constants.DRAWABLE_LAYOUT_COMPONENT, TestDrawableComponent.class);
        registerComponent(Constants.LIKERT_LAYOUT_COMPONENT, TestLikertComponent.class);
        registerComponent(Constants.QUESTION_LAYOUT_COMPONENT, ItemQuestionComponent.class);
    }

    private final Test                                                    test;
    private TrialController                                               controller;
    private List<VisualStimulus>                                          visualStimuli         = new ArrayList<VisualStimulus>();

    public TestSceneComponent(final Test test) {
        this.test = test;
        setFocusable(true);
    }

    public Test getTest() {
        return test;
    }

    public List<VisualStimulus> getVisualStimuli() {
        return visualStimuli;
    }

    public boolean hasController() {
        return controller != null;
    }

    public boolean hasTest() {
        return test != null;
    }

    public abstract void reset();

    public void setVisualStimuli(final List<VisualStimulus> visualStimuli) {
        if (visualStimuli == null) {
            this.visualStimuli.clear();
        } else {
            this.visualStimuli = visualStimuli;
        }
    }

    private void setController(final TrialController controller) {
        this.controller = controller;
    }

    protected TrialController getController() {
        return controller;
    }

}
