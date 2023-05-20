package edu.umb.cs681.hw19;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        StockQuoteObservable stockQuoteObservable = new StockQuoteObservable();
        stockQuoteObservable.addObserver((Observable<StockEvent> ob, StockEvent sev)->{
            System.out.println("Event: " + sev.getTicker() + "\n Sender: " + ob + "\n Quote: " + sev.getQuote() + "\n Thread: " + Thread.currentThread().getName());
        });
        stockQuoteObservable.addObserver((Observable<StockEvent> ob, StockEvent sev)->{
            System.out.println("Event: " + sev.getQuote() + "\n Sender: " + ob + "\n Quote: " + sev.getQuote() + "\n Thread: " + Thread.currentThread().getName());
        });
        stockQuoteObservable.addObserver((Observable<StockEvent> ob, StockEvent sev)->{
            System.out.println("Event: " + sev.getTicker() + "\n Sender: " + ob + "\n Quote: " + sev.getQuote() + "\n Thread: " + Thread.currentThread().getName());
        });
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 12; i++)
        {
            threads.add(new Thread(()->{
                stockQuoteObservable.changeQuote("Dsp", 56770);
                stockQuoteObservable.changeQuote("SFP", 87655);
                try
                {
                    Thread.sleep(230);
                }
                catch (InterruptedException ex)
                {
                    System.out.println(ex);
                }
            }));
        }
        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            try
            {
                t.join();
            }
            catch (InterruptedException ex)
            {
                System.out.println(ex);
            }
        }
    }
}
