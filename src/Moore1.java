import java.io.IOException;
import java.util.*;
public class Moore1
{
    static ArrayList<Integer> out = new ArrayList<Integer>();

    static void state0(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("Stop!");
            return;
        }
        System.out.print("State 0 (Output=0)----"+str.charAt(n)+"---->");
        out.add(0);
        if(str.charAt(n)=='a')
        {
            state1(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state0(str, n+1);
        }
    }

    static void state1(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("Stop!");
            return;
        }
        System.out.print("State 1 (Output=0)----"+str.charAt(n)+"---->");
        out.add(0);
        if(str.charAt(n)=='a')
        {
            state1(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state2(str, n+1);
        }
    }

    static void state2(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("Stop!");
            return;
        }
        System.out.print("State 2 (Output=0)----"+str.charAt(n)+"---->");
        out.add(0);
        if(str.charAt(n)=='a')
        {
            state1(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state3(str, n+1);
        }
    }

    static void state3(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("Stop!");
            return;
        }
        System.out.print("State 3 (Output=1)----"+str.charAt(n)+"---->");
        out.add(1);
        if(str.charAt(n)=='a')
        {
            state0(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state1(str, n+1);
        }
    }

    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string consisting of a's and b's");
        String x = sc.nextLine();

        state0(x, 0);

        System.out.println("Output: ");
        for(int i=0;i<out.size();i++)
        {
            System.out.print(out.get(i));
        }

        System.out.println();

        int count = 0;

        for(int i=0;i<out.size();i++)
        {
            if(out.get(i)==1)
            {
                count++;
            }
        }

        System.out.println("Number of occurrences of abb = "+count);
    }
}
