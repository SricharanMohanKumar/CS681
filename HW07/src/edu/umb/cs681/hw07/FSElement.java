package edu.umb.cs681.hw07;
import java.time.LocalDateTime;
import java.util.Optional;
public abstract class FSElement
{
    private Directory parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
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
        return this.parent;
    }
    public int getSize()
    {
        return this.size;
    }
    public String getName()
    {
        return this.name;
    }
    public LocalDateTime getCreationTime()
    {
        return this.creationTime;
    }
    abstract public boolean isDirectory();
    protected void setParent(Directory parent)
    {
        this.parent = parent;
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
        File d = new File(home,"a",1,localTime);
        Optional<Directory> optionalDirectory = Optional.ofNullable(code.getParent());
        String[] dirInfo = {code.getName(), Integer.toString(code.getSize()), optionalDirectory.isPresent()?code.getParent().getName():null};
        String[] ar ={"Directory name","Directory size","Parent of the Directory"};
        for(int i=0;i<3;i++)
        {
            System.out.print(ar[i]+"=");
            System.out.print(dirInfo[i]+ ",");
        }
    }
}
