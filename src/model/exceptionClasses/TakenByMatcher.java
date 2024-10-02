package model.exceptionClasses;

import model.Task;

public class TakenByMatcher implements ITaskMatcher {
    private String takenBy;
    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }
    @Override
    public boolean match(Task task) {
        return task.toString().contains("takenBy='" + takenBy + "'");
    }
}
