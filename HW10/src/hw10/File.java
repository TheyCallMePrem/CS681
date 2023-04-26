package hw10;


import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class File extends FSElement {
    private Lock lock;

    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        lock = new ReentrantLock();
    }

    public void setSize(int size) {
        lock.lock();
        try {
            super.setSize(size);
        } finally {
            lock.unlock();
        }
    }

    public boolean isFile() {
        return true;
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isLink() {
        return false;
    }

    public boolean isProxy() {
        return false;
    }
}
