package Task;
import DukeException.IncompleteListEntryException;
import DukeException.UnknownDateTimeFormatException;

import java.time.*;
import java.time.format.*;

public class Task {
    private String status;
    private String description;
    private LocalDateTime dateTime;
    private DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Task(String[] description, String status) throws UnknownDateTimeFormatException, IncompleteListEntryException {
        this.status = status;
        this.description = taskInputParse(description);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDescription(String[] description) throws UnknownDateTimeFormatException, IncompleteListEntryException {
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

    private String taskInputParse(String[] inputArr) throws UnknownDateTimeFormatException, IncompleteListEntryException {
        int index = 0;
        String description = "";
        String stringDateTime = "";
        boolean dateTimeFound = false;

        if (inputArr[0].equals("/by")) {
            throw new IncompleteListEntryException("Incomplete list entry. Entry should be of format <task> /by " +
                    "dd/mm/yyyy hhmm");
        }

        for (; index < inputArr.length - 1; index++) {
            if (inputArr[index].equals("/by")) {
                index += 1; // Move current index to next index, which refers to the start of the datetime string.
                dateTimeFound = true;
                break;
            } else {
                description += (inputArr[index] + " ");
            }
        }

        if (dateTimeFound) {
            if (inputArr.length - index == 2) {
                try {
                    setDateTime(LocalDateTime.parse(inputArr[index] + " " + inputArr[index + 1],
                            dukeDateTimeFormat));
                } catch(Exception e) {
                    throw new UnknownDateTimeFormatException("Unknown DateTime parameters entered. Please ensure they " +
                            "follow this format: 'dd/mm/yyyy hhmm'");
                }

                stringDateTime = "/by ";
                for (; index < inputArr.length - 1; index++) {
                    stringDateTime += (inputArr[index] + " ");
                }
                stringDateTime += (inputArr[inputArr.length - 1]);

            } else {
                throw new UnknownDateTimeFormatException("Unknown DateTime parameters entered. Please ensure they " +
                        "follow this format: 'dd/mm/yyyy hhmm'");
            }

        } else {
            throw new IncompleteListEntryException("Incomplete list entry. Entry should be of format <task> /by " +
                    "dd/mm/yyyy hhmm");
        }

        return description + stringDateTime;
    }
}

