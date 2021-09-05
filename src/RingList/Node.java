package RingList;

class  Node<T> {
    T value;
    int index;
    Node<T> next;

    public Node(T value, int index) {
        this.index = index;
        this.value = value;
        this.next = null;
    }
}