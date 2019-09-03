package Task;

public class Deadline extends Task {
    private String taskType;

    public Deadline(String[] description, String status) {
        super(description, status);
        this.taskType = "[D]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
