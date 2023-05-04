package hw10;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem instance;
    private static final Lock instanceLock = new ReentrantLock();
    private List<Directory> rootDirs;
    private final Lock rootDirsLock = new ReentrantLock();

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

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 1: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 1: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        
        Runnable runnable4 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        Runnable runnable5 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 1: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        
        Runnable runnable6 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        Runnable runnable7 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 1: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        
        Runnable runnable8 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        Runnable runnable9 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 1: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        
        Runnable runnable10 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        Runnable runnable11 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 1: Root Directories: " + fileSystem.getRootDirs());
            }
        };
        
        Runnable runnable12 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };

        Runnable runnable13 = new Runnable() {
            @Override
            public void run() {
                FileSystem fileSystem = FileSystem.getInstance();
                fileSystem.appendRootDir(root);
                System.out.println("Thread 2: Root Directories: " + fileSystem.getRootDirs());
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        Thread thread4 = new Thread(runnable4);
        Thread thread5 = new Thread(runnable5);
        Thread thread6 = new Thread(runnable6);
        Thread thread7 = new Thread(runnable7);
        Thread thread8 = new Thread(runnable8);
        Thread thread9 = new Thread(runnable9);
        Thread thread10 = new Thread(runnable10);
        Thread thread11 = new Thread(runnable11);
        Thread thread12 = new Thread(runnable12);
        Thread thread13 = new Thread(runnable13);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread11.start();
        thread12.start();
        thread13.start();
    }
}
