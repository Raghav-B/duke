import Task.*;
import java.time.LocalDateTime;
import java.util.*;

public class TaskList {
    private ArrayList<Task> taskListInternal;

    public TaskList() {
        taskListInternal = new ArrayList<Task>();
    }

    public void addListItem(String itemType, String itemStatus, String description, LocalDateTime dateTime) {
        Task curTask = new Task(itemStatus, description, dateTime);

        if (itemType.equals("todo")) {
            curTask = new ToDo(itemStatus, description, dateTime);
        } else if (itemType.equals("deadline")) {
            curTask = new Deadline(itemStatus, description, dateTime);
        } else if (itemType.equals(("event"))) {
            curTask = new Event(itemStatus, description, dateTime);
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

    public void markItemComplete(int listItemNumber) {
        listItemNumber -= 1;
        taskListInternal.get(listItemNumber).setStatus("[✓]");
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

                    searchList.addListItem(tempTaskType, getListItem(i).getStatus(),
                            getListItem(i).getDescription(), getListItem(i).getDateTime());
                } catch(Exception e) {
                    System.out.println("Unhandled exception oops");
                }
            }
        }

        return searchList;
    }

    public void deleteItem(int listItemNumber) {
        listItemNumber -= 1;
        taskListInternal.remove(listItemNumber);
    }
}
