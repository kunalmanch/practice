package eight.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kmishra on 5/12/2016.
 */
public class Notations {

    static class ExpTreeNode {
        boolean operator;
        String val;
        ExpTreeNode left, right;

        public ExpTreeNode(String s) {
            operator = operators.contains(s);
            val = s;
        }
    }

    static Set<String> operators = new HashSet<>();
    static {
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }

    static int evalRPN(String[] tokens) {
        Deque<String> operatorStack = new ArrayDeque<>();
        Deque<Integer> operandStack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (operators.contains(token)) {
                operatorStack.push(token);
            } else {
                operandStack.push(Integer.parseInt(token));
            }
            if (operatorStack.size() > 0) {
                operandStack.push(operate(operatorStack.pop(), operandStack.pop(), operandStack.pop()));
            }
        }
        return operandStack.pop();
    }

    private static int operate(String op, int r, int l) {
        switch (op) {
            case "+":
                return l + r;
            case "*":
                return l * r;
            case "-":
                return l - r;
            case "/":
                return l / r;
            default:
                throw new RuntimeException("operator not supported");
        }
    }

    static String[] infixToPostFix(String[] infix) {
        Deque<String> operatorStack = new ArrayDeque<>();
        String[] postfix = new String[infix.length];
        int i = 0;
        for (String token : infix) {
            if (operators.contains(token)) {
                while (!operatorStack.isEmpty() && compareOp(token, operatorStack.peek()) < 0) { //if current op is smaller than stack.peek() op.
                    postfix[i++] = operatorStack.pop();
                }
                operatorStack.push(token);
            } else {
                postfix[i++] = token;
            }
        }
        while (!operatorStack.isEmpty()) postfix[i++] = operatorStack.pop();
        return postfix;
    }

    private static int compareOp(String op1, String op2) {
        if (op1.equals("/")) return 1;
        if (op1.equals("*")) {
            if (op2.equals("/")) return -1;
            else return 1;
        }
        if (op1.equals("+")) {
            if (op2.equals("-")) return 1;
            else return -1;
        }
        return op1.equals("-") ? -1 : 1;
    }

    static ExpTreeNode createExpTreeFromPostFix(String[] postfix) {
        Deque<ExpTreeNode> stack = new ArrayDeque<>();
        ExpTreeNode node;
        for (String token : postfix) {
            node = new ExpTreeNode(token);
            if (operators.contains(token)) {
                node.right = stack.pop();
                node.left = stack.pop();
            }
            stack.push(node);
        }
        return stack.pop();
    }

    static int evalExpTree(ExpTreeNode root) {
        if (root == null) return 0;
        int l = evalExpTree(root.left);
        int r = evalExpTree(root.right);
        if (root.operator) return operate(root.val, r, l);
        return Integer.parseInt(root.val);
    }

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.err.println(evalRPN(tokens));
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.err.println(evalRPN(tokens2));
        String[] infix = {"a", "+", "b","*", "c", "-", "d"};
        String[] postfix = infixToPostFix(infix);
        for (String p : postfix) System.err.print(p + " ");
        ExpTreeNode expTreeNode = createExpTreeFromPostFix(tokens2);
        System.err.println();
        System.err.println(evalExpTree(expTreeNode));
    }
}
