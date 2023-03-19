package edu.umb.cs681.hw05;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException
    {
        var path = Paths.get("src/edu/umb/cs681/hw05/bos-housing.csv");
        List<List<String>> price = new ArrayList<>();
        try(Stream<String> lines = Files.lines(path) )
        {
            List<List<String>> matrix =
                    lines.map( line -> {
                        return Stream.of( line.split(",") )
                                .map(value->value.substring(0))
                                .collect( Collectors.toList() );
                    }) .collect( Collectors.toList() );

            Thread thread1 = new Thread(()->{
            System.out.println("-----------------------------\nData processing 1");
                long threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);
            System.out.println("\nAreas/blocks next to Charles river");
            System.out.println(matrix.get(0));
            matrix.forEach(row ->{if(row.get(3).contains("1")){System.out.println(row);price.add(row);}});
            System.out.println("\nLowest price of houses in Areas/blocks next to Charles river: ");
            var minprice= price.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minprice);
            System.out.println("\nHighest price of houses in Areas/blocks next to Charles river: ");
            var maxprice= price.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxprice);
            System.out.println("\nAverage price of houses in Areas/blocks next to Charles river: ");
            var avgprice= price.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);
                threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);});
            Thread thread2 = new Thread(()-> {
            System.out.println("\n------------------------------\n Data processing 2");
                long threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);
            List<List<String>> filteredDatacrime = matrix.stream()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row.get(0))))
                    .limit((int) Math.ceil(matrix.size() / 10))
                    .collect(Collectors.toList());
            //System.out.println(filteredDatacrime.stream().count());

            List<List<String>> filteredDatateacher = matrix.stream()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row.get(10))))
                    .limit((int) Math.ceil(matrix.size()/10))
                    .collect(Collectors.toList());
            //System.out.println(filteredDatateacher);
            System.out.println("\nAreas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            List<List<String>> commonData = new ArrayList<>(filteredDatacrime);
            commonData.retainAll(filteredDatateacher);
            System.out.println(matrix.get(0));
            commonData.forEach(System.out::println);

//            List<List<String>> combinedList = new ArrayList<>();
//            combinedList.addAll(filteredDatacrime);
//            combinedList.addAll(filteredDatateacher);
//
//            List<List<String>> uniqueList = combinedList.stream()
//                    .distinct()
//                    .collect(Collectors.toList());
            System.out.println("\nLowest price of houses in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            var minaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minaf);
            System.out.println("\nHighest price of houses in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            var maxaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxaf);
            System.out.println("\nAverage price of houses in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            var avgaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgaf);
            System.out.println("\nLowest NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            minaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(4)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minaf);
            System.out.println("\nHighest NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            maxaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(4)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxaf);
            System.out.println("\nAverage NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            avgaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(4)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgaf);
            System.out.println("\nLowest NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            minaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(5)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minaf);
            System.out.println("\nHighest number of rooms in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            maxaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(5)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxaf);
            System.out.println("\nAverage number of rooms in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            avgaf= commonData.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(5)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgaf);
                threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);});

            Thread thread3 = new Thread(() -> {
            System.out.println("-------------------------------\nData processing 3");
                long threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);
            System.out.println("\ntop 10 places with highest tax rate");
            List<List<String>> sortedtax = matrix.stream()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> -Double.parseDouble(row.get(9))))
                    .limit(10)
                    .collect(Collectors.toList());
            System.out.println(matrix.get(0));
            sortedtax.forEach(System.out::println);
            System.out.println("\naverage house price of top 10 places with highest tax rate ");
            var avgp= sortedtax.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgp);
            price.clear();
            System.out.println("\nnumber of cities where the tax is equal to 711: ");
            sortedtax.forEach(row -> {if(row.get(9).contains("711")){price.add(row);}});
            System.out.println(price.stream().count());
                threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);});
            Thread thread4 = new Thread(() -> {
            System.out.println("-------------------------------\nData processing 4");
                long threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);

            System.out.println("\nTop 5 old houses by age");
            List<List<String>> oldhouse = matrix.stream()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> -Double.parseDouble(row.get(6))))
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println(matrix.get(0));
            oldhouse.forEach(System.out::println);
            System.out.println("\nTop 5 new houses by age");
            List<List<String>> newhouse = matrix.stream()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row.get(6))))
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println(matrix.get(0));
            newhouse.forEach(System.out::println);

            System.out.println("\nAverage age of top 5 new houses");
            var avgage= newhouse.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(6)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgage);

            System.out.println("\nAverage price of top 5 new houses");
            var avgpn= newhouse.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgpn);

            System.out.println("\nAverage age of top 5 old houses");
            var avgao= oldhouse.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(6)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgao);

            System.out.println("\nAverage price of top 5 old houses");
            var avgpo= oldhouse.stream()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgpo);
            price.clear();
            System.out.println("\nNumber of new houses next to Charles river from the top 5 new houses:");
            newhouse.forEach(row ->{if(row.get(3).contains("1")){price.add(row);}});
            System.out.println(price.stream().count());
            price.clear();
            System.out.println("\nNumber of old houses next to Charles river from the top 5 old houses:");
            oldhouse.forEach(row ->{if(row.get(3).contains("1")){price.add(row);}});
            System.out.println(price.stream().count());
                threadId = Thread.currentThread().getId();
                System.out.println("ThreadId currently running: " + threadId);});
            try{
                thread1.start();
                thread1.join();
                thread2.start();
                thread2.join();
                thread3.start();
                thread3.join();
                thread4.start();
                thread4.join();
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

}