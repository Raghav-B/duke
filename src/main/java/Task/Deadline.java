package Task;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private String taskType;

    public Deadline(String itemStatus, String description, LocalDateTime dateTime) {
        super(itemStatus, description, dateTime);
        this.taskType = "[D]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
