package hw03;


import java.util.List;

public class WkSummary extends Summary {

    private List<List<Double>> lastFiveDaysData;

    public WkSummary(double open, double close, double high, double low, List<List<Double>> lastFiveDaysData) {
        super(open, close, high, low);
        this.lastFiveDaysData = lastFiveDaysData;
    }

    public List<List<Double>> getLastFiveDaysData() {
        return lastFiveDaysData;
    }
    public WkSummary(double open, double close, double high, double low) {
        super(open, close, high, low);
    }

    public double getMinValue() {
        double minValue = Double.MAX_VALUE;
        for (List<Double> row : lastFiveDaysData) {
            for (Double value : row) {
                if (value < minValue) {
                    minValue = value;
                }
            }
        }
        return minValue;
    }
    public void setLastFiveDaysData(List<List<Double>> lastFiveDaysData) {
        this.lastFiveDaysData = lastFiveDaysData;
    }
    public double getMaxValue() {
        double maxValue = Double.MIN_VALUE;
        for (List<Double> row : lastFiveDaysData) {
            for (Double value : row) {
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }
        return maxValue;
    }
}
