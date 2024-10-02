package ui;

import model.matcher.ITaskMatcher;
import model.matcher.NotDoneMatcher;
import model.matcher.PrioMatcher;
import model.matcher.TakenByMatcher;
import model.matcher.AllMatcher;
import model.*;

import java.util.List;
import java.util.Scanner;

/**
 * User interactions for a specific project, current project.
 * The user selects actions on current project in the projectLoop method.
 */
class CurrentProjectUI {
  private Project currentProject;
  private final Scanner scan;

  // package private visibility - only visible to other classes in
  // package ui - intended for MainUI.
  CurrentProjectUI(Scanner scan) {
    this.scan = scan;
    this.currentProject = null; // TODO: Ugly!
  }

  void setCurrentProject(Project project) {
    this.currentProject = project;
    projectLoop();
  }

  Project getCurrentProject() {
    return currentProject;
  }

  void projectLoop() {
    char choice;
    do {
      printCurrentProjectMenu();
      choice = InputUtils.scanAndReturnFirstChar(scan);

      switch (choice) {
        case 'T':
          System.out.print("Name? ");
          String takenBy = scan.nextLine();
          viewTasks(new TakenByMatcher(takenBy));
          break;
        case 'N':
          viewTasks(new NotDoneMatcher());
          break;
        case 'H':
          viewTasks(new PrioMatcher(TaskPrio.High));
          break;
        case 'A':
          addTask();
          break;
        case 'G':
          giveTaskTo();
          break;
        case 'U':
          updateTask();
          break;
        case 'X':
          break;
        case 'L':
          listTasks();
          break;
        default:
          System.out.println("Unknown command");
      }

    } while (choice != 'X');
  }
  private void listTasks() {
    for(Task task : currentProject.findTasks(new AllMatcher())) {
      System.out.println(task.toString());
    }
  }
  private void giveTaskTo() {
    System.out.print("Task id? ");
    int id = scan.nextInt();
    scan.nextLine(); // remove "new line" from scanner buffer
    Task task = currentProject.getTaskById(id);
    System.out.print("Give task to? ");
    String user = scan.nextLine();
    try {
      task.setTakenBy(user);
    } catch (IllegalStateException exception) {
      System.out.println(exception.getMessage());
    }
  }

  private void viewTasks(ITaskMatcher matcher) {
    System.out.println(currentProject.toString());
    List<Task> tasks = currentProject.findTasks(matcher);
    printTasks(tasks);
  }

  private void addTask() {
    System.out.print("Description? ");
    String descr = scan.nextLine();
    System.out.print("Priority (L)ow, (M)edium, (H)igh? ");
    char prioChar = InputUtils.scanAndReturnFirstChar(scan);
    TaskPrio prio = prioChar == 'H' ? TaskPrio.High : prioChar == 'L' ? TaskPrio.Low : TaskPrio.Medium;
    currentProject.addTask(descr, prio);
  }

  private void updateTask() {
    System.out.print("Task id? ");
    int id = scan.nextInt();
    scan.nextLine(); // remove "new line" from scanner buffer
    Task task = currentProject.getTaskById(id);
    if (task != null) {
      System.out.println(task);
      System.out.print("New state (T)odo (D)one? ");
      char stateChar = InputUtils.scanAndReturnFirstChar(scan);
      if (stateChar == 'T') {
        System.out.print("Taken by (name or email address)? ");
        String emailStr = scan.nextLine();
        task.setState(TaskState.TO_DO);
        task.setTakenBy(emailStr);
      } else if (stateChar == ('D')) {
        task.setState(TaskState.DONE);
      }
    } else {
      System.out.println("Id not found.");
    }
  }

  private void printCurrentProjectMenu() {
    System.out.println("--- Manage " + currentProject.getTitle() + " ---");
    System.out.println("T - list tasks taken by ...");
    System.out.println("N - list tasks not done");
    System.out.println("H - list high priority tasks");
    System.out.println("A - add task");
    System.out.println("G - give task to");
    System.out.println("L - list tasks");
    System.out.println("U - update task");
    System.out.println("X - exit project menu");
    System.out.println("----------");
  }

  private void printTasks(List<Task> tasks) {
    if (tasks.isEmpty()) {
      System.out.println("No tasks added");
    } else {
      for (Task task : tasks) {
        System.out.println(task.toString());
      }
    }
  }
}
