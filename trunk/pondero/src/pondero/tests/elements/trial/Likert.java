package pondero.tests.elements.trial;

import java.util.HashMap;
import java.util.Map;
import pondero.tests.elements.interfaces.HasPosition;
import pondero.tests.staples.Coordinate;
import pondero.tests.staples.Coordinates;
import pondero.tests.test.Test;
import pondero.ui.testing.TestScene;
import pondero.ui.testing.components.TestDrawableComponent;
import pondero.ui.testing.components.TestLikertComponent;
import pondero.util.StringUtil;

public class Likert extends Trial implements HasPosition {

    public static final String         TYPENAME    = "likert";

    private final Map<Integer, String> anchors     = new HashMap<Integer, String>();
    private int                        anchorwidth = 0;
    private boolean                    mouse       = true;
    private int                        numpoints   = 5;
    private Coordinates                position    = null;
    private String                     quiz;

    public Likert(final String name) {
        super(name);
        numpoints(5);
    }

    public String _getAnchor(final int key) {
        final String value = anchors.get(key);
        if (StringUtil.isNullOrBlank(value)) { return ""; }
        return value;
    }

    @Override
    public Coordinates getPosition() {
        return position == null ? test.getDefaults().getPosition() : position;
    }

    public String _getQuiz() {
        return quiz;
    }

    public void _setAnchor(final int key, final String value) {
        anchors.put(key, value);
    }

    @Override
    public void setPosition(final Coordinates position) {
        this.position = position;
    }

    public void _setQuiz(final String quiz) {
        this.quiz = quiz;
    }

    @Override
    public Coordinate getHPosition() {
        return getPosition().getX();
    }

    @Override
    public Coordinate getVPosition() {
        return getPosition().getY();
    }

    public void anchors() {
    }

    public int anchorwidth() {
        return anchorwidth;
    }

    public void anchorwidth(final int anchorwidth) {
        this.anchorwidth = anchorwidth;
    }

    public boolean mouse() {
        return mouse;
    }

    public void mouse(final boolean mouse) {
        this.mouse = mouse;
    }

    public int numpoints() {
        return numpoints;
    }

    public void numpoints(final int numpoints) {
        this.numpoints = numpoints;
        final String[] validResponses = new String[numpoints];
        for (int i = 0; i < numpoints;) {
            validResponses[i] = String.valueOf(++i);
        }
        super.setValidResponses(validResponses);
        super.correctresponse(validResponses);
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

    @Override
    public void setValidResponses(final String... validresponse) {
        throw new RuntimeException("validresponse is not supported for " + TYPENAME);
    }

    @Override
    protected void configureScene() {
        final Test test = _getTest();
        final TestScene scene = test.getTestWindow().getScene();
        scene.setNorth(null);
        scene.setEast(null);
        scene.setWest(null);
        if (!(scene.getCenter() instanceof TestDrawableComponent)) {
            scene.setCenter(new TestDrawableComponent(_getTest()));
        }
        TestLikertComponent lk = null;
        final Object south = scene.getSouth();
        if (!(south instanceof TestLikertComponent)) {
            lk = new TestLikertComponent(test);
            scene.setSouth(lk);
        } else {
            lk = (TestLikertComponent) south;
        }
        lk.setQuiz(_getQuiz());
        lk.setPointCount(numpoints());
        for (int i = 0; i < numpoints(); ++i) {
            lk.setAnchor(i, _getAnchor(i));
        }
    }

}
