package pondero.task.controllers;

import java.util.ArrayList;
import java.util.List;
import pondero.task.Task;
import pondero.tests.elements.interfaces.IsNavigableController;

public abstract class TaskController implements IsNavigableController {

    private final Task                 task;
    private TaskController             parent;
    private final List<TaskController> children = new ArrayList<>();

    public TaskController(final Task task) {
        this.task = task;
    }

    public void addChild(final TaskController child) {
        children.add(child);
    }

    public TaskController getParent() {
        return parent;
    }

    public void setParent(final TaskController parent) {
        this.parent = parent;
    }

}
