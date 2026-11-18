package studyplanner;

import java.time.LocalDate;

public class Exam extends Task {
    public Exam(int id, String title, String subject, LocalDate deadline, String priority) {
        super(id, title, subject, deadline, priority);
    }

    @Override
    public String getTaskType() {
        return "Assignment";
    }
}