import DukeException.*;
import Task.*;
import java.util.*;

public class TaskList {
    private ArrayList<Task> taskListInternal;

    TaskList() {
        taskListInternal = new ArrayList<Task>();
    }

    public void addListItem(String[] inputArr, String itemStatus) throws Exception {
        addListItemBase(inputArr, itemStatus);

        //try {
        //    saveList();
        //} catch(Exception e) {
        //    throw e;
        //}
    }

    public void addListItemBase(String[] inputArr, String itemStatus) throws UnknownDateTimeFormatException,
            IncompleteListEntryException {

        Task curTask = new Task(Arrays.copyOfRange(inputArr, 1, inputArr.length, String[].class), itemStatus);

        String taskType = inputArr[0].toLowerCase();
        if (taskType.equals("todo")) {
            curTask = new ToDo(Arrays.copyOfRange(inputArr, 1, inputArr.length, String[].class), itemStatus);
        } else if (taskType.equals("deadline")) {
            curTask = new Deadline(Arrays.copyOfRange(inputArr, 1, inputArr.length, String[].class), itemStatus);
        } else if (taskType.equals(("event"))) {
            curTask = new Event(Arrays.copyOfRange(inputArr, 1, inputArr.length, String[].class), itemStatus);
        }

        taskListInternal.add(curTask);
    }

    public Task getListItem(int itemIndex) {
        return taskListInternal.get(itemIndex);
    }

    public int getListLength() {
        return taskListInternal.size();
    }

    public void clearList() {
        taskListInternal.clear();
    }

    public void markItemComplete(int listItemNumber) {// throws Exception {
        listItemNumber -= 1;
        taskListInternal.get(listItemNumber).setStatus("[✓]");
        //saveList();
    }

    public TaskList search(String query) {
        TaskList searchList = new TaskList();

        for (int i = 0; i < getListLength(); i++) {
            if (getListItem(i).getDescription().contains(query)) {
                try {
                    String tempTaskType = "";
                    if (getListItem(i).getTaskType().equals("[T]")) {
                        tempTaskType = "todo";
                    } else if (getListItem(i).getTaskType().equals("[D]")) {
                        tempTaskType = "deadline";
                    } else if (getListItem(i).getTaskType().equals("[E]")) {
                        tempTaskType = "event";
                    }

                    searchList.addListItemBase((tempTaskType + " " + getListItem(i).getDescription())
                            .split(" ", 0), getListItem(i).getStatus());
                } catch(Exception e) {
                    System.out.println("Unhandled exception oops");
                }
            }
        }

        return searchList;
    }

    public void deleteItem(int listItemNumber) { //throws Exception {
        listItemNumber -= 1;
        taskListInternal.remove(listItemNumber);
        //saveList();
    }
}
