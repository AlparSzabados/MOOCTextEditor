package document;

import java.util.List;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocument extends Document {

	private int numWords;
	private int numSentences;
	private int numSyllables;

	public EfficientDocument(String text) {
		super(text);
		processText();
	}

	/** 
	 * Take a string that either contains only alphabetic characters,
	 * or only sentence-ending punctuation.  Return true if the string
	 * contains only alphabetic characters, and false if it contains
	 * end of sentence punctuation.  
	 * 
	 * @param tok The string to check
	 * @return true if tok is a word, false if it is punctuation. 
	 */
	private boolean isWord(String tok) {
		return !(tok.contains("!") || tok.contains(".") || tok.contains("?"));
	}

    /** Passes through the text one time to count the number of words, syllables 
     * and sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText() {
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");

		for (int i = 0; i < tokens.size(); i++) {
			String s = tokens.get(i);
			if (isWord(s)) {
				if (i + 1 == tokens.size()) {
					numSentences++;
				}
				numSyllables += countSyllables(s);
				numWords++;
			} else {
				numSentences++;
			}
		}
	}

	@Override
	public int getNumSentences() {
		return numSentences;
	}

	@Override
	public int getNumWords() {
		return numWords;
	}

	@Override
	public int getNumSyllables() {
		return numSyllables;
	}
}
