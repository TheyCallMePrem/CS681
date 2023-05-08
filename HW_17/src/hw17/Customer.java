package hw17;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private Movie movie;
    private Lock lock = new ReentrantLock();

   

    public void rentMovie(Movie movie) {
        lock.lock();
        movie.rentMovie();
        this.movie = movie;
        lock.unlock();
    }

    public void returnMovie() {
        lock.lock();
        movie.returnMovie();
        this.movie = null;
        lock.unlock();
    }
}