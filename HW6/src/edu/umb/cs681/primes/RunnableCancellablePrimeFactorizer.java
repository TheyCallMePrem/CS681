package edu.umb.cs681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        lock.lock();
        try {
            done = false;
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
        }
    }

	public static void main(String[] args) {
        RunnableCancellablePrimeFactorizer factorizer1 = new RunnableCancellablePrimeFactorizer(100, 2, 500);
        RunnableCancellablePrimeFactorizer factorizer2 = new RunnableCancellablePrimeFactorizer(1024, 2, 500);
        RunnableCancellablePrimeFactorizer factorizer3 = new RunnableCancellablePrimeFactorizer(93754863, 2, 500);

        Thread thread1 = new Thread(factorizer1);
        Thread thread2 = new Thread(factorizer2);
        Thread thread3 = new Thread(factorizer3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        factorizer3.setDone();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Factorizer 1 factors: " + factorizer1.getPrimeFactors());
        System.out.println("Factorizer 2 factors: " + factorizer2.getPrimeFactors());
        System.out.println("Factorizer 3 factors (cancelled): " + factorizer3.getPrimeFactors());
    }
}