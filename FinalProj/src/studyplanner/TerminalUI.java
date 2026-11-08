package studyplanner;

public class TerminalUI {

    public static void clear() {
        for (int i = 0; i < 0; i++) {
            System.out.println();
        }
    }

    public static void box(String title, String content, int width) {
        String top = "╭─ " + title + " ";
        int remaining = width - top.length() - 1;
        top += "─".repeat(Math.max(0, remaining)) + "╮";
        System.out.println(top);

        for (String line : content.split("\n")) {
            String padded = String.format("%-" + (width - 4) + "s", line);
            System.out.println("│ " + padded + " │");
        }

        String bottom = "╰" + "─".repeat(width - 2) + "╯";
        System.out.println(bottom);
    }

    public static String progressBar(double percent, int length) {
        int filled = (int) Math.round((percent / 100.0) * length);
        filled = Math.max(0, Math.min(length, filled));
        return "█".repeat(filled) + "░".repeat(length - filled);
    }

    public static String deadlineTag(long daysLeft) {
        if (daysLeft < 0)  return "[OVERDUE]";
        if (daysLeft == 0) return "[TODAY]  ";
        if (daysLeft <= 3) return "[URGENT]   ";
        return "[SOON]";
    }
}