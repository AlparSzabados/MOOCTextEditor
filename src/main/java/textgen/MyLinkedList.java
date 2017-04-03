package textgen;

import java.util.AbstractList;

/** A class that implements a doubly linked list
 * 
 * @author Szabados Alpár
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	private LLNode<E> head;
	private LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
        this.head = new LLNode<>(null);
        this.tail = new LLNode<>(null);
        this.head.next = tail;
        this.tail.prev = head;
        this.size = 0;
    }

	/**
	 * Appends an element to the end of the list
     * @param element to add
     */
    public boolean add(E element) {
        LLNode<E> newNode = new LLNode<>(element);
        LLNode<E> prevNode = tail.prev;

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = tail;
        tail.prev = newNode;
        size++;
        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if index is outside of the bounds of the list
     */
    public E get(int index) {
        if (!isPositionIndex(index)) throw new IndexOutOfBoundsException("Not valid Index");
        return getNode(index, head).data;
    }

	/**
	 * Add an element to the list at the specified index
     * @param index where the element should be added
     * @param element to add
     * @throws IndexOutOfBoundsException if index is outside of the bounds of the list
     */
    public void add(int index, E element) {
        if (!isPositionIndex(index)) throw new IndexOutOfBoundsException("Not valid Index");

        LLNode<E> newNode = new LLNode<>(element);
        LLNode<E> nodeAtIndex = getNode(index, head);
        LLNode<E> prevNode = nodeAtIndex.prev;

        prevNode.next = newNode;
        newNode.prev = prevNode;
        newNode.next = nodeAtIndex;
        nodeAtIndex.prev = newNode;
        size++;
    }

	/** Return the size of the list */
    public int size() {
        return size;
    }

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
     * @throws IndexOutOfBoundsException if index is outside of the bounds of the list
     *
     */
    public E remove(int index) {
        if (!isPositionIndex(index)) throw new IndexOutOfBoundsException("Not valid Index");

        LLNode<E> nodeToRemove = getNode(index, head);
        LLNode<E> prevNode = nodeToRemove.prev;

        prevNode.next = nodeToRemove.next;
        size--;
        return nodeToRemove.data;
    }

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
     * @throws IndexOutOfBoundsException if index is outside of the bounds of the list
     */
    public E set(int index, E element) {
        if (!isPositionIndex(index)) throw new IndexOutOfBoundsException("Not valid Index");

        LLNode<E> target = getNode(index, head);

        target.data = element;
        return element;
    }

    private LLNode<E> getNode(int index, LLNode<E> target) {
        for (int i = 0; i <= index; i++) {
            target = target.next;
        }
        return target;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && (index <= size - 1) && size != 0;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E         data;

    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }
}
