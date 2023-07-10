package patterns.example.service;

import java.io.*;
import java.util.Scanner;

public class FileOperator {

    public static final FileOperator INSTANCE = new FileOperator();

    private FileOperator() {}

    public boolean createFileIfNotExist(String filePath) {
        File file = new File(filePath);
        boolean created = false;

        try {
            created = file.createNewFile();
        }
        catch (IOException exception) {
            System.out.println("Exception Occurred");
        }
        return created;
    }

    public void appendText(String filePath, String text) {
        File file = new File(filePath);
        // to append to file, you need to initialize FileWriter using below constructor
        try (FileWriter fr = new FileWriter(file, true);
             BufferedWriter br = new BufferedWriter(fr);
             PrintWriter pr = new PrintWriter(br)) {
            pr.println(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();

        try (Scanner dataReader = new Scanner(file)) {
            while (dataReader.hasNextLine()) {
                String fileData = dataReader.nextLine();
                sb.append(fileData).append("\n");
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("Exception Occurred");
        }

        return sb.toString();
    }
}
