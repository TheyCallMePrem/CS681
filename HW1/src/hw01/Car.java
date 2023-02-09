package hw01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

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

    public static void main(String[] args) throws Exception{
        ArrayList<Car> carList = new ArrayList<>();

        //  Making car list, the cars which will eventually end up in the "High" group are written first, 
        // followed by the ones that will belong to "Low"

        Car c1 = new Car("BMW", "M8 Coupe", 2019, 10, 131995);
        Car c2 = new Car("Mercedes-Benz", "EQS Sedan", 2021, 20 , 151050 );
        Car c3 = new Car("Bentley", "Continental gt", 2003, 5, 240000  );
        Car c4 = new Car("Nissan ", "Sentra", 2019, 50, 20635);
        Car c5 = new Car("Kia", "Soul ", 1999, 60 , 20505 );
        Car c6 = new Car("Mitsubishi", "Mirage", 1998, 70, 16125  );
        carList.add(c1);
        carList.add(c2);
        carList.add(c3);
        carList.add(c4);
        carList.add(c5);
        carList.add(c6);

        // Once we have the list of cars, we need to compute DominationCount for each car
        
        for (Car car : carList) {
            car.setDominationCount(carList);
        }


        // The threshold that seperates "High" from "Low" are as follows:

        // Price : 100000 (>= 100000 -> High)

        // Year : 2000  (>= 2000 -> High)

        // Milage : 50 (>= 50 -> High)

        // domination Count : 15 ( >=15 High)

        // 

        System.out.println("\n\nSorting cars by price ");
        var priceSort = carList.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice))
                .collect(Collectors.toList());
        priceSort.forEach(System.out::println);

        System.out.println("\n\nSorting cars by year ");
        var yearSort = carList.stream()
                .sorted(Comparator.comparingInt(Car::getYear))
                .collect(Collectors.toList());
        yearSort.forEach(System.out::println);


        System.out.println("\n\nSorting cars by mileage ");
        var MileageSort = carList.stream()
                .sorted(Comparator.comparingInt(Car::getMileage))
                .collect(Collectors.toList());
        MileageSort.forEach(System.out::println);

        System.out.println("\n\nSorting cars by DominationCount ");
        var dominationSort = carList.stream()
                .sorted(Comparator.comparingInt(Car::getDominationCount))
                .collect(Collectors.toList());
        dominationSort.forEach(System.out::println);


        // Filtering results into HIGH and LOW. 

        System.out.println("\n\nPrice 'LOW'");
        carList.stream().filter((Car car)-> car.getPrice() < 100000).forEach(System.out::println);

        System.out.println("\n\nPrice 'HIGH'");
        carList.stream().filter((Car car)-> car.getPrice() >= 100000).forEach(System.out::println);

        System.out.println("\n\nYear 'LOW'");
        carList.stream().filter((Car car)-> car.getYear() < 2000).forEach(System.out::println);

        System.out.println("\n\nYear 'HIGH'");
        carList.stream().filter((Car car)-> car.getYear() >= 2000).forEach(System.out::println);


        System.out.println("\n\nmilage 'LOW'");
        carList.stream().filter((Car car)-> car.getMileage() < 50).forEach(System.out::println);

        System.out.println("\n\nmilage 'HIGH'");
        carList.stream().filter((Car car)-> car.getMileage() >= 50).forEach(System.out::println);

        System.out.println("\n\nDominationCount 'LOW'");
        carList.stream().filter((Car car)-> car.getDominationCount() <1.5).forEach(System.out::println);

        System.out.println("\n\nDominationCount 'HIGH'");
        carList.stream().filter((Car car)-> car.getDominationCount() >= 1.5).forEach(System.out::println);



        // Counting cars in each of the  Price groups
        long Number_Of_Cars_in_HIGH_Price_Group = carList.stream()
        .filter( (Car car)-> car.getPrice()>= 100000 )
        .count();

        long Number_Of_Cars_in_LOW_Price_Group = carList.stream()
        .filter( (Car car)-> car.getPrice() < 100000 )
        .count();

        // Counting cars in each of the year groups
        long Number_Of_Cars_in_HIGH_year_Group = carList.stream()
        .filter( (Car car)-> car.getYear()>= 2000 )
        .count();

        long Number_Of_Cars_in_LOW_year_Group = carList.stream()
        .filter( (Car car)-> car.getYear() < 2000 )
        .count();

        // Counting cars in each of the milage groups
        long Number_Of_Cars_in_HIGH_milage_Group = carList.stream()
        .filter( (Car car)-> car.getMileage()>= 50 )
        .count();

        long Number_Of_Cars_in_LOW_milage_Group = carList.stream()
        .filter( (Car car)-> car.getMileage() < 50 )
        .count();

        // Counting cars in each of the  DominationCount groups
        long Number_Of_Cars_in_HIGH_DominationCount_Group = carList.stream()
        .filter( (Car car)-> car.getDominationCount()>= 1.5 )
        .count();

        long Number_Of_Cars_in_LOW_DominationCount_Group = carList.stream()
        .filter( (Car car)-> car.getDominationCount() < 1.5 )
        .count();

        // Price
        double Avg_High_Price= carList.stream().filter( (Car car)-> car.getPrice()>= 100000 ).mapToDouble((Car car) -> car.getPrice()).average().orElse(0.0);
        double Avg_Low_Price= carList.stream().filter( (Car car)-> car.getPrice()< 100000 ).mapToDouble((Car car) -> car.getPrice()).average().orElse(0.0);
        
        double MIN_High_Price = carList.stream().filter( (Car car)-> car.getPrice()>= 100000 ).mapToDouble((Car car) -> car.getPrice()).min().orElse(Double.MAX_VALUE);
        double MAX_High_Price = carList.stream().filter( (Car car)-> car.getPrice()>= 100000 ).mapToDouble((Car car) -> car.getPrice()).max().orElse(Double.MAX_VALUE);

        double MIN_Low_Price = carList.stream().filter( (Car car)-> car.getPrice()< 100000 ).mapToDouble((Car car) -> car.getPrice()).min().orElse(Double.MAX_VALUE);
        double MAX_Low_Price = carList.stream().filter( (Car car)-> car.getPrice()< 100000 ).mapToDouble((Car car) -> car.getPrice()).max().orElse(Double.MAX_VALUE);
        

        //Year
        
        double Avg_High_year= carList.stream().filter( (Car car)-> car.getYear()>= 2000 ).mapToDouble((Car car) -> car.getYear()).average().orElse(0.0);
        double Avg_Low_year= carList.stream().filter( (Car car)-> car.getYear()< 2000 ).mapToDouble((Car car) -> car.getYear()).average().orElse(0.0);
        double MIN_High_year = carList.stream().filter( (Car car)-> car.getYear()>= 2000 ).mapToDouble((Car car) -> car.getYear()).min().orElse(Double.MAX_VALUE);
        double MAX_High_year = carList.stream().filter( (Car car)-> car.getYear()>= 2000 ).mapToDouble((Car car) -> car.getYear()).max().orElse(Double.MAX_VALUE);
        double MIN_Low_year = carList.stream().filter( (Car car)-> car.getYear()< 2000 ).mapToDouble((Car car) -> car.getYear()).min().orElse(Double.MAX_VALUE);
        double MAX_Low_year = carList.stream().filter( (Car car)-> car.getYear()< 2000 ).mapToDouble((Car car) -> car.getYear()).max().orElse(Double.MAX_VALUE);

        // mileage
        double Avg_High_mileage= carList.stream().filter( (Car car)-> car.getMileage()>= 50 ).mapToDouble((Car car) -> car.getMileage()).average().orElse(0.0);
        double Avg_Low_mileage= carList.stream().filter( (Car car)-> car.getMileage()< 50 ).mapToDouble((Car car) -> car.getMileage()).average().orElse(0.0);
        double MIN_High_mileage = carList.stream().filter( (Car car)-> car.getMileage()>= 50 ).mapToDouble((Car car) -> car.getMileage()).min().orElse(Double.MAX_VALUE);
        double MAX_High_mileage = carList.stream().filter( (Car car)-> car.getMileage()>= 50 ).mapToDouble((Car car) -> car.getMileage()).max().orElse(Double.MAX_VALUE);
        double MIN_Low_mileage = carList.stream().filter( (Car car)-> car.getMileage()< 50 ).mapToDouble((Car car) -> car.getMileage()).min().orElse(Double.MAX_VALUE);
        double MAX_Low_mileage = carList.stream().filter( (Car car)-> car.getMileage()< 50 ).mapToDouble((Car car) -> car.getMileage()).max().orElse(Double.MAX_VALUE);

        // domination count
        double Avg_High_dominationCount= carList.stream().filter( (Car car)-> car.getDominationCount()>= 0.75 ).mapToDouble((Car car) -> car.getDominationCount()).average().orElse(0.0);
        double Avg_Low_dominationCount= carList.stream().filter( (Car car)-> car.getDominationCount()< 0.75 ).mapToDouble((Car car) -> car.getDominationCount()).average().orElse(0.0);
        double MIN_High_dominationCount = carList.stream().filter( (Car car)-> car.getDominationCount()>= 0.75 ).mapToDouble((Car car) -> car.getDominationCount()).min().orElse(Double.MAX_VALUE);
        double MAX_High_dominationCount = carList.stream().filter( (Car car)-> car.getDominationCount()>= 0.75 ).mapToDouble((Car car) -> car.getDominationCount()).max().orElse(Double.MAX_VALUE);
        double MIN_Low_dominationCount = carList.stream().filter( (Car car)-> car.getDominationCount()< 0.75 ).mapToDouble((Car car) -> car.getDominationCount()).min().orElse(Double.MAX_VALUE);
        double MAX_Low_dominationCount = carList.stream().filter( (Car car)-> car.getDominationCount()< 0.75 ).mapToDouble((Car car) -> car.getDominationCount()).max().orElse(Double.MAX_VALUE);
        //Printing min, max and average value for each group of cars

        System.out.print("\n\nPrinting min, max and average value for each group of cars");

        System.out.print("\n\n==========Price==========");
        System.out.printf("\n\nNumber of cars in the 'High' year group : " + Double.toString(Number_Of_Cars_in_HIGH_Price_Group) );
        System.out.printf("\nNumber of cars in the 'Low' year group : " + Double.toString(Number_Of_Cars_in_LOW_Price_Group) );
        System.out.printf("\nAverage of car prices in the 'High' price group : " + Double.toString(Avg_High_Price) );
        System.out.printf("\nAverage of car prices in the 'Low' price group : " + Double.toString(Avg_Low_Price) );
        System.out.printf("\nMin of car price in the 'High' price group : " + Double.toString(MIN_High_Price) );
        System.out.printf("\nMax of car price in the 'High' price group : " + Double.toString(MAX_High_Price) );
        System.out.printf("\nMin of car price in the 'Low' price group : " + Double.toString(MIN_Low_Price) );
        System.out.printf("\nMax of car price in the 'Low' price group : " + Double.toString(MAX_Low_Price) );


        System.out.print("\n\n==========Year==========");
        System.out.printf("\n\nNumber of cars in the 'High' year group : " + Double.toString(Number_Of_Cars_in_HIGH_year_Group) );
        System.out.printf("\nNumber of cars in the 'Low' year group : " + Double.toString(Number_Of_Cars_in_LOW_year_Group) );
        System.out.printf("\nAverage of car year in the 'High' year group : " + Double.toString(Avg_High_year) );
        System.out.printf("\nAverage of car year in the 'Low' year group : " + Double.toString(Avg_Low_year) );
        System.out.printf("\nMin of car year in the 'High' year group : " + Double.toString(MIN_High_year) );
        System.out.printf("\nMax of car year in the 'High' year group : " + Double.toString(MAX_High_year) );
        System.out.printf("\nMin of car year in the 'Low' year group : " + Double.toString(MIN_Low_year) );
        System.out.printf("\nMax of car year in the 'Low' year group : " + Double.toString(MAX_Low_year) );

        System.out.print("\n\n==========Mileage==========");
        System.out.printf("\n\nNumber of cars in the 'High' mileage group : " + Double.toString(Number_Of_Cars_in_HIGH_milage_Group) );
        System.out.printf("\nNumber of cars in the 'Low' mileage group : " + Double.toString(Number_Of_Cars_in_LOW_milage_Group) );
        System.out.printf("\nAverage of car mileage in the 'High' mileage group : " + Double.toString(Avg_High_mileage) );
        System.out.printf("\nAverage of car mileage in the 'Low' mileage group : " + Double.toString(Avg_Low_mileage) );
        System.out.printf("\nMin of car mileage in the 'High' mileage group : " + Double.toString(MIN_High_mileage) );
        System.out.printf("\nMax of car mileage in the 'High' mileage group : " + Double.toString(MAX_High_mileage) );
        System.out.printf("\nMin of car mileage in the 'Low' mileage group : " + Double.toString(MIN_Low_mileage) );
        System.out.printf("\nMax of car mileage in the 'Low' mileage group : " + Double.toString(MAX_Low_mileage) );

        System.out.print("\n\n==========Domination Count==========");
        System.out.printf("\n\nNumber of cars in the 'High' Domination Count group : " + Double.toString(Number_Of_Cars_in_HIGH_DominationCount_Group) );
        System.out.printf("\nNumber of cars in the 'Low' Domination Count group : " + Double.toString(Number_Of_Cars_in_LOW_DominationCount_Group) );
        System.out.printf("\nAverage of car Domination Count in the 'High' Domination Count group : " + Double.toString(Avg_High_dominationCount) );
        System.out.printf("\nAverage of car Domination Count in the 'Low' Domination Count group : " + Double.toString(Avg_Low_dominationCount) );
        System.out.printf("\nMin of car Domination Count in the 'High' Domination Count group : " + Double.toString(MIN_High_dominationCount) );
        System.out.printf("\nMax of car Domination Count in the 'High' Domination Count group : " + Double.toString(MAX_High_dominationCount ) );
        System.out.printf("\nMin of car Domination Count in the 'Low' Domination Count group : " + Double.toString(MIN_Low_dominationCount) );
        System.out.printf("\nMax of car Domination Count in the 'Low' Domination Count group : " + Double.toString(MAX_Low_dominationCount) );
        

    }
}
