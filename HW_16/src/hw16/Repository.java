package hw16;

import java.util.HashMap;
import java.util.Map;

public class Repository {
    private Map<String, String> files = new HashMap<>();

    public void push(String filename, String content) {
        files.put(filename, content);
    }

    public String pull(String filename) {
        return files.get(filename);
    }
}

