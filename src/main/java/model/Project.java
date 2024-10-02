package model;

import model.matcher.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project>, Serializable {
    private String title;
    private int id;
    private String description;
    private LocalDate created;
    private int nextTaskId;
    private List<Task> tasks;

    Project(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.created = LocalDate.now();
        this.nextTaskId = 1;
        this.tasks = new ArrayList<>();
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getID() == id) {
                return task;
            }
        }
        // implementera i/o find istället
        return null;
    }

    public List<Task> findTasks(ITaskMatcher match) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (match.match(task))
                matchedTasks.add(task);
        }
        matchedTasks.sort(null);
        return matchedTasks;
    }

    public Task addTask(String descr, TaskPrio prio) {
        Task newTask = new Task(descr, nextTaskId++, prio);
        tasks.add(newTask);
        return newTask;
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

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

    public String getTitle() {
        return this.title;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public int compareTo(Project o) {
        return this.title.compareTo(o.title);
    }

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
