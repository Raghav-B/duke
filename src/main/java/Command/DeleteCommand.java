package Command;
import Duke.*;
import Task.*;

public class DeleteCommand extends Command {
    int listNumToDelete;

    public DeleteCommand(int listNumToDelete) {
        this.listNumToDelete = listNumToDelete;
    }

    public void execute(TaskList curList) throws Exception {
        if (curList.getListLength() == 0) {
            Ui.printOutput("There are no tasks to delete.\n");
        }

        else if (listNumToDelete > curList.getListLength()) {
            Ui.printOutput("My apologies. There is no task numbered " + listNumToDelete +
                    " in your task list.\n");
        }

        else {
            Task itemToDelete = curList.getListItem(listNumToDelete - 1);
            curList.deleteItem(listNumToDelete);
            Ui.printOutput("As requested, I have deleted the following task:\n" +
                    Ui.addIndent(4) + itemToDelete.getTaskType() +
                    itemToDelete.getStatus() +
                    " " + itemToDelete.getDescription() + " by " + itemToDelete.getDateTimeString() + "\n" +
                    "Now you have " + curList.getListLength() + " task(s) in your list.\n");
            Storage.saveList(curList);
        }
    }
}
