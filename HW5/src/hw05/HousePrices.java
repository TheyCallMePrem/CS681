package hw05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HousePrices {

    public static void main(String[] args) {

        Path path = Paths.get("bos-housing.csv");
        List<List<Object>> data;
        try (var lines = Files.lines(path)) {
            data = lines
                    .skip(1)
                    .map(line -> {
                        String[] values = line.split(",");
                        return List.of(
                                (Object) Double.parseDouble(values[0]), // CRIM
                                (Object) Double.parseDouble(values[1]), // ZN
                                (Object) Double.parseDouble(values[2]), // INDUS
                                (Object) (values[3].substring(1, 2).equals("1") ? true : false),   // CHAS
                                (Object) Double.parseDouble(values[4]), // NOX
                                (Object) Double.parseDouble(values[5]), // RM
                                (Object) Double.parseDouble(values[6]), // AGE
                                (Object) Double.parseDouble(values[7]), // DIS
                                (Object) Integer.parseInt(values[8]),   // RAD
                                (Object) Integer.parseInt(values[9]),   // TAX
                                (Object) Double.parseDouble(values[10]),// PTRATIO
                                (Object) Double.parseDouble(values[11]),// B
                                (Object) Double.parseDouble(values[12]),// LSTAT
                                (Object) Double.parseDouble(values[13]) // MEDV
                        );
                    })
                    .collect(Collectors.toList());
            
            System.out.println("For every output I have written 'DPx' where x is the data processing identity it belongs to, this is done to keep a track of output as multithreaded  appraoch messes up the sequence of execution");
            // Data processing task 1
            Thread thread1 = new Thread(() -> {
                var charlesRiverBlocks = data.stream()
                        .filter(list -> list.get(3).equals(true))
                        .collect(Collectors.toList());

                double highestPrice = charlesRiverBlocks.stream()
                        .mapToDouble(list -> (double) list.get(13))
                        .max()
                        .orElse(Double.NaN);
                double lowestPrice = charlesRiverBlocks.stream()
                        .mapToDouble(list -> (double) list.get(13))
                        .min()
                        .orElse(Double.NaN);
                double averagePrice = charlesRiverBlocks.stream()
                        .mapToDouble(list -> (double) list.get(13))
                        .average()
                        .orElse(Double.NaN);

                System.out.println("DP1. Data Processing #1 : \n Identify the areas/blocks next to Charles river. \n Compute the highest, lowest and average price of those houses. \n");
                System.out.println("DP1. Number of blocks next to Charles river: " + charlesRiverBlocks.size());
                System.out.println("DP1. Highest price of houses next to Charles river: " + highestPrice);
                System.out.println("DP1. Lowest price of houses next to Charles river: " + lowestPrice);
                System.out.println("DP1. Average price of houses next to Charles river: " + averagePrice);
            });

            // Data processing task 2
            Thread thread2 = new Thread(() -> {
                List<List<Object>> sortedDataByCrimeRate = data.stream()
                        .sorted(Comparator.comparingDouble(row -> (double) row.get(0)))
                        .collect(Collectors.toList());

                int numLowestCrimeRateRows = (int) Math.ceil(data.size() * 0.1);

                List<List<Object>> lowestCrimeRateData =            sortedDataByCrimeRate.stream()
                .limit(numLowestCrimeRateRows)
                .collect(Collectors.toList());

        double averageNumRooms = lowestCrimeRateData.stream()
                .mapToDouble(list -> (double) list.get(5))
                .average()
                .orElse(Double.NaN);

        System.out.println("\n\nDP2. Data Processing #2 : \n Identify the areas/blocks with lowest crime rate. \n Compute the average number of rooms in houses in those areas. \n");
        System.out.println("DP2. Number of rows representing 10% of the total data: " + numLowestCrimeRateRows);
        System.out.println("DP2. Average number of rooms in houses in areas with lowest crime rate: " + averageNumRooms);
    });

    Thread thread3 = new Thread(() -> {
        List<List<Object>> sortedDataByPropertyValue = data.stream()
                .sorted(Comparator.comparingDouble(row -> (double) row.get(13)))
                .collect(Collectors.toList());

        int numHighestPropertyValueRows = (int) Math.ceil(data.size() * 0.05);

        List<List<Object>> highestPropertyValueData =
                sortedDataByPropertyValue.stream()
                        .skip(data.size() - numHighestPropertyValueRows)
                        .collect(Collectors.toList());

        double averageAge = highestPropertyValueData.stream()
                .mapToDouble(list -> (double) list.get(6))
                .average()
                .orElse(Double.NaN);

        System.out.println("\n\nDP3. Data Processing #3 : \n Identify the most expensive houses. \n Compute the average age of those houses. \n");
        System.out.println("DP3. Number of rows representing 5% of the total data: " + numHighestPropertyValueRows);
        System.out.println("DP3. Average age of the most expensive houses: " + averageAge);
    });

    // Data processing task 4
    Thread thread4 = new Thread(() -> {
        List<Double> pricePerRoom = data.stream()
        .map(row -> (double) row.get(13) / (double) row.get(5))
        .collect(Collectors.toList());

    double costliestPricePerRoom = Collections.max(pricePerRoom);
    double cheapestPricePerRoom = Collections.min(pricePerRoom);
    double avgPricePerRoom = pricePerRoom.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

    System.out.println("\nDP4. Data Processing #4 : \nI have computed the costliest, cheapest, and average price of houses per room. \n");
    System.out.println("DP4. Costliest price per room: " + costliestPricePerRoom);
    System.out.println("DP4. Cheapest price per room: " + cheapestPricePerRoom);
    System.out.println("DP4. Average price per room: " + avgPricePerRoom);
    });

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    try {
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

        } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
