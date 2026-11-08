package studyplanner;

import java.util.ArrayList;
import java.util.List;

public class ManageTask {
    private static final List<Task> tasks = new ArrayList<>();
    private static int nextId = 1;

    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static List<Task> getAllTasks() {
        return tasks;
    }

    public static void deleteTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    public static void markTaskAsDone(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus("DONE");
                break;
            }
        }
    }

    public static int getNextId() {
        return nextId++;
    }
}