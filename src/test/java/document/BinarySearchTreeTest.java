package document;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * @author Szabados Alpár
 */
public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> binarySearchTree;
    private TreeSet<Integer> treeSet;

    @Before
    public void setUp() {
        binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.setRoot(new Node<>());
        binarySearchTree.getRoot().value = 10;
        binarySearchTree.getRoot().right = new Node<>();
        binarySearchTree.getRoot().right.value = 15;
        binarySearchTree.getRoot().right.left = new Node<>();
        binarySearchTree.getRoot().right.left.value = 12;
        binarySearchTree.getRoot().left = new Node<>();
        binarySearchTree.getRoot().left.value = 5;
        binarySearchTree.getRoot().left.left = new Node<>();
        binarySearchTree.getRoot().left.left.value = 3;
        binarySearchTree.getRoot().left.right = new Node<>();
        binarySearchTree.getRoot().left.right.value = 6;
        binarySearchTree.getRoot().left.left.left = new Node<>();
        binarySearchTree.getRoot().left.left.left.value = 2;
        binarySearchTree.getRoot().left.left.left.left = new Node<>();
        binarySearchTree.getRoot().left.left.left.left.value = 1;

        treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(15);
        treeSet.add(12);
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(6);
        treeSet.add(2);
        treeSet.add(1);
    }

    @Test
    public void shouldConstructEmpty() {
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        assertEquals(binaryTree.getRoot(), null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenNodeValueNull() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Node<>(1, new Node<>(null)));
    }

    @Test
    public void shouldConstructFromNodesValue() {
        assertEquals(binarySearchTree.toString(), treeSet.toString());
    }

    @Test
    public void shouldConstructFromValue() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Node<>(10, new Node<>(5, new Node<>(3, new Node<>(2, new Node<>(1))), new Node<>(6)), new Node<>(15, new Node<>(12))));
        assertEquals(binarySearchTree.toString(), bst.toString());
    }

    @Test
    public void shouldAddValue() {
        binarySearchTree.add(20);
        binarySearchTree.add(9);
        treeSet.add(20);
        treeSet.add(9);
        assertEquals(binarySearchTree.toString(), treeSet.toString());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowWhenAddNull() {
        binarySearchTree.add(null);
    }

    @Test
    public void shouldFindElement() {
        assertFalse(binarySearchTree.contains(8));
        assertTrue(binarySearchTree.contains(10));
    }
}