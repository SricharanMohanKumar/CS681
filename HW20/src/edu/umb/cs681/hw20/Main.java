package edu.umb.cs681.hw20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("src/edu/umb/cs681/hw20/bos-housing.csv");
        List<List<String>> price = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)) {
            System.out.println("-----------------------------\nData processing 1");
            List<List<String>> matrix =
                    lines.parallel()
                            .map(line -> {
                                return Stream.of(line.split(","))
                                        .map(value -> value.substring(0))
                                        .collect(Collectors.toList());
                            })
                            .collect(Collectors.toList());

            System.out.println("\nAreas/blocks next to Charles river");
            System.out.println(matrix.get(0));
            matrix.stream()
                    .parallel()
                    .filter(row -> row.get(3).contains("1"))
                    .peek(price::add)
                    .forEach(System.out::println);

            System.out.println("\nLowest price of houses in Areas/blocks next to Charles river: ");
            var minprice = price.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minprice);

            System.out.println("\nHighest price of houses in Areas/blocks next to Charles river: ");
            var maxprice = price.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxprice);

            System.out.println("\nAverage price of houses in Areas/blocks next to Charles river: ");
            var avgprice = price.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("\n------------------------------\n Data processing 2");
            List<List<String>> filteredDatacrime = matrix.stream()
                    .parallel()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row.get(0))))
                    .limit((int) Math.ceil(matrix.size() / 10))
                    .collect(Collectors.toList());

            List<List<String>> filteredDatateacher = matrix.stream()
                    .parallel()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row.get(10))))
                    .limit((int) Math.ceil(matrix.size() / 10))
                    .collect(Collectors.toList());

            System.out.println("\nAreas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            List<List<String>> commonData = new ArrayList<>(filteredDatacrime);
            commonData.retainAll(filteredDatateacher);
            System.out.println(matrix.get(0));
            commonData.forEach(System.out::println);

            System.out.println("\nLowest price of houses in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            minprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minprice);

            System.out.println("\nHighest price of houses in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            maxprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxprice);

            System.out.println("\nAverage price of houses in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            avgprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("\nLowest NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            minprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(4)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minprice);

            System.out.println("\nHighest NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            maxprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(4)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxprice);

            System.out.println("\nAverage NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            avgprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(4)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("\nLowest NOX concentration in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            minprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(5)))
                    .min()
                    .orElse(Double.NaN);
            System.out.println(minprice);

            System.out.println("\nHighest number of rooms in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            maxprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(5)))
                    .max()
                    .orElse(Double.NaN);
            System.out.println(maxprice);

            System.out.println("\nAverage number of rooms in areas/blocks within the top 10% of “low” crime rate and the top 10% of “low” pupil-teacher ratio: ");
            avgprice = commonData.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(5)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("-------------------------------\nData processing 3");
            System.out.println("\ntop 10 places with highest tax rate");
            List<List<String>> sortedtax = matrix.stream()
                    .parallel()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> -Double.parseDouble(row.get(9))))
                    .limit(10)
                    .collect(Collectors.toList());
            System.out.println(matrix.get(0));
            sortedtax.forEach(System.out::println);

            System.out.println("\naverage house price of top 10 places with highest tax rate ");
            avgprice = sortedtax.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);
            price.clear();
            System.out.println("\nnumber of cities where the tax is equal to 711: ");
            sortedtax.forEach(row -> {if(row.get(9).contains("711")){price.add(row);}});
            System.out.println(price.stream()
                    .parallel().count());
            System.out.println("-------------------------------\nData processing 4");

            System.out.println("\nTop 5 old houses by age");
            List<List<String>> oldhouse = matrix.stream()
                    .parallel()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> -Double.parseDouble(row.get(6))))
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println(matrix.get(0));
            oldhouse.forEach(System.out::println);

            System.out.println("\nTop 5 new houses by age");
            List<List<String>> newhouse = matrix.stream()
                    .parallel()
                    .skip(1)
                    .sorted(Comparator.comparingDouble(row -> Double.parseDouble(row.get(6))))
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println(matrix.get(0));
            newhouse.forEach(System.out::println);

            System.out.println("\nAverage age of top 5 new houses");
            avgprice = newhouse.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(6)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("\nAverage price of top 5 new houses");
            avgprice = newhouse.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("\nAverage age of top 5 old houses");
            avgprice = oldhouse.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(6)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            System.out.println("\nAverage price of top 5 old houses");
            avgprice = oldhouse.stream()
                    .parallel()
                    .mapToDouble(row -> Double.parseDouble(row.get(13)))
                    .average()
                    .orElse(Double.NaN);
            System.out.println(avgprice);

            price.clear();
            System.out.println("\nNumber of new houses next to Charles river from the top 5 new houses:");
            newhouse.forEach(row -> {if(row.get(3).contains("1")){price.add(row);}});
            System.out.println(price.stream()
                    .parallel().count());
            price.clear();
            System.out.println("\nNumber of old houses next to Charles river from the top 5 old houses:");
            oldhouse.forEach(row -> {if(row.get(3).contains("1")){price.add(row);}});
            System.out.println(price.stream()
                    .parallel().count());
        } catch (IOException ex) {}
    }
}
