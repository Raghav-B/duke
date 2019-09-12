package Duke;
import java.util.Scanner;
import Command.*;

public class Duke {
    public static void main(String[] args) {
        System.out.print(Ui.logo + "\n");
        Ui.printOutput("Greetings, I am Duke.Duke!\nHow may I be of assistance?\n");

        mainLoop();
    }

    /**
     * The actual execution flow of Duke.Duke. Runs infinitely till 'bye' command is inputted.
     */
    private static void mainLoop() {
        Scanner myScanner = new Scanner(System.in);
        TaskList curList = new TaskList();

        try {
            curList = Storage.loadList();
        } catch(Exception e) {
            Ui.printOutput(e.getMessage());
        }

        while (true) {
            String input = myScanner.nextLine();

            try {
                Command c = Parser.parseInput(input);
                c.execute(curList);
                if (c instanceof ExitCommand) {
                    break;
                }
            }

            catch(Exception e) {
                Ui.printOutput(e.getMessage());
            }
        }
    }
}
