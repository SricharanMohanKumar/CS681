package edu.umb.cs681.hw19;
public class StockEvent
{
    private String ticker;
    private Double quote;

    public StockEvent(String t, Double q)
    {
        this.ticker = t;
        this.quote = q;
    }

    public String getTicker()
    {
        return ticker;
    }

    public Double getQuote()
    {
        return quote;
    }

}
