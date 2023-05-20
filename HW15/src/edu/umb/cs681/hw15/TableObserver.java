package edu.umb.cs681.hw15;

public class TableObserver implements Observer<Double>
{
    public void update(Observable<Double> s, Double e)
    {
        System.out.println("Event: " + e + "\n Sender: " + s);
    }
}
