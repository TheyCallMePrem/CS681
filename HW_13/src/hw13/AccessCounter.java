package hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
    private static AccessCounter instance = null;
    private HashMap<Path, Integer> accessCounts;
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private AccessCounter() {
        accessCounts = new HashMap<>();
    }

    public static AccessCounter getInstance() {
        rwLock.writeLock().lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void increment(Path path) {
        rwLock.writeLock().lock();
        try {
            int count = accessCounts.getOrDefault(path, 0);
            accessCounts.put(path, count + 1);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getCount(Path path) {
        rwLock.readLock().lock();
        try {
            return accessCounts.getOrDefault(path, 0);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
