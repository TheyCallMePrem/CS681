package hw12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 implements BankAccount {
    private double balance = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition sufficientFundsCondition = lock.newCondition();
    private Condition belowUpperLimitFundsCondition = lock.newCondition();
    private volatile boolean shuttingDown = false;
    private List<Thread> threads = new ArrayList<>();

    public void deposit(double amount) {
        lock.lock();
        try {
            if (shuttingDown) {
                return;
            }
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId()  +
                    " (d): current balance: " + balance);
            while (balance >= 300) {
                System.out.println(Thread.currentThread().threadId()  +
                        " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().threadId() +
                    " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            if (shuttingDown) {
                return;
            }
            System.out.println("Lock obtained");
            System.out.println(Thread.currentThread().threadId()  +
                    " (w): current balance: " + balance);
            while (balance <= 0) {
                System.out.println(Thread.currentThread().threadId()  +
                        " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().threadId()  +
                    " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void shutdown() {
        lock.lock();
        try {
            shuttingDown = true;
            for (Thread thread : threads) {
                thread.interrupt();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        for (int i = 0; i < 5; i++) {
            Thread depositThread = new Thread(new DepositRunnable(bankAccount));
            Thread withdrawThread = new Thread(new WithdrawRunnable(bankAccount));
            depositThread.start();
            withdrawThread.start();
            bankAccount.threads.add(depositThread);
            bankAccount.threads.add(withdrawThread);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bankAccount.shutdown();
    }
}
