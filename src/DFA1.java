import java.util.*;
public class DFA1
{
    static void state0(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("String Accepted!");
            System.exit(0);
        }
        System.out.print("State 0----"+str.charAt(n)+"---->");
        if(str.charAt(n)=='a')
        {
            state1(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state2(str, n+1);
        }
    }

    static void state1(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("String Rejected!");
            System.exit(0);
        }
        System.out.print("State 1----"+str.charAt(n)+"---->");
        if(str.charAt(n)=='a')
        {
            state0(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state3(str, n+1);
        }
    }

    static void state2(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("String Rejected!");
            System.exit(0);
        }
        System.out.print("State 2----"+str.charAt(n)+"---->");
        if(str.charAt(n)=='a')
        {
            state3(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state4(str, n+1);
        }
    }

    static void state3(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("String Rejected!");
            System.exit(0);
        }
        System.out.print("State 3----"+str.charAt(n)+"---->");
        if(str.charAt(n)=='a')
        {
            state2(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state5(str, n+1);
        }
    }

    static void state4(String str, int n)
    {

        if(n==str.length())
        {
            System.out.print("String Rejected!");
            System.exit(0);
        }
        System.out.print("State 4----"+str.charAt(n)+"---->");
        if(str.charAt(n)=='a')
        {
            state5(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state0(str, n+1);
        }
    }

    static void state5(String str, int n)
    {

        if(n==str.length())
        {
            System.out.print("String Rejected!");
            System.exit(0);
        }
        System.out.print("State 5----"+str.charAt(n)+"---->");
        if(str.charAt(n)=='a')
        {
            state4(str, n+1);
        }
        else if(str.charAt(n)=='b')
        {
            state1(str, n+1);
        }
    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        String input="";
        System.out.println("Enter input string consisting of a's and b's");
        input=sc.nextLine();

        if(input.length()==0)
        {
            System.out.print("String Rejected!");
            System.exit(0);
        }

        state0(input,0);
    }
}
