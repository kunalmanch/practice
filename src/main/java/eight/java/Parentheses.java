package eight.java;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by kmishra on 4/26/2016.
 */
public class Parentheses {

    static boolean isValidParentheses(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                default:
                    if (!stack.isEmpty() && stack.peek() == c) {
                        stack.pop();
                    } else return false;
            }
        }
        return stack.isEmpty();
    }

    static void generateParenthesis(int n) {
        char[] ar = new char[n * 2];
        for (int i = 0; i < n * 2; ) {
            ar[i++] = '(';
            ar[i++] = ')';
        }
        permute(ar, 0, ar.length - 1);
    }

    private static void permute(char[] ar, int l, int r) {
        if (l == r) {
            String s = new String(ar);
            if (isValidParentheses(s)) System.err.println(s);
        } else {
            for (int i = l; i <= r; i++) {
                char temp = ar[i];
                ar[i] = ar[l];
                ar[l] = temp;
                permute(ar, l + 1, r);
                temp = ar[i];
                ar[i] = ar[l];
                ar[l] = temp;
            }
        }
    }

    public static void main(String[] args) {
//        System.err.println("()     : " + isValidParentheses("()"));
//        System.err.println("()(    : " + isValidParentheses("()("));
//        System.err.println("()[]{} : " + isValidParentheses("()[]{}"));
//        System.err.println("([{}]) : " + isValidParentheses("([{}])"));
//        System.err.println("(})  : " + isValidParentheses("(})"));
//        System.err.println("[({(())}[()])]  : " + isValidParentheses("[({(())}[()])]"));

        generateParenthesis(2);
    }

}
