package model.matcher;

import model.Task;

public class AllMatcher implements ITaskMatcher {
    @Override
    public boolean match(Task task) {
        return true;
    }
}
