package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        List<RequestHandler> rhs = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
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
            ex.printStackTrace();
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
