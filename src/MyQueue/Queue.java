package MyQueue;

public class Queue <T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void push(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public T poll() {
        if (head != null) {
            T returnedItem = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return returnedItem;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder queueStr = new StringBuilder();
        if (head != null) {
            Node<T> current = head;
            queueStr.append("[");
            while (current != null) {
                queueStr.append(current.value).append(", ");
                current = current.next;
            }
            queueStr.delete(queueStr.length() - 2, queueStr.length());
            queueStr.append("]");
        } else {
            queueStr.append("[]");
        }
        return queueStr.toString();
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
        }
        return current != null;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
