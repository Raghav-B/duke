package Task;

public class Event extends Task {
    private String taskType;

    public Event(String status, String description) {
        super(status, description);
        this.taskType = "[E]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
