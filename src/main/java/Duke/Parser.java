package Duke;
import Command.*;
import DukeException.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {
    private static DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");


    public static Command parseInput(String input) throws IncompleteCommandException,
            UnknownCommandException, IncompleteListEntryException, UnknownDateTimeFormatException,
            NumberFormatException {
        String[] inputArr = input.split(" ", 0);

        if (inputArr[0].toLowerCase().equals("bye")) {
            return new ExitCommand();
        }

        switch (inputArr[0].toLowerCase()) {
            case "bye":
                return new ExitCommand();

            case "todo":
            case "deadline":
            case "event":
                incompleteCommandCheck(inputArr);
                String itemType = inputArr[0].toLowerCase();
                String itemStatus = "[✗]";
                String description = descriptionParse(Arrays.copyOfRange(inputArr, 1, inputArr.length));
                LocalDateTime dateTime = dateTimeParse(Arrays.copyOfRange(inputArr, 1, inputArr.length));
                return new AddCommand(itemType, itemStatus, description, dateTime);

            case "done":
                incompleteCommandCheck(inputArr);
                int listNumCompleted;
                try {
                    listNumCompleted = Integer.parseInt(inputArr[1]);
                } catch(NumberFormatException e) {
                    throw new NumberFormatException("'done' command's argument must be a numerical value.");
                }
                return new DoneCommand(listNumCompleted);

            case "find":
                incompleteCommandCheck(inputArr);
                return new FindCommand(String.join(" ",
                        Arrays.copyOfRange(inputArr, 1, inputArr.length)));

            case "delete":
                incompleteCommandCheck(inputArr);
                int listNumToDelete;
                try {
                    listNumToDelete = Integer.parseInt(inputArr[1]);
                } catch(NumberFormatException e) {
                    throw new NumberFormatException("'delete' command's argument must be a numerical value.");
                }
                return new DeleteCommand(listNumToDelete);

            case "clear":
                return new ClearCommand();

            case "list":
                return new ListCommand();

            default:
                unknownInput(input);
                return new Command();
        }
    }

    /**
     * Checks whether command inputted by user is incomplete, i.e. if it is missing any required arguments.
     * @param inputArr Array of words entered by user. Precondition in this case is that inputArr.length = 1
     * @throws IncompleteCommandException When an incomplete command is input without further arguments, e.g. 'todo' or 'done'
     */
    private static void incompleteCommandCheck(String[] inputArr) throws IncompleteCommandException {
        if (inputArr.length == 1) {
            throw new IncompleteCommandException("'" + inputArr[0].toLowerCase() +
                    "' command requires 1 or more arguments.");
        }
    }

    /**
     *
     * @param input
     * @throws UnknownCommandException
     */
    private static void unknownInput(String input) throws UnknownCommandException {
        throw new UnknownCommandException("Apologies, but '" + input + "' is an invalid input");
    }

    public static String descriptionParse(String[] inputArr) throws IncompleteListEntryException {
        String description = "";
        int index = 0;
        while (!inputArr[index].equals("/by")) {
            if (index != 0) {
                description += " ";
            }
            description += inputArr[index];

            index++;
            if (index >= inputArr.length) {
                throw new IncompleteListEntryException("Incomplete list entry. Entry should be of format <task> /by " +
                        "dd/mm/yyyy hhmm");
            }
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
