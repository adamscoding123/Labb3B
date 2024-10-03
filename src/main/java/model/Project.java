package model;

import model.matcher.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*** Class that implements logic and data for a project that manages tasks ***/
public class Project implements Comparable<Project>, Serializable {
    private String title;
    private int id;
    private String description;
    private LocalDate created;
    private int nextTaskId;
    private List<Task> tasks;

    /*** Constructs a new Project from a title and id ***/
    Project(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.created = LocalDate.now();
        this.nextTaskId = 1;
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets a task from a specified id
     *
     * @return Task
     */
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getID() == id) {
                return task;
            }
        }
        // implementera i/o find istället
        return null;
    }

    /**
     * Finds task by using a specified matcher
     *
     * @return Collection of task that was matched by matcher
     */
    public List<Task> findTasks(ITaskMatcher match) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (match.match(task))
                matchedTasks.add(task);
        }
        matchedTasks.sort(null);
        return matchedTasks;
    }

    /**
     * Adds a task to the project
     *
     * @return The added task
     */
    public Task addTask(String descr, TaskPrio prio) {
        Task newTask = new Task(descr, nextTaskId++, prio);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Removes a task from the project
     *
     * @return Boolean if got removed
     */
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Gets the ProjectState of the project
     *
     * @return ProjectState enum
     */
    public ProjectState getState() {
        boolean allDone = true;
        if (tasks.isEmpty())
            return ProjectState.EMPTY;
        for (Task task : tasks) {
            TaskState state = task.getState();
            if (state == TaskState.TO_DO || state == TaskState.IN_PROGRESS) {
                allDone = false;
                break;
            }
        }
        if (allDone)
            return ProjectState.COMPLETED;
        else
            return ProjectState.ONGOING;
    }

    /**
     * Gets the time that the project has lastly been updated
     * Updates occur when the tasks gets added/removed
     *
     * @return LocalDate from the latest task
     */
    public LocalDate getLastUpdated() {
        LocalDate lastUpdated = LocalDate.from(created);
        if (tasks.isEmpty())
            return created;
        for (Task task : tasks) {
            LocalDate time = task.getLastUpdated();
            if (time.isAfter(lastUpdated)) {
                lastUpdated = time;
            }
        }
        return lastUpdated;
    }

    /**
     * Gets the project title
     *
     * @return Title of the project
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the project ID
     *
     * @return ID of the project
     */
    public int getID() {
        return this.id;
    }

    /**
     * Compares the titles of two projects
     *
     * @return The compare result as integer, 0 defines exact match
     */
    @Override
    public int compareTo(Project o) {
        return this.title.compareTo(o.title);
    }

    /**
     * Gets the class representation in String format
     * Mainly for debug purposes
     *
     * @return Pretty string from class data
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("title='").append(title).append('\'');
        sb.append(", id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", created=").append(created);
        sb.append(", nextTaskId=").append(nextTaskId);
        sb.append(", tasks=").append(tasks);
        sb.append('}');
        return sb.toString();
    }
}
