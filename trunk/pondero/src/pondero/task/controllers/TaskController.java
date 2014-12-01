package pondero.task.controllers;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import pondero.task.Task;
import pondero.tests.elements.Element;
import pondero.tests.interfaces.IsController;

public abstract class TaskController implements IsController {

    private final Task                 task;
    private final Element              element;
    private TaskController             parent;
    private final List<TaskController> children = new ArrayList<>();

    private Font                       instructFont;
    private Character                  instructNextKey;
    private Character                  instructPrevKey;
    private Color                      instructScreenColor;
    private Color                      instructTextColor;

    public TaskController(final Task task, final Element element) {
        this.task = task;
        this.element = element;
    }

    public void addChild(final TaskController child) {
        children.add(child);
        child.setParent(this);
    }

    public Element getElement() {
        return element;
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

    public TaskController getPrevSibling() {
        if (parent != null) {
            final int index = parent.children.indexOf(this) - 1;
            if (index >= 0) { return parent.children.get(index); }
        }
        return null;
    }

    public Task getTask() {
        return task;
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
