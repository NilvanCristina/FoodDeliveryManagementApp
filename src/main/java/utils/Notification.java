package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Notification {
    private static final String FILE_NAME = "Notification.txt";

    public static void writeMessage(String message) {
        File file = new File(FILE_NAME);
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(message);
            myWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String readMessage() {
        String content = "";

        try {
            File file = new File(FILE_NAME);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                content = content.concat(myReader.nextLine());
                content = content.concat("\n");
            }

            myReader.close();
        } catch (FileNotFoundException notFoundException) {
            notFoundException.printStackTrace();
        }

        return content;
    }
}
