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
import pondero.ui.testing.components.DrawableComponent;
import pondero.ui.testing.components.LikertComponent;
import pondero.ui.testing.components.QuestionItemComponent;

@SuppressWarnings("serial")
public abstract class TestSceneComponent extends JComponent implements Senzor {

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
        registerComponent(DrawableComponent.class);
        registerComponent(LikertComponent.class);
        registerComponent(QuestionItemComponent.class);
    }

    private final Test                                   test;
    private TrialController                              controller;
    private List<VisualStimulus>                         visualStimuli         = new ArrayList<VisualStimulus>();

    protected TaskKeyAdapter                             senzorKeyAdapter      = new TaskKeyAdapter(this);
    protected TaskMouseAdapter                           senzorMouseAdapter    = new TaskMouseAdapter(this);

    public TestSceneComponent() {
        setFocusable(true);
        test = null;
    }

    @Deprecated
    public TestSceneComponent(final Test test) {
        this.test = test;
        setFocusable(true);
    }

    @Override
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
