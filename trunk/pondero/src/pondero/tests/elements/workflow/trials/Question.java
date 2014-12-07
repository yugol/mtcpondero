package pondero.tests.elements.workflow.trials;

import pondero.tests.elements.workflow.Trial;
import pondero.tests.interfaces.HasLikertConfig;
import pondero.ui.testing.components.LikertComponent;
import pondero.ui.testing.components.QuestionItemComponent;

public class Question extends Trial implements HasLikertConfig {

    private String       question;
    private LikertConfig likertConfig;

    public Question(final String name) {
        super(name);
        getLayout().setCenter(QuestionItemComponent.class.getName());
        setLikert(true);
    }

    @Override
    public LikertConfig getLikertConfig() {
        return likertConfig;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    private void setLikert(final boolean flag) {
        if (flag && likertConfig == null) {
            likertConfig = new LikertConfig();
            getLayout().setSouth(LikertComponent.class.getName());
        }
        if (!flag) {
            likertConfig = null;
            getLayout().setSouth(null);
        }
    }

}
