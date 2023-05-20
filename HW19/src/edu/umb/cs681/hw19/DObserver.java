package edu.umb.cs681.hw19;

public class DObserver extends Observable<Double>
{
    private double quote;

    public void changeQuote(double qu)
    {
        quote = qu;
        notifyObservers(quote);
    }

    public static void main(String args[])
    {
        DObserver observable = new DObserver();
        observable.addObserver( new LineChartObserver() );
        observable.addObserver( new TableObserver() );
        System.out.println("Count of Observer objects before adding "+observable.countObservers());
        Observer<Double> objectToadd = ((Observable<Double> object, Double a) -> {System.out.println("Event: " + a + ", Sender: " + object);});
        observable.addObserver(objectToadd);
        observable.changeQuote(5675.87);
        observable.changeQuote(3675.49);
        System.out.println("Count of Observer objects after adding "+observable.countObservers());
        observable.removeObserver(objectToadd);
        System.out.println("Count of Observer objects after removing "+observable.countObservers());

    }

}
