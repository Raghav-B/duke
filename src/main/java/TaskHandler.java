import java.util.Vector;

public class TaskHandler {
    private Vector<String> taskList;

    TaskHandler() {
        taskList = new Vector();
    }

    public void addListItem(String itemToAdd) {
        taskList.add(itemToAdd);
    }

    public String getListItem(int itemIndex) {
        return taskList.get(itemIndex);
    }

    public int getListLength() {
        return taskList.size();
    }

    public void clearList() {
        taskList.clear();
    }
}
