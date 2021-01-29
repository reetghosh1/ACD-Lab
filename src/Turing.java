import java.util.*;

public class Turing
{
    static ArrayList<Character> tape = new ArrayList<Character>();

    static void state0(int n)
    {
        System.out.println("State 0-->a(Write x)-->");
        if(tape.get(n)=='a')
        {
            tape.set(n,'x');
            state1(n+1);
        }
        else
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
    }

    static void state1(int n)
    {
        System.out.print("State 1-->");
        if(tape.get(n)=='a')
        {
            System.out.println("a(Write a)-->");
            state1(n+1);
        }
        else if(tape.get(n)=='y')
        {
            System.out.println("y(Write y)-->");
            state1(n+1);
        }
        else if(tape.get(n)=='b')
        {
            tape.set(n,'y');
            System.out.println("b(Write y)-->");
            state2(n-1);
        }
        else if(tape.get(n)=='B')
        {
            System.out.println("B(Write B)-->");
            state3(n-1);
        }
        else
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
    }

    static void state2(int n)
    {
        System.out.print("State 2-->");
        if(tape.get(n)=='a')
        {
            System.out.println("a(Write a)-->");
            state2(n - 1);
        }
        else if(tape.get(n)=='y')
        {
            System.out.println("y(Write y)-->");
            state2(n-1);
        }
        else if(tape.get(n)=='x')
        {
            System.out.println("x(Write x)-->");
            state0(n+1);
        }
        else
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
    }

    static void state3(int n)
    {
        System.out.print("State 3-->");
        if(tape.get(n)=='a')
        {
            System.out.println("a(Write a)-->");
            state3(n - 1);
        }
        else if(tape.get(n)=='y')
        {
            System.out.println("y(Write y)-->");
            state3(n-1);
        }
        else if(tape.get(n)=='x')
        {
            System.out.println("x(Write x)-->");
            state4(n+1);
        }
        else
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
    }

    static void state4(int n)
    {
        System.out.print("State 4-->");
        if(tape.get(n)=='a')
        {
            System.out.println("a(Write a)-->");
            state5(n + 1);
        }
        else
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
    }

    static void state5(int n)
    {
        System.out.print("State 5-->");
        if(tape.get(n)=='y')
        {
            System.out.println("y(Write y)-->");
            state5(n+1);
        }
        else if(tape.get(n)=='B')
        {
            System.out.println("String Accepted!");
            System.exit(0);
        }
        else
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner((System.in));
        System.out.println("Enter TM tape: ");
        String input = sc.nextLine();

        if(input.length()==0)
        {
            System.out.println("String Rejected!");
            System.exit(0);
        }

        for(int i=0;i<input.length();i++)
        {
            tape.add(input.charAt(i));
        }
        tape.add('B');
        state0(0);
    }
}
