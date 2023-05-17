package edu.umb.cs681.primes.hw06_1;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator
{
    private final ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeGenerator(long from, long to)
    {
        super(from, to);
    }

    public void setDone()
    {
        lock.lock();
        try
        {
            done = true;
        }
        finally
        {
            lock.unlock();
        }
    }

    public void generatePrimes()
    {
        for (long n = from; n <= to; n++)
        {
            lock.lock();
            try
            {
                // Stop generating prime numbers if done==true
                if(done){
                    System.out.println("Stopped generating prime numbers.");
                    this.primes.clear();
                    break;
                }
                if( isPrime(n) )
                {
                    this.primes.add(n);
                }
            }
            finally
            {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println("Prime Generator");
        System.out.println("Prime numbers between 1 to 100 are:");
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        gen.setDone();
        gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );

        System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found between 1 to 100.\n\n");
    }
}