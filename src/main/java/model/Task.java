package model;

import java.io.Serializable;
import java.time.LocalDate;

/*** Task that contains logic and metadata ***/
public class Task implements Comparable<Task>, Serializable {
    private int id;
    private String takenBy;
    private TaskState state;
    private LocalDate lastUpdated;
    private TaskPrio prio;
    private String description;

    /*** Constructs a new Task object from given description, id and priority ***/
    Task(String description, int id, TaskPrio prio) {
        this.description = description;
        this.id = id;
        this.prio = prio;
        this.state = TaskState.TO_DO;
        this.lastUpdated = LocalDate.now();
    }

    /**
     * Gives the task to a user
     *
     * @param takenBy The user that will be given that task to
     * @exception IllegalStateException if task already been given to someone
     */
    public void setTakenBy(String takenBy) throws IllegalStateException {
        if (this.takenBy != null) {
            throw new IllegalStateException("Uppgiften upptagen");
        }
        this.takenBy = takenBy;
        this.lastUpdated = LocalDate.now();
    }

    /**
     * Sets the task state and updates its modified date
     *
     * @param state The state that the task will be changed to
     */
    public void setState(TaskState state) {
        this.state = state;
        this.lastUpdated = LocalDate.now();
    }

    /**
     * Sets the priority of the task
     *
     * @param prio The task priority to be set to
     */
    public void setPrio(TaskPrio prio) {
        this.prio = prio;
        this.lastUpdated = LocalDate.now();
    }

    /**
     * Gets the modified date
     *
     * @return The modified date of the task
     */
    public LocalDate getLastUpdated() {
        return this.lastUpdated;
    }

    /**
     * Gets the state
     *
     * @return The state of the task
     */
    public TaskState getState() {
        return this.state;
    }

    /**
     * Gets the ID
     *
     * @return The ID of the task
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets the 'taken by' username
     *
     * @return The name of the responsible member
     */
    public String getTakenBy() {
        return this.takenBy;
    }

    /**
     * Gets the priority
     *
     * @return Gets the task priority
     */
    public TaskPrio getPriority() {
        return this.prio;
    }

    /**
     * Compare two task priorities and description
     *
     * @return The result of the compare of priority if both don't match
     * else the result of the compare of description if priority matches
     */
    @Override
    public int compareTo(Task o) {
        int prioCompare = this.prio.compareTo(o.prio);
        if (prioCompare == 0)
            return this.description.compareTo(o.description);
        return prioCompare;
    }

    /**
     * Gets the class representation in String
     *
     * @return Pretty String from classes representation
     */
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
