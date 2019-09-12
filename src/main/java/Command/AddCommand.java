package Command;
import Duke.*;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    String itemType;
    String itemStatus;
    String description;
    LocalDateTime dateTime;

    public AddCommand(String itemType, String itemStatus, String description, LocalDateTime dateTime) {
        this.itemType = itemType;
        this.itemStatus = itemStatus;
        this.description = description;
        this.dateTime = dateTime;
    }

    public void execute(TaskList curList) throws Exception {
        curList.addListItem(itemType, itemStatus, description, dateTime);
        Storage.saveList(curList);
        Ui.printOutput("Understood. I have added the following task to the list:\n" +
                Ui.addIndent(4) + curList.getListItem(curList.getListLength() - 1).getTaskType() +
                curList.getListItem(curList.getListLength() - 1).getStatus() + " " +
                curList.getListItem(curList.getListLength() - 1).getDescription() +
                " by " + curList.getListItem(curList.getListLength() - 1).getDateTimeString() + "\n" +
                "Now you have " + curList.getListLength() + " task(s) in your list.\n");
    }
}
