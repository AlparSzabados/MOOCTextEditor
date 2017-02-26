package textgen;

import java.util.*;
import java.util.stream.Collectors;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
    private String starter;

    // The random number generator
    private Random rnGenerator;

    public MarkovTextGeneratorLoL(Random generator) {
        wordList = new LinkedList<>();
        starter = "";
        rnGenerator = generator;
    }

    /**
     * Train the generator by adding the sourceText
     */
    @Override
    public void train(String sourceText) {
        if (sourceText.length() > 0) {
            final List<String> stringList = Arrays.asList(sourceText.split("[\\s]+"));
            for (int i = 0; i < stringList.size(); i++) {
                starter = stringList.get(0);
                if (i + 1 < stringList.size()) {
                    final String currentWord = stringList.get(i);
                    if (!wordList.contains(getNode(currentWord))) {
                        wordList.add(getNode(currentWord));
                    }
                    final String nextWord = stringList.get(i + 1);
                    getNode(currentWord).addNextWord(nextWord);
                } else {
                    final ListNode end = new ListNode(stringList.get(stringList.size() - 1));
                    end.addNextWord(starter);
                    wordList.add(end);
                }
            }
        }
    }

    /**
     * Generate the number of words requested.
     */
    @Override
    public String generateText(int numWords) {
        if (wordList.size() > 0) {
            final List<String> result = new ArrayList<>();
            ListNode node = wordList.get(0);
            result.add(node.getWord());
            while (numWords > 0) {
                final String randomNextWord = node.getRandomNextWord(rnGenerator);
                result.add(randomNextWord);
                node = getNode(randomNextWord);
                numWords--;
            }
            return result.stream().collect(Collectors.joining(" "));
        }
        return "";
    }

    // Can be helpful for debugging
    @Override
    public String toString() {
        String toReturn = "";
        for (ListNode n : wordList) {
            toReturn += n.toString();
        }
        return toReturn;
    }

    /**
     * Retrain the generator from scratch on the source text
     */
    @Override
    public void retrain(String sourceText) {
        wordList = new LinkedList<>();
        train(sourceText);
    }

    private ListNode getNode(String word) {
        for (ListNode listNode : wordList) {
            if (Objects.equals(listNode.getWord(), word)) {
                return listNode;
            }
        }
        return new ListNode(word);
    }
}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode {
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;

    ListNode(String word) {
        this.word = word;
        nextWords = new LinkedList<>();
    }

    public String getWord() {
        return word;
    }

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        final int index = generator.nextInt(nextWords.size());
        return nextWords.get(index);
    }

    public String toString() {
        String toReturn = word + ": ";
        for (String s : nextWords) {
            toReturn += s + "->";
        }
        toReturn += "\n";
        return toReturn;
    }
}