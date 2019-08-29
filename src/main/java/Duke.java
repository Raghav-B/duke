public class Duke {
    private static String logo = "____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.print(logo + "\n");
        printOutput("Greetings, I am Duke!\nHow may I be of assistance?\n");

        InputHandler inputReq = new InputHandler();
        TaskHandler curList = new TaskHandler();

        while (true) {
            String[] curInput = inputReq.getInput();

            if (curInput[0].toLowerCase().equals("bye")) {
                quitFunc();
                break;
            }

            else if (curInput[0].toLowerCase().equals("list")) {
                if (curList.getListLength() == 0) {
                    printOutput("Your task list is currently empty.\n");
                } else {
                    String tempString = "Now listing items in your task list:\n";
                    for (int i = 1; i < curList.getListLength() + 1; i++) {
                        tempString += (i + ". " + curList.getListItem(i - 1).getStatus() +
                                " " + curList.getListItem(i - 1).getDescription() + "\n");
                    }
                    printOutput(tempString);
                }
            }

            else if (curInput[0].toLowerCase().equals("clear")) {
                int tempListLength = curList.getListLength();
                curList.clearList();
                printOutput("I have cleared your task list of " +
                         tempListLength + " items.\n");
            }

            else if (curInput[0].toLowerCase().equals("done")) {
                if (curInput.length == 1) {
                    printOutput("Please input a task number to mark as complete.\n");
                }
                else {
                    int listNumCompleted = Integer.parseInt(curInput[1]);

                    if (curList.getListLength() == 0) {
                        printOutput("There are no tasks to complete.\n");
                    } else if (listNumCompleted > curList.getListLength()) {
                        printOutput("My apologies. There is no task numbered " + listNumCompleted +
                                " in your task list.\n");
                    } else {
                        curList.markItemComplete(listNumCompleted);
                        printOutput("Splendid. I have marked the following task as completed:\n    " +
                                curList.getListItem(listNumCompleted - 1).getStatus() + " " +
                                curList.getListItem(listNumCompleted - 1).getDescription() + "\n");
                    }
                }
            }

            else {
                curList.addListItem(curInput[0]);
                printOutput("I have added: '" + curInput[0] + "'.\n");
            }
        }
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

    private static void quitFunc() {
        printOutput("Farewell. I do hope to see you again.\n");
    }

    private static void quitFunc(String optionalOutput) {
        printOutput(optionalOutput);
        printOutput("Farewell. I do hope to see you again.");
    }
}
