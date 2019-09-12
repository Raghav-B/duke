package Duke;
import DukeException.*;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;

public class Storage {
    private static String defaultSavePath = "task_list.txt";

    /**
     * Saves the user's current task list into a text file.
     * @param listToSave TaskList object to be saved.
     * @throws IOException When file cannot be written to.
     */
    public static void saveList(TaskList listToSave) throws IOException {
        FileWriter fw = new FileWriter(defaultSavePath);

        String stringToWrite = "";
        for (int i = 0; i < listToSave.getListLength(); i++) {
            stringToWrite += (listToSave.getListItem(i).getTaskType() + " | " + listToSave.getListItem(i).getStatus() + " | " +
                    listToSave.getListItem(i).getDescription() + " /by " + listToSave.getListItem(i).getDateTimeSaveString() + "\n");
        }

        fw.write(stringToWrite);
        fw.close();
    }

    /**
     * Loads a new task list from a previously saved text file.
     * @return TaskList object containing tasks from text file.
     * @throws IOException When file cannot be loaded from/written to.
     * @throws IncompleteListEntryException When text format inside save file is corrupt/unrecognizable.
     * @throws UnknownDateTimeFormatException When text format inside save file is corrupt/unrecognizable.
     */
    public static TaskList loadList() throws IOException, IncompleteListEntryException,
            UnknownDateTimeFormatException  {
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
        } catch(IOException e) {
            FileWriter fw = new FileWriter(defaultSavePath);
            fw.close();
        }

        return readTaskList;
    }
}
