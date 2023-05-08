package hw19;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Observable<T> {
    private final ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    public void notifyObservers(T event) {
        for (Observer<T> observer : observers) {
            observer.update(this, event);
        }
    }
}
