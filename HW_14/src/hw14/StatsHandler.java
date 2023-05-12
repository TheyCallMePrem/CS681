package hw14;

public class StatsHandler implements Runnable {
    private AdmissionMonitor monitor;
    private volatile boolean running = true;

    public StatsHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (running) {
            int currentVisitors = monitor.countCurrentVisitors();
            
            System.out.println(": Current visitors: " + currentVisitors);
            try {
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
