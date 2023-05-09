package hw20;

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

                    var charlesRiverBlocks = data.parallelStream()
                    .filter(list -> list.get(3).equals(true))
                    .collect(Collectors.toList());
        
            double highestPrice = charlesRiverBlocks.parallelStream()
                    .mapToDouble(list -> (double) list.get(13))
                    .max()
                    .orElse(Double.NaN);
            double lowestPrice = charlesRiverBlocks.parallelStream()
                    .mapToDouble(list -> (double) list.get(13))
                    .min()
                    .orElse(Double.NaN);
            double averagePrice = charlesRiverBlocks.parallelStream()
                    .mapToDouble(list -> (double) list.get(13))
                    .average()
                    .orElse(Double.NaN);
        
            System.out.println("Data Processing #1 : \n Identify the areas/blocks next to Charles river. \n Compute the highest, lowest and average price of those houses. \n");
            System.out.println("Number of blocks next to Charles river: " + charlesRiverBlocks.size());
            System.out.println("Highest price of houses next to Charles river: " + highestPrice);
            System.out.println("Lowest price of houses next to Charles river: " + lowestPrice);
            System.out.println("Average price of houses next to Charles river: " + averagePrice);

            List<List<Object>> sortedDataByCrimeRate = data.parallelStream()
            .sorted(Comparator.comparingDouble(row -> (double) row.get(0)))
            .collect(Collectors.toList());

        int numLowestCrimeRateRows = (int) Math.ceil(data.size() * 0.1);

        List<List<Object>> lowestCrimeRateRows = sortedDataByCrimeRate.parallelStream()
            .limit(numLowestCrimeRateRows)
            .collect(Collectors.toList());

        List<List<Object>> sortedDataByPupilTeacherRatio = data.parallelStream()
            .sorted(Comparator.comparingDouble(row -> (double) row.get(10)))
            .collect(Collectors.toList());

        int numLowestPupilTeacherRatioRows = (int) Math.ceil(data.size() * 0.1);

        List<List<Object>> lowestPupilTeacherRatioRows = sortedDataByPupilTeacherRatio.parallelStream()
            .limit(numLowestPupilTeacherRatioRows)
            .collect(Collectors.toList());

        System.out.println("\nData Processing #2 : \n  Identify the areas/blocks within the top (lowest) 10% of  “low” crime rate and the top (lowest) 10% of pupil-teacher ratio. \n  Compute the max, min and average of:\n   -> Price\n   -> NOX concentration\n   -> # of rooms \n");
        System.out.println("\n Areas/blocks with lowest crime rate: \n");
        for (List<Object> row : lowestCrimeRateRows) {
          System.out.println(row);
        }

        System.out.println("\n Areas/blocks with lowest pupil-teacher ratio: \n ");
        for (List<Object> row : lowestPupilTeacherRatioRows) {
            System.out.println(row);
        }

        List<Double> prices = data.parallelStream()
                .map(row -> (double) row.get(13))
                .collect(Collectors.toList());

        double maxPrice = prices.parallelStream().mapToDouble(Double::doubleValue).max().orElse(0);
        double minPrice = prices.parallelStream().mapToDouble(Double::doubleValue).min().orElse(0);
        double avgPrice = prices.parallelStream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

        List<Double> noxConcentrations = data.parallelStream()
                .map(row -> (double) row.get(4))
                .collect(Collectors.toList());

        double maxNOX = noxConcentrations.parallelStream().mapToDouble(Double::doubleValue).max().orElse(0);
        double minNOX = noxConcentrations.parallelStream().mapToDouble(Double::doubleValue).min().orElse(0);
        double avgNOX = noxConcentrations.parallelStream().mapToDouble(Double::doubleValue).average().orElse(0);

        List<Double> numRooms = data.parallelStream()
                .map(row -> (double) row.get(5))
                .collect(Collectors.toList());

        double maxNumRooms = numRooms.parallelStream().mapToDouble(Double::doubleValue).max().orElse(0);
        double minNumRooms = numRooms.parallelStream().mapToDouble(Double::doubleValue).min().orElse(0);
        double avgNumRooms = numRooms.parallelStream().mapToDouble(Double::doubleValue).average().orElse(0);

        System.out.println("\nMax, min, and average of price:");
        System.out.println("Max: " + maxPrice);
        System.out.println("Min: " + minPrice);
        System.out.println("Average: " + avgPrice);

        System.out.println("\nMax, min, and average of NOX concentration:");
        System.out.println("Max: " + maxNOX);
        System.out.println("Min: " + minNOX);
        System.out.println("Average: " + avgNOX);

        System.out.println("\nMax, min, and average of number of rooms:");
        System.out.println("Max: " + maxNumRooms);
        System.out.println("Min: " + minNumRooms);
        System.out.println("Average: " + avgNumRooms);



        List<Double> ages = data.parallelStream()
        .map(list -> (Double) list.get(6))
        .collect(Collectors.toList());

        double minAge = Collections.min(ages);
        double maxAge = Collections.max(ages);
        double avgAge = ages.parallelStream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        System.out.println("\nData Processing #3 : \nI have computed newest, oldest and average age among all houses in the dataset, the column used is house age (AGE)");
        System.out.println("Minimum age: " + minAge);
        System.out.println("Maximum age: " + maxAge);
        System.out.println("Average age: " + avgAge);

        List<Double> pricePerRoom = data.parallelStream()
        .map(row -> (double) row.get(13) / (double) row.get(5))
        .collect(Collectors.toList());

        double costliestPricePerRoom = Collections.max(pricePerRoom);
        double cheapestPricePerRoom = Collections.min(pricePerRoom);
        double avgPricePerRoom = pricePerRoom.parallelStream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);

        System.out.println("\nData Processing #4 : \nI have computed the costliest, cheapest, and average price of houses per room. \n");
        System.out.println("Costliest price per room: " + costliestPricePerRoom);
        System.out.println("Cheapest price per room: " + cheapestPricePerRoom);
        System.out.println("Average price per room: " + avgPricePerRoom);

        } catch (IOException ex) {
            System.out.println("An exception occurred while processing the file: " + ex.getMessage());
        }
    }
}
