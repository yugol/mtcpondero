package pondero.tests.elements.workflow.trials;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import pondero.Constants;
import pondero.util.StringUtil;

public class LikertConfig {

    private Color                      answersBgColor = Constants.DEFAULT_ANSWERS_BG_COLOR;
    private Color                      answersFgColor = Constants.DEFAULT_ANSWERS_FG_COLOR;
    private Color                      infoBgColor    = Constants.DEFAULT_INFO_BG_COLOR;
    private Color                      infoFgColor    = Constants.DEFAULT_INFO_FG_COLOR;
    private String                     info           = "";
    private boolean                    mouse          = true;
    private final Map<Integer, String> anchors        = new HashMap<Integer, String>();
    private int                        numPoints      = 5;
    private int                        startIndex     = 1;

    public String getAnchor(final int key) {
        final String value = anchors.get(key);
        if (StringUtil.isNullOrBlank(value)) { return ""; }
        return value;
    }

    public Color getAnswersBgColor() {
        return answersBgColor;
    }

    public Color getAnswersFgColor() {
        return answersFgColor;
    }

    public String getInfo() {
        return info;
    }

    public Color getInfoBgColor() {
        return infoBgColor;
    }

    public Color getInfoFgColor() {
        return infoFgColor;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public String[] getValidResponses() {
        final String[] responses = new String[numPoints];
        for (int i = 0; i < numPoints; ++i) {
            responses[i] = String.valueOf(startIndex + i);
        }
        return responses;
    }

    public boolean isMouse() {
        return mouse;
    }

    public void setAnchor(final int key, final String value) {
        anchors.put(key, value);
    }

    public void setAnswersBgColor(final Color answersBgColor) {
        this.answersBgColor = answersBgColor;
    }

    public void setAnswersFgColor(final Color answersFgColor) {
        this.answersFgColor = answersFgColor;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public void setInfoBgColor(final Color infoBgColor) {
        this.infoBgColor = infoBgColor;
    }

    public void setInfoFgColor(final Color infoFgColor) {
        this.infoFgColor = infoFgColor;
    }

    public void setMouse(final boolean mouse) {
        this.mouse = mouse;
    }

    public void setNumPoints(final int numPoints) {
        this.numPoints = numPoints;
    }

    public void setStartIndex(final int startIndex) {
        this.startIndex = startIndex;
    }

}
