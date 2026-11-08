package studyplanner;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskMenu {
	
	public static void show() {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			TerminalUI.clear();
			System.out.println("\n╭─ 📋 TO-DO LIST ──────────────────────────────────────────╮");
            System.out.println("│ [1] View All Tasks                                       │");
            System.out.println("│ [2] Add Task                                             │");
            System.out.println("│ [3] Mark Task as Done                                    │");
            System.out.println("│ [4] Delete Task                                          │");
            System.out.println("│ [5] Back to Dashboard                                    │");
            System.out.println("╰──────────────────────────────────────────────────────────╯");
            
            System.out.print("\nChoose: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    viewTasks(scanner);
                    break;
                case "2":
                    addTask(scanner);
                    break;
                case "3":
                    markDone(scanner);
                    break;
                case "4":
                    deleteTask(scanner);
                    break;
                case "5":
                    return; 
                default:
                    System.out.println("Invalid choice!");
                    System.out.print("Press ENTER to continue...");
                    scanner.nextLine();
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.println("\n--- ADD NEW TASK ---");
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter Course Code: ");
        String subject = scanner.nextLine().trim();
        
        System.out.print("Enter Priority (HIGH/MED/LOW): ");
        String priority = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("Enter days until deadline: ");
        int days = 1; 
        try {
        	String daysInput = scanner.nextLine().trim();
            if (!daysInput.isEmpty()) {
                days = Integer.parseInt(daysInput);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid days format. Defaulting to 1 day.");
        }
        
        LocalDate deadline = LocalDate.now().plusDays(days);
        Task newTask = new Task(ManageTask.getNextId(), title, subject, deadline, priority);
        
        ManageTask.addTask(newTask);
        FileManager.saveTasks(ManageTask.getAllTasks()); 
        
        System.out.println("\n✅ Success! Task added.");
        System.out.print("Press ENTER to continue...");
        scanner.nextLine(); 
    }

    private static void markDone(Scanner scanner) {
        System.out.print("\nEnter Task ID to mark as DONE: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            ManageTask.markTaskAsDone(id);
            FileManager.saveTasks(ManageTask.getAllTasks()); 
            System.out.println("✅ Task updated!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID.");
        }
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

        private static void viewTasks(Scanner scanner) {
    	System.out.println("\n+----------------------------------------------------------+");
    	System.out.println("|                     ALL TASKS                            |");
    	System.out.println("+----------------------------------------------------------+");
    	
    	List<Task> tasks = ManageTask.getAllTasks();
        
        if (tasks.isEmpty()) {
            System.out.println("| No tasks found.                                          |");
        } else {
            for (Task t : tasks) {
                String tag = TerminalUI.deadlineTag(t.getDaysLeft());
                
                String line1 = "ID: " + t.getId() + " | [" + t.getStatus() + "] " + t.getTitle();
                String line2 = "Course Code: " + t.getSubject() + " | Due: " + t.getDeadline();
                String line3 = "Priority: " + t.getPriority() + " " + tag;

                System.out.printf("| %-56s |%n", line1);
                System.out.printf("| %-56s |%n", line2);
                System.out.printf("| %-56s |%n", line3);
                
                System.out.println("+----------------------------------------------------------+");
            }
        }
        
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("\nEnter Task ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            ManageTask.deleteTask(id);
            FileManager.saveTasks(ManageTask.getAllTasks()); 
            System.out.println("✅ Task deleted.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid ID.");
        }
        System.out.print("Press ENTER to continue...");
        scanner.nextLine(); 
    }
}