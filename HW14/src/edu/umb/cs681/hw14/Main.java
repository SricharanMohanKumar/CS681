package edu.umb.cs681.hw14;

public class Main
{
    public static void main(String[] args)
    {
        AdmissionMonitor monitor = new AdmissionMonitor(0);
        EntranceHandler entranceHandler = new EntranceHandler(monitor);
        ExitHandler exitHandler = new ExitHandler(monitor);
        StatsHandler statsHandler = new StatsHandler(monitor);

        Thread entranceThread = new Thread(entranceHandler);
        Thread exitThread = new Thread(exitHandler);
        Thread statsThread = new Thread(statsHandler);


        entranceThread.start();
        exitThread.start();
        statsThread.start();

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex)
        {
            System.out.println("Exception occured"+ex);
        }

        entranceHandler.setDone();
        exitHandler.setDone();
        statsHandler.setDone();

        entranceThread.interrupt();
        exitThread.interrupt();
        statsThread.interrupt();

        try
        {
            entranceThread.join();
            exitThread.join();
            statsThread.join();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Exception occured"+ex);
        }
    }
}
