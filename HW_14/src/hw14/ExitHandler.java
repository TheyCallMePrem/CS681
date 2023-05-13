package hw14;

public class ExitHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean running = true;

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (running) {
            try {
                monitor.exit();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                stopRunning();
            }
        }
    }

    public void stopRunning() {
        running = false;
        Thread.currentThread().interrupt();
    }
}
