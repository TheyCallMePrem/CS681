package hw16;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeRepository {
    private Map<String, String> files = new HashMap<>();
    private Lock lock = new ReentrantLock();

    public void push(String filename, String content) {
        lock.lock();
        try {
            files.put(filename, content);
        } finally {
            lock.unlock();
        }
    }

    public String pull(String filename) {
        lock.lock();
        try {
            return files.get(filename);
        } finally {
            lock.unlock();
        }
    }
}
