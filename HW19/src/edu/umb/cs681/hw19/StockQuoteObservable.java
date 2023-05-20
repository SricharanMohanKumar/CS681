package edu.umb.cs681.hw19;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable<StockEvent>
{

    private Map<String, Double> quotes = new HashMap<String, Double>();
    private ReentrantLock lock = new ReentrantLock();

    public void changeQuote(String t, double q)
    {
        lock.lock();
        try
        {
            quotes.put(t, q);
            setChanged();
        }
        finally
        {
            lock.unlock();
        }
        notifyObservers(new StockEvent(t, q));
    }

}
