package hw11;
import java.util.HashMap;
import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    private static AccessCounter instance = null;
    private HashMap<Path, Integer> accessCounts;
    private static final ReentrantLock lock = new ReentrantLock();

    private AccessCounter() {
        accessCounts = new HashMap<>();
    }

    public static AccessCounter getInstance() {
        
        lock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public void increment(Path path) {
        lock.lock();
        try {
            int count = accessCounts.getOrDefault(path, 0); // Returns the number of access of a path, if path doesn't exist, returns 0
            accessCounts.put(path, count + 1);
        } finally {
            lock.unlock();
        }
    }

    public int getCount(Path path) {
        lock.lock();
        try {
            return accessCounts.getOrDefault(path, 0);
        } finally {
            lock.unlock();
        }
    }
}

