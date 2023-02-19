package hw03;


import java.util.List;

public class CandleStickObserver implements Observer<WkSummary> {

    @Override
    public void update(Observable<WkSummary> sender, WkSummary wkSummary) {
        List<List<Double>> lastFiveDaysData = wkSummary.getLastFiveDaysData();
        double minValue = wkSummary.getMinValue();
        double maxValue = wkSummary.getMaxValue();
        double open = lastFiveDaysData.get(0).get(3);
        double close = lastFiveDaysData.get(4).get(0);
        
        System.out.println("Min Value: " + minValue);
        System.out.println("Max Value: " + maxValue);
        System.out.println("Open: " + open);
        System.out.println("Close: " + close);
    }

    public void update(double open, double close, double high, double low) {
        System.out.println(" Open : The 9am-price on the 1st day = " + open);
        System.out.println(" Close : The 4pm-price on the last (5th) day = " + close);
        System.out.println(" High : The highest price during 5days = " + high);
        System.out.println(" Low : The lowest price during 5 days. = " + low);
    }
}
