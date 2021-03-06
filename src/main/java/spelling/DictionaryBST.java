package spelling;

import java.util.Objects;
import java.util.TreeSet;

/**
 * @author Szabados Alpár
 */
public class DictionaryBST implements Dictionary {
    private TreeSet<String> dict;

    public DictionaryBST(){
        dict = new TreeSet<>();
    }

    /**
     * Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     *
     * @param word The word to add
     * @return true if the word was added to the dictionary
     * (it wasn't already there).
     */
    public boolean addWord(String word) {
        Objects.requireNonNull(word);
        dict.add(word.toLowerCase());
        return isWordInDictionary(word);
    }

    /**
     * Return the number of words in the dictionary
     */
    public int size() {
        return dict.size();
    }

    /**
     * Is this a word according to this dictionary?
     */
    public boolean isWordInDictionary(String word) {
        Objects.requireNonNull(word);
        return dict.contains(word.toLowerCase());
    }
}
