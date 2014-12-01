package pondero.tests.elements.trial;

import java.util.List;
import pondero.task.responses.LikertResponse;
import pondero.task.responses.Response;
import pondero.tests.Test;
import pondero.tests.interfaces.HasPosition;
import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.components.TestDrawableComponent;
import pondero.ui.testing.components.TestLikertComponent;
import pondero.util.StringUtil;

public class Likert extends Trial implements HasPosition {

    public static final String TYPENAME = "likert";

    private final LikertConfig config;
    private Coordinates        position = null;

    public Likert(final String name) {
        this(name, new LikertConfig());
    }

    public Likert(final String name, final LikertConfig config) {
        super(name);
        this.config = config;
    }

    public String getAnchor(final int key) {
        final String value = config.getAnchor(key);
        if (StringUtil.isNullOrBlank(value)) { return ""; }
        return value;
    }

    @Override
    public Coordinate getHPosition() {
        return getPosition().getX();
    }

    public int getNumPoints() {
        return config.getNumPoints();
    }

    @Override
    public Coordinates getPosition() {
        return position == null ? test.getDefaults().getPosition() : position;
    }

    public String getQuiz() {
        return config.getInfo();
    }

    public int getStartIndex() {
        return config.getStartIndex();
    }

    @Override
    public Coordinate getVPosition() {
        return getPosition().getY();
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

    @Override
    public void setPosition(final Coordinates position) {
        this.position = position;
    }

    @Override
    public void setPosition(final double x, final double y) {
        position = new Coordinates(x, y);
    }

    @Override
    public void setPosition(final String xExpr, final String yExpr) {
        position = new Coordinates(xExpr, yExpr);
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

    @Override
    protected void configureScene() {
        final Test test = getTest();
        final TestScene scene = test.getTestWindow().getScene();
        scene.setNorth(null);
        scene.setEast(null);
        scene.setWest(null);
        if (!(scene.getCenter() instanceof TestDrawableComponent)) {
            scene.setCenter(new TestDrawableComponent(getTest()));
        }
        TestLikertComponent lk = null;
        final Object south = scene.getSouth();
        if (!(south instanceof TestLikertComponent)) {
            lk = new TestLikertComponent(test, config);
            scene.setSouth(lk);
        } else {
            lk = (TestLikertComponent) south;
        }
        lk.setInfo(getQuiz());
        for (int i = 0; i < getNumPoints(); ++i) {
            lk.setAnchor(i, getAnchor(i));
        }
    }

}
