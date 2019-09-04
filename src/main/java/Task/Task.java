package Task;
import java.time.*;
import java.time.format.*;

public class Task {
    private String status;
    private String description;
    private LocalDateTime dateTime;
    private DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Task(String[] description, String status) {
        this.status = status;
        this.description = taskInputParse(description);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDescription(String[] description) {
        this.description = taskInputParse(description);
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return "default";
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        //System.out.println(dateTime);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    // Handles
    private String taskInputParse(String[] inputArr) {
        int index = 1;
        String description = "";
        String stringDateTime = "";
        boolean dateTimeFound = false;

        for (; index < inputArr.length - 1; index++) {
            if (inputArr[index].equals("/by")) {
                index += 1;
                dateTimeFound = true;
                break;
            } else {
                description += (inputArr[index] + " ");
            }
        }

        if (dateTimeFound) {
            if (inputArr.length - index == 2) {
                setDateTime(LocalDateTime.parse(inputArr[index] + " " + inputArr[index + 1],
                        dukeDateTimeFormat));
            }

            stringDateTime = "(by: ";
            for (; index < inputArr.length - 1; index++) {
                stringDateTime += (inputArr[index] + " ");
            }
            stringDateTime += (inputArr[inputArr.length - 1] + ")");
        } else {
            description += inputArr[index];
        }

        return description + stringDateTime;
    }
}

