package hw03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("HistoricalPrices.csv");
        try (Stream<String> lines = Files.lines(path)) {
            List<List<Double>> LastFiveDaysData = lines
                                                    .skip(1)
                                                    .map(line -> {
                                                            String[] values = line.split(",");
                                                            return Stream.of(values)
                                                            .skip(1) // skip the first column because it contains dates, we want double datatype
                                                            .map(Double::parseDouble)
                                                            .collect(Collectors.toList());
                                                        })
                                                    .limit(5) // limit to the top 5 rows
                                                    .collect(Collectors.toList());
                    
                    //Finding Min, max values
                    Double minValue = LastFiveDaysData.stream()
                                                  .flatMap(List::stream)
                                                  .min(Double::compareTo)
                                                  .orElse(Double.NaN);

                    Double maxValue = LastFiveDaysData.stream()
                                                  .flatMap(List::stream)
                                                  .max(Double::compareTo)
                                                  .orElse(Double.NaN);
                    // Print the converted CSV data using Stream API
                    LastFiveDaysData.forEach(System.out::println);
                    System.out.println("Weekly Summary :");

                    System.out.println(" Open : The 9am-price on the 1st day = " + LastFiveDaysData.get(4).get(0));
                    System.out.println(" Close : The 4pm-price on the last (5th) day = " + LastFiveDaysData.get(0).get(3));
                    System.out.println(" High : The highest price during 5days = " + maxValue);
                    System.out.println(" Low : The lowest price during 5 days. = " + minValue);
                } 
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    
    }
}
