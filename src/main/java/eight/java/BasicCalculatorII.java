package eight.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmishra on 5/13/2016.
 */
public class BasicCalculatorII {

    static int calculate(String s) {
        String delim = "[ ]+";
        s = s.replaceAll(delim, "");
        List<String> tokenList = new ArrayList<>();
        int st = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*' || c == '+' || c == '-' || c == '/') {
                tokenList.add(s.substring(st, i));
                tokenList.add(s.substring(i, i + 1));
                st = i + 1;
            }
        }
        tokenList.add(s.substring(st));
        String[] postFix = Notations.infixToPostFix(tokenList.toArray(new String[0]));
        return Notations.evalRPN(postFix);
    }

    public static void main(String[] args) {
        System.err.println(calculate("32 * 25+ 67"));
        System.err.println(calculate("2+1*3"));
        System.err.println(calculate("4+ 13/5"));
    }
}
