# 2108_Group8_Final_Project
**Encapsulation**

private String day;
private String time;
private String title;

public String getDay() { 
    return day; 
}

public String getTime() { 
    return time; 
}

public String getTitle() { 
    return title; 
}


The data fields (day, time, and title) are kept private so they cannot be changed directly from outside the class. They are safely exposed for reading using public getter methods (getDay(), getTime(), and getTitle()).
