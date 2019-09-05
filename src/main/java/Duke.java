import java.util.Arrays;
import java.util.Scanner;
import DukeException.*;
import Task.*;

public class Duke {
    public static void main(String[] args) {
        System.out.print(Ui.logo + "\n");
        Ui.printOutput("Greetings, I am Duke!\nHow may I be of assistance?\n");

        mainLoop();
    }

    private static void mainLoop() {
        Scanner myScanner = new Scanner(System.in);
        TaskList curList = new TaskList();
        try {
            curList = Storage.loadList();
        } catch(Exception e) {
            Ui.printOutput(e.getMessage());
        }

        while (true) {
            try {
                String input = myScanner.nextLine();

                if (input.toLowerCase().equals("bye")) {
                    Ui.quitFunc();
                    break;
                }
                String[] inputArr = input.split(" ", 0);

                switch (inputArr[0].toLowerCase()) {
                    case "todo":
                    case "deadline":
                    case "event": {
                        Parser.incompleteCommandCheck()(inputArr);
                        try {
                            curList.addListItem(inputArr, "[✗]");
                            Storage.saveList(curList);
                        } catch(Exception e) {
                            Ui.printOutput(e.getMessage());
                        }
                        Ui.printOutput("Understood. I have added the following task to the list:\n" +
                                Ui.addIndent(4) + curList.getListItem(curList.getListLength() - 1).getTaskType() +
                                curList.getListItem(curList.getListLength() - 1).getStatus() + " " +
                                curList.getListItem(curList.getListLength() - 1).getDescription() + "\n" +
                                "Now you have " + curList.getListLength() + " task(s) in your list.\n");
                        break;
                    }

                    case "list": {
                        if (curList.getListLength() == 0) {
                            Ui.printOutput("Your task list is currently empty.\n");
                        } else {
                            String tempString = "Now listing items in your task list:\n";
                            for (int i = 1; i < curList.getListLength() + 1; i++) {
                                tempString += (i + ". " + curList.getListItem(i - 1).getTaskType() +
                                        curList.getListItem(i - 1).getStatus() +
                                        " " + curList.getListItem(i - 1).getDescription() + "\n");
                            }
                            Ui.printOutput(tempString);
                        }
                        break;
                    }

                    case "clear": {
                        int tempListLength = curList.getListLength();
                        curList.clearList();
                        Ui.printOutput("I have cleared your task list of " +
                                tempListLength + " items.\n");
                        break;
                    }

                    case "done": {
                        if (inputArr.length == 1) {
                            Ui.printOutput("Please input a task number to mark as complete.\n");
                        } else {
                            try {
                                int listNumCompleted = Integer.parseInt(inputArr[1]);

                                if (curList.getListLength() == 0) {
                                    Ui.printOutput("There are no tasks to complete.\n");
                                } else if (listNumCompleted > curList.getListLength()) {
                                    Ui.printOutput("My apologies. There is no task numbered " + listNumCompleted +
                                            " in your task list.\n");
                                } else {
                                    curList.markItemComplete(listNumCompleted);
                                    try {
                                        Storage.saveList(curList);
                                    } catch(Exception e) {
                                        Ui.printOutput(e.getMessage());
                                    }
                                    Ui.printOutput("Splendid. I have marked the following task as completed:\n" +
                                            Ui.addIndent(4) + curList.getListItem(listNumCompleted - 1).getTaskType() +
                                            curList.getListItem(listNumCompleted - 1).getStatus() +
                                            " " + curList.getListItem(listNumCompleted - 1).getDescription() + "\n");
                                }
                            } catch(NumberFormatException e) {
                                throw new NumberFormatException("'done' command's argument must be a numerical value.");
                            }
                        }
                        break;
                    }

                    case "find": {
                        incompleteCommandCheck(inputArr);
                        TaskList searchList = curList.search(String.join(" ",
                                Arrays.copyOfRange(inputArr, 1, inputArr.length)));

                        if (searchList.getListLength() == 0) {
                            Ui.printOutput("I have found no matching tasks in your list.\n");
                        } else {
                            String tempString = "I have found " + searchList.getListLength() + " matching tasks in your list.\n";
                            for (int i = 0; i < searchList.getListLength(); i++) {
                                tempString += ((i + 1) + ". " + searchList.getListItem(i).getTaskType() +
                                        searchList.getListItem(i).getStatus() +
                                        " " + searchList.getListItem(i).getDescription() + "\n");
                            }
                            Ui.printOutput(tempString);
                        }

                        break;
                    }

                    case "delete": {
                        if (inputArr.length == 1) {
                            Ui.printOutput("Please input a task number to delete.\n");
                        } else {
                            try {
                                int listNumToDelete = Integer.parseInt(inputArr[1]);

                                if (curList.getListLength() == 0) {
                                    Ui.printOutput("There are no tasks to delete.\n");
                                } else if (listNumToDelete > curList.getListLength()) {
                                    Ui.printOutput("My apologies. There is no task numbered " + listNumToDelete +
                                            " in your task list.\n");
                                } else {
                                    Task itemToDelete = curList.getListItem(listNumToDelete - 1);
                                    curList.deleteItem(listNumToDelete);
                                    Ui.printOutput("As requested, I have deleted the following task:\n" +
                                            Ui.addIndent(4) + itemToDelete.getTaskType() +
                                            itemToDelete.getStatus() +
                                            " " + itemToDelete.getDescription() + "\n" +
                                            "Now you have " + curList.getListLength() + " task(s) in your list.\n");
                                    try {
                                        Storage.saveList(curList);
                                    } catch(Exception e) {
                                        Ui.printOutput(e.getMessage());
                                    }
                                }
                            } catch(NumberFormatException e) {
                                throw new NumberFormatException("'delete' command's argument must be a numerical value.");
                            }
                        }
                        break;
                    }

                    default: {
                        unknownInput(input);
                    }
                }
            } catch(IncompleteCommandException | UnknownCommandException | NumberFormatException b) {
                Ui.printOutput(b.getMessage());
            }
        }
    }
}
