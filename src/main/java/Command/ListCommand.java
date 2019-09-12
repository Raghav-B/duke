package Command;
import Duke.*;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public void execute(TaskList curList) {
        if (curList.getListLength() == 0) {
            Ui.printOutput("Your task list is currently empty.\n");
        }

        else {
            String tempString = "Now listing items in your task list:\n";
            for (int i = 1; i < curList.getListLength() + 1; i++) {
                tempString += (i + ". " + curList.getListItem(i - 1).getTaskType() +
                        curList.getListItem(i - 1).getStatus() +
                        " " + curList.getListItem(i - 1).getDescription() +
                        " by " + curList.getListItem(i - 1).getDateTimeString() + "\n");
            }
            Ui.printOutput(tempString);
        }
    }
}
