package document;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Szabados Alpár
 */
public class BasicDocumentTest {

    /**
     * A method for testing
     *
     * @param doc       The Document object to test
     * @param syllables The expected number of syllables
     * @param words     The expected number of words
     * @param sentences The expected number of sentences
     */
    private void testCase(BasicDocument doc, int syllables, int words, int sentences) {
        assert doc.getNumSyllables() == syllables;
        assert doc.getNumWords() == words;
        assert doc.getNumSentences() == sentences;
    }

    // -- getNumWords, getNumSyllables, getNumSentences
    @Test
    public void test1() {
        testCase(new BasicDocument("This is a test.  How many??? Senteeeeeeeeeences are here... there should be 5!  Right?"), 16, 13, 5);
    }

    @Test
    public void test2() {
        testCase(new BasicDocument(""), 0, 0, 0);
    }

    @Test
    public void test3() {
        testCase(new BasicDocument("sentence, with, lots, of, commas.! (And some poaren)).  The output is: 7.5."), 15, 11, 4);
    }

    @Test
    public void test4() {
        testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
    }

    @Test
    public void test5() {
        testCase(new BasicDocument("Here is a series of test sentences. Your program should find 3 sentences, 33 words, and 49 syllables. Not every word will have the correct amount of syllables (example, for example), but most of them will."), 49, 33, 3);
    }

    @Test
    public void test6() {
        testCase(new BasicDocument("Segue"), 2, 1, 1);
    }

    @Test
    public void test7() {
        testCase(new BasicDocument("Sentence"), 2, 1, 1);
    }

    @Test
    public void test8() {
        testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
    }

    @Test
    public void test9() {
        testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."), 32, 15, 1);
    }

    // -- getFleschIndex
    @Test
    public void shouldTestFleschIndex() {
        BasicDocument text = new BasicDocument("All children, except one, grow up. They soon know that they will grow up, and the way Wendy knew was this. " +
                                                       "One day when she was two years old she was playing in a garden, and she plucked another flower and ran with it to her mother. " +
                                                       "I suppose she must have looked rather delightful, for Mrs.Darling put her hand to her heart and cried, “Oh, why can’t you remain like this for ever!” " +
                                                       "This was all that passed between them on the subject, but henceforth Wendy knew that she must grow up. You always know after you are two. " +
                                                       "Two is the beginning of the end.\n" + "Of course they lived at 14 [their house number on their street], and until Wendy came her mother was the chief one. " +
                                                       "She was a lovely lady, with a romantic mind and such a sweet mocking mouth. Her romantic mind was like the tiny boxes, one within the other, that come from " +
                                                       "the puzzling East, however many you discover there is always one more; and her sweet mocking mouth had one kiss on it that Wendy could never get, though there " +
                                                       "it was, perfectly conspicuous in the right-hand corner.The way Mr. Darling won her was this: the many gentlemen who had been boys when she was a girl discovered " +
                                                       "simultaneously that they loved her, and they all ran to her house to propose to her except Mr. Darling, who took a cab and nipped in first, and so he got her. " +
                                                       "He got all of her, except the innermost box and the kiss. He never knew about the box, and in time he gave up trying for the kiss. Wendy thought Napoleon could " +
                                                       "have got it, but I can picture him trying, and then going off in a passion, slamming the door.");
        double actual = text.getFleschScore();
        double expected = 81.23;
        assertEquals(actual, expected, 0.1);
    }
}