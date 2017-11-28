package edu.taylor.cse.clipclop;


import java.util.LinkedList;
import java.util.Queue;

public class BufferData {
    public static Queue<String> data = new LinkedList();
    private static int size = 5;

    public static void setSize(int newSize)
    {
        size=newSize;
        while (data.size()>size)
        {
            data.poll();
        }
        BufferNotif.showBigBufferInterface();
    }

    public static int getSize()
    {
        return size;
    }
}
