package studyplanner;

import java.util.List;
import java.util.Scanner;

public class CalendarMenu {
    
    public static void show() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            TerminalUI.clear();
            System.out.println("\n+----------------------------------------------------------+");
            System.out.println("|                      CALENDAR                            |");
            System.out.println("+----------------------------------------------------------+");
            System.out.println("| [1] View All Schedules                                   |");
            System.out.println("| [2] Add Schedule                                         |");
            System.out.println("| [3] Delete Schedule                                      |");
            System.out.println("| [4] Back to Dashboard                                    |");
            System.out.println("+----------------------------------------------------------+");
            
            System.out.print("\nChoose: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1": viewSchedules(scanner); break;
                case "2": addSchedule(scanner); break;
                case "3": deleteSchedule(scanner); break;
                case "4": return;
                default:
                    System.out.println("Invalid choice!");
                    System.out.print("Press ENTER to continue...");
                    scanner.nextLine();
            }
        }
    }

    private static void addSchedule(Scanner scanner) {
        System.out.println("\n--- ADD SCHEDULE ---");
        
        System.out.print("Enter Day (e.g., Monday): ");
        String day = scanner.nextLine().trim();
        
        System.out.print("Enter Time (e.g., 8:00 AM): ");
        String time = scanner.nextLine().trim();
        
        System.out.print("Enter Subject/Title: ");
        String title = scanner.nextLine().trim();
        
        Calendar.addItem(new Calendar.CalendarItem(day, time, title));
        
        System.out.println("\n[OK] Schedule added!");
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

        private static void viewSchedules(Scanner scanner) {
        System.out.println("\n+----------------------------------------------------------+");
        System.out.println("|                    ALL SCHEDULES                         |");
        System.out.println("+----------------------------------------------------------+");
        
        List<Calendar.CalendarItem> items = Calendar.getAllItems();
        
        if (items.isEmpty()) {
            System.out.println("| No schedules found.                                      |");
        } else {
            for (Calendar.CalendarItem item : items) {
                String scheduleText = item.getDay() + " | " + item.getTime() + " | " + item.getTitle();
                
                System.out.printf("| %-56s |%n", scheduleText);
                System.out.println("+----------------------------------------------------------+");
            }
        }
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

    private static void deleteSchedule(Scanner scanner) {
        System.out.print("\nEnter Day to delete (e.g., Monday): ");
        String day = scanner.nextLine().trim();
        
        System.out.print("Enter Time to delete (e.g., 8:00 AM): ");
        String time = scanner.nextLine().trim();
        
        Calendar.deleteItem(day, time);
        System.out.println("[OK] Schedule deleted.");
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }
}