package edu.umb.cs681.hw08;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer
{
    private boolean done = false;
    private final ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeFactorizer(int dividend, int from, int to)
    {
        super(dividend, from, to);
    }

    public void setDone()
    {
        lock.lock();
        try
        {
            done = false;
        }
        finally
        {
            lock.unlock();
        }
    }
    public void generatePrimeFactors()
    {
        long divisor = from;
        while (dividend != 1 && divisor <= to)
        {
            lock.lock();
            try
            {
                if (done)
                {
                    System.out.println("Stopped generating primes");
                    break;
                }
                if (divisor > 2 && isEven(divisor))
                {
                    divisor += 1;
                    continue;
                }
                if (dividend % divisor == 0)
                {
                    factors.add(divisor);
                    dividend = dividend/divisor;
                }
                else
                {
                    if (divisor == 2)
                        divisor += 1;
                    else
                        divisor += 2;
                }
            }
            finally
            {
                lock.unlock();
            }
        }
    }
}
