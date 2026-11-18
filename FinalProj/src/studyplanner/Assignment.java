package studyplanner;

import java.time.LocalDate;

public class Assignment extends Task {
    public Assignment(int id, String title, String subject, LocalDate deadline, String priority) {
        super(id, title, subject, deadline, priority);
    }

    @Override
    public String getTaskType() {
        return "Assignment";
    }
}