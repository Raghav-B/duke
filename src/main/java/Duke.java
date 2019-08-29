public class Duke {
    private static String logo = "         ____        _        \n"
        + "        |  _ \\ _   _| | _____ \n"
        + "        | | | | | | | |/ / _ \\\n"
        + "        | |_| | |_| |   <  __/\n"
        + "        |____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        InputHandler inputReq = new InputHandler();
        System.out.println(logo);
        printOutput("Greetings, I am Duke!\n        How may I be of assistance?");

        while (true) {
            String curInput = inputReq.getInput();

            if (curInput.toLowerCase() == "bye") {
                quitFunc();
                break;
            }

            printOutput(curInput);
        }
    }

    private static void printOutput(String stringToOutput) {
        System.out.println("        ____________________________________________________________");
        System.out.println("        " + stringToOutput);
        System.out.println("        ____________________________________________________________\n");
    }

    private static void quitFunc() {
    }

    private static void quitFunc(String optionalOutput) {
        printOutput(optionalOutput);
        printOutput("Farewell. I do hope to see you again.");
    }
}
