package hw14;
public class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                monitor.enter();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
