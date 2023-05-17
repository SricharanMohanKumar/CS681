package edu.umb.cs681.primes.hw06_2;

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
                    dividend /= divisor;
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

    public static void main(String[] args)
    {
        System.out.println("Prime Factorizer");
        var gen1 = new RunnableCancellablePrimeFactorizer(56, 2, 56/2);

        var thread = new Thread(gen1);
        thread.start();
        gen1.setDone();
        try
        {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Prime Factors of 56 are: " + gen1.getPrimeFactors() + "\n");
//        System.out.println("Factorization of 76");
//        var runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
//        var threads = new LinkedList<Thread>();
//        runnables.add(new RunnableCancellablePrimeFactorizer(76, 2, (long) Math.sqrt(76) / 2));
//        runnables.add(new RunnableCancellablePrimeFactorizer(76, 1 + (long) Math.sqrt(76) / 2, (long) Math.sqrt(76)));
//        thread = new Thread(runnables.get(0));
//        threads.add(thread);
//        System.out.println("Thread #" + thread.getId() + " performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());
//        thread = new Thread(runnables.get(1));
//        threads.add(thread);
//        System.out.println("Thread #" + thread.getId() + " performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());
//        threads.forEach(Thread::start);
//        runnables.forEach(RunnableCancellablePrimeFactorizer::setDone);
//        threads.forEach((t) -> {try {t.join();}
//                catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//        }
//        );
//        var factors2 = new LinkedList<Long>();
//        runnables.forEach((factorizer) -> factors2.addAll(factorizer.getPrimeFactors()));
//        System.out.println("Final result: " + factors2);

    }
}
