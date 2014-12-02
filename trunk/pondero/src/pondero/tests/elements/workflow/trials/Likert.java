package pondero.tests.elements.workflow.trials;

import java.util.List;
import pondero.task.responses.LikertResponse;
import pondero.task.responses.Response;
import pondero.tests.elements.workflow.Trial;
import pondero.tests.interfaces.HasLikertConfig;
import pondero.ui.testing.components.LikertComponent;
import pondero.util.StringUtil;

public class Likert extends Trial implements HasLikertConfig {

    public static final String TYPENAME = "likert";

    private final LikertConfig config;

    public Likert(final String name) {
        this(name, new LikertConfig());
    }

    public Likert(final String name, final LikertConfig config) {
        super(name);
        this.config = config;
        getLayout().setSouth(LikertComponent.class.getName());
    }

    public String getAnchor(final int key) {
        final String value = config.getAnchor(key);
        if (StringUtil.isNullOrBlank(value)) { return ""; }
        return value;
    }

    @Override
    public LikertConfig getLikertConfig() {
        return config;
    }

    public int getNumPoints() {
        return config.getNumPoints();
    }

    public String getQuiz() {
        return config.getInfo();
    }

    public int getStartIndex() {
        return config.getStartIndex();
    }

    @Override
    public boolean isCorrectResponse(final List<Response> input) {
        final Response participantInput = input.get(0);
        return participantInput instanceof LikertResponse;
    }

    public boolean isUseMouse() {
        return config.isMouse();
    }

    public void setAnchor(final int key, final String value) {
        config.setAnchor(key, value);
    }

    public void setInfo(final String quiz) {
        config.setInfo(quiz);
    }

    public void setNumPoints(final int numpoints) {
        config.setNumPoints(numpoints);
    }

    public void setStartIndex(final int startIndex) {
        config.setStartIndex(startIndex);
    }

    public void setUseMouse(final boolean mouse) {
        config.setMouse(mouse);
    }

    @Override
    public void setValidResponses(final String... validresponse) {
        throw new RuntimeException("validresponse is not supported for " + TYPENAME);
    }

}
