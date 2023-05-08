package hw18;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter {

    private static AccessCounter instance = null;
    private ConcurrentHashMap<Path, Integer> accessCounts;

    private AccessCounter() {
        accessCounts = new ConcurrentHashMap<>();
    }

    public static AccessCounter getInstance() {
        if (instance == null) {
            instance = new AccessCounter();
        }
        return instance;
    }

    public void increment(Path path) {
        accessCounts.compute(path, (k, v) -> v == null ? 1 : v + 1);
    }

    public int getCount(Path path) {
        return accessCounts.getOrDefault(path, 0);
    }
}
