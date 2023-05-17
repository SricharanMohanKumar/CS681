package edu.umb.cs681.hw07;
public class main
{
    public static void main(String[] args)
    {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(() -> {
                FileSystem fileSystem = FileSystem.getFileSystem();
                System.out.println("File system instance created: " + fileSystem);
            });
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            try
            {
                t.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
