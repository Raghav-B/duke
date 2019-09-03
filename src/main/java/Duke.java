import java.util.Scanner;
import DukeException.*;

public class Duke {
    private static String logo = "____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.print(logo + "\n");
        printOutput("Greetings, I am Duke!\nHow may I be of assistance?\n");

        mainLoop();
    }

    private static void mainLoop() {
        Scanner myScanner = new Scanner(System.in);
        TaskGroup curList = new TaskGroup();
        try {
            curList.loadList();
        } catch(Exception e) {
            printOutput(e.getMessage());
        }

        while (true) {
            try {
                String input = myScanner.nextLine();

                if (input.toLowerCase().equals("bye")) {
                    quitFunc();
                    break;
                }
                String[] inputArr = input.split(" ", 0);

                switch (inputArr[0].toLowerCase()) {
                    case "todo":
                    case "deadline":
                    case "event": {
                        incompleteCommandCheck(inputArr);
                        //String listItem = taskInputParse(inputArr);
                        try {
                            curList.addListItem(inputArr, "[✗]");
                        } catch(Exception e) {
                            printOutput(e.getMessage());
                        }
                        printOutput("Understood. I have added the following task to the list:\n" +
                                addIndent(4) + curList.getListItem(curList.getListLength() - 1).getTaskType() +
                                curList.getListItem(curList.getListLength() - 1).getStatus() + " " +
                                curList.getListItem(curList.getListLength() - 1).getDescription() + "\n" +
                                "Now you have " + curList.getListLength() + " tasks in your list.\n");
                        break;
                    }

                    case "list": {
                        if (curList.getListLength() == 0) {
                            printOutput("Your task list is currently empty.\n");
                        } else {
                            String tempString = "Now listing items in your task list:\n";
                            for (int i = 1; i < curList.getListLength() + 1; i++) {
                                tempString += (i + ". " + curList.getListItem(i - 1).getTaskType() +
                                        curList.getListItem(i - 1).getStatus() +
                                        " " + curList.getListItem(i - 1).getDescription() + "\n");
                            }
                            printOutput(tempString);
                        }
                        break;
                    }

                    case "clear": {
                        int tempListLength = curList.getListLength();
                        curList.clearList();
                        printOutput("I have cleared your task list of " +
                                tempListLength + " items.\n");
                        break;
                    }

                    case "done": {
                        if (inputArr.length == 1) {
                            printOutput("Please input a task number to mark as complete.\n");
                        } else {
                            try {
                                int listNumCompleted = Integer.parseInt(inputArr[1]);

                                if (curList.getListLength() == 0) {
                                    printOutput("There are no tasks to complete.\n");
                                } else if (listNumCompleted > curList.getListLength()) {
                                    printOutput("My apologies. There is no task numbered " + listNumCompleted +
                                            " in your task list.\n");
                                } else {
                                    try {
                                        curList.markItemComplete(listNumCompleted);
                                    } catch(Exception e) {
                                        printOutput(e.getMessage());
                                    }
                                    printOutput("Splendid. I have marked the following task as completed:\n" +
                                            addIndent(4) + curList.getListItem(listNumCompleted - 1).getTaskType() +
                                            curList.getListItem(listNumCompleted - 1).getStatus() +
                                            " " + curList.getListItem(listNumCompleted - 1).getDescription() + "\n");
                                }
                            } catch(NumberFormatException e) {
                                throw new NumberFormatException("'done' command's argument must be a numerical value.");
                            }
                        }
                        break;
                    }

                    default: {
                        unknownInput(input);
                    }
                }
            } catch(IncompleteCommandException | UnknownInputException | NumberFormatException b) {
                printOutput(b.getMessage());
            }
        }
    }

    private static void incompleteCommandCheck(String[] inputArr) throws IncompleteCommandException {
        if (inputArr.length == 1) {
            throw new IncompleteCommandException("'" + inputArr[0].toLowerCase() +
                    "' command requires 1 or more arguments.");
        }
    }

    private static void unknownInput(String input) throws UnknownInputException {
        throw new UnknownInputException("Apologies, but '" + input + "' is an invalid input");
    }

    private static void printOutput(String stringToOutput) {
        System.out.println(addIndent() + "____________________________________________________________");

        String[] linesToPrint = stringToOutput.split("\n", 0);
        for (int i = 0; i < linesToPrint.length; i++) {
            System.out.println(addIndent() + linesToPrint[i]);
        }

        System.out.println(addIndent() + "____________________________________________________________\n");
    }
    private static String addIndent() {
        return "        ";
    }
    private static String addIndent(int indentSize) {
        String indent = "";
        for (int i = 0; i < indentSize; i++) {
            indent += " ";
        }
        return indent;
    }

    private static void quitFunc() {
        printOutput("Farewell. I do hope to see you again.\n");
    }
    private static void quitFunc(String optionalOutput) {
        printOutput(optionalOutput);
        printOutput("Farewell. I do hope to see you again.");
    }
}
