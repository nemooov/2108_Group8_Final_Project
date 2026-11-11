package studyplanner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    
    public static class CalendarItem {
        private String day;
        private String time;
        private String title;

        public CalendarItem(String day, String time, String title) {
            this.day = day;
            this.time = time;
            this.title = title;
        }

        public String getDay() { return day; }
        public String getTime() { return time; }
        public String getTitle() { return title; }
    }

    private static final List<CalendarItem> items = new ArrayList<>();
    private static final String FILE_NAME = "calendar.txt";

    public static void addItem(CalendarItem item) {
        items.add(item);
        saveCalendar();
    }

    public static List<CalendarItem> getAllItems() {
        return items;
    }

    public static List<CalendarItem> getTodayItems() {
        String today = java.time.LocalDate.now().getDayOfWeek().toString();
        today = today.substring(0, 1).toUpperCase() + today.substring(1).toLowerCase();
        
        List<CalendarItem> result = new ArrayList<>();
        for (CalendarItem item : items) {
            if (item.getDay().equalsIgnoreCase(today)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void deleteItem(String day, String time) {
        items.removeIf(i -> i.getDay().equalsIgnoreCase(day) && i.getTime().equals(time));
        saveCalendar(); // Auto-save pag may binura
    }

    // Savinggg and load
    private static void saveCalendar() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (CalendarItem item : items) {
                writer.println(item.getDay() + "," + item.getTime() + "," + item.getTitle());
            }
        } catch (IOException e) {
            System.out.println("Error saving calendar.");
        }
    }

    public static void loadCalendar() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    items.add(new CalendarItem(parts[0], parts[1], parts[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading calendar.");
        }
    }
}