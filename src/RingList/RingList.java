package RingList;

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
        Node<T> previous = null;
        if (index + 1 > size) {
            throw new IndexOutOfBoundsException(index);
        }
        while (current.index != index) {
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        changeIndices(previous.next, index);
        size--;
    }

    public T get(int index) {
        Node<T> current = head;
        while (current != null && index != current.index) {
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException(index);
        }
        return current.value;
    }

    public int getSize() {
        return size;
    }

    private void changeIndices(Node<T> current, int index) {
        while (current != head) {
            current.index = index;
            index++;
            current = current.next;
        }
    }


    public static void main(String[] args) {
        RingList<Integer> list = new RingList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.removeAt(3);

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }


    }
}
