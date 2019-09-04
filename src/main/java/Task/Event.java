package Task;

import DukeException.IncompleteListEntryException;
import DukeException.UnknownDateTimeFormatException;

public class Event extends Task {
    private String taskType;

    public Event(String[] description, String status) throws UnknownDateTimeFormatException,
            IncompleteListEntryException {
        super(description, status);
        this.taskType = "[E]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
