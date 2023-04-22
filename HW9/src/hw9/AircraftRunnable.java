package hw9;

// I have used the fact that since Record Type is convenience mechanism to implement immutable classes,
// so if isRecord() is true, it means it must be immutable, hence I have printed it
public class AircraftRunnable {
    public static void main(String[] args) {
        Aircraft aircraft = new Aircraft(new Position(0, 0, 0));
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    aircraft.setPosition(i, i, i);
                    Position position = aircraft.getPosition();
                    System.out.println(Thread.currentThread().getName() + ": " + position.coordinate() 
                            + " (Immutable: " + (position.getClass().isRecord() ? "Yes" : "No") + ")");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
