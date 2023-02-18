package hw02;

import java.util.ArrayList;


public class Car {
    private String make, model;
    private int mileage, year;
    private float price;
    private ArrayList<Car> carList;
    private int dominationCount = 0;

    // Public constructor
    public Car(String make, String model, int year, int mileage, float price) {
        super();
        if (make == null || model == null || year == 0 || mileage==0 || price==0.0) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        if (year > 2023) {
            throw new IllegalArgumentException("Illegal input value, year can't be future date ");
        }
        if(year <1886){
            throw new IllegalArgumentException("Illegal input value, first car was invented in 1886 by Karl Benz, year can't be less than 1886");
        }

        if(mileage < 0){
            throw new IllegalArgumentException("Illegal input value, mileage can't be negative value ");
        }

        if(price <0){
            throw new IllegalArgumentException("Illegal input value, price can't be negative value ");

        }
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.price=price;
    }

     // Public getter methods
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public float getPrice() {
        return price;
    }
    
    public ArrayList<Car> getcarList() {
        return carList;
    }
    
    public int getDominationCount() {
        return dominationCount;
    }
    
    // Protected setter methods (Avoided public access specifier on purpose )
    protected void setCars(ArrayList<Car> carList) {
        this.carList = carList;
    }

    //Helper method to calculate and set dominationCount 
    
    public void setDominationCount(ArrayList<Car> cars) {
		this.dominationCount = 0;
		for (Car car : cars) {
			if(car.getYear() >= this.getYear() && car.getMileage() >= this.getMileage() && car.getPrice() >= this.getPrice()) 
            { //  Checking if A’s objective values are superior than, or equal to, B’s in all objectives
                
                    // Checking A’s objective values are superior than B’s in at least one objective
					dominationCount++;
				
			}
		}
	}

    
}
