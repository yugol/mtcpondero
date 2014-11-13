package pondero.tests.elements.trial;

import java.awt.Color;
import pondero.tests.staples.ElementUtil;
import pondero.tests.test.Test;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.components.ItemQuestionComponent;
import pondero.ui.testing.components.TestLikertComponent;
import pondero.util.StringUtil;

public class Question extends Trial {

    public static final Color DEFAULT_ANSWERS_BG_COLOR  = ElementUtil.createColor(0xe6, 0xe6, 0xdc);
    public static final Color DEFAULT_ANSWERS_FG_COLOR  = ElementUtil.createColor(0x00, 0x00, 0x00);
    public static final Color DEFAULT_INFO_BG_COLOR     = ElementUtil.createColor(0x00, 0x62, 0x8b);
    public static final Color DEFAULT_INFO_FG_COLOR     = ElementUtil.createColor(0xFF, 0xFF, 0xFF);
    public static final Color DEFAULT_QUESTION_BG_COLOR = ElementUtil.createColor(0x81, 0xa5, 0x94);
    public static final Color DEFAULT_QUESTION_FG_COLOR = ElementUtil.createColor(0xFF, 0xFF, 0xFF);

    private LikertConfig      likertConfig;
    private String            question;

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
            likertConfig.setInfoBgColor(DEFAULT_INFO_BG_COLOR);
            likertConfig.setInfoFgColor(DEFAULT_INFO_FG_COLOR);
            likertConfig.setAnswersBgColor(DEFAULT_ANSWERS_BG_COLOR);
            likertConfig.setAnswersFgColor(DEFAULT_ANSWERS_FG_COLOR);
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
            final ItemQuestionComponent center = new ItemQuestionComponent(test);
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
