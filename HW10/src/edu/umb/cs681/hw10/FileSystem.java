package edu.umb.cs681.hw10;

import java.util.LinkedList;

public class FileSystem
{

    private LinkedList<Directory> Dir = new LinkedList<Directory>();
    private FileSystem()
    {

    }
    private static FileSystem FS = null;
    public static FileSystem getFileSystem()
    {
        if(FS == null)
        {
            FS = new FileSystem();
        }
        return FS;
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