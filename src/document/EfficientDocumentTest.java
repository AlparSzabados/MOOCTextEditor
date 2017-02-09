package document;

import org.junit.jupiter.api.Test;

class EfficientDocumentTest {

    @Test
    public void test1() {
        testCase(new EfficientDocument("This is a test.  How many??? Senteeeeeeeeeences are here... there should be 5!  Right?"), 16, 13, 5);
    }

    @Test
    public void test2() {
        testCase(new EfficientDocument(""), 0, 0, 0);
    }

    @Test
    public void test3() {
        testCase(new EfficientDocument("sentence, with, lots, of, commas.! (And some poaren)).  The output is: 7.5."), 15, 11, 4);
    }

    @Test
    public void test4() {
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
    }

    @Test
    public void test5() {
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should find 3 sentences, 33 words, and 49 syllables. Not every word will have the correct amount of syllables (example, for example), but most of them will."), 49, 33, 3);
    }

    @Test
    public void test6() {
        testCase(new EfficientDocument("Segue"), 2, 1, 1);
    }

    @Test
    public void test7() {
        testCase(new EfficientDocument("Sentence"), 2, 1, 1);
    }

    @Test
    public void test8() {
        testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
    }

    @Test
    public void test9() {
        testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."), 32, 15, 1);
    }

    /**
     * A method for testing
     *
     * @param doc       The Document object to test
     * @param syllables The expected number of syllables
     * @param words     The expected number of words
     * @param sentences The expected number of sentences
     */
    public static void testCase(Document doc, int syllables, int words, int sentences) {
        assert doc.getNumSyllables() == syllables;
        assert doc.getNumWords() == words;
        assert doc.getNumSentences() == sentences;
    }
}