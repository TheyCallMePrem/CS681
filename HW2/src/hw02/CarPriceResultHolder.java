package hw02;

//This class will be used to keep track of the number of elements processed from the stream and their average
public class CarPriceResultHolder {
    private int numCarExamined;
    private double average;

    public CarPriceResultHolder() {
        numCarExamined = 0;
        average = 0.0;
    }

    public int getNumCarExamined() {
        return numCarExamined;
    }

    public void setNumCarExamined(int numCarExamined) {
        this.numCarExamined = numCarExamined;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
