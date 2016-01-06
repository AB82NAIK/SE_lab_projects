import java.util.*;
import java.io.*;

class linkedStack

{

    protected Node top ;

    protected int size ;



    public linkedStack()

    {

        top = null;

        size = 0;

    }    

   

    public boolean isEmpty()

    {

        return top == null;

    }    

   

    public int getSize()

    {

        return size;

    }    

   

    public void push(int data)

    {

        Node nptr = new Node (data, null);

        if (top == null)

            top = nptr;

        else

        {

            nptr.setLink(top);

            top = nptr;

        }

        size++ ;

    }    

  

    public int pop()

    {

        if (isEmpty() )

            throw new NoSuchElementException("Underflow Exception") ;

        Node ptr = top;

        top = ptr.getLink();

        size-- ;

        return ptr.getData();

    }    

  

    public int peek()

    {

        if (isEmpty() )

            throw new NoSuchElementException("Underflow Exception") ;

        return top.getData();

    }    

 

    public void display()

    {

        System.out.print("\nStack = ");

        if (size == 0) 

        {

            System.out.print("Empty\n");

            return ;

        }

        Node ptr = top;

        while (ptr != null)

        {

            System.out.print(ptr.getData()+" ");

            ptr = ptr.getLink();

        }

        System.out.println();        

    }

}