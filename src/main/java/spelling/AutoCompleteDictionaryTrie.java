package spelling;

import java.util.*;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 *
 * @author Szabados Alpár
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;

    public AutoCompleteDictionaryTrie() {
        root = new TrieNode();
    }

    /**
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie, as described outlined in the videos for this week. It
     * should appropriately use existing nodes in the trie, only creating new
     * nodes when necessary. E.g. If the word "no" is already in the trie,
     * then adding the word "now" would add only one additional node
     * (for the 'w').
     *
     * @return true if the word was successfully added or false if it already exists
     * in the dictionary.
     */
    public boolean addWord(String word) {
        TrieNode node = getTrieNode(word);
        if (!node.endsWord()) {
            node.setEndsWord(true);
            size++;
            return true;
        }

        return false;
    }

    /**
     * Return the number of words in the dictionary.  This is NOT necessarily the same
     * as the number of TrieNodes in the trie.
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the string is a word in the trie, using the algorithm
     * described in the videos for this week.
     */
    @Override
    public boolean isWordInDictionary(String word) {
        String s = word.toLowerCase();
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.getValidNextCharacters().contains(c)) {
                node = node.getChild(c);
            }
        }
        return Objects.equals(node.getText(), s) && node.endsWord();
    }

    /**
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
     * in the list of returned words.
     * <p>
     * The list of completions must contain
     * all of the shortest completions, but when there are ties, it may break
     * them in any order. For example, if there the prefix string is "ste" and
     * only the words "step", "stem", "stew", "steer" and "steep" are in the
     * dictionary, when the user asks for 4 completions, the list must include
     * "step", "stem" and "stew", but may include either the word
     * "steer" or "steep".
     * <p>
     * If this string prefix is not in the trie, it returns an empty list.
     *
     * @param prefix         The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        Queue<TrieNode> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();
        TrieNode node = getTrieNode(prefix);
        while (numCompletions > 0) {
            if (!queue.isEmpty())
                node = queue.poll();
            if (node.endsWord()) {
                result.add(node.getText());
                numCompletions--;
            }
            queue.addAll(validNodesToQueue(node.getValidNextCharacters(), node));
            if (queue.isEmpty()) return result;
        }
        return result;
    }

    private Queue<TrieNode> validNodesToQueue(Set<Character> keys, TrieNode node) {
        Queue<TrieNode> result = new LinkedList<>();
        for (Character key : keys) {
            result.add(node.getChild(key));
        }
        return result;
    }

    private TrieNode getTrieNode(String word) {
        final String s = word.toLowerCase();
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.getValidNextCharacters().contains(c)) {
                node = node.getChild(c);
            } else {
                node = node.insert(c);
            }
        }
        return node;
    }

    // For debugging
    public void printTree() {
        printNode(root);
    }

    /**
     * Do a pre-order traversal from this node down
     */
    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getText());

        TrieNode next;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }
}