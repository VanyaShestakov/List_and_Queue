package MyQueue;

public class QueueIsEmptyException extends RuntimeException {
    public QueueIsEmptyException(){
        super("Queue is empty!");
    }
}
