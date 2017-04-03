package document;

import org.junit.Before;
import org.junit.Test;
import spelling.AutoCompleteDictionaryTrie;
import spelling.DictionaryLoader;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author UC San Diego MOOC team
 */
public class AutoCompleteDictionaryTrieTester {

    private String dictFile = "/words.small.txt";

    AutoCompleteDictionaryTrie emptyDict;
    AutoCompleteDictionaryTrie smallDict;
    AutoCompleteDictionaryTrie largeDict;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        emptyDict = new AutoCompleteDictionaryTrie();
        smallDict = new AutoCompleteDictionaryTrie();
        largeDict = new AutoCompleteDictionaryTrie();

        smallDict.addWord("Hello");
        smallDict.addWord("HElLo");
        smallDict.addWord("help");
        smallDict.addWord("he");
        smallDict.addWord("hem");
        smallDict.addWord("hot");
        smallDict.addWord("hey");
        smallDict.addWord("a");
        smallDict.addWord("subsequent");

        DictionaryLoader.loadDictionary(largeDict, dictFile);
    }

    /**
     * Test if the size method is working correctly.
     */
    @Test
    public void testSize() {
        assertEquals("Testing size for empty dict", 0, emptyDict.size());
        assertEquals("Testing size for small dict", 8, smallDict.size());
        assertEquals("Testing size for large dict", 4438, largeDict.size());
    }

    /**
     * Test the isWordInDictionary method
     */
    @Test
    public void testIsWord() {
        assertEquals("Testing isWordInDictionary on empty: Hello", false, emptyDict.isWordInDictionary("Hello"));
        assertEquals("Testing isWordInDictionary on small: Hello", true, smallDict.isWordInDictionary("Hello"));
        assertEquals("Testing isWordInDictionary on large: Hello", true, largeDict.isWordInDictionary("Hello"));

        assertEquals("Testing isWordInDictionary on small: hello", true, smallDict.isWordInDictionary("hello"));
        assertEquals("Testing isWordInDictionary on large: hello", true, largeDict.isWordInDictionary("hello"));

        assertEquals("Testing isWordInDictionary on small: hellow", false, smallDict.isWordInDictionary("hellow"));
        assertEquals("Testing isWordInDictionary on large: hellow", false, largeDict.isWordInDictionary("hellow"));

        assertEquals("Testing isWordInDictionary on empty: empty string", false, emptyDict.isWordInDictionary(""));
        assertEquals("Testing isWordInDictionary on small: empty string", false, smallDict.isWordInDictionary(""));
        assertEquals("Testing isWordInDictionary on large: empty string", false, largeDict.isWordInDictionary(""));

        assertEquals("Testing isWordInDictionary on small: no", false, smallDict.isWordInDictionary("no"));
        assertEquals("Testing isWordInDictionary on large: no", true, largeDict.isWordInDictionary("no"));

        assertEquals("Testing isWordInDictionary on small: subsequent", true, smallDict.isWordInDictionary("subsequent"));
        assertEquals("Testing isWordInDictionary on large: subsequent", true, largeDict.isWordInDictionary("subsequent"));

    }

    /**
     * Test the addWord method
     */
    @Test
    public void testAddWord() {

        assertEquals("Asserting hellow is not in empty dict", false, emptyDict.isWordInDictionary("hellow"));
        assertEquals("Asserting hellow is not in small dict", false, smallDict.isWordInDictionary("hellow"));
        assertEquals("Asserting hellow is not in large dict", false, largeDict.isWordInDictionary("hellow"));

        emptyDict.addWord("hellow");
        smallDict.addWord("hellow");
        largeDict.addWord("hellow");

        emptyDict.printTree();

        assertEquals("Asserting hellow is in empty dict", true, emptyDict.isWordInDictionary("hellow"));
        assertEquals("Asserting hellow is in small dict", true, smallDict.isWordInDictionary("hellow"));
        assertEquals("Asserting hellow is in large dict", true, largeDict.isWordInDictionary("hellow"));

        assertEquals("Asserting xyzabc is not in empty dict", false, emptyDict.isWordInDictionary("xyzabc"));
        assertEquals("Asserting xyzabc is not in small dict", false, smallDict.isWordInDictionary("xyzabc"));
        assertEquals("Asserting xyzabc is in large dict", false, largeDict.isWordInDictionary("xyzabc"));

        emptyDict.addWord("XYZAbC");
        smallDict.addWord("XYZAbC");
        largeDict.addWord("XYZAbC");

        assertEquals("Asserting xyzabc is in empty dict", true, emptyDict.isWordInDictionary("xyzabc"));
        assertEquals("Asserting xyzabc is in small dict", true, smallDict.isWordInDictionary("xyzabc"));
        assertEquals("Asserting xyzabc is large dict", true, largeDict.isWordInDictionary("xyzabc"));

        assertEquals("Testing isWordInDictionary on empty: empty string", false, emptyDict.isWordInDictionary(""));
        assertEquals("Testing isWordInDictionary on small: empty string", false, smallDict.isWordInDictionary(""));
        assertEquals("Testing isWordInDictionary on large: empty string", false, largeDict.isWordInDictionary(""));

        assertEquals("Testing isWordInDictionary on small: no", false, smallDict.isWordInDictionary("no"));
        assertEquals("Testing isWordInDictionary on large: no", true, largeDict.isWordInDictionary("no"));

        assertEquals("Testing isWordInDictionary on small: subsequent", true, smallDict.isWordInDictionary("subsequent"));
        assertEquals("Testing isWordInDictionary on large: subsequent", true, largeDict.isWordInDictionary("subsequent"));

    }

    @Test
    public void testPredictCompletions() {
        List<String> completions;
        boolean allIn;
        completions = smallDict.predictCompletions("", 0);
        assertEquals(0, completions.size());

        completions = smallDict.predictCompletions("", 4);
        assertEquals(4, completions.size());
        assertTrue(completions.contains("a"));
        assertTrue(completions.contains("he"));
        boolean twoOfThree = completions.contains("hey") && completions.contains("hot") ||
                completions.contains("hey") && completions.contains("hem") ||
                completions.contains("hot") && completions.contains("hem");
        assertTrue(twoOfThree);

        completions = smallDict.predictCompletions("he", 2);
        allIn = completions.contains("he") && (completions.contains("hem") || completions.contains("hey"));
        assertEquals(2, completions.size());
        assertTrue(allIn);

        completions = smallDict.predictCompletions("hel", 10);
        assertEquals(2, completions.size());
        allIn = completions.contains("hello") && completions.contains("help");
        assertTrue(allIn);

        completions = smallDict.predictCompletions("x", 5);
        assertEquals(0, completions.size());
    }
}
