package Task;

public class Deadline extends Task {
    private String taskType;

    public Deadline(String status, String description) {
        super(status, description);
        this.taskType = "[D]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
