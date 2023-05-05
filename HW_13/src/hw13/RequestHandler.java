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
        long timeout = System.currentTimeMillis() + 5000;
        while (running && System.currentTimeMillis() < timeout) {
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
    
        handler1.stop();
        handler2.stop();
        handler3.stop();
        handler4.stop();
        handler5.stop();
        handler6.stop();
        handler7.stop();
        handler8.stop();
        handler9.stop();
        handler10.stop();
        handler11.stop();
        handler12.stop();
        handler13.stop();
    
        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        thread4.interrupt();
        thread5.interrupt();
        thread6.interrupt();
        thread7.interrupt();
        thread8.interrupt();
        thread9.interrupt();
        thread10.interrupt();
        thread11.interrupt();
        thread12.interrupt();
        thread13.interrupt();
    
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();
        thread11.join();
        thread12.join();
        thread13.join();
    
        System.out.println("All threads interrupted");
    
    }
    
}
