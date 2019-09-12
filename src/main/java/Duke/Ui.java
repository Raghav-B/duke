package Duke;

public class Ui {
    public static String logo = "____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Simple print function that handles indentation and divider lines while printing desired output.
     * @param stringToOutput String to print in UI.
     */
    public static void printOutput(String stringToOutput) {
        System.out.println(addIndent() + "____________________________________________________________");

        String[] linesToPrint = stringToOutput.split("\n", 0);
        for (int i = 0; i < linesToPrint.length; i++) {
            System.out.println(addIndent() + linesToPrint[i]);
        }

        System.out.println(addIndent() + "____________________________________________________________\n");
    }

    /**
     * Returns an 8 character indent string.
     * @return Empty indented String.
     */
    public static String addIndent() {
        return "        ";
    }

    /**
     * Returns a custom indent character string.
     * @param indentSize Size of indentation.
     * @return Empty indented String.
     */
    public static String addIndent(int indentSize) {
        String indent = "";
        for (int i = 0; i < indentSize; i++) {
            indent += " ";
        }
        return indent;
    }

    /**
     * Simple quit output function.
     */
    public static void quitFunc() {
        printOutput("Farewell. I do hope to see you again.\n");
    }

    /**
     * Quit output function with custom message.
     * @param optionalOutput Custom message to output.
     */
    public static void quitFunc(String optionalOutput) {
        printOutput(optionalOutput);
        printOutput("Farewell. I do hope to see you again.");
    }
}
