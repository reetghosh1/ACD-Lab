import java.io.IOException;
import java.util.*;
public class PDA1
{
    static Stack<Character> stack = new Stack<>(); ;

    static void state0(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("Stop!");
            return;
        }
        System.out.println("State 0-----(Push z, Pop NULL)----->State 1----->");
        stack.push('z');
        state1(str, n);
    }

    static void state1(String str, int n)
    {
        if(n==str.length())
        {
            System.out.print("Stop!");
            return;
        }
        if(str.charAt(n)=='a')
        {
            System.out.println("a, (Push a, Pop NULL)----->State 1----->");
            stack.push(str.charAt(n));
            state1(str,n+1);
        }
        else if(str.charAt(n)=='b')
        {
            System.out.println("b, (Push NULL, Pop a)----->State 2----->");
            stack.pop();
            state2(str,n+1);
        }
    }

    static void state2(String str, int n)
    {
        if(n==str.length())
        {
            System.out.println("NULL, (Push NULL, Pop z)----->State 3\n");
            if(!stack.empty())
                stack.pop();
            else {
                System.out.println("String Rejected!");
                System.exit(0);
            }
            state3();
        }
        if(str.charAt(n)=='b')
        {
            System.out.println("b, (Push NULL, Pop a)----->State 2----->");
            stack.pop();
            state2(str,n+1);
        }
        else if(str.charAt(n)=='a')
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
        else
        {
            System.out.println("NULL, (Push NULL, Pop z)----->State 3\n");
            stack.pop();
            state3();
        }
    }

    static void state3()
    {
        if(stack.empty())
        {
            System.out.println("String Accepted!");
        }
        else
        {
            System.out.println("String Rejected!");
        }
        System.exit(0);
    }

    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String: ");
        String in = sc.nextLine();

        if(in.length()==0)
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
        state0(in,0);
    }
}
