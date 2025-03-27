import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypingAccuracyTest {

    @Test
    void getTypeAccuracy_correctlyTypedText_fullAccuracy() {
        ArrayList<String> testText = new ArrayList<>();
        ArrayList<String> userInput = new ArrayList<>();

        testText.add("On a sunny day, the park is a great place to relax.");
        testText.add("Many people walk their dogs, children play on swings, and families have picnics.");
        testText.add("The trees provide shade, and the grass is soft underfoot.");
        testText.add("Some people like to sit on benches, reading books or chatting with friends.");

        TypingAccuracy testTypeAccuracy = new TypingAccuracy(userInput);
        testTypeAccuracy.setTestText(testText);

        userInput.add("On a sunny day, the park is a great place to relax.");
        userInput.add("Many people walk their dogs, children play on swings, and families have picnics.");
        userInput.add("The trees provide shade, and the grass is soft underfoot.");
        userInput.add("Some people like to sit on benches, reading books or chatting with friends.");

        assertEquals(1.0,testTypeAccuracy.getTypingAccuracy());
    }

    @Test
    void getTypeAccuracy_halfCorrectlyTypedText_halfAccuracy() {
        ArrayList<String> testText = new ArrayList<>();
        ArrayList<String> userInput = new ArrayList<>();

        testText.add("On a sunny day, the park is a great place.");
        testText.add("Many people walk their dogs, children play on swings, and families have picnics.");
        testText.add("The trees provide shade, and the grass is soft underfoot.");
        testText.add("Some people like to sit on benches, reading books or chatting with friends.");

        TypingAccuracy testTypeAccuracy = new TypingAccuracy(userInput);
        testTypeAccuracy.setTestText(testText);

        userInput.add("On a sunny day, the park is a great place.");
        userInput.add("Many people walk their dogs, children play on swings, and families have picnics.");
        userInput.add("");
        userInput.add("");

        assertEquals(0.5,testTypeAccuracy.getTypingAccuracy());
    }

    @Test
    void getTypeAccuracy_incorrectlyTypedText_zeroAccuracy() {
        ArrayList<String> testText = new ArrayList<>();
        ArrayList<String> userInput = new ArrayList<>();

        testText.add("On a sunny day, the park is a great place.");
        testText.add("Many people walk their dogs, children play on swings, and families have picnics.");
        testText.add("The trees provide shade, and the grass is soft underfoot.");
        testText.add("Some people like to sit on benches, reading books or chatting with friends.");

        TypingAccuracy testTypeAccuracy = new TypingAccuracy(userInput);
        testTypeAccuracy.setTestText(testText);

        userInput.add("");
        userInput.add("");
        userInput.add("");
        userInput.add("");

        assertEquals(0.0,testTypeAccuracy.getTypingAccuracy());
    }


}
