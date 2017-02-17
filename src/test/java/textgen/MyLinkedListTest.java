package textgen;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        assert list.get(0) == 1;
        assert list.get(1) == 2;
    }

    @Test
    public void shouldAddElementOnIndex() {
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(0, 2);
        assertEquals(2, list.size());
        assert list.get(0) == 2;
        assert list.get(1) == 1;
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowWhenInvalidIndexAdd() {
        new MyLinkedList<Integer>().add(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowWhenInvalidIndexGet() {
        new MyLinkedList<Integer>().get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowWhenGetIndexEqualsSize() {
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    // -- size

    @Test
    public void shouldReturnSize() {
        final MyLinkedList<Integer> list = new MyLinkedList<>();
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
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.set(1, null);
        assertEquals(null, list.get(1));
    }

    // -- remove

    @Test
    public void shouldRemoveNode() {
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(2);
        assertEquals(4, list.size());
    }

    @Test
    public void shouldReturnRemovedNodeData() {
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        final Integer remove = list.remove(1);
        assert remove == 2;
    }

    @Test
    public void shouldReturnRemovedNodeData1() {
        final MyLinkedList<Integer> actual = new MyLinkedList<>();
        actual.add(1);
        actual.add(2);
        actual.add(3);
        actual.add(4);
        actual.remove(3);
        final LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(1, 2, 3));
        assertEquals(expected, actual);
    }
}
