package hw15;

public class ThreeDObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        String ticker = event.ticker();
        double quote = event.quote();
        System.out.println("ThreeDObserver received event: " + ticker + " " + quote);
    }
}
