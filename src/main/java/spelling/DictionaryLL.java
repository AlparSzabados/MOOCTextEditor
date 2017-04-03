package spelling;

import java.util.LinkedList;
import java.util.Objects;

/**
 * A class that implements the Dictionary interface using a LinkedList
 */
public class DictionaryLL implements Dictionary {

    private LinkedList<String> dict;

    public DictionaryLL(){
        dict = new LinkedList<>();
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
        final String toLowerCase = word.toLowerCase();
        if (isWordInDictionary(toLowerCase)) return false;
        dict.add(toLowerCase);
        return true;
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
        final String s = word.toLowerCase();
        return dict.contains(s);
    }
}
