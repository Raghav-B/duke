import DukeException.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static void incompleteCommandCheck(String[] inputArr) throws IncompleteCommandException {
        if (inputArr.length == 1) {
            throw new IncompleteCommandException("'" + inputArr[0].toLowerCase() +
                    "' command requires 1 or more arguments.");
        }
    }

    public static void unknownInput(String input) throws UnknownCommandException {
        throw new UnknownCommandException("Apologies, but '" + input + "' is an invalid input");
    }




    private static String taskInputParse(String[] inputArr) throws UnknownDateTimeFormatException, IncompleteListEntryException {
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
