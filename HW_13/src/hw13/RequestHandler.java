package hw13;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;



class RequestHandler implements Runnable {
    private volatile boolean running;
    private final Random rand;
    private final Path[] files;

    public RequestHandler(Path[] files) {
        this.files = files;
        running = true;
        rand = new Random();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Path file = files[rand.nextInt(files.length)];
            AccessCounter ac = AccessCounter.getInstance();
            ac.increment(file);
            int count = ac.getCount(file);
            System.out.println(Thread.currentThread().getName() + ": " + file + " accessed " + count + " times." );
            try {
                Thread.sleep(rand.nextInt(5000) + 1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " finishing");
    }

    public void shutdown() throws InterruptedException {
        stop();
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName() + " shutting down");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted while waiting for thread to finish");
            Thread.currentThread().interrupt();
        }
    }
    

    public static void main(String[] args) throws InterruptedException{
        Path[] files = { Paths.get("a.html"), Paths.get("b.html"), Paths.get("c.html") };

        RequestHandler handler1 = new RequestHandler(files);
        RequestHandler handler2 = new RequestHandler(files);
        RequestHandler handler3 = new RequestHandler(files);
        RequestHandler handler4 = new RequestHandler(files);
        RequestHandler handler5 = new RequestHandler(files);
        RequestHandler handler6 = new RequestHandler(files);
        RequestHandler handler7 = new RequestHandler(files);
        RequestHandler handler8 = new RequestHandler(files);
        RequestHandler handler9 = new RequestHandler(files);
        RequestHandler handler10 = new RequestHandler(files);
        RequestHandler handler11 = new RequestHandler(files);
        RequestHandler handler12 = new RequestHandler(files);
        RequestHandler handler13 = new RequestHandler(files);

        Thread thread1 = new Thread(handler1);
        Thread thread2 = new Thread(handler2);
        Thread thread3 = new Thread(handler3);
        Thread thread4 = new Thread(handler4);
        Thread thread5 = new Thread(handler5);
        Thread thread6 = new Thread(handler6);
        Thread thread7 = new Thread(handler7);
        Thread thread8 = new Thread(handler8);
        Thread thread9 = new Thread(handler9);
        Thread thread10 = new Thread(handler10);
        Thread thread11 = new Thread(handler11);
        Thread thread12 = new Thread(handler12);
        Thread thread13 = new Thread(handler13);

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

        Thread.sleep(5000); // wait for 5 seconds

        handler1.shutdown();
        handler2.shutdown();
        handler3.shutdown();
        handler4.shutdown();
        handler5.shutdown();
        handler6.shutdown();
        handler7.shutdown();
        handler8.shutdown();
        handler9.shutdown();
        handler10.shutdown();
        handler11.shutdown();
        handler12.shutdown();
        handler13.shutdown();

        System.out.println("All threads shut down");
    }
}
