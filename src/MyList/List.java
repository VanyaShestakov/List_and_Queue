package MyList;

public class List<T> {
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
        size++;
    }

    public void removeAt(int index) {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null && current.index != index) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (previous != null) {
                previous.next = current.next;
                if (current.next == null) {
                    tail = previous;
                }
                changeIndices(previous.next, index);
            } else {
                head = head.next;
                if (head == null) {
                    tail = null;
                }
                changeIndices(head, index);
            }
            size--;
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public void removeAll(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                removeAt(current.index);
            }
            current = current.next;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder listStr = new StringBuilder();
        if (!isEmpty()) {
            Node<T> current = head;
            listStr.append("{");
            while (current != null) {
                listStr.append(current.value).append(" -> ");
                current = current.next;
            }
            listStr.delete(listStr.length() - 4, listStr.length());
            listStr.append("}");
        } else {
            listStr.append("{}");
        }
        return listStr.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        List<T> list = (List<T>) o;
        if (this.size != list.size) {
            return false;
        }
        for (int i = 0; i < list.size; i++) {
            if (!this.get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
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

    public int indexOf(T value) {
        Node<T> current = head;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
        }
        return current == null ? -1 : current.index;
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

    public int size() {
        return size;
    }

    private void changeIndices(Node<T> current, int index) {
        while (current != null) {
            current.index = index;
            index++;
            current = current.next;
        }
    }
}
