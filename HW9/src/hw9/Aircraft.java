package hw9;
import java.util.concurrent.locks.ReentrantLock;

// Since Position class is always thread safe by virtue of being immutable, this class which serves as it's 
// client code needs to be thread safe. 

// To make this class thread-safe, I used a ReentrantLock instance variable called positionLock. 


public class Aircraft {
    private final ReentrantLock lock = new ReentrantLock();
    private volatile Position position;

    public Aircraft(Position pos) {
        this.position = pos;
    }

    public void setPosition(double newLat, double newLong, double newAlt) {
        lock.lock();
        try {
            position = position.change(newLat, newLong, newAlt);
        } finally {
            lock.unlock();
        }
    }

    public Position getPosition() {
        lock.lock();
        try {
            return position;
        } finally {
            lock.unlock();
        }
    }

    
}
