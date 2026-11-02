package studyplanner;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class Dashboard {

    private static class FakeTask {
        String title;
        long daysLeft;
        FakeTask(String t, long d) { title = t; daysLeft = d; }
    }

    private static class FakeCourse {
        String time, name;
        FakeCourse(String t, String n) { time = t; name = n; }
    }

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
                continue;
            }

            handleChoice(choice);  
        }
    }

    private static void printStats() {
        int pending = 0, overdue = 0, done = 0;
        double progress = 0;

        String content =
                "Pending: " + pending +
                "  Overdue: " + overdue + "\n" +
                "Done:    " + done +
                "  " + TerminalUI.progressBar(progress, 10);
        TerminalUI.box("📊 STATS", content, 60);
    }

    private static void printTodoList() {
        List<FakeTask> tasks = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int shown = 0;
        for (FakeTask t : tasks) {
            if (shown >= 4) break;
            String tag = TerminalUI.deadlineTag(t.daysLeft);
            sb.append(tag).append(" ").append(t.title).append("\n");
            shown++;
        }
        TerminalUI.box("📌 TASKS", sb.toString().stripTrailing(), 60);
    }

    private static void printCourseList() {
        List<FakeCourse> courses = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (FakeCourse c : courses) {
            sb.append(c.time).append("  ").append(c.name).append("\n");
        }
        TerminalUI.box("📅 TODAY", sb.toString().stripTrailing(), 60);
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
                System.out.println("→ [To-Do List] Coming soon...");
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
