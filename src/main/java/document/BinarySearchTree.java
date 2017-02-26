package document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile("[\\S]+");
        Matcher m = p.matcher(print(root));

        final List<String> stringList = new ArrayList<>();
        while (m.find()){
            stringList.add(m.group());
        }
        return stringList.toString();
    }

    private String print(Node<E> node) {
        StringBuilder result = new StringBuilder();
        if (node != null) {
            result.append(print(node.left));
            if (node.value != null) result.append(" ").append(node.value);
            result.append(print(node.right));
        }
        return result.toString();
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