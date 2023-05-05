package hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private final int MAX_VISITORS = 10;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Condition notFull = lock.writeLock().newCondition();

    public void enter() throws InterruptedException {
        lock.writeLock().lock();
        try {
            while (currentVisitors >= MAX_VISITORS) {
                System.out.println("Too many visitors. Please wait for a while!");
                notFull.await();
            }
            currentVisitors++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void exit() {
        lock.writeLock().lock();
        try {
            currentVisitors--;
            notFull.signalAll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors() {
        lock.readLock().lock();
        try {
            return currentVisitors;
        } finally {
            lock.readLock().unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        AdmissionMonitor monitor = new AdmissionMonitor();

        Thread entranceThread1 = new Thread(new EntranceHandler(monitor));
        Thread entranceThread2 = new Thread(new EntranceHandler(monitor));
        Thread entranceThread3 = new Thread(new EntranceHandler(monitor));
        Thread exitThread1 = new Thread(new ExitHandler(monitor));
        Thread exitThread2 = new Thread(new ExitHandler(monitor));
        Thread statsThread = new Thread(new StatsHandler(monitor));

        entranceThread1.start();
        entranceThread2.start();
        entranceThread3.start();
        exitThread1.start();
        exitThread2.start();
        statsThread.start();

        
            Thread.sleep(20000);
        
            entranceThread1.interrupt();
            entranceThread2.interrupt();
            entranceThread3.interrupt();
            exitThread1.interrupt();
            exitThread2.interrupt();
            statsThread.interrupt();

            entranceThread1.join();
            entranceThread2.join();
            entranceThread3.join();
            exitThread1.join();
            exitThread2.join();
            statsThread.join();
    }
}

