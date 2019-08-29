import java.util.Vector;

public class TaskHandler {
    private Vector<Task> taskList;

    TaskHandler() {
        taskList = new Vector();
    }

    public void addListItem(String itemToAdd) {
        Task curTask = new Task("[✗]", itemToAdd);
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
