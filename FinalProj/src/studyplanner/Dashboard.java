package studyplanner;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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

            handleChoice(choice, scanner); 
            
            if (choice == 4) return; 
            
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
        TerminalUI.box("STATS", content, 60);
    }

    private static void printTodoList() {
        List<Task> tasks = ManageTask.getAllTasks();

        List<Task> pendingTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getStatus().equals("PENDING")) {
                pendingTasks.add(t);
            }
        }

        if (pendingTasks.isEmpty()) {
            TerminalUI.box("TASKS", "No pending tasks. You're all caught up!", 60);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int shown = 0;
        for (Task t : pendingTasks) {
            if (shown >= 4) break;
            String tag = TerminalUI.deadlineTag(t.getDaysLeft());
            sb.append(tag).append(" ").append(t.getTitle()).append("\n");
            shown++;
        }
        
        if (pendingTasks.size() > 4) {
            sb.append("... and ").append(pendingTasks.size() - 4).append(" more\n");
        }
        
        TerminalUI.box("TASKS", sb.toString().stripTrailing(), 60);
    }

    private static void printCourseList() {
        List<Calendar.CalendarItem> todayItems = Calendar.getTodayItems();
        
        if (todayItems.isEmpty()) {
            TerminalUI.box("TODAY", "No classes scheduled for today.", 60);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Calendar.CalendarItem item : todayItems) {
            sb.append(item.getTime()).append(" - ").append(item.getTitle()).append("\n");
        }
        
        TerminalUI.box("TODAY", sb.toString().stripTrailing(), 60);
    }

    private static void filterTasksDashboard(Scanner scanner) {
        System.out.println("\n--- FILTER TASKS ---");
        System.out.println("[1] Filter by Priority (HIGH/MED/LOW)");
        System.out.println("[2] Filter by Status (PENDING/DONE)");
        System.out.print("Choose filter type: ");
        String filterChoice = scanner.nextLine().trim();

        List<Task> allTasks = ManageTask.getAllTasks();
        List<Task> results = new ArrayList<>();
        String filterTitle = "";

        if (filterChoice.equals("1")) {
            System.out.print("Enter Priority to filter (HIGH/MED/LOW): ");
            String priority = scanner.nextLine().trim().toUpperCase();
            filterTitle = "Priority: " + priority;
            for (Task t : allTasks) {
                if (t.getPriority().equals(priority)) {
                    results.add(t);
                }
            }
        } else if (filterChoice.equals("2")) {
            System.out.print("Enter Status to filter (PENDING/DONE): ");
            String status = scanner.nextLine().trim().toUpperCase();
            filterTitle = "Status: " + status;
            for (Task t : allTasks) {
                if (t.getStatus().equals(status)) {
                    results.add(t);
                }
            }
        } else {
            System.out.println("Invalid filter choice.");
            return;
        }

        System.out.println("\n+----------------------------------------------------------+");
        String titleLine = "FILTERED TASKS (" + filterTitle + ")";
        System.out.printf("| %-56s |%n", titleLine);
        System.out.println("+----------------------------------------------------------+");

        if (results.isEmpty()) {
            System.out.println("| No tasks found matching your filter.                     |");
            System.out.println("+----------------------------------------------------------+");
        } else {
            for (Task t : results) {
                String tag = TerminalUI.deadlineTag(t.getDaysLeft());
                String line1 = "ID: " + t.getId() + " | [" + t.getTaskType() + "] " + t.getTitle();
                String line2 = "Subject: " + t.getSubject() + " | Due: " + t.getDeadline();
                String line3 = "Priority: " + t.getPriority() + " | Status: " + t.getStatus() + " " + tag;

                System.out.printf("| %-56s |%n", line1);
                System.out.printf("| %-56s |%n", line2);
                System.out.printf("| %-56s |%n", line3);
                System.out.println("+----------------------------------------------------------+");
            }
        }
    }

    private static void printMenu() {
        String menu = "[1] To-Do List     [2] Calendar     [3] Filter Tasks\n" +
                      "[4] Logout";
        TerminalUI.box("MENU", menu, 60);
    }

    private static void handleChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                TaskMenu.show();
                break; 
            case 2:
                CalendarMenu.show(); 
                break;
            case 3:
                filterTasksDashboard(scanner); 
                break;
            case 4:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }
}