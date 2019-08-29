import Task.*;

import java.io.IOException;
import java.util.Vector;
import java.io.FileWriter;

public class TaskGroup {
    private Vector<Task> taskList;

    TaskGroup() {
        taskList = new Vector<Task>();
    }

    public void addListItem(String itemType, String itemToAdd) throws Exception {
        Task curTask = new Task("[✗]", itemToAdd);

        if (itemType.equals("todo")) {
            curTask = new ToDo("[✗]", itemToAdd);
        } else if (itemType.equals("deadline")) {
            curTask = new Deadline("[✗]", itemToAdd);
        } else {
            curTask = new Event("[✗]", itemToAdd);
        }

        taskList.add(curTask);
        try {
            saveList();
        } catch(Exception e) {
            throw e;
        }
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

    public void saveList() throws Exception {
        FileWriter fw = new FileWriter("./task_list.txt");

        String stringToWrite = "";
        for (int i = 0; i < getListLength(); i++) {
            stringToWrite += (taskList.get(i).getTaskType() + " | " + taskList.get(i).getStatus() + " | " +
                    taskList.get(i).getDescription() + "\n");
        }

        fw.write(stringToWrite);
        fw.close();
    }

    public void loadList() {
        return;
    }
}
