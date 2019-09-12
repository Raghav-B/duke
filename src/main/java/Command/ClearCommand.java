package Command;
import Duke.*;

public class ClearCommand extends Command {
    public ClearCommand() {

    }

    public void execute(TaskList curList) {
        int tempListLength = curList.getListLength();
        curList.clearList();
        Ui.printOutput("I have cleared your task list of " +
                tempListLength + " items.\n");
    }
}
