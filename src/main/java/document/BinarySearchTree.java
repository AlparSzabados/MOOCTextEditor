package document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An BinarySearchTree data structure implemented from scratch as part of the assignment
 *
 * @author Szabados Alpár
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
    private Node<E> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public boolean contains(E value) {
        Node<E> curr = root;
        while (curr != null) {
            final int compareTo = value.compareTo(curr.value);
            if (compareTo > 0) {
                curr = curr.right;
            } else if (compareTo < 0) {
                curr = curr.left;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean add(E value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Node<>(value);
            return true;
        }
        return insertNode(root, value);
    }

    private boolean insertNode(Node<E> curr, E value) {
        final boolean smaller = value.compareTo(curr.value) < 0;
        if (value.compareTo(curr.value) == 0) return false;
        else if (smaller && curr.left != null) {
            return insertNode(curr.left, value);
        } else if (!smaller && curr.right != null) {
            return insertNode(curr.right, value);
        }

        if (smaller) curr.left = new Node<>(value);
        else curr.right = new Node<>(value);
        return true;
    }

    @Override
    public String toString() {
        final List<E> list = toList(root, new ArrayList<>());
        return list.toString();
    }

    private List<E> toList(Node<E> node, List<E> results) {
        if (node != null) {
            toList(node.left, results);
            results.add(node.value);
            toList(node.right, results);
        }
        return results;
    }
}

class Node<E> {
    E value;
    Node<E> left, right;

    Node() {
    }

    Node(E value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    Node(E value, Node<E> left) {
        this(value);
        this.left = left;
    }

    Node(E value, Node<E> left, Node<E> right) {
        this(value, left);
        this.right = right;
    }
}