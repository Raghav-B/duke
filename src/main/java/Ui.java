public class Ui {
    public static String logo = "____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printOutput(String stringToOutput) {
        System.out.println(addIndent() + "____________________________________________________________");

        String[] linesToPrint = stringToOutput.split("\n", 0);
        for (int i = 0; i < linesToPrint.length; i++) {
            System.out.println(addIndent() + linesToPrint[i]);
        }

        System.out.println(addIndent() + "____________________________________________________________\n");
    }
    public static String addIndent() {
        return "        ";
    }
    public static String addIndent(int indentSize) {
        String indent = "";
        for (int i = 0; i < indentSize; i++) {
            indent += " ";
        }
        return indent;
    }

    public static void quitFunc() {
        printOutput("Farewell. I do hope to see you again.\n");
    }
    public static void quitFunc(String optionalOutput) {
        printOutput(optionalOutput);
        printOutput("Farewell. I do hope to see you again.");
    }
}
