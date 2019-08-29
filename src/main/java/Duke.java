import java.util.Scanner;

public class Duke {
    private static String logo = "____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.print(logo + "\n");
        printOutput("Greetings, I am Duke!\nHow may I be of assistance?\n");

        //InputHandler inputReq = new InputHandler();
        Scanner myScanner = new Scanner(System.in);
        TaskGroup curList = new TaskGroup();

        while (true) {
            String input = myScanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                quitFunc();
                break;
            }

            // Error handling
            //if (input.equals("")) {
            //}

            String[] inputArr = input.split(" ", 0);

            switch (inputArr[0].toLowerCase()) {
                case "todo": {
                    String listItem = taskInputParse(inputArr);
                    curList.addListItem("todo", listItem);
                    printOutput("Understood. I have added the following task to the list:\n" +
                            addIndent(4) + curList.getListItem(curList.getListLength() - 1).getTaskType() +
                            curList.getListItem(curList.getListLength() - 1).getStatus() + " " +
                            curList.getListItem(curList.getListLength() - 1).getDescription() + "\n" +
                            "Now you have " + curList.getListLength() + " in your list.\n");
                    break;
                }
                case "deadline": {
                    String listItem = taskInputParse(inputArr);
                    curList.addListItem("deadline", listItem);
                    printOutput("Understood. I have added the following task to the list:\n" +
                            addIndent(4) + curList.getListItem(curList.getListLength() - 1).getTaskType() +
                            curList.getListItem(curList.getListLength() - 1).getStatus() + " " +
                            curList.getListItem(curList.getListLength() - 1).getDescription() + "\n" +
                            "Now you have " + curList.getListLength() + " in your list.\n");
                    break;
                }
                case "event": {
                    String listItem = taskInputParse(inputArr);
                    curList.addListItem("event", listItem);
                    printOutput("Understood. I have added the following task to the list:\n" +
                            addIndent(4) + curList.getListItem(curList.getListLength() - 1).getTaskType() +
                            curList.getListItem(curList.getListLength() - 1).getStatus() + " " +
                            curList.getListItem(curList.getListLength() - 1).getDescription() + "\n" +
                            "Now you have " + curList.getListLength() + " in your list.\n");
                    break;
                }
                case "list":
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
                case "clear":
                    int tempListLength = curList.getListLength();
                    curList.clearList();
                    printOutput("I have cleared your task list of " +
                            tempListLength + " items.\n");
                    break;
                case "done":
                    if (inputArr.length == 1) {
                        printOutput("Please input a task number to mark as complete.\n");
                    } else {
                        int listNumCompleted = Integer.parseInt(inputArr[1]);

                        if (curList.getListLength() == 0) {
                            printOutput("There are no tasks to complete.\n");
                        } else if (listNumCompleted > curList.getListLength()) {
                            printOutput("My apologies. There is no task numbered " + listNumCompleted +
                                    " in your task list.\n");
                        } else {
                            curList.markItemComplete(listNumCompleted);
                            printOutput("Splendid. I have marked the following task as completed:\n" +
                                    addIndent(4) + curList.getListItem(listNumCompleted - 1).getStatus() +
                                    " " + curList.getListItem(listNumCompleted - 1).getDescription() + "\n");
                        }
                    }
                    break;
            }
        }
    }

    private static String taskInputParse(String[] inputArr) {
        int index = 1;
        String action = "";
        String dateTime = "(by: ";
        boolean dateTimeFound = false;

        for (; index < inputArr.length - 1; index++) {
            if (inputArr[index].equals("/by")) {
                index += 1;
                dateTimeFound = true;
                break;
            } else {
                action += (inputArr[index] + " ");
            }
        }
        //action += inputArr[index];

        if (dateTimeFound) {
            for (; index < inputArr.length - 1; index++) {
                dateTime += (inputArr[index] + " ");
            }
        }
        dateTime += (inputArr[inputArr.length - 1] + ")");

        return action + dateTime;
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
