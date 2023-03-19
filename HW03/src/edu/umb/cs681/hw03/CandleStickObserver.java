package edu.umb.cs681.hw03;

public  class CandleStickObserver implements Observer<WkSummary>
{
    public void update(Observable<WkSummary> sender, WkSummary event)
    {
        System.out.println("Weekly Summary: ");
        System.out.println("\t Opening price of the week: "+event.getOpen());
        System.out.println("\t Highest price of the week: "+event.getHigh());
        System.out.println("\t Lowest price of the week: "+event.getLow());
        System.out.println("\t Closing price of the week: "+event.getClose());

    }
}

