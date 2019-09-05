import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    private static String defaultSavePath = "D:/App Development/CS2113T_duke/duke/task_list.txt";

    public static void saveList(TaskList listToSave) throws Exception {
        FileWriter fw = new FileWriter(defaultSavePath);

        String stringToWrite = "";
        for (int i = 0; i < listToSave.getListLength(); i++) {
            stringToWrite += (listToSave.getListItem(i).getTaskType() + " | " + listToSave.getListItem(i).getStatus() + " | " +
                    listToSave.getListItem(i).getDescription() + "\n");
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

                readTaskList.addListItemBase(newReadLineArr, itemStatus);
            }
        } catch(Exception e) {
            FileWriter fw = new FileWriter(defaultSavePath);
            fw.close();
        }

        return readTaskList;
    }
}
