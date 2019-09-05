import DukeException.IncompleteListEntryException;
import DukeException.UnknownDateTimeFormatException;
import Task.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class TaskGroup {
    private ArrayList<Task> taskList;
    private String defaultSavePath = "./task_list.txt";

    TaskGroup() {
        taskList = new ArrayList<Task>();
    }

    public void addListItem(String[] inputArr, String itemStatus) throws Exception {
        addListItemBase(inputArr, itemStatus);

        try {
            saveList();
        } catch(Exception e) {
            throw e;
        }
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

    public void deleteItem(int listItemNumber) throws Exception {
        listItemNumber -= 1;
        taskList.remove(listItemNumber);
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

            String[] readLineArr = readLine.split(".\\|.| ", 0);
            if (readLineArr[0].equals("[T]")) {
                readLineArr[0] = "todo";
            } else if (readLineArr[0].equals("[D]")) {
                readLineArr[0] = "deadline";
            } else if (readLineArr[0].equals(("[E]"))) {
                readLineArr[0] = "event";
            }

            String[] newReadLineArr = Arrays.copyOfRange(readLineArr, 1, readLineArr.length, String[].class);

            newReadLineArr[0] = readLineArr[0];
            String itemStatus = readLineArr[1];

            addListItemBase(newReadLineArr, itemStatus);
        }
    }
}
