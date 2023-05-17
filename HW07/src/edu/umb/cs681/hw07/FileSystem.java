package edu.umb.cs681.hw07;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem
{
    private LinkedList<Directory> Dir = new LinkedList<Directory>();
    private FileSystem()
    {

    }
    private static FileSystem FS = null;
    private static ReentrantLock lock = new ReentrantLock();
    public static FileSystem getFileSystem()
    {
        lock.lock();
        try
        {
            if(FS == null)
            {
                FS = new FileSystem();
            }
            return FS;
        }
        finally
        {
            lock.unlock();
        }
    }
    public LinkedList<Directory> getRootDirs()
    {
        return Dir;
    }
    public void appendRootDir(Directory root)
    {
        this.Dir.add(root);
    }
}