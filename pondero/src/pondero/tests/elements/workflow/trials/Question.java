package pondero.tests.elements.workflow.trials;

import pondero.tests.Test;
import pondero.tests.elements.workflow.Trial;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.components.ItemQuestionComponent;
import pondero.ui.testing.components.TestLikertComponent;
import pondero.util.StringUtil;

public class Question extends Trial {

    private LikertConfig likertConfig;
    private String       question;

    public Question(final String name) {
        super(name);
    }

    public LikertConfig getLikertConfig() {
        return likertConfig;
    }

    public String getQuestion() {
        return question;
    }

    public void setLikert(final boolean flag) {
        if (flag && likertConfig == null) {
            likertConfig = new LikertConfig();
        }
        if (!flag) {
            likertConfig = null;
        }
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    @Override
    protected void configureScene() {
        final Test test = getTest();
        final TestScene scene = test.getTestWindow().getScene();
        scene.setNorth(null);
        scene.setWest(null);
        if (StringUtil.isNullOrBlank(question)) {
            scene.setCenter(null);
        } else {
            final ItemQuestionComponent center = new ItemQuestionComponent();
            center.setQuestion(question);
            scene.setCenter(center);
        }
        scene.setEast(null);
        if (likertConfig != null) {
            scene.setSouth(new TestLikertComponent(test, likertConfig));
        } else {
            scene.setSouth(null);
        }
    }
}
