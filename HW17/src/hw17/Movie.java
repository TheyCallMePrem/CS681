// Movie.java
package hw17;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Movie {
    private String title;
    private int availableCopies;
    private Lock lock = new ReentrantLock();

    public Movie(String title, int availableCopies) {
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
        availableCopies--;
        lock.unlock();
    }

    public void returnMovie() {
        lock.lock();
        availableCopies++;
        lock.unlock();
    }
}


