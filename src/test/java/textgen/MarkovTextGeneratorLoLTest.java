package textgen;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MarkovTextGeneratorLoLTest {

    private MarkovTextGeneratorLoL MarkovTextGen;

    @Before
    public void setUp() {
        MarkovTextGen = new MarkovTextGeneratorLoL(new Random(1));
    }

    // -- train

    @Test
    public void shouldTrainTextEmpty() {
        String test = "";
        MarkovTextGen.train(test);
        assertEquals(test, MarkovTextGen.toString());
    }

    @Test
    public void shouldTrainText() {
        String test = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        MarkovTextGen.train(test);

        String actual = MarkovTextGen.toString();
        String expected = "Hello.: Hello->\n" +
                "Hello: there.->there.->Bob.->\n" +
                "there.: This->Hello->\n" +
                "This: is->\n" +
                "is: a->\n" +
                "a: test.->\n" +
                "test.: Hello->\n" +
                "Bob.: Test->\n" +
                "Test: again.->\n" +
                "again.: Hello.->\n";
        assertEquals(expected, actual);
    }

    // -- generate

    @Test
    public void shouldGenerateTextEmpty() {
        String test = "";
        MarkovTextGen.train(test);
        final String generateText = MarkovTextGen.generateText(40);

        assertEquals(generateText, "");
    }

    @Test
    public void shouldGenerateTextEmptyAndNumWordsZero() {
        String test = "";
        MarkovTextGen.train(test);
        final String generateText = MarkovTextGen.generateText(0);

        assertEquals(generateText, "");
    }

    @Test
    public void shouldGenerateTextEmptyAndNumWordsNegative() {
        String test = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        MarkovTextGen.train(test);
        final String generateText = MarkovTextGen.generateText(-1);

        assertEquals(generateText, "");
    }

    @Test
    public void shouldGenerateText() {
        String test = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        MarkovTextGen.train(test);

        final String generateText1 = MarkovTextGen.generateText(40);
        final String generateText2 = MarkovTextGen.generateText(40);

        final int generateText1Length = generateText1.split("[\\s]+").length;
        final int generateText2Length = generateText2.split("[\\s]+").length;

        assertNotEquals(generateText1, generateText2);
        assertEquals(generateText1Length, generateText2Length);
    }

    @Test
    public void shouldGenerateTextLongerInput() {
        String test = "You say yes, I say no, " +
                "You say stop, and I say go, go, go, " +
                "Oh no. You say goodbye and I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "I say high, you say low, " +
                "You say why, and I say I don't know. " +
                "Oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "Why, why, why, why, why, why, " +
                "Do you say goodbye. " +
                "Oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "You say yes, I say no, " +
                "You say stop and I say go, go, go. " +
                "Oh, oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello,";
        MarkovTextGen.train(test);

        final String generateText1 = MarkovTextGen.generateText(100);
        final String generateText2 = MarkovTextGen.generateText(100);

        final int generateText1Length = generateText1.split("[\\s]+").length;
        final int generateText2Length = generateText2.split("[\\s]+").length;

        assertNotEquals(generateText1, generateText2);
        assertEquals(generateText1Length, generateText2Length);
    }

    // -- retrain

    @Test
    public void shouldRetrainTextEmpty() {
        String train = "Hello.";
        MarkovTextGen.train(train);
        String actualTrain = MarkovTextGen.toString();
        String expectedTrain = "Hello.: Hello.->\n";
        assertEquals(expectedTrain, actualTrain);

        String retrain = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        MarkovTextGen.retrain(retrain);
        String actualRetrain = MarkovTextGen.toString();
        String expectedRetrain = "Hello.: Hello->\n" +
                "Hello: there.->there.->Bob.->\n" +
                "there.: This->Hello->\n" +
                "This: is->\n" +
                "is: a->\n" +
                "a: test.->\n" +
                "test.: Hello->\n" +
                "Bob.: Test->\n" +
                "Test: again.->\n" +
                "again.: Hello.->\n";
        assertEquals(expectedRetrain, actualRetrain);
    }
}