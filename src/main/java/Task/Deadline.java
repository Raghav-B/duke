package Task;

import DukeException.IncompleteListEntryException;
import DukeException.UnknownDateTimeFormatException;

public class Deadline extends Task {
    private String taskType;

    public Deadline(String[] description, String status) throws UnknownDateTimeFormatException,
            IncompleteListEntryException {
        super(description, status);
        this.taskType = "[D]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
