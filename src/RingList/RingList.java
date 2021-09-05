package RingList;

import java.util.Scanner;

public class RingList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value, size);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        tail.next = head;
        size++;
    }

    public void removeAt(int index) {
        Node<T> current = head;
        Node<T> previous = tail;
        if (index + 1 > size) {
            throw new IndexOutOfBoundsException(index);
        }
        while (current.index != index) {
            previous = current;
            current = current.next;
        }
        if (current == current.next) {
            head = null;
            tail = null;
        }else {
            if (current == head) {
                previous.next = current.next;
                head = current.next;
                changeIndices();
            } else if (current == tail){
                previous.next = current.next;
                tail = previous;
                changeIndices();
            } else {
                previous.next = current.next;
                changeIndices();
            }
        }
        size--;
    }

    public T get(int index) {
        Node<T> current = head;
        if (index + 1 > size) {
            throw new IndexOutOfBoundsException(index);
        }
        while (index != current.index) {
            current = current.next;
        }
        return current.value;
    }

    public Node<T> getNode(int index) {
        Node<T> current = head;
        if (index + 1 > size) {
            throw new IndexOutOfBoundsException(index);
        }
        while (index != current.index) {
            current = current.next;
        }
        return current;
    }

    public int getSize() {
        return size;
    }

    public int indexOf(T value) {
        Node<T> current = head;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
        }
        return current == null ? -1 : current.index;
    }

    private void changeIndices() {
        Node<T> current = head;
        int index = 0;
        while (current != tail) {
            current.index = index;
            index++;
            current = current.next;
        }
        current.index = index;
    }


    public static void main(String[] args) {
        RingList<Integer> list = new RingList<Integer>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }






        System.out.println("Enter K:");
        int k = Integer.parseInt(scanner.nextLine());
        int counter = 1;
        int index = 0;
        Node<Integer> current = list.head;
        Node<Integer> next;
        while (list.size != 0) {
            if (counter == k) {
                next = current.next;
                System.out.println(current.value);
                list.removeAt(current.index);
                current = next;
                counter = 1;
            } else {
                current = current.next;
                counter++;
            }
        }


    }
}
