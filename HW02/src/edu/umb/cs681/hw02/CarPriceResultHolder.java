package edu.umb.cs681.hw02;
import java.util.*;
public class CarPriceResultHolder
{
    private int numCarExamined;
    private double average;
    public static void main(String[] args)
    {
        ArrayList<Car> Cars = new ArrayList<>();
        Cars.add(new Car("A5", "Audi", 30, 2021, 40000));
        Cars.add(new Car("Accord", "Honda", 50, 2016, 27000));
        Cars.add(new Car("320x", "mazda", 30, 2018, 45000));
        Cars.add(new Car("Ertiga", "MarathuSuzuki", 15, 2020, 25000));
        Cars.add(new Car("EcoSport", "Ford", 23, 2013, 15000));
        Cars.add(new Car("Rav 4", "Toyota", 60, 2015, 5000));
        double averagePrice = Cars.stream()
                .map(Car::Price)
                .reduce( new CarPriceResultHolder(),
                        (result, price)->
                        {
                            result.average = (result.numCarExamined*result.average+price)/(++result.numCarExamined);
                            return result;
                        },(finalResult, intermediateResult)->finalResult).getAverage();
        System.out.println("\nAverage Car Price is: "+averagePrice);
    }
    private double getAverage()
    {
        return average;
    }
}