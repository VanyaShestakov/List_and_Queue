package DoublyLinkedList;

class  Node<T> {
    T value;
    int index;
    Node<T> next;
    Node<T> previous;

    public Node(T value, int index) {
        this.index = index;
        this.value = value;
        this.next = null;
    }
}