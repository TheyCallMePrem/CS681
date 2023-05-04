package hw10;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Directory extends FSElement {
    private List<FSElement> children;
    LinkedList<FSElement> FSlist = new LinkedList<FSElement>();
    LinkedList<Directory> DIRList = new LinkedList<Directory>();
    LinkedList<File> Filelist = new LinkedList<File>();
    LinkedList<Link> ProxyList = new LinkedList<Link>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        this.children = new LinkedList<>();
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            FSlist.add(child);
 
            if(child.isDirectory()){
                DIRList.add((Directory) child);
            }
            else{
                if(child.isProxy()){
                    ProxyList.add((Link) child);
                }
                else{
                    Filelist.add((File) child);
                }
            } 
        }
        finally {
            lock.unlock();
        }
    }

    public List<FSElement> getChildren() {
        lock.lock();
        try {
            return new LinkedList<>(this.children);
        } finally {
            lock.unlock();
        }
    }

    public int getTotalSize() {
        int size = 0;
        lock.lock();
        try {
            for (FSElement child : this.children) {
                size += child.getSize();
            }
        } finally {
            lock.unlock();
        }
        return size;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isProxy() {
        return false;
    }
}
