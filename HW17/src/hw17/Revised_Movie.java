package hw17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Revised_Movie {
    private String title;
    private int availableCopies;
    private Lock lock = new ReentrantLock();
    private Condition movieReturned = lock.newCondition();

    public Revised_Movie(String title, int availableCopies) {
        this.title = title;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void rentMovie() {
        lock.lock();
        try {
            while (availableCopies == 0) {
                movieReturned.await();
            }
            availableCopies--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void returnMovie() {
        lock.lock();
        try {
            availableCopies++;
            movieReturned.signalAll();
        } finally {
            lock.unlock();
        }
    }
}


