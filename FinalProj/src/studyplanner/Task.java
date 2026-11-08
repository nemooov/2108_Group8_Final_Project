package studyplanner;

import java.time.LocalDate;

public class Task {
	private int id;
	private String title, subject;
	private LocalDate deadline;
	private String priority, status;
	
	public Task(int id, String title, String subject, LocalDate deadline, String priority) {
		this.id = id;
		this.title = title;
		this.subject = subject;
		this.deadline = deadline;
		this.priority = priority;
		this.status = "PENDING";
	}
	
	public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public LocalDate getDeadline() { return deadline; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public long getDaysLeft() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), deadline);
    }
}
