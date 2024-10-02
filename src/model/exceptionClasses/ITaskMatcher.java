package model.exceptionClasses;

import model.Task;

public interface ITaskMatcher {
    boolean match(Task task);
}
