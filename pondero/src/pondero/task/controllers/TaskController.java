package pondero.task.controllers;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import pondero.task.Task;
import pondero.tests.elements.Element;
import pondero.tests.elements.workflow.Block;
import pondero.tests.interfaces.HasFeedback;
import pondero.tests.interfaces.HasFeedback.FeedbackStimulus;
import pondero.tests.interfaces.HasFont;
import pondero.tests.interfaces.HasPosition;
import pondero.tests.interfaces.HasScreencolor;
import pondero.tests.interfaces.HasTextColor;
import pondero.tests.interfaces.HasTextColors;
import pondero.tests.interfaces.IsController;
import pondero.tests.staples.Coordinates;

public abstract class TaskController implements IsController {

    private final Task                 task;
    private final Element              element;

    private Font                       instructFont;
    private Character                  instructNextKey;
    private Character                  instructPrevKey;
    private Color                      instructScreenColor;
    private Color                      instructTextColor;

    private Color                      screenColor;
    private Color                      textBgColor;
    private Color                      textColor;
    private Font                       font;
    private Coordinates                position;

    private FeedbackStimulus           correctMessage;
    private FeedbackStimulus           errorMessage;

    private List<String>               bgstim;

    private TaskController             parent;
    private final List<TaskController> children = new ArrayList<>();

    public TaskController(final Task task, final Element element) {
        this.task = task;
        this.element = element;
        if (element instanceof HasScreencolor) {
            final HasScreencolor foo = (HasScreencolor) element;
            screenColor = foo.getScreenColor();
        }
        if (element instanceof HasTextColor) {
            final HasTextColor foo = (HasTextColor) element;
            textColor = foo.getTextColor();
        }
        if (element instanceof HasTextColors) {
            final HasTextColors foo = (HasTextColors) element;
            textBgColor = foo.getTextBgColor();
        }
        if (element instanceof HasFont) {
            final HasFont foo = (HasFont) element;
            font = foo.getFont();
        }
        if (element instanceof HasPosition) {
            final HasPosition foo = (HasPosition) element;
            position = foo.getPosition();
        }
        if (element instanceof HasFeedback) {
            final HasFeedback foo = (HasFeedback) element;
            correctMessage = foo.getCorrectMessage();
            errorMessage = foo.getErrorMessage();
        }
        if (element instanceof Block) {
            final Block foo = (Block) element;
            bgstim = foo.getBgstim();
        }
    }

    public void addChild(final TaskController child) {
        children.add(child);
        child.setParent(this);
    }

    public List<String> getBgstim() {
        if (bgstim == null && parent != null) { return parent.getBgstim(); }
        return bgstim;
    }

    public FeedbackStimulus getCorrectMessage() {
        if (correctMessage == null && parent != null) { return parent.getCorrectMessage(); }
        return correctMessage;
    }

    public Element getElement() {
        return element;
    }

    public FeedbackStimulus getErrorMessage() {
        if (errorMessage == null && parent != null) { return parent.getErrorMessage(); }
        return errorMessage;
    }

    public Font getFont() {
        if (font == null && parent != null) { return parent.getFont(); }
        return font;
    }

    public Font getInstructFont() {
        if (instructFont == null && parent != null) { return parent.getInstructFont(); }
        return instructFont;
    }

    public Character getInstructNextKey() {
        if (instructNextKey == null && parent != null) { return parent.getInstructNextKey(); }
        return instructNextKey;
    }

    public Character getInstructPrevKey() {
        if (instructPrevKey == null && parent != null) { return parent.getInstructPrevKey(); }
        return instructPrevKey;
    }

    public Color getInstructScreenColor() {
        if (instructScreenColor == null && parent != null) { return parent.getInstructScreenColor(); }
        return instructScreenColor;
    }

    public Color getInstructTextColor() {
        if (instructTextColor == null && parent != null) { return parent.getInstructTextColor(); }
        return instructTextColor;
    }

    public TaskController getNextSibling() {
        if (parent != null) {
            final int index = parent.children.indexOf(this) + 1;
            if (parent.children.size() > index) { return parent.children.get(index); }
        }
        return null;
    }

    public TaskController getParent() {
        return parent;
    }

    public Coordinates getPosition() {
        if (position == null && parent != null) { return parent.getPosition(); }
        return position;
    }

    public TaskController getPrevSibling() {
        if (parent != null) {
            final int index = parent.children.indexOf(this) - 1;
            if (index >= 0) { return parent.children.get(index); }
        }
        return null;
    }

    public Color getScreenColor() {
        if (screenColor == null && parent != null) { return parent.getScreenColor(); }
        return screenColor;
    }

    public Task getTask() {
        return task;
    }

    public Color getTextBgColor() {
        if (textBgColor == null && parent != null) { return parent.getTextBgColor(); }
        return textBgColor;
    }

    public Color getTextColor() {
        if (textColor == null && parent != null) { return parent.getTextColor(); }
        return textColor;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public void setInstructFont(final Font instructFont) {
        this.instructFont = instructFont;
    }

    public void setInstructNextKey(final Character instructNextKey) {
        this.instructNextKey = instructNextKey;
    }

    public void setInstructPrevKey(final Character instructPrevKey) {
        this.instructPrevKey = instructPrevKey;
    }

    public void setInstructScreenColor(final Color instructScreenColor) {
        this.instructScreenColor = instructScreenColor;
    }

    public void setInstructTextColor(final Color instructTextColor) {
        this.instructTextColor = instructTextColor;
    }

    public void setParent(final TaskController parent) {
        this.parent = parent;
    }

}
