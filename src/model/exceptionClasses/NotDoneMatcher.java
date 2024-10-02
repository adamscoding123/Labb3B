package model.exceptionClasses;

import model.Task;

public class NotDoneMatcher implements ITaskMatcher {
    @Override
    public boolean match(Task task) {
        return !task.toString().contains("state=DONE");
    }
}
gitgit