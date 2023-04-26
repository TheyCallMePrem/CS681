package hw10;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {
    private FSElement target;
    private Lock lock = new ReentrantLock();

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
    }

    public boolean isFile() {
        lock.lock();
        try {
            return !target.isDirectory();
        } finally {
            lock.unlock();
        }
    }

    public boolean isLink() {
        return true;
    }

    public FSElement getTarget() {
        lock.lock();
        try {
            return target;
        } finally {
            lock.unlock();
        }
    }

    public void setTarget(FSElement target) {
        lock.lock();
        try {
            this.target = target;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isProxy() {
        return true;
    }
}
