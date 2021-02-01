import java.io.*;
import java.util.*;
public class LexicalAnalyser
{
    public static void main(String[] args) {
        File file = new File("Lol.txt");

        try {
            LexerClass lexerClass = new LexerClass(file);
            List<Token> tokenList = lexerClass.generateTokens();
            for (int i = 0; i < tokenList.size(); i++) {
                System.out.println(tokenList.get(i).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
