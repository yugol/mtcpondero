package pondero.tests.elements.workflow.trials;

import java.util.List;
import pondero.task.responses.LikertResponse;
import pondero.task.responses.Response;
import pondero.tests.elements.workflow.Trial;
import pondero.tests.interfaces.HasLikertConfig;
import pondero.ui.testing.components.LikertComponent;

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

    @Override
    public LikertConfig getLikertConfig() {
        return config;
    }

    @Deprecated
    public int getNumPoints() {
        return config.getNumPoints();
    }

    @Deprecated
    public String getQuiz() {
        return config.getInfo();
    }

    @Deprecated
    public int getStartIndex() {
        return config.getStartIndex();
    }

    @Override
    public boolean isCorrectResponse(final List<Response> input) {
        final Response participantInput = input.get(0);
        return participantInput instanceof LikertResponse;
    }

    @Deprecated
    public boolean isUseMouse() {
        return config.isMouse();
    }

    @Deprecated
    public void setAnchor(final int key, final String value) {
        config.setAnchor(key, value);
    }

    @Deprecated
    public void setInfo(final String quiz) {
        config.setInfo(quiz);
    }

    @Deprecated
    public void setNumPoints(final int numpoints) {
        config.setNumPoints(numpoints);
    }

    @Deprecated
    public void setStartIndex(final int startIndex) {
        config.setStartIndex(startIndex);
    }

    @Deprecated
    public void setUseMouse(final boolean mouse) {
        config.setMouse(mouse);
    }

    @Override
    public void setValidResponses(final String... validresponse) {
        throw new RuntimeException("validresponse is not supported for " + TYPENAME);
    }

}
