package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {
    private int id;
    private String takenBy;
    private TaskState state;
    private LocalDate lastUpdated;
    private TaskPrio prio;
    private String description;

    Task(String description, int id, TaskPrio prio) {
        this.description = description;
        this.id = id;
        this.prio = prio;
        this.state = TaskState.TO_DO;
        this.lastUpdated = LocalDate.now();
    }

    public void setTakenBy(String takenBy) throws IllegalStateException {
        if (this.takenBy != null) {
            throw new IllegalStateException("Uppgiften upptagen");
        }
        this.takenBy = takenBy;
        this.lastUpdated = LocalDate.now();
    }

    public void setState(TaskState state) {
        this.state = state;
        this.lastUpdated = LocalDate.now();
    }

    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        this.lastUpdated = LocalDate.now();
    }

    public LocalDate getLastUpdated() {
        return this.lastUpdated;
    }

    public TaskState getState() {
        return this.state;
    }

    public int getID() {
        return this.id;
    }

    public String getTakenBy() {
        return this.takenBy;
    }

    public TaskPrio getPriority() {
        return this.prio;
    }

    @Override
    public int compareTo(Task o) {
        int prioCompare = this.prio.compareTo(o.prio);
        if (prioCompare == 0)
            return this.description.compareTo(o.description);
        return prioCompare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", takenBy='").append(takenBy).append('\'');
        sb.append(", state=").append(state);
        sb.append(", lastUpdated=").append(lastUpdated);
        sb.append(", prio=").append(prio);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
