package hw17;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Revised_Customer {
    private Revised_Movie movie;
    private Lock lock = new ReentrantLock();
    private Condition movieReturned = lock.newCondition();

  

    public void rentMovie(Revised_Movie movie1) {
        lock.lock();
        try {
            while (movie1.getAvailableCopies() == 0) {
                movieReturned.await();
            }
            movie1.rentMovie();
            this.movie = movie1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void returnMovie() {
        lock.lock();
        try {
            movie.returnMovie();
            this.movie = null;
            movieReturned.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
