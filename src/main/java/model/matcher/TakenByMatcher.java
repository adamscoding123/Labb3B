package model.matcher;

import model.Task;

public class TakenByMatcher implements ITaskMatcher {
    private String takenBy;

    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }

    @Override
    public boolean match(Task task) {
        return task.getTakenBy().compareTo(this.takenBy) == 0;
    }
}
