package Duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private static String defaultSavePath = "task_list.txt";

    public static void saveList(TaskList listToSave) throws Exception {
        FileWriter fw = new FileWriter(defaultSavePath);

        String stringToWrite = "";
        for (int i = 0; i < listToSave.getListLength(); i++) {
            stringToWrite += (listToSave.getListItem(i).getTaskType() + " | " + listToSave.getListItem(i).getStatus() + " | " +
                    listToSave.getListItem(i).getDescription() + " /by " + listToSave.getListItem(i).getDateTimeSaveString() + "\n");
        }

        fw.write(stringToWrite);
        fw.close();
    }

    public static TaskList loadList() throws Exception {
        TaskList readTaskList = new TaskList();

        try {
            File f = new File(defaultSavePath);
            BufferedReader b = new BufferedReader(new FileReader(f));

            while (true) {
                String readLine = b.readLine();
                if (readLine == null) {
                    break;
                }

                String[] readLineArr = readLine.split(".\\|.| ", 0);
                String itemType = "";
                String itemStatus = readLineArr[1];
                String description = Parser.descriptionParse(Arrays.copyOfRange(readLineArr, 2, readLineArr.length));
                LocalDateTime dateTime = Parser.dateTimeParse(Arrays.copyOfRange(readLineArr, 2, readLineArr.length));
                if (readLineArr[0].equals("[T]")) {
                    itemType = "todo";
                } else if (readLineArr[0].equals("[D]")) {
                    itemType = "deadline";
                } else if (readLineArr[0].equals(("[E]"))) {
                    itemType = "event";
                }

                readTaskList.addListItem(itemType, itemStatus, description, dateTime);
            }
        } catch(Exception e) {
            FileWriter fw = new FileWriter(defaultSavePath);
            fw.close();
        }

        return readTaskList;
    }
}
