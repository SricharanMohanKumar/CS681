package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{

    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;




    private void setDone()
    {
        lock.lock();
        try
        {
            this.done = true;
        }
        finally
        {
            lock.unlock();
        }
    }



    public void run()
    {
        ArrayList<String> filesList = new ArrayList<>();
        filesList.add("a.html");
        filesList.add("b.html");
        filesList.add("c.html");
        filesList.add("d.html");
        while (true)
        {
            lock.lock();
            try
            {
                if (done)
                {
                    break;
                }

                int num = (int) (Math.random() * 4);
                String file = filesList.get(num);
                AccessCounter.getInstance().increment(Path.of(file));
                AccessCounter.getInstance().getCount(Path.of(file));

            }
            finally
            {
                lock.unlock();
            }

            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {
                continue;
            }
        }
    }

    public static void main(String[] args)
    {
        List<RequestHandler> rhs = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RequestHandler rh = new RequestHandler();
            rhs.add(rh);
            Thread thread = new Thread(rh);
            threads.add(thread);
            thread.start();
        }
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex)
        {
            System.out.println("Exception occured"+ex);
        }
        for (RequestHandler requestHandler : rhs)
        {
            requestHandler.setDone();
        }
        for (Thread thread : threads)
        {
            thread.interrupt();
        }

        System.out.println("a.html: " + AccessCounter.getInstance().getCount(Path.of("a.html")));

        System.out.println("b.html: " + AccessCounter.getInstance().getCount(Path.of("b.html")));

        System.out.println("c.html: " + AccessCounter.getInstance().getCount(Path.of("c.html")));

        System.out.println("d.html: " + AccessCounter.getInstance().getCount(Path.of("d.html")));

        try
        {
            for (Thread thread : threads)
            {
                thread.join();
            }
        }
        catch (InterruptedException ex)
        {
            System.out.println("Exception occured"+ex);
        }
    }
}