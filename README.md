# 2108_Group8_Final_Project
# Study Planner – Task Management System

## Project Description

A Java-based task management component designed to organize and manage tasks in a Study Planner application. It allows users to add tasks, view all available tasks, delete specific tasks, mark tasks as completed, and generate unique task IDs.

## Problem Being Addressed

Managing academic tasks manually can make it difficult for students to organize their responsibilities and monitor their progress. Without a proper task management system, keeping track of completed and unfinished tasks may become confusing and disorganized.

## Project Objectives

- Provide a method for adding tasks to the task list.
- Display all available tasks.
- Allow users to delete tasks using their unique IDs.
- Enable users to mark tasks as completed.
- Generate unique identification numbers for each task.

## Target Users

Students who need a system for organizing and managing their academic tasks and monitoring their task completion status.

## Main Features

Task Addition; Task Listing; Task Deletion; Task Completion Status; Automatic Task ID Generation.

## Technologies Used

Java, Java Collections Framework (`ArrayList` and `List`), Lambda Expressions, and Object-Oriented Programming (OOP).

## Team Members and Roles

- Aurelio, Anika Ava K. - Team Leader
- Catral, Ian Dave G. - System/Design Lead
- Hernandez, Denise Charelle D. - Documentation
- Villegas, Nemesio P. - Lead Programmer

## OOP Concepts Demonstrated

- **Classes and Objects:** The `ManageTask` class works with `Task` objects to manage individual tasks.
- **Encapsulation:** Uses private static variables to manage the task collection and ID counter.
- **Static Methods and Variables:** Allows task management operations and ID generation to be accessed through the class without creating an instance of `ManageTask`.
- **Java Collections:** Uses `ArrayList` and `List` to store and manage multiple tasks.
- **Lambda Expressions:** Uses `removeIf()` to identify and remove tasks based on their IDs.
- **Iteration:** Uses an enhanced for-loop to search for a task and update its completion status.

## How to Run

The `ManageTask.java` file is part of the `studyplanner` package and depends on the `Task` class.

To compile and run the complete application, the other required Java files and the main program must also be included.

The exact compilation and execution commands depend on the complete project structure.

## Sample Screenshots

### 1. Task Management Menu

### 2. Adding a Task

### 3. Displaying All Tasks

### 4. Deleting a Task

### 5. Marking a Task as Completed

### 6. Task List and Status

## Known Limitations

- The provided `ManageTask.java` file only contains task management operations.
- Task details and their structure depend on the separate `Task` class.
- The task collection is stored in memory using an `ArrayList`.
- No file-saving or database functionality is implemented in the provided class.
- No graphical user interface is included in the provided code.

## Future Improvements

- **Task Persistence:** Implement file handling or database storage to preserve task records.
- **Task Prioritization:** Add priority levels to help users organize important academic responsibilities.
- **Due Date Management:** Include deadlines for individual tasks.
- **Task Categories:** Organize tasks according to subjects or academic activities.
- **Graphical User Interface (GUI):** Develop a more user-friendly interface for managing tasks.
- **Task Search and Filtering:** Allow users to search and filter tasks according to their status or other details.

## Documentation

*To be updated with the project proposal, UML class diagram, and other supporting documentation.*
