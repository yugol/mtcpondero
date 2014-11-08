package pondero.tests.elements.trial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pondero.tests.elements.interfaces.HasPosition;
import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;
import pondero.tests.test.Test;
import pondero.tests.test.responses.LikertResponse;
import pondero.tests.test.responses.Response;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.components.TestDrawableComponent;
import pondero.ui.testing.components.TestLikertComponent;
import pondero.util.StringUtil;

public class Likert extends Trial implements HasPosition {

    public static final String         TYPENAME    = "likert";

    private final Map<Integer, String> anchors     = new HashMap<Integer, String>();
    private int                        anchorwidth = 0;
    private boolean                    mouse       = true;
    private int                        startIndex  = 1;
    private int                        numPoints   = 5;
    private Coordinates                position    = null;
    private String                     quiz;

    public Likert(final String name) {
        super(name);
    }

    public String getAnchor(final int key) {
        final String value = anchors.get(key);
        if (StringUtil.isNullOrBlank(value)) { return ""; }
        return value;
    }

    public int getAnchorWidth() {
        return anchorwidth;
    }

    @Override
    public Coordinate getHPosition() {
        return getPosition().getX();
    }

    public int getNumPoints() {
        return numPoints;
    }

    @Override
    public Coordinates getPosition() {
        return position == null ? test.getDefaults().getPosition() : position;
    }

    public String getQuiz() {
        return quiz;
    }

    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public Coordinate getVPosition() {
        return getPosition().getY();
    }

    public boolean isUseMouse() {
        return mouse;
    }

    public void setAnchor(final int key, final String value) {
        anchors.put(key, value);
    }

    public void setAnchorWidth(final int anchorwidth) {
        this.anchorwidth = anchorwidth;
    }

    public void setNumPoints(final int numpoints) {
        numPoints = numpoints;
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

    public void setQuiz(final String quiz) {
        this.quiz = quiz;
    }

    public void setStartIndex(final int startIndex) {
        this.startIndex = startIndex;
    }

    public void setUseMouse(final boolean mouse) {
        this.mouse = mouse;
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
            lk = new TestLikertComponent(test, getNumPoints(), getStartIndex());
            scene.setSouth(lk);
        } else {
            lk = (TestLikertComponent) south;
        }
        lk.setQuiz(getQuiz());
        for (int i = 0; i < getNumPoints(); ++i) {
            lk.setAnchor(i, getAnchor(i));
        }
    }

    @Override
    protected boolean isCorrectResponse(final List<Response> input) {
        final Response participantInput = input.get(0);
        return participantInput instanceof LikertResponse;
    }

}
