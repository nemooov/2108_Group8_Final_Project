package studyplanner;

public class Main {
    public static void main(String[] args) {
        FileManager.loadTasks();          
        Calendar.loadCalendar();     
        
        Dashboard.show();
    }
}