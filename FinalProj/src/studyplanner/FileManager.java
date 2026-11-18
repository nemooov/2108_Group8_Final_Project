package studyplanner;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "tasks.txt";

    public static void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                writer.println(t.getId() + "," + t.getTaskType() + "," + t.getTitle() + "," + 
                               t.getSubject() + "," + t.getDeadline() + "," + t.getPriority() + "," + t.getStatus());
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
                if (parts.length == 7) {
                    int id = Integer.parseInt(parts[0]);
                    String type = parts[1];
                    String title = parts[2];
                    String subject = parts[3];
                    LocalDate deadline = LocalDate.parse(parts[4]);
                    String priority = parts[5];
                    String status = parts[6];

                    Task t;
                    if (type.equals("Exam")) {
                        t = new Exam(id, title, subject, deadline, priority);
                    } else if (type.equals("Project")) {
                        t = new Project(id, title, subject, deadline, priority);
                    } else {
                        t = new Assignment(id, title, subject, deadline, priority);
                    }
                    
                    t.setStatus(status);
                    ManageTask.addTask(t);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }
    }
}