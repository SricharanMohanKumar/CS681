package edu.umb.cs681.hw03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DJIAWkSummaryObservable extends Observable
{
    List<DSummary> collection=new ArrayList<DSummary>();
    static DJIAWkSummaryObservable observable=new DJIAWkSummaryObservable();
    public static void main(String[] args)
    {
        Path path = Paths.get("src/edu/umb/cs681/hw03/data.csv");
        try( Stream<String> lines = Files.lines(path) )
        {
            List<List<String>> csv = lines.map(line -> {
                return Stream.of( line.split(",") )
                        .map(value->value.substring(0))
                        .collect( Collectors.toList() ); })
                    .collect( Collectors.toList() );
            //csv.forEach(System.out::println);
            List<DSummary> dailySummaryList = csv.stream()
                    .skip(1)
                    .map(row -> {
                        double open = Double.parseDouble(row.get(1));
                        double close = Double.parseDouble(row.get(2));
                        double high = Double.parseDouble(row.get(3));
                        double low = Double.parseDouble(row.get(4));
                        return new DSummary(open, close, high, low);
            }).collect(Collectors.toList());
            dailySummaryList.forEach(row -> observable.addSummary(new DSummary(row.getOpen(), row.getHigh(), row.getLow(), row.getClose())));


        } catch (IOException ex) {}
    }


    public void addSummary(DSummary dSummary)
    {
        collection.add(dSummary);
        if(collection.stream().count()==5)
        {
            Double opv =collection.get(0).getOpen();
            Double clv =collection.get(4).getClose();
            Double H = collection.stream()
                    .map(row -> (row.getHigh()))
                    .max(Comparator.naturalOrder())
                    .orElse(Double.NaN);
            Double L = collection.stream()
                    .map(row -> (row.getLow()))
                    .min(Comparator.naturalOrder())
                    .orElse(Double.NaN);

            CandleStickObserver candle = new CandleStickObserver();

            WkSummary wKSummary =new WkSummary(opv, H, L, clv);
            observable.addObserver(candle);
            observable.notifyObservers(wKSummary);
            collection.clear();

        }
    }


}