public class Task {
    private String status;
    private String description;

    Task(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
