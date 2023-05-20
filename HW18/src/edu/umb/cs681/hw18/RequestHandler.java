package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    void setDone()
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
        filesList.add("b.txt");
        filesList.add("c.html");
        filesList.add("d.txt");

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
                Thread.sleep(1500);
            }
            catch (InterruptedException ex)
            {
                continue;
            }
        }
    }
}
