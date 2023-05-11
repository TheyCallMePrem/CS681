package edu.umb.cs681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            lock.lock();
            try {
                if (done)
                    break;
                if (divisor > 2 && isEven(divisor)) {
                    divisor++;
                    continue;
                }
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    dividend /= divisor;
                } else {
                    if (divisor == 2)
                        divisor++;
                    else
                        divisor += 2;
                }
            } finally {
                lock.unlock();
            }
            Thread.yield();
        }
        lock.lock();
        try {
            if (done) {
                System.out.println("Thread #" + Thread.currentThread().threadId()+ " is cancelled.");
                return;
            }
            if (Thread.interrupted()) {
                try {
                    throw new InterruptedException("Thread interrupted");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("Thread #" + Thread.currentThread().threadId() + " is completed.");
    }

    public void run() {
        generatePrimeFactors();
    }
    public static void main(String[] args) {
        RunnableCancellableInterruptiblePrimeFactorizer factorizer1 = new RunnableCancellableInterruptiblePrimeFactorizer(36, 2, 7);
        RunnableCancellableInterruptiblePrimeFactorizer factorizer2 = new RunnableCancellableInterruptiblePrimeFactorizer(84, 2, 10);

        Thread thread1 = new Thread(factorizer1);
        Thread thread2 = new Thread(factorizer2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        factorizer1.setDone();
        factorizer2.setDone();

        thread1.interrupt();
        thread2.interrupt();
    }
}
