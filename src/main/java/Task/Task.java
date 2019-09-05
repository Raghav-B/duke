package Task;
import DukeException.IncompleteListEntryException;
import DukeException.UnknownDateTimeFormatException;

import java.time.*;
import java.time.format.*;

public class Task {
    private String status;
    private String description;
    private LocalDateTime dateTime;


    public Task(String[] description, String status) throws UnknownDateTimeFormatException, IncompleteListEntryException {
        this.status = status;
        this.description = taskInputParse(description);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDescription(String[] description) throws UnknownDateTimeFormatException, IncompleteListEntryException {
        this.description = taskInputParse(description);
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return "default";
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        //System.out.println(dateTime);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
}

