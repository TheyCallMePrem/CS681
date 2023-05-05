package hw14;

public class ExitHandler implements Runnable {
    private AdmissionMonitor monitor;

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                monitor.exit();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
