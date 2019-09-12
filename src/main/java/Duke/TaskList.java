package Duke;

import Task.*;
import java.time.LocalDateTime;
import java.util.*;

public class TaskList {
    private ArrayList<Task> taskListInternal;

    public TaskList() {
        taskListInternal = new ArrayList<Task>();
    }

    /**
     * Adds a new Task to the internal ArrayList.
     * @param itemType Type of item, i.e. 'todo', 'deadline', 'event'
     * @param itemStatus Tick or cross string to represent task completion status.
     * @param description Description of task.
     * @param dateTime LocalDateTime object with information on deadline of task.
     */
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

    /**
     * Returns Task object at desired index.
     * @param itemIndex 0-based index of Task to get.
     * @return Requested Task object.
     */
    public Task getListItem(int itemIndex) {
        return taskListInternal.get(itemIndex);
    }

    /**
     * Gets length of internal ArrayList.
     * @return Length of internal ArrayList.
     */
    public int getListLength() {
        return taskListInternal.size();
    }

    /**
     * Empties internal ArrayList.
     */
    public void clearList() {
        taskListInternal.clear();
    }

    /**
     * Marks Task at input index as complete.
     * @param listItemNumber 1-based index of Task to mark complete.
     */
    public void markItemComplete(int listItemNumber) {
        listItemNumber -= 1;
        taskListInternal.get(listItemNumber).setStatus("[✓]");
    }

    /**
     * Searches through internal ArrayList and returns a new TaskList object with all Tasks
     * that match a query String.
     * @param query String to search for in internal ArrayList.
     * @return TaskList containing matching Tasks from original ArrayList.
     */
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

    /**
     * Deletes Task from particular index from internal ArrayList.
     * @param listItemNumber 1-based index of Task to delete.
     */
    public void deleteItem(int listItemNumber) {
        listItemNumber -= 1;
        taskListInternal.remove(listItemNumber);
    }
}
