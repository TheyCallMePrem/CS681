package hw14;
public class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean running = true;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (running) {
            try {
                monitor.enter();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}
