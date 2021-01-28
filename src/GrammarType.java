import java.util.*;

public class GrammarType {
    public static void main(String[] args) {
        String beginnningSymbol;
        String grammarStr;                    // Record the name of the grammar.  e.g. G[N]
        String classificationResult = "";
        Boolean nullExisted = false;                  // Record whether there is e(ε).
        ArrayList<String> productions = new ArrayList<String>();
        ArrayList<String> leftProductions = new ArrayList<String>();
        ArrayList<String> rightProductions = new ArrayList<String>();
        ArrayList<String> rightProductionsSliced = new ArrayList<String>();
        ArrayList<String> terminalSymbols = new ArrayList<String>();
        ArrayList<String> nonterminalSymbols0 = new ArrayList<String>();
        ArrayList<String> nonterminalSymbols = new ArrayList<String>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the grammar:");
        grammarStr = sc.nextLine();
        System.out.println("Enter non-terminals, separated by commas and spaces!");
        String nonterminalStr = sc.nextLine();
        System.out.println("Enter the production rule once, for example: N::=ND|D, again separated by commas and space");
        String productionsStr = sc.nextLine();

        beginnningSymbol = grammarStr.substring(grammarStr.indexOf('[') + 1, grammarStr.indexOf(']'));

        if (nonterminalStr.indexOf(",") > -1) {
            String[] nonterminalTemp = nonterminalStr.split(", ");
            for (String temp: nonterminalTemp)
                nonterminalSymbols.add(temp);
        }
        else
            nonterminalSymbols.add(nonterminalStr);

        String[] productionsTemp = new String[1];
        if (productionsStr.indexOf(",") > -1)
            productionsTemp = productionsStr.split(", ");
        else
            productionsTemp[0] = productionsStr;

        for (String temp: productionsTemp) {
            productions.add(temp);
            leftProductions.add(temp.substring(0, temp.indexOf("::=")));
            rightProductions.add(temp.substring(temp.indexOf("::=") + 3));
        }

        if (nonterminalSymbols.indexOf(beginnningSymbol) == -1) {
            System.out.println("Error: The start symbol of the grammar is missing for non-terminal symbols");
            return;
        }
        for (String leftItem: leftProductions) {
            char[] temp = leftItem.toCharArray();
            for(int i=0; i < temp.length; i++){
                if (temp[i] >= 'A' & temp[i] <= 'Z' & nonterminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                    if (nonterminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                        continue;
                    else
                        nonterminalSymbols.add(String.valueOf(temp[i]));
                else if (temp[i] == 'e')
                    System.out.println("Epsilon cannot be there in left part of production!");
                else if (terminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                    continue;
                else
                    terminalSymbols.add(String.valueOf(temp[i]));
            }
        }
        boolean isTrue = false;
        for (String str: leftProductions) {
            String[] temp = str.split("|");
            for (int i = 0; i < temp.length; i++) {
                if (nonterminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                    isTrue = true;
            }
        }
        if (!isTrue) {
            System.out.println("Left part of production is missing a non-terminal symbol, please try again!");
            return;
        }

        for (String rightItem: rightProductions) {
            char[] temp = rightItem.toCharArray();
            for(int i=0; i < temp.length; i++){
                if (temp[i] >= 'A' & temp[i] <= 'Z' & nonterminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                    if (nonterminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                        continue;
                    else
                        nonterminalSymbols.add(String.valueOf(temp[i]));
                else if (temp[i] == 'e')
                    nullExisted = true;
                else if (temp[i] == '|')
                    continue;
                else if (terminalSymbols.indexOf(String.valueOf(temp[i])) > -1)
                    continue;
                else
                    terminalSymbols.add(String.valueOf(temp[i]));
            }
        }

        boolean flag23 = true;
        for (int i = 0; i < leftProductions.size(); i++) {
            if (!Recognition23(leftProductions.get(i), rightProductions.get(i), nonterminalSymbols))
                flag23 = false;
        }
        if (flag23) {
            for (int i = 0; i < leftProductions.size(); i++) {
                if (rightProductions.get(i).indexOf("|") > -1) {
                    String[] tempRight = rightProductions.get(i).split("\\|");
                    for (String str: tempRight) {
                        if (Recognition3(str, terminalSymbols, nonterminalSymbols)
                                && !classificationResult.equals("Type-2 Grammar!")
                                && !classificationResult.equals("Chomsky type-3 grammar (ie formal grammar, left-line grammar)"))
                            classificationResult = "Type-3 Grammar!";
                        else if (nullExisted)
                            classificationResult = "Type-2 Grammar!";
                        else
                            classificationResult = "Type-2 Grammar!";
                    }
                }
                else {
                    if (Recognition3(rightProductions.get(i), terminalSymbols, nonterminalSymbols)
                            && !classificationResult.equals("Type-2 Grammar!"))
                        //&& !classificationResult.equals("扩充的 Chomsky 2 型文法"))
                        classificationResult = "Type-3 Grammar!";
                    else if (nullExisted)
                        classificationResult = "Type-2 Grammar!";
                    else
                        classificationResult = "Type-2 Grammar!";
                }
            }
            if (classificationResult.equals("Type-3 Grammar!") && nullExisted)
                classificationResult = "Type-3 Grammar!";
        }
        else {
            for (int i = 0; i < leftProductions.size(); i++) {
                if (rightProductions.get(i).indexOf("|") > -1) {
                    // If there are more than one right parts.
                    String[] tempRight = rightProductions.get(i).split(", ");
                    for (String str: tempRight) {
                        if (Recognition1(leftProductions.get(i), str)
                                && !classificationResult.equals("Type-0 Grammar"))
                            if (nullExisted)
                                classificationResult = "Type-0 Grammar";
                            else
                                classificationResult = "Type-1 Grammar";
                        else
                            classificationResult = "Type-0 Grammar";
                    }
                }
                else {
                    if (Recognition1(leftProductions.get(i), rightProductions.get(i))
                            && !classificationResult.equals("Type-0 Grammar"))
                        if (nullExisted)
                            classificationResult = "Type-0 Grammar";
                        else
                            classificationResult = "Type-1 Grammar";
                    else
                        classificationResult = "Type-0 Grammar";
                }
            }
        }

        String nonterminalResult = "";
        String terminalResult = "";
        for (String str: nonterminalSymbols) {
            nonterminalResult += "," + str;
        }
        if (nonterminalResult.length() != 0)
            nonterminalResult = nonterminalResult.substring(1);
        for (String str: terminalSymbols) {
            terminalResult += "," + str;
        }

        if (terminalResult == null) {
            terminalResult = "";
        }
        if (nonterminalResult == null) {
            terminalResult = "";
        }
        terminalResult = terminalResult.substring(1);
        System.out.println();
        System.out.println("========================== Result ==========================");
        System.out.println("Grammar: " + grammarStr + " = " + "({" + nonterminalResult + "}, {" + terminalResult + "}, P, " + beginnningSymbol + "}");
        System.out.println("P:");
        for (String str: productions) {
            System.out.println("   "+str);
        }
        System.out.println("The grammar is: " + classificationResult + ".");
    }


    public static boolean Recognition23(String left, String right, ArrayList<String> nonterminal) {
        if (nonterminal.indexOf(left) > -1)
            return true;
        else
            return false;
    }

    public static boolean Recognition3(String right, ArrayList<String> terminal, ArrayList<String> nonterminal) {
        char[] temp = right.toCharArray();
        boolean flag = false;
        if (nonterminal.indexOf(String.valueOf(temp[0])) > -1)
            return false;
        for (int i = 0; i < temp.length; i++) {
            if (nonterminal.indexOf(String.valueOf(temp[i])) == -1 && flag)
                return false;
            else if (nonterminal.indexOf(String.valueOf(temp[i])) > -1 && !flag)
                flag = true;
            else if (nonterminal.indexOf(String.valueOf(temp[i])) > -1 && flag)
                return false;
        }
        return true;
    }

    public static boolean Recognition1(String left, String right) {
        if (left.length() <= right.length())
            return true;
        else
            return false;
    }
}