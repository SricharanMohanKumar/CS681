package edu.umb.cs681.hw07;
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
        return this.children;
    }
    public void appendChild(FSElement child)
    {
        this.children.add(child);
        child.setParent(this);
    }
    public int countChildren()
    {
        return this.children.size();
    }
    public LinkedList<Directory> getSubDirectories()
    {
        for (FSElement element : children)
        {
            if (element.isDirectory())
            {
                subDirectory.add((Directory) element);
            }
        }
        return subDirectory;
    }
    public LinkedList<File> getFiles()
    {
        for (FSElement e : children) {
            if (e.isFile()) {
                file.add((File) e);
            }
        }
        return file;
    }
    public int getTotalSize()
    {
        int finalsize = 0;
        for(FSElement e : children)
        {
            if(e.isDirectory())
            {
                finalsize = finalsize+((Directory)e).getTotalSize();
            }
            else 
            {
                finalsize = finalsize+e.getSize();
            }
        }
        return finalsize;
    }
    public boolean isDirectory()
    {
        return true;
    }
    public boolean isFile()
    {
        return false;
    }
    public boolean isLink()
    {
        return false;
    }

}
