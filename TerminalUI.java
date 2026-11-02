package studyplanner;

public class TerminalUI {
	public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        String bar = "█".repeat(filled) + "░".repeat(length - filled);
        return bar;
    }

    public static String deadlineTag(long daysLeft) {
        if (daysLeft < 0)  return "[OVERDUE]";
        if (daysLeft == 0) return "[TODAY]  ";
        if (daysLeft <= 3) return "[SOON]   ";
        return "[UPCOMING]";
    }
}
