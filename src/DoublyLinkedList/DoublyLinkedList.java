package DoublyLinkedList;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value, size);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            tail.next.previous = tail;
        }
        tail = node;
        size++;
    }

    public void removeAt(int index) {
        Node<T> current = head;
        if (index + 1 > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        while (current.index != index) {
            current = current.next;
        }
        Node<T> previous = current.previous;
        Node<T> next = current.next;

        if (previous == null) {
            head = next;
        } else {
            previous.next = next;
            current.previous = null;
        }

        if (next == null) {
            tail = previous;
        } else {
            next.previous = previous;
            current.next = null;
        }
        changeIndices();
        size--;
    }

    public T get(int index) {
        Node<T> current = head;
        if (index + 1 > size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        while (index != current.index) {
            current = current.next;
        }

        return current.value;
    }

    private void changeIndices() {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            current.index = index;
            index++;
            current = current.next;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.removeAt(1);
        for (int i = 0; i < list.size; i++) {
            System.out.println(list.get(i));
        }

    }
}
