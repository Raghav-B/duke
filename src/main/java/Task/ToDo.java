package Task;

public class ToDo extends Task {
    private String taskType;

    public ToDo(String[] description, String status) {
        super(description, status);
        this.taskType = "[T]";
    }

    public String getTaskType() {
        return this.taskType;
    }
}
