package document;

/**
 * A class that represents a text document
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

    private String text;

    /**
     * Create a new document from the given text.
     * Because this class is abstract, this is used only from subclasses.
     *
     * @param text The text of the document.
     */
    protected Document(String text) {
        this.text = text;
    }

    /**
     * Returns the tokens that match the regex pattern from the document
     * text string.
     *
     * @param pattern A regular expression string specifying the
     *                token pattern desired
     * @return A List of tokens from the document text that match the regex
     * pattern
     */
    protected List<String> getTokens(String pattern) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }

    /** Helper method for getNumSyllables.
     *
     * @param word The word to count the syllables in
     * @return The number of syllables in the given word, according to
     * this rule: Each contiguous sequence of one or more vowels is a syllable,
     * with the following exception: a lone "e" at the end of a word
     * is not considered a syllable unless the word has no other syllables.
     * You should consider y a vowel.
     */
    protected int countSyllables(String word, int syllableCount, boolean previousCharVowel) {
        if (word.length() == 0) {
            return syllableCount;
        } else if (isSyllable(word, syllableCount, previousCharVowel)) {
            syllableCount += 1;
            return countSyllables(word.substring(1, word.length()), syllableCount, true);
        }
        return countSyllables(word.substring(1, word.length()), syllableCount, isVowel(word.charAt(0)));
    }

    private boolean isSyllable(String word, int syllableCount, boolean previousCharVowel) {
        if (isVowel(word.charAt(0)) && !previousCharVowel) {
            if ((word.length() > 1) || (word.charAt(0) != 'e') || (syllableCount == 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean isVowel(char c) {
        return String.valueOf(c).matches("(?i)[aeuioy]");
    }

    /**
     * Return the number of words in this document
     */
    public abstract int getNumWords();

    /**
     * Return the number of sentences in this document
     */
    public abstract int getNumSentences();

    /**
     * Return the number of syllables in this document
     */
    public abstract int getNumSyllables();

    /**
     * Return the entire text of this document
     */
    public String getText() {
        return this.text;
    }

    /**
     * return the Flesch readability score of this document
     */
    public double getFleschScore() {
        final double wordsDivSentences = (double) getNumWords() / (double) getNumSentences();
        final double syllablesDivWords = (double) getNumSyllables() / (double) getNumWords();

        return 206.835 - 1.015 * wordsDivSentences - 84.6 * syllablesDivWords;
    }
}