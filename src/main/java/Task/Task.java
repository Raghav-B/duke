package Task;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Task {
    private String status;
    private String description;
    private LocalDateTime dateTime;

    public DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public Task(String status, String description, LocalDateTime dateTime) {
        this.status = status;
        this.description = description;
        this.dateTime = dateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTaskType() {
        return "default";
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getDateTimeSaveString() {
        return dateTime.format(dukeDateTimeFormat);
    }

    public String getDateTimeString() {
        String dateString = dateTime.getDayOfMonth() + " " + dateTime.getMonth().name() +
                " " + dateTime.getYear() + ", ";

        String timeString = "";
        if (Integer.toString(dateTime.getHour()).length() != 2) {
            timeString += "0" + dateTime.getHour();
        } else {
            timeString += Integer.toString(dateTime.getHour());
        }
        if (Integer.toString(dateTime.getMinute()).length() != 2) {
            timeString += "0" + dateTime.getMinute();
        } else {
            timeString += Integer.toString(dateTime.getMinute());
        }
        timeString += " hours.";

        return dateString + timeString;
    }
}

