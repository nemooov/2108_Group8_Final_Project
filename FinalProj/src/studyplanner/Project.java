package studyplanner;

import java.time.LocalDate;

public class Project extends Task {
    public Project(int id, String title, String subject, LocalDate deadline, String priority) {
        super(id, title, subject, deadline, priority);
    }

    @Override
    public String getTaskType() {
        return "Assignment";
    }
}