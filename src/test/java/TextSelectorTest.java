import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextSelectorTest {

    @Test
    void testTextSelector_validInput_validTextOutput() {
        List<String> resultText = new ArrayList<>();
        resultText.add("Dogs wag their tails happily as they run through the grass.");
        resultText.add("The sound of laughter fills the air.");
        resultText.add("Everyone enjoys the beautiful weather, making it a perfect day.");

        List<String> actualText = TextSelector.selectText("easy", "short",2);
        assertEquals(resultText, actualText);
    }

    @Test
    void testTextSelector_invalidDifficultyLevel_invalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            TextSelector.selectText("e", "short", 2);
        });
    }

    void testTextSelector_invalidTextLength_invalidInputException() {
        assertThrows(InvalidInputException.class, () -> {
            TextSelector.selectText("easy", "s", 2);
        });
    }

    @Test
    void testTextSelector_invalidRandomNum_fileProcessingException() {
        assertThrows(FileProcessingException.class, () -> {
            TextSelector.selectText("intermediate", "short",0);
        });
    }
}
