package edu.umb.cs681.hw19;

public class LineChartObserver implements Observer<Double>
{
    public void update(Observable<Double> s, Double e)
    {
        System.out.println("Event: " + e + "/nSender: " + s);
    }
}
