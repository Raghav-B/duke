package Task;
import java.time.LocalDateTime;

public class Event extends Task {
    private String taskType;

    public Event(String itemStatus, String description, LocalDateTime dateTime) {
        super(itemStatus, description, dateTime);
        this.taskType = "[E]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
