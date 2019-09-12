package Command;
import Duke.*;

public class DoneCommand extends Command {
    int listNumCompleted;

    public DoneCommand(int listNumCompleted) {
        this.listNumCompleted = listNumCompleted;
    }

    public void execute(TaskList curList) throws Exception {
        if (curList.getListLength() == 0) {
            Ui.printOutput("There are no tasks to complete.\n");
        }

        else if (listNumCompleted > curList.getListLength()) {
            Ui.printOutput("My apologies. There is no task numbered " + listNumCompleted +
                    " in your task list.\n");
        }

        else {
            curList.markItemComplete(listNumCompleted);
            Storage.saveList(curList);
            Ui.printOutput("Splendid. I have marked the following task as completed:\n" +
                    Ui.addIndent(4) + curList.getListItem(listNumCompleted - 1).getTaskType() +
                    curList.getListItem(listNumCompleted - 1).getStatus() +
                    " " + curList.getListItem(listNumCompleted - 1).getDescription() +
                    " by " + curList.getListItem(listNumCompleted - 1).getDateTimeString() + "\n");
        }
    }
}
