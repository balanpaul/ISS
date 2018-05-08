package Observer;

public interface Observable<E> {
    void addObserver(Observer<E> obs);
    void removeObserver(Observer<E> obs);
    void notifyAllObservers(ListEvent<E> ev);
}
