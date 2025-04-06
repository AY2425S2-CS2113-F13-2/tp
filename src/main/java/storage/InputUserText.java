package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ui.Ui;

public class InputUserText {

    private final Ui ui;
    private Scanner scanner;

    public InputUserText(Ui ui) {
        this.ui = ui;
    }

    public List<String> inputText() {
        scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        String filepath = "data/user_text/inputs.txt";
        createDirectoryIfNotExists("data/user_text");

        ui.showInputUserText();
        try (FileWriter fileWriter = new FileWriter(filepath, false)) { // Overwrite the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                list.add(line);
                fileWriter.write(line + System.lineSeparator());
            }
            ui.showTextSaved();
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
