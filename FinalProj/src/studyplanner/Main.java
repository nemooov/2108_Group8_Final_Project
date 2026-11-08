package studyplanner;

import studyplanner.Dashboard;

public class Main {
	public static void main(String[] args) {
		FileManager.loadTasks();
		Dashboard.show();
	}
}
