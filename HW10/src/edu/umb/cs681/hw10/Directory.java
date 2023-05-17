package edu.umb.cs681.hw10;
import java.time.LocalDateTime;
import java.util.LinkedList;
public class Directory extends FSElement
{
    private LinkedList<File> file = new LinkedList<File>();
    private LinkedList<Directory> subDirectory = new LinkedList<Directory>();
    private LinkedList<FSElement> children = new LinkedList<FSElement>();
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime)
    {
        super(parent, name, 0, creationTime);
    }
    public LinkedList<FSElement> getChildren()
    {
        lock.lock();
        try
        {
            return this.children;
        }
        finally
        {
            lock.unlock();
        }

    }
    public void appendChild(FSElement child)
    {
        lock.lock();
        try
        {
            this.children.add(child);
            child.setParent(this);
        }
        finally
        {
            lock.unlock();
        }

    }
    public int countChildren()
    {
        lock.lock();
        try
        {
            return this.children.size();
        }
        finally
        {
            lock.unlock();
        }
    }
    public LinkedList<Directory> getSubDirectories()
    {
        lock.lock();
        try
        {
            for (FSElement element : children) {
                if (element.isDirectory()) {
                    subDirectory.add((Directory) element);
                }
            }
            return subDirectory;
        }
        finally
        {
            lock.unlock();
        }
    }
    public LinkedList<File> getFiles()
    {
        lock.lock();
        try {

            for (FSElement e : children) {
                if (e.isFile()) {
                    file.add((File) e);
                }
            }
            return file;
        }
        finally {
            lock.unlock();
        }
    }
    public int getTotalSize()
    {
        lock.lock();
        try {
            int finalsize = 0;
            for (FSElement e : children) {
                if (e.isDirectory()) {
                    finalsize = finalsize + ((Directory) e).getTotalSize();
                } else {
                    finalsize = finalsize + e.getSize();
                }
            }
            return finalsize;
        }
        finally {
            lock.unlock();
        }
    }
    public boolean isDirectory()
    {
        lock.lock();
        try {
            return true;
        }
        finally {
            lock.unlock();
        }
    }
    public boolean isFile()
    {
        lock.lock();
        try {
            return false;
        }
        finally {
            lock.unlock();
        }
    }
    public boolean isLink()
    {
        lock.lock();
        try {
            return false;
        }
        finally {
            lock.unlock();
        }
    }

}
