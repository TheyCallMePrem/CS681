package hw15;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int NUM_OBSERVABLES = 5;
    private static final int NUM_LINE_CHART_OBSERVERS = 10;
    private static final int NUM_TABLE_OBSERVERS = 2;
    private static final int NUM_3D_OBSERVERS = 1;

    public static void main(String[] args) throws InterruptedException {
        // Create observables and observers
        StockQuoteObservable[] observables = new StockQuoteObservable[NUM_OBSERVABLES];
        List<Observer<StockEvent>> observers = new ArrayList<>();
        for (int i = 0; i < NUM_OBSERVABLES; i++) {
            observables[i] = new StockQuoteObservable();
        }
        for (int i = 0; i < NUM_LINE_CHART_OBSERVERS; i++) {
            observers.add(new LineChartObserver());
        }
        for (int i = 0; i < NUM_TABLE_OBSERVERS; i++) {
            observers.add(new TableObserver());
        }
        for (int i = 0; i < NUM_3D_OBSERVERS; i++) {
            observers.add(new ThreeDObserver());
        }

        // Subscribe observers to observables
        Random random = new Random();
        for (Observer<StockEvent> observer : observers) {
            for (int i = 0; i < 2; i++) {
                int index = random.nextInt(NUM_OBSERVABLES);
                observables[index].addObserver(observer);
            }
        

        // Start threads
        Thread thread1 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread3");
        thread3.start();

        Thread thread4 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread4");
        thread4.start();

        Thread thread5 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread5");
        thread5.start();

        Thread thread6 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread6");
        thread6.start();

        Thread thread7 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread7");
        thread7.start();

        Thread thread8 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread8");
        thread8.start();

        Thread thread9 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread9");
        thread9.start();

        Thread thread10 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread10");
        thread10.start();

        Thread thread11 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread11");
        thread11.start();

        Thread thread12 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread12");
        thread12.start();

        Thread thread13 = new Thread(() -> {
            Random random1 = new Random();
            Random random2 = new Random();
            for (int j = 0; j < 10; j++) {
                int index = random1.nextInt(NUM_OBSERVABLES);
                String ticker = "TICKER" + random2.nextInt(10);
                double quote = random2.nextDouble() * 100;
                observables[index].changeQuote(ticker, quote);
            }
        }, "thread13");
        thread13.start();

    
    }
    }
}

