package Command;
import Duke.*;

public class FindCommand extends Command {
    String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    public void execute(TaskList curList) {
        TaskList searchList = curList.search(searchString);

        if (searchList.getListLength() == 0) {
            Ui.printOutput("I have found no matching tasks in your list.\n");
        }

        else {
            String tempString = "I have found " + searchList.getListLength() + " matching tasks in your list.\n";
            for (int i = 0; i < searchList.getListLength(); i++) {
                tempString += ((i + 1) + ". " + searchList.getListItem(i).getTaskType() +
                        searchList.getListItem(i).getStatus() +
                        " " + searchList.getListItem(i).getDescription() +
                        " by " + searchList.getListItem(i).getDateTimeString() + "\n");
            }
            Ui.printOutput(tempString);
        }
    }
}
