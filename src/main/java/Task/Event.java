package Task;

public class Event extends Task {
    private String taskType;

    public Event(String[] description, String status) {
        super(description, status);
        this.taskType = "[E]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
