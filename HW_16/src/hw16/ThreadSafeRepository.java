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
    public static void main(String[] args) {
        ThreadSafeRepository repository = new ThreadSafeRepository();
        
        // Spawn multiple threads to push files to the repository
        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            new Thread(() -> {
                String filename = "file" + threadNum + ".txt";
                String content = "Hello from thread " + threadNum;
                repository.push(filename, content);
                System.out.println("Thread " + threadNum + " pushed file: " + filename);
            }).start();
        }
    
        // Spawn multiple threads to pull files from the repository
        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            new Thread(() -> {
                String filename = "file" + threadNum + ".txt";
                String content = repository.pull(filename);
                System.out.println("Thread " + threadNum + " pulled content: " + content);
            }).start();
        }
    }
}
