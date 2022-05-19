package data;

import java.io.FileWriter;
import java.io.IOException;

public class Write {
    private String content;
    private String fileName;

    public Write(String fileName, String content) {
        this.content = content;
        this.fileName = fileName;
        writeOutput();
    }

    private void writeOutput() {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
