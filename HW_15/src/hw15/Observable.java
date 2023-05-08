package hw15;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observable<T> {
    private final LinkedList<Observer<T>> observers = new LinkedList<>();
    private final Lock lockObs = new ReentrantLock();

    public void addObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            observers.add(observer);
        } finally {
            lockObs.unlock();
        }
    }

    public void removeObserver(Observer<T> observer) {
        lockObs.lock();
        try {
            observers.remove(observer);
        } finally {
            lockObs.unlock();
        }
    }

    public void notifyObservers(T event) {
        List<Observer<T>> observersLocal;
        lockObs.lock();
        try {
            observersLocal = new ArrayList<Observer<T>>(observers);
        } finally {
            lockObs.unlock();
        }
        for (Observer<T> observer : observersLocal) {
            observer.update(this, event);
        }
    }
}
