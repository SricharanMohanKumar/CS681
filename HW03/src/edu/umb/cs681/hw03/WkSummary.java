package edu.umb.cs681.hw03;

public class WkSummary
{
    double open;
    double close;
    double high;
    double low;
    public WkSummary(Double open, Double high, Double low, Double close)
    {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
    }
    double getOpen() {

        return open;
    }
    double getClose() {

        return close;
    }
    double getHigh() {

        return high;
    }
    double getLow() {

        return low;
    }
}
