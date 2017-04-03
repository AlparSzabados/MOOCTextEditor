package spelling;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author UC San Diego Intermediate MOOC team
 */
public class NearbyWords implements SpellingSuggest {

    private Dictionary dict;

    public NearbyWords(Dictionary dict) {
        this.dict = dict;
    }

    /**
     * Return the list of Strings that are one modification away from the input string.
     *
     * @param s         The original String
     * @param wordsOnly controls whether to return only words or any String
     * @return list of Strings which are nearby the original string
     */
    private List<String> distanceOne(String s, boolean wordsOnly) {
        List<String> result = new ArrayList<>();
        insertions(s, result, wordsOnly);
        append(s, result, wordsOnly);
        substitution(s, result, wordsOnly);
        deletions(s, result, wordsOnly);
        return result;
    }

    private void iterateAlphabet(Consumer<Character> consumer) {
        for (char charCode = 'a'; charCode <= 'z'; charCode++) {
            consumer.accept(charCode);
        }
    }

    /**
     * Add to the currentList Strings that are one character mutation away from the input string.
     *
     * @param s           The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly   controls whether to return only words or any String
     * @return
     */
    private void substitution(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < s.length(); index++) {
            int i = index;
            iterateAlphabet(c -> addIfValid(s, currentList, wordsOnly, setChar(s, i, c)));
        }
    }

    private String setChar(String s, int index, char charCode) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(index, charCode);
        return sb.toString();
    }

    private boolean isWord(String s, List<String> currentList, boolean wordsOnly, String string) {
        return !currentList.contains(string) && (!wordsOnly || dict.isWordInDictionary(string)) && !Objects.equals(s, string);
    }

    private void addIfValid(String s, List<String> currentList, boolean wordsOnly, String string) {
        if (isWord(s, currentList, wordsOnly, string)) {
            currentList.add(string);
        }
    }

    /**
     * Add to the currentList Strings that are one character insertion away from the input string.
     *
     * @param s           The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly   controls whether to return only words or any String
     */
    private void insertions(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < s.length(); index++) {
            int i = index;
            iterateAlphabet(c -> addIfValid(s, currentList, wordsOnly, insertChar(s, i, c)));
        }
    }

    private String insertChar(String s, int index, char charCode) {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(index, charCode);
        return sb.toString();
    }

    /**
     * Add to the currentList Strings that are one character append away from the input string.
     *
     * @param s           The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly   controls whether to return only words or any String
     * @return
     */
    private void append(String s, List<String> currentList, boolean wordsOnly) {
        iterateAlphabet(c -> {
            final String string = append(s, c);
            addIfValid(s, currentList, wordsOnly, string);
        });
    }

    private String append(String s, char charCode) {
        return s + charCode;
    }

    /**
     * Add to the currentList Strings that are one character deletion away from the input string.
     *
     * @param s           The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly   controls whether to return only words or any String
     * @return
     */
    private void deletions(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < s.length(); index++) {
            addIfValid(s, currentList, wordsOnly, delete(s, index));
        }
    }

    private String delete(String s, int index) {
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(index);
        return sb.toString();
    }

    @Override
    public List<String> suggestions(String word, int numSuggestions) {
        return distanceOne(word, true);
    }
}
