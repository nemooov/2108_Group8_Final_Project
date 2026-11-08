package studyplanner;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "tasks.txt";

    public static void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                writer.println(t.getId() + "," + t.getTitle() + "," + t.getSubject() + "," + 
                               t.getDeadline() + "," + t.getPriority() + "," + t.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String subject = parts[2];
                    LocalDate deadline = LocalDate.parse(parts[3]);
                    String priority = parts[4];
                    String status = parts[5];

                    Task t = new Task(id, title, subject, deadline, priority);
                    t.setStatus(status);
                    ManageTask.addTask(t); 
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }
    }
}