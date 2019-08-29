import Task.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TaskGroup {
    private Vector<Task> taskList;
    private String defaultSavePath = "./task_list.txt";

    TaskGroup() {
        taskList = new Vector<Task>();
    }

    public void addListItem(String itemType, String itemStatus, String itemToAdd) throws Exception {
        Task curTask = new Task(itemStatus, itemToAdd);

        if (itemType.equals("todo")) {
            curTask = new ToDo(itemStatus, itemToAdd);
        } else if (itemType.equals("deadline")) {
            curTask = new Deadline(itemStatus, itemToAdd);
        } else {
            curTask = new Event(itemStatus, itemToAdd);
        }

        taskList.add(curTask);
        try {
            saveList();
        } catch(Exception e) {
            throw e;
        }
    }
    public void addListItemNoSave(String itemType, String itemStatus, String itemToAdd) {
        Task curTask = new Task(itemStatus, itemToAdd);

        if (itemType.equals("todo")) {
            curTask = new ToDo(itemStatus, itemToAdd);
        } else if (itemType.equals("deadline")) {
            curTask = new Deadline(itemStatus, itemToAdd);
        } else {
            curTask = new Event(itemStatus, itemToAdd);
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

    public void markItemComplete(int listItemNumber) throws Exception {
        listItemNumber -= 1;
        taskList.get(listItemNumber).setStatus("[✓]");
        saveList();
    }

    public void saveList() throws Exception {
        FileWriter fw = new FileWriter(defaultSavePath);

        String stringToWrite = "";
        for (int i = 0; i < getListLength(); i++) {
            stringToWrite += (taskList.get(i).getTaskType() + " | " + taskList.get(i).getStatus() + " | " +
                    taskList.get(i).getDescription() + "\n");
        }

        fw.write(stringToWrite);
        fw.close();
    }

    public void loadList() throws Exception {
        File f = new File(defaultSavePath);
        BufferedReader b = new BufferedReader(new FileReader(f));

        while (true) {
            String readLine = b.readLine();
            if (readLine == null) {
                break;
            }

            String[] readLineArr = readLine.split(".\\|.", 0);
            String itemType = "";
            if (readLineArr.equals("[T]")) {
                itemType = "todo";
            } else if (readLineArr.equals("[D]")) {
                itemType = "deadline";
            } else {
                itemType = "event";
            }

            addListItemNoSave(itemType, readLineArr[1], readLineArr[2]);
        }
    }
}
