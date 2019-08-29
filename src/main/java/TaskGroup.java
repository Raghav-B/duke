import Task.*;
import java.util.Vector;

public class TaskGroup {
    private Vector<Task> taskList;

    TaskGroup() {
        taskList = new Vector<Task>();
    }

    public void addListItem(String itemType, String itemToAdd) {
        Task curTask = new Task("[✗]", itemToAdd);

        if (itemType.equals("todo")) {
            curTask = new ToDo("[✗]", itemToAdd);
        } else if (itemType.equals("deadline")) {
            curTask = new Deadline("[✗]", itemToAdd);
        } else {
            curTask = new Event("[✗]", itemToAdd);
        }

        taskList.add(curTask);
    }

    public Task getListItem(int itemIndex) {
        return taskList.get(itemIndex);
    }

    public int getListLength() {
        return taskList.size();
    }

    public void clearList() {
        taskList.clear();
    }

    public void markItemComplete(int listItemNumber) {
        listItemNumber -= 1;
        taskList.get(listItemNumber).setStatus("[✓]");
    }
}
