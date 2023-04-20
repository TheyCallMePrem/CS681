package hw07;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem instance;
    private static final Lock instanceLock = new ReentrantLock();
    private List<Directory> rootDirs;
    private static final Lock rootDirsLock = new ReentrantLock();

    private FileSystem() {
        rootDirs = new ArrayList<>();
    }

    public static FileSystem getInstance() {
        instanceLock.lock();
        try {
            if (instance == null) {
                instance = new FileSystem();
            }
            return instance;
        } finally {
            instanceLock.unlock();
        }
    }

    public void appendRootDir(Directory root) {
        rootDirsLock.lock();
        try {
            rootDirs.add(root);
        } finally {
            rootDirsLock.unlock();
        }
    }

    public List<Directory> getRootDirs() {
        rootDirsLock.lock();
        try {
            return new ArrayList<>(rootDirs);
        } finally {
            rootDirsLock.unlock();
        }
    }

    public static void main(String[] args) {
        Directory root = new Directory(null, "root", 0, LocalDateTime.now());

        Directory home = new Directory(root, "home", 0, LocalDateTime.now());
        Directory bin = new Directory(root, "bin", 0, LocalDateTime.now());
        Directory apps = new Directory(root, "apps", 0, LocalDateTime.now());

        File x = new File(apps, "x", 7, LocalDateTime.now());
        File y = new File(bin, "y", 6, LocalDateTime.now());

        Directory pictures = new Directory(home, "pictures", 0, LocalDateTime.now());
        File c = new File(home, "c", 1, LocalDateTime.now());
        File a = new File(pictures, "a", 3, LocalDateTime.now());
        File b = new File(pictures, "b", 2, LocalDateTime.now());

        Link d = new Link(root, "d", 4, LocalDateTime.now(), pictures);
        Link e = new Link(root, "e", 5, LocalDateTime.now(), x);

        root.appendChild(home);
        root.appendChild(bin);
        root.appendChild(apps);
        root.appendChild(d);
        root.appendChild(e);
        apps.appendChild(x);
        bin.appendChild(y);
        home.appendChild(pictures);
        home.appendChild(c);
        pictures.appendChild(a);
        pictures.appendChild(b);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread ID: " + Thread.currentThread(). threadId()  + ", Root Directories: " + fileSystem.getRootDirs());
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
