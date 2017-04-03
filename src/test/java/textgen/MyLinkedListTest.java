package textgen;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Szabados Alpár
 */
public class MyLinkedListTest {

    // -- construct

    @Test
    public void shouldConstructEmptyMyLinkedList() {
        List actual = new MyLinkedList();
        List expected = new LinkedList();
        assertEquals(actual, expected);
    }

    @Test
    public void shouldConstructMyLinkedList() {
        List<Integer> actual = new MyLinkedList<>();
        actual.add(1);
        actual.add(2);
        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(2);
        assertEquals(expected, actual);
    }

    // -- add, get

    @Test
    public void shouldAddElement() {
        final List<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
    }

    @Test
    public void shouldAddElementOnIndex() {
        final List<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(0, 2);
        assertEquals(2, (int) list.get(0));
        assertEquals(1, (int) list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowWhenInvalidIndexAdd() {
        new MyLinkedList<Integer>().add(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowWhenInvalidIndexGet() {
        new MyLinkedList<Integer>().get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowWhenGetIndexEqualsSize() {
        final List<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.get(1);
    }

    // -- size

    @Test
    public void shouldReturnSizeIfNull() {
        assertEquals(0, new MyLinkedList<Integer>().size());
    }

    @Test
    public void shouldReturnSizeIfNonNull() {
        final List<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        assertEquals(6, list.size());
    }

    // -- set

    @Test
    public void shouldSet() {
        final List<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.set(1, 2);
        assertEquals(2, (int) list.get(1));
    }

    // -- remove

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowOnInvalidIndex() {
        new MyLinkedList<Integer>().remove(0);
    }

    @Test
    public void shouldReturnRemovedValue() {
        final List<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        final int remove = list.remove(2);
        assertEquals(3, remove);
        assertEquals(4, list.size());
    }

    @Test
    public void shouldRemoveNode() {
        final List<Integer> actual = new MyLinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.remove(3);
        final List<Integer> expected = new LinkedList<>(Arrays.asList(1, 2, 3));
        assertEquals(expected, actual);
    }
}