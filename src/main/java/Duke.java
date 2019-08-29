public class Duke {
    private static String logo = addIndent() + "____        _        \n"
        + addIndent() + "|  _ \\ _   _| | _____ \n"
        + addIndent() + "| | | | | | | |/ / _ \\\n"
        + addIndent() + "| |_| | |_| |   <  __/\n"
        + addIndent() + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.print(logo + "\n");
        printOutput(addIndent() + "Greetings, I am Duke!\n" + addIndent()
                + "How may I be of assistance?\n");

        InputHandler inputReq = new InputHandler();
        TaskHandler curList = new TaskHandler();

        while (true) {
            String curInput = inputReq.getInput();
            //System.out.println(curInput);
            //System.out.println(curInput.toLowerCase());
            //System.out.println(curInput);

            if (curInput.toLowerCase().equals("bye")) {
                quitFunc();
                break;
            }

            else if (curInput.toLowerCase().equals("list")) {
                if (curList.getListLength() == 0) {
                    printOutput(addIndent() + "Your task list is currently empty.\n");
                } else {
                    String tempString = addIndent() + "Now listing items in your task list:\n";
                    for (int i = 1; i < curList.getListLength() + 1; i++) {
                        tempString += (addIndent() + i + ". " + curList.getListItem(i - 1) + "\n");
                    }
                    printOutput(tempString);
                }
            }

            else if (curInput.toLowerCase().equals("clear")) {
                int tempListLength = curList.getListLength()
                curList.clearList();
                printOutput(addIndent() + "I have cleared your task list of " +
                         tempListLength + " items.\n");
            }

            else {
                curList.addListItem(curInput);
                printOutput("I have added: '" + curInput + "'.\n");
            }
        }
    }

    private static void printOutput(String stringToOutput) {
        System.out.print(addIndent() + "____________________________________________________________\n");
        System.out.print(stringToOutput);
        System.out.print(addIndent() + "____________________________________________________________\n\n");
    }
    private static String addIndent() {
        return "        ";
    }

    private static void quitFunc() {
        printOutput(addIndent() + "Farewell. I do hope to see you again.\n");
    }

    private static void quitFunc(String optionalOutput) {
        printOutput(optionalOutput);
        printOutput("Farewell. I do hope to see you again.");
    }
}
