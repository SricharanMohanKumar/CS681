package edu.umb.cs681.hw01;

import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Car> CLT = new ArrayList<>();
        ArrayList<Car> Cars = new ArrayList<>();

        Cars.add(new Car("A5", "Audi", 30, 2021, 40000));
        Cars.add(new Car("Accord", "Honda", 50, 2016, 27000));
        Cars.add(new Car("320x", "mazda", 30, 2018, 45000));
        Cars.add(new Car("Ertiga", "MarathuSuzuki", 15, 2020, 25000));
        Cars.add(new Car("EcoSport", "Ford", 23, 2013, 15000));
        Cars.add(new Car("Rav 4", "Toyota", 60, 2015, 5000));

        System.out.println("Sorting cars by year in ascending order");
        var byYearAscending = Cars.stream()
                .sorted(Comparator.comparingInt(Car::Year))
                .collect(Collectors.toList());
        byYearAscending.forEach(System.out::println);
        System.out.println("\nHigh Bound of cars by year");
        var LThreshold =
                Cars.stream()
                .map((Car car)-> {if(car.Year()>2017)
                {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price()));}
                    return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);

        System.out.print("\nMinimum year of the car in High Bound: ");
        var minYear= CLT.stream().map(Car::Year).min(Comparator.comparing((Integer year) -> year)).get();
        System.out.println(minYear);

        System.out.print("\nMaximum year of the car in High Bound: ");
        var maxYear = CLT.stream().map(Car::Year).max(Comparator.comparing((Integer year) -> year)).get();
        System.out.println(maxYear);


        System.out.print("\nAverage Year of all cars in High Bound: ");
        var averageYearH = CLT.stream()
                .map(Car::Year)
                .reduce(new float[2], (result, year) -> {
                    var r = (result[1] * result[0] + year) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averageYearH);

        System.out.print("\nTotal number of cars in High Bound: ");
        var Count = CLT.stream().count();
        System.out.println(Count);


        // Low bound by year
        CLT.clear();
        System.out.println("\nLow Bound of cars by year");
        var ThresholdYearL =
                Cars.stream()
                        .map((Car car)-> {if(car.Year()<2017)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);

        System.out.print("\nMinimum year of the car in Low Bound: ");
        var minYearL= CLT.stream().map(Car::Year).min(Comparator.comparing((Integer year) -> year)).get();
        System.out.println(minYearL);

        System.out.print("\nMaximum year of the car in Low Bound: ");
        var maxYearL = CLT.stream().map(Car::Year).max(Comparator.comparing((Integer year) -> year)).get();
        System.out.println(maxYearL);


        System.out.print("\nAverage Year of all cars in Low Bound: ");
        var averageYearL = CLT.stream()
                .map(Car::Year)
                .reduce(new float[2], (result, year) -> {
                    var r = (result[1] * result[0] + year) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averageYearL);

        System.out.print("\nTotal number of cars in Low Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);

//Sorting by Price
        System.out.println("------------------------------------------------\n\n\nSorting cars by Price in ascending order");
        var byPriceAscending = Cars.stream()
                .sorted(Comparator.comparing(Car::Price))
                .collect(Collectors.toList());
        byPriceAscending.forEach(System.out::println);
        System.out.println("\nHigh Bound of cars by Price");
        CLT.clear();
        var ThresholdpriceH =
                Cars.stream()
                        .map((Car car)-> {if(car.Price()>25000)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);
        //Higher Bound Price
        System.out.print("\nMinimum Price of the car in High Bound: ");
        var minPriceH= CLT.stream().map(Car::Price).min(Comparator.comparing((Float price) -> price)).get();
        System.out.println(minPriceH);

        System.out.print("\nMaximum Price of the car in High Bound: ");
        var maxPriceH = CLT.stream().map(Car::Price).max(Comparator.comparing((Float price) -> price)).get();
        System.out.println(maxPriceH);


        System.out.print("\nAverage Price of all cars in High Bound: ");
        var averagePriceH = CLT.stream()
                .map(Car::Price)
                .reduce(new float[2], (result, price) -> {
                    var r = (result[1] * result[0] + price) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averagePriceH);
        System.out.print("\nTotal number of cars in High Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);
        // Lower bound price
        CLT.clear();
        System.out.println("\nLow Bound of cars by Price");
        var ThresholdPriceL =
                Cars.stream()
                        .map((Car car)-> {if(car.Price()<=25000)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);
        System.out.print("\nTotal number of cars in High Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);

        System.out.print("\nMinimum Price of the car in Low Bound: ");
        var minPriceL= CLT.stream().map(Car::Price).min(Comparator.comparing((Float price) -> price)).get();
        System.out.println(minPriceL);

        System.out.print("\nMaximum Price of the car in Low Bound: ");
        var maxPriceL = CLT.stream().map(Car::Price).max(Comparator.comparing((Float price) -> price)).get();
        System.out.println(maxPriceL);


        System.out.print("\nAverage Price of all cars in Low Bound: ");
        var averagePriceL = CLT.stream()
                .map(Car::Price)
                .reduce(new float[2], (result, price) -> {
                    var r = (result[1] * result[0] + price) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averagePriceL);

        System.out.print("\nTotal number of cars in High Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);
// sorting by milage
        System.out.println("------------------------------------------\n\n\nSorting cars by Milage in ascending order");
        var byMilageAscending = Cars.stream()
                .sorted(Comparator.comparing(Car::Price))
                .collect(Collectors.toList());
        byPriceAscending.forEach(System.out::println);
        System.out.println("\nHigh Bound of cars by Milage");
        CLT.clear();
        var ThresholdmilageH =
                Cars.stream()
                        .map((Car car)-> {if(car.Mileage()>35)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);
        //Higher Bound Mileage
        System.out.print("\nMinimum Mileage of the car in High Bound: ");
        var minMilageH= CLT.stream().map(Car::Mileage).min(Comparator.comparing((Integer mileage) -> mileage)).get();
        System.out.println(minMilageH);

        System.out.print("\nMaximum Mileage of the car in High Bound: ");
        var maxMilageH = CLT.stream().map(Car::Mileage).max(Comparator.comparing((Integer milage) -> milage)).get();
        System.out.println(maxMilageH);


        System.out.print("\nAverage Mileage of all cars in High Bound: ");
        var averageMilageH = CLT.stream()
                .map(Car::Mileage)
                .reduce(new float[2], (result, mileage) -> {
                    var r = (result[1] * result[0] + mileage) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averageMilageH);
        System.out.print("\nTotal number of cars in High Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);
        // Lower bound milage
        CLT.clear();
        System.out.println("\nLow Bound of cars by Mileage");
        var ThresholdMilageL =
                Cars.stream()
                        .map((Car car)-> {if(car.Mileage()<=35)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);

        System.out.print("\nMinimum Mileage of the car in Low Bound: ");
        var minMilageL= CLT.stream().map(Car::Mileage).min(Comparator.comparing((Integer mileage) -> mileage)).get();
        System.out.println(minMilageL);

        System.out.print("\nMaximum Mileage of the car in Low Bound: ");
        var maxMileageL = CLT.stream().map(Car::Mileage).max(Comparator.comparing((Integer mileage) -> mileage)).get();
        System.out.println(maxMileageL);


        System.out.print("\nAverage Mileage of all cars in Low Bound: ");
        var averageMileageL = CLT.stream()
                .map(Car::Mileage)
                .reduce(new float[2], (result, mileage) -> {
                    var r = (result[1] * result[0] + mileage) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averageMileageL);
        System.out.print("\nTotal number of cars in High Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);
//domination count sorting

        System.out.println("---------------------------------------------\n\n\nSorting cars by domination count in ascending order");
        Cars.forEach(car -> {
            car.setCars(Cars);
            car.dominationCount();
        });
        var bycountAscending = Cars.stream()
                .sorted(Comparator.comparingInt(Car::getDominationCount))
                .collect(Collectors.toList());
        bycountAscending.forEach(System.out::println);


        System.out.println("\nHigh Bound of cars by domination");
        CLT.clear();
        var ThresholddominationH =
                Cars.stream()
                        .map((Car car)-> {if(car.getDominationCount()>2)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price(), car.getDominationCount()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);
        //Higher Bound Mileage
        System.out.print("\nMinimum domination of the car in High Bound: ");
        var mindominationH= CLT.stream().map(Car::getDominationCount).min(Comparator.comparing((Integer domination) -> domination)).get();
        System.out.println(mindominationH);

        System.out.print("\nMaximum domination of the car in High Bound: ");
        var maxdominationH = CLT.stream().map(Car::getDominationCount).max(Comparator.comparing((Integer domination) -> domination)).get();
        System.out.println(maxdominationH);


        System.out.print("\nAverage domination of all cars in High Bound: ");
        var averagedominationH = CLT.stream()
                .map(Car::getDominationCount)
                .reduce(new float[2], (result, domination) -> {
                    var r = (result[1] * result[0] + domination) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averagedominationH);
        System.out.print("\nTotal Number of cars in High Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);
        // Lower bound domination
        CLT.clear();
        System.out.println("\nLow Bound of cars by domination");
        var ThresholddominationL =
                Cars.stream()
                        .map((Car car)-> {if(car.getDominationCount()<=2)
                        {CLT.add(new Car(car.Make(), car.Model(), car.Mileage(), car.Year(), car.Price(), car.getDominationCount()));}
                            return CLT;})
                        .collect(Collectors.toList());
        CLT.forEach(System.out::println);

        System.out.print("\nMinimum domination of the car in Low Bound: ");
        var mindominationL= CLT.stream().map(Car::getDominationCount).min(Comparator.comparing((Integer domination) -> domination)).get();
        System.out.println(mindominationL);

        System.out.print("\nMaximum domination of the car in Low Bound: ");
        var maxdominationL = CLT.stream().map(Car::getDominationCount).max(Comparator.comparing((Integer domination) -> domination)).get();
        System.out.println(maxdominationL);


        System.out.print("\nAverage domination of all cars in Low Bound: ");
        var averagedominationL = CLT.stream()
                .map(Car::getDominationCount)
                .reduce(new float[2], (result, domination) -> {
                    var r = (result[1] * result[0] + domination) / ++result[0];
                    return new float[]{result[0]++, r};
                }, (finalResult, intermediateResult) -> finalResult)[1];
        System.out.println(averagedominationL);
        System.out.print("\nTotal number of cars in Low Bound: ");
        Count = CLT.stream().count();
        System.out.println(Count);

    }
}