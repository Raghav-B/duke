package Task;

import DukeException.IncompleteListEntryException;
import DukeException.UnknownDateTimeFormatException;

public class ToDo extends Task {
    private String taskType;

    public ToDo(String[] description, String status) throws UnknownDateTimeFormatException,
            IncompleteListEntryException {
        super(description, status);
        this.taskType = "[T]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
