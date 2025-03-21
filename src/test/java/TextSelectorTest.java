import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextSelectorTest {

    @Test
    void testTextSelector_validDifficultyLevel_validTextOutput() {
        List<String> resultText = new ArrayList<>();
        resultText.add("My favorite food is pizza.");
        resultText.add("I love the crispy crust, melted cheese, and the variety of toppings.");
        resultText.add("Sometimes I enjoy pizza with just cheese and tomato sauce, " +
                "while other times I add vegetables or meat.");
        resultText.add("I usually order pizza on weekends " +
                "when I’m feeling hungry for something tasty and filling.");
        resultText.add("It’s a fun food to share with friends or family.");
        resultText.add("Everyone can pick their favorite toppings, and we can all enjoy it together.");

        List<String> actualText = SampleTextReader.readSampleText("easy", 2);
        assertEquals(resultText, actualText);
    }

    @Test
    void testTextSelector_invalidDifficultyLevel_invalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            TextSelector.selectText("medium", 2);
        });
    }
}
