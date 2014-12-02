package pondero.util;

import pondero.Context;
import pondero.task.Task;
import pondero.task.controllers.TaskController;
import pondero.task.launch.DefaultMonitor;
import pondero.task.launch.DefaultRenderer;
import pondero.tests.Test;

public class TaskUtil {

    public static void testRun(final Test test) throws Exception {
        Context.init(null);
        final Task task = new Task(new DefaultRenderer(), test);
        for (final TaskController controller : task) {
            System.out.println(controller.getElement().getName() + " : " + controller.getElement().getClass().getSimpleName());
        }
        task.addMonitor(new DefaultMonitor());
        task.start();
    }

}
