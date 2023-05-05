package hw14;

public class StatsHandler implements Runnable {
    private AdmissionMonitor monitor;

    public StatsHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            int currentVisitors = monitor.countCurrentVisitors();
            
            System.out.println(": Current visitors: " + currentVisitors);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
