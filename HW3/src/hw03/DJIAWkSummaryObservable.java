package hw03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DJIAWkSummaryObservable extends Observable<WkSummary> {
    private static List<List<Double>> LastFiveDaysData = new ArrayList<>();

    public void addData(List<Double> data) {
        if (LastFiveDaysData.size() == 5) {
            WkSummary summary = calculateSummary(LastFiveDaysData);
            notifyObservers(summary);
            LastFiveDaysData.clear();
        }
        LastFiveDaysData.add(data);
    }

    private WkSummary calculateSummary(List<List<Double>> data) {
        double open = data.get(0).get(0);
        double close = data.get(data.size() - 1).get(3);
        double high = data.stream().flatMap(List::stream).max(Double::compare).orElse(0.0);
        double low = data.stream().flatMap(List::stream).min(Double::compare).orElse(0.0);
        return new WkSummary(open, close, high, low, data);
    }

    

    public static void main(String[] args) throws IOException {

        DJIAWkSummaryObservable DJIAobservable = new DJIAWkSummaryObservable();
        
        CandleStickObserver CSO = new CandleStickObserver();

        DJIAobservable.addObserver(CSO);

        Path path = Paths.get("HistoricalPrices.csv");
        List<List<Double>> lastFiveDaysData;
        try (Stream<String> lines = Files.lines(path)) {
            lastFiveDaysData = lines
                    .skip(1)
                    .map(line -> {
                        String[] values = line.split(",");
                        return Stream.of(values)
                                .skip(1)
                                .map(Double::parseDouble)
                                .collect(Collectors.toList());
                    })
                    .limit(5)
                    .collect(Collectors.toList());

                    lastFiveDaysData.forEach(System.out::println);
                    CSO.update(DJIAobservable, DJIAobservable.calculateSummary(lastFiveDaysData));
                    
        }catch (IOException ex) {}

        
        

    }
}
