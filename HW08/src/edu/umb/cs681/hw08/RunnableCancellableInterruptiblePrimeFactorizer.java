package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellableInterruptiblePrimeFactorizer(int dividend, int from, int to)
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
                    System.out.println("Stopped generating factors");
                    this.factors.clear();
                    break;
                }
                if (divisor > 2 && isEven(divisor))
                {
                    divisor += 1;
                    continue;
                }
                if (dividend % divisor == 0) {
                    factors.add(divisor);
                    System.out.println(divisor);
                    dividend = dividend/divisor;
                }
                else
                {
                    if (divisor == 2)
                    {
                        divisor += 1;
                    }
                    else
                    {
                        divisor += 2;
                    }
                }
            }
            finally
            {
                lock.unlock();
            }

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e.toString());
                continue;
            }
        }
    }

    public static void main(String[] args)
    {
        var gen = new RunnableCancellableInterruptiblePrimeFactorizer(84, 2, 84/2);
        Thread thread = new Thread(gen);
        thread.start();
        try
        {
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        gen.setDone();
        thread.interrupt();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("\n Prime factors of 84 are " + gen.getPrimeFactors());
    }

}