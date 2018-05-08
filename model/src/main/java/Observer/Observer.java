package Observer;

public interface Observer<E> {
    void notifyEvent(ListEvent<E> event);
}
