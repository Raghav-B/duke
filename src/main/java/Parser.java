import DukeException.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static void incompleteCommandCheck(String[] inputArr) throws IncompleteCommandException {
        if (inputArr.length == 1) {
            throw new IncompleteCommandException("'" + inputArr[0].toLowerCase() +
                    "' command requires 1 or more arguments.");
        }
    }

    public static void unknownInput(String input) throws UnknownCommandException {
        throw new UnknownCommandException("Apologies, but '" + input + "' is an invalid input");
    }

    public static String descriptionParse(String[] inputArr) throws IncompleteListEntryException {
        String description = "";
        int index = 0;
        while (!inputArr[index].equals("/by")) {
            if (index >= inputArr.length) {
                throw new IncompleteListEntryException("Incomplete list entry. Entry should be of format <task> /by " +
                        "dd/mm/yyyy hhmm");
            }
            if (index != 0) {
                description += " ";
            }
            description += inputArr[index];
            index++;
        }
        return description;
    }

    public static LocalDateTime dateTimeParse(String[] inputArr) throws UnknownDateTimeFormatException,
            IncompleteListEntryException {
        LocalDateTime dateTime;
        boolean dateTimeFound = false;
        int index = 0;

        for (index = 0; index < inputArr.length; index++) {
            if (inputArr[index].equals("/by")) {
                index++;
                dateTimeFound = true;
                break;
            }
        }

        if (dateTimeFound) {
            if (inputArr.length - index == 2) {
                try {
                    dateTime = LocalDateTime.parse(inputArr[index] + " " + inputArr[index + 1],
                            dukeDateTimeFormat);
                } catch(Exception e) {
                    throw new UnknownDateTimeFormatException("Unknown DateTime parameters entered. Please ensure they " +
                            "follow this format: 'dd/mm/yyyy hhmm'");
                }
            } else {
                throw new UnknownDateTimeFormatException("Unknown DateTime parameters entered. Please ensure they " +
                        "follow this format: 'dd/mm/yyyy hhmm'");
            }
        } else {
            throw new IncompleteListEntryException("Incomplete list entry. Entry should be of format <task> /by " +
                    "dd/mm/yyyy hhmm");
        }

        return dateTime;
    }


}
