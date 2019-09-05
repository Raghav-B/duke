package Task;
import java.time.LocalDateTime;

public class ToDo extends Task {
    private String taskType;

    public ToDo(String itemStatus, String description, LocalDateTime dateTime) {
        super(itemStatus, description, dateTime);
        this.taskType = "[T]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
