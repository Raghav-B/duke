package Task;

public class ToDo extends Task {
    private String taskType;

    public ToDo(String status, String description) {
        super(status, description);
        this.taskType = "[T]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
