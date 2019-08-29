import java.util.Scanner;

public class InputHandler {
    public static String[] getInput() {
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine();

        String[] inputArr = input.split(" ", 0);
        if (inputArr[0].equals("done")) {
            if (inputArr.length == 2) {
                // In this case this is a command
                try {
                    int testNum = Integer.parseInt(inputArr[1]);
                } catch (NumberFormatException e) {
                    inputArr[0] = restoreString(inputArr);
                }
            } else {
                inputArr[0] = restoreString(inputArr);
            }
        } else {
            inputArr[0] = restoreString(inputArr);
        }

        return inputArr;
    }

    private static String restoreString(String[] stringArr) {
        String resultString = "";
        for (int i = 0; i < stringArr.length - 1; i++) {
            resultString += (stringArr[i] + " ");
        }
        resultString += stringArr[stringArr.length - 1];

        return resultString;
    }
}
