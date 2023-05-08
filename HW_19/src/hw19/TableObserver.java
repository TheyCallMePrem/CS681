package hw19;


public class TableObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        String ticker = event.ticker();
        double quote = event.quote();
        System.out.println("TableObserver received event: " + ticker + " " + quote);
    }
}
