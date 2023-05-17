package edu.umb.cs681.hw10;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
public abstract class FSElement
{
    private Directory parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected ReentrantLock lock = new ReentrantLock();

    public FSElement(Directory root, String name,int size,LocalDateTime creationTime)
    {
        if (root != null)
        {
            root.appendChild(this);
        }
        else
        {
            this.parent = null;
        }
        this.name = name;
        if (isDirectory())
        {
            this.size = 0;
        }
        else
        {
            this.size = size;
        }
        this.creationTime = creationTime;
    }
    public Directory getParent()
    {
        lock.lock();
        try
        {
            return this.parent;
        }
        finally
        {
            lock.unlock();
        }
    }
    public int getSize()
    {
        lock.lock();
        try
        {
            return this.size;
        }
        finally
        {
            lock.unlock();
        }
    }
    public String getName()
    {
        lock.lock();
        try
        {
            return this.name;
        }
        finally
        {
            lock.unlock();
        }
    }
    public LocalDateTime getCreationTime()
    {
        lock.lock();
        try
        {
            return this.creationTime;
        }
        finally
        {
            lock.unlock();
        }

    }
    abstract public boolean isDirectory();
    protected void setParent(Directory parent)
    {
        lock.lock();
        try
        {
            this.parent = parent;
        }
        finally
        {
            lock.unlock();
        }

    }
    abstract public boolean isFile();
    abstract public boolean isLink();
    public static void main(String[] args)
    {
        LocalDateTime localTime = LocalDateTime.now();
        Directory root = new Directory(null,"root",0, localTime);
        Directory Apps = new Directory(root,"Apps",0, localTime);
        Directory lib = new Directory(root,"lib",0, localTime);
        Directory home = new Directory(root,"home",0, localTime);
        Directory code = new Directory(home,"code",0, localTime);
        File x = new File(Apps,"x",1,localTime);
        File y = new File(Apps,"y",1,localTime);
        File z = new File(lib,"z",1,localTime);
        File a = new File(code,"a",1,localTime);
        File b = new File(code,"b",1,localTime);
        File c = new File(code,"c",1,localTime);
        Link d = new Link(root,"d",2,localTime,y);
        Link e = new Link(root,"e",2,localTime,x);
        Optional<Directory> optionalDirectory = Optional.ofNullable(code.getParent());
        String[] dirInfo = {code.getName(), Integer.toString(code.getSize()), optionalDirectory.isPresent() ? code.getParent().getName() : null};
        String[] ar ={"Directory name","Directory size","Parent of the Directory"};

        String file[] = {z.getName(), Integer.toString(z.getSize()), z.getParent().getName()};
        System.out.println();
        String[] af ={"File name","File size","Parent of the File"};

        String link[] = {d.getName(), Integer.toString(d.getSize()), d.getParent().getName()};
        System.out.println();
        String[] al ={"Link name","Link size","Parent of the Link"};


        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("\nThread " + Thread.currentThread().getId() + " is running\n");
                for(int j=0;j<3;j++) {
                    System.out.print(ar[j]+"=");
                    System.out.print(dirInfo[j]+ ",");
                }
                System.out.println("\nThread " + Thread.currentThread().getId() + " is running\n");

                for(int j=0;j<3;j++) {
                    System.out.print(af[j]+"=");
                    System.out.print(file[j]+ ",");
                }
                System.out.println("\nThread " + Thread.currentThread().getId() + " is running\n");

                for(int j=0;j<3;j++) {
                    System.out.print(al[j]+"=");
                    System.out.print(link[j]+ ",");
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++)
        {
            try
            {
                threads[i].join();
            } catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            mainThread.interrupt();
        }));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
