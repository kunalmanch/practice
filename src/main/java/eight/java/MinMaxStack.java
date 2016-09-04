package eight.java;

import java.util.ArrayList;

/**
 * Created by kmishra on 5/6/2016.
 */
public class MinMaxStack {

    private ArrayList<Integer> stack;
    private ArrayList<Integer> min, max;

    public MinMaxStack() {
        stack = new ArrayList<>();
        min = new ArrayList<>();
        max = new ArrayList<>();
    }

    public int getMin() {
        if (min.isEmpty()) return 0;
        return min.get(min.size() - 1);
    }

    public int getMax() {
        if (max.isEmpty()) return -1;
        return max.get(max.size() - 1);
    }

    public boolean push(int x) {
        stack.add(x);
        int tempMin = min.isEmpty() ? Integer.MAX_VALUE : min.get(min.size() - 1);
        min.add(Math.min(x, tempMin));
        int tempMax = max.isEmpty() ? Integer.MIN_VALUE : max.get(max.size() - 1);
        max.add(Math.max(x, tempMax));
        return true;
    }

    public int peek() {
        if (stack.isEmpty()) return -1;
        return stack.get(stack.size() - 1);
    }

    public int pop() {
        if (stack.isEmpty()) return -1;
        int ret = stack.remove(stack.size() - 1);
        max.remove(max.size() - 1);
        min.remove(min.size() - 1);
        return ret;
    }

    public static void main(String[] args) {
        MinMaxStack minMaxStack = new MinMaxStack();
        System.err.println(minMaxStack.pop());
        System.err.println(minMaxStack.push(1));
        System.err.println(minMaxStack.push(2));
        System.err.println(minMaxStack.push(3));
        System.err.println(minMaxStack.getMin());
        System.err.println(minMaxStack.getMax());
        System.err.println(minMaxStack.pop());
        System.err.println(minMaxStack.getMin());
        System.err.println(minMaxStack.getMax());
        System.err.println("====");
        System.err.println(minMaxStack.push(4));
        System.err.println(minMaxStack.getMin());
        System.err.println(minMaxStack.getMax());
        System.err.println(minMaxStack.push(-2));
        System.err.println(minMaxStack.getMin());
        System.err.println(minMaxStack.getMax());
    }
}
