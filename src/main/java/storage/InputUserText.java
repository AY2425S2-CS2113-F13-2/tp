package storage;

import exceptions.FileProcessingException;
import util.FileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputUserText {

    private String text;
    private Scanner scanner;

    public void parseText() {
        List<String> list = inputText();
        text = ""; // Initialize text to avoid null pointer exception
        for (String line : list) {
            text += line;
        }
        String[] sentences = text.split("[.!?]");
        String filepath = "data/user_text/inputs.txt";
        try (FileWriter fileWriter = new FileWriter(filepath, false)) { // Overwrite the file
            for (String sentence : sentences) {
                fileWriter.write(sentence + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> inputText() {
        scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String filepath = "data/user_text/inputs.txt";
        createDirectoryIfNotExists("data/user_text");

        System.out.println("Enter text (type 'exit' to finish):");
        try (FileWriter fileWriter = new FileWriter(filepath, false)) { // Overwrite the file
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                list.add(line);
                fileWriter.write(line + System.lineSeparator());
            }
            System.out.println("Custom text saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}