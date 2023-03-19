package edu.umb.cs681.hw02;

import java.util.ArrayList;

public class Car
{
    private ArrayList<Car> cars;
    private String make,model;
    private int mileage,year;
    private float price;
    private int dominationCount=0;

    public Car(String make, String model, int mileage, int year, float price)
    {
        super();
        this.make = make;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
    }
    public String Make()
    {
        return make;
    }
    public String Model()
    {
        return model;
    }
    public int Mileage()
    {
        return mileage;
    }
    public int Year()
    {
        return year;
    }
    public float Price()
    {
        return price;
    }
    public ArrayList<Car> getCars() {
        return cars;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public void setDominationCount(int dominationCount) {
        this.dominationCount = dominationCount;
    }

    public int dominationCount()
    {
        int count = 0;
        for (Car c : cars)
            if (price >= c.Price() && this.year >= c.Year() && this.mileage >= c.Mileage())
            {
                count++;
            }
        setDominationCount(count);
        return count;
    }
    public static void main(String[] args)
    {
        Car car = new Car("Ertiga", "ZXI",39, 2019,12500);
    }
}
