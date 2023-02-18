package hw02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws Exception {
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

        System.out.print("Average price of all cars: ");

        double averagePrice = carList.stream()
                          .map(car -> car.getPrice())
                          .reduce(new CarPriceResultHolder(),
                                  (result, price) -> {
                                      
                                      double total = (result.getAverage() * result.getNumCarExamined()) + price;
                                      result.setNumCarExamined(result.getNumCarExamined() + 1);
                                      result.setAverage(total / (result.getNumCarExamined()));
                                      
                                      return result;
                                  },
                                  
                                  (finalResult, intermediateResult) -> finalResult)
                          .getAverage();

        System.out.println(averagePrice);

    }
}


// double averagePrice= carList.stream()
// .map( car -> car.getPrice() )
// .reduce( 
//     new CarPriceResultHolder(),
//     (result, price)->{.... return result;}, 
//     (finalResult, intermediateResult)->finalResult)
// .getAverage();