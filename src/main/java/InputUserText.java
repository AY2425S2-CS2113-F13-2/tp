import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputUserText {

    private String text;
    private Scanner scanner;

    public List<String> outputText() {
        String filepath = "/sample_texts/user_input.txt";
        FileReader fileReader = new FileReader();
        List<String> list = fileReader.readFile(filepath);
        return list;
    }

    // parse the text that the user inputs into sentences
    public void parseText() {
        List<String> list = inputText();
        for (String line : list) {
            text += line;
        }
        String[] sentences = text.split("[.!?]");
        for (String sentence : sentences) {
            String filepath = "/sample_texts/user_input.txt";
            try (FileWriter fileWriter = new FileWriter(filepath, true)) {
                fileWriter.write(sentence + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> inputText() {
        scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        String filepath = "/sample_texts/user_input.txt";
        list = fileReader.readFile(filepath);
        return list;
    }

}
