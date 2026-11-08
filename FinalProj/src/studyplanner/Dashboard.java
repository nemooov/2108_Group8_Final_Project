package studyplanner;

import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

public class Dashboard {

    public static void show() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            TerminalUI.clear();

            System.out.println("\n                                        ✦ STUDY PLANNER ✦\n");

            printStats();
            printTodoList();
            printCourseList();
            printMotivation();
            printMenu();

            int choice = -1;
            try {
                System.out.print("\nChoose: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                scanner.nextLine(); 
                System.out.print("Press ENTER to continue...");
                scanner.nextLine();
                continue;
            }

            handleChoice(choice); 
            
            if (choice == 2) return; 
            
            System.out.print("\nPress ENTER to continue...");
            scanner.nextLine();
        }
    }

    private static void printStats() {
        int pending = 0, overdue = 0, done = 0;
        
        List<Task> allTasks = ManageTask.getAllTasks();
        
        for (Task t : allTasks) {
            if (t.getStatus().equals("PENDING")) pending++;
            if (t.getStatus().equals("DONE")) done++;
            if (t.getDaysLeft() < 0 && t.getStatus().equals("PENDING")) overdue++;
        }
        
        double progress = allTasks.isEmpty() ? 0 : (done * 100.0) / allTasks.size();

        String content =
                "Pending: " + pending +
                "  Overdue: " + overdue + "\n" +
                "Done:    " + done +
                "  " + TerminalUI.progressBar(progress, 10);
        TerminalUI.box("📊 STATS", content, 60);
    }

    private static void printTodoList() {
        List<Task> tasks = ManageTask.getAllTasks();

        if (tasks.isEmpty()) {
            TerminalUI.box("📌 TASKS", "No tasks yet. Add one in To-Do List!", 60);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int shown = 0;
        for (Task t : tasks) {
            if (shown >= 4) break;
            String tag = TerminalUI.deadlineTag(t.getDaysLeft());
            sb.append(tag).append(" ").append(t.getTitle()).append("\n");
            shown++;
        }
        TerminalUI.box("📌 TASKS", sb.toString().stripTrailing(), 60);
    }

    private static void printCourseList() {
        TerminalUI.box("📅 TODAY", "No classes scheduled for today.", 60);
    }

    private static void printMotivation() {
        System.out.println("\n  ✦ Start now! 🚀");
    }

    private static void printMenu() {
        String menu = "[1] To-Do List     [2] Logout";
        TerminalUI.box("📖 MENU", menu, 60);
    }

    private static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                TaskMenu.show();
                break; 
            case 2:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }
}