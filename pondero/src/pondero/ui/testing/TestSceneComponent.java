package pondero.ui.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import pondero.Logger;
import pondero.task.controllers.TrialController;
import pondero.task.stimuli.VisualStimulus;
import pondero.tests.Test;
import pondero.ui.testing.components.ItemQuestionComponent;
import pondero.ui.testing.components.TestDrawableComponent;
import pondero.ui.testing.components.TestLikertComponent;

@SuppressWarnings("serial")
public abstract class TestSceneComponent extends JComponent {

    public static final TestSceneComponent getInstance(final String name) {
        return REGISTERED_COMPONENTS.get(name);
    }

    public static final void registerComponent(final Class<? extends TestSceneComponent> componentClass) {
        try {
            final TestSceneComponent component = componentClass.newInstance();
            REGISTERED_COMPONENTS.put(componentClass.getName(), component);
        } catch (InstantiationException | IllegalAccessException e) {
            Logger.error(e);
        }
    }

    private static final Map<String, TestSceneComponent> REGISTERED_COMPONENTS = new HashMap<>();

    static {
        registerComponent(TestDrawableComponent.class);
        registerComponent(TestLikertComponent.class);
        registerComponent(ItemQuestionComponent.class);
    }

    private final Test                                   test;
    private TrialController                              controller;
    private List<VisualStimulus>                         visualStimuli         = new ArrayList<VisualStimulus>();

    @Deprecated
    public TestSceneComponent(final Test test) {
        this.test = test;
        setFocusable(true);
    }

    public TrialController getController() {
        return controller;
    }

    @Deprecated
    public Test getTest() {
        return test;
    }

    public List<VisualStimulus> getVisualStimuli() {
        return visualStimuli;
    }

    public boolean hasController() {
        return controller != null;
    }

    @Deprecated
    public boolean hasTest() {
        return test != null;
    }

    public void reset() {
    }

    public void setController(final TrialController controller) {
        this.controller = controller;
    }

    public void setVisualStimuli(final List<VisualStimulus> visualStimuli) {
        if (visualStimuli == null) {
            this.visualStimuli.clear();
        } else {
            this.visualStimuli = visualStimuli;
        }
    }

}
