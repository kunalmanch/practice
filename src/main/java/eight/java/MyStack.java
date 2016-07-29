package eight.java;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by kmishra on 5/10/2016.
 */
public class MyStack {

    Queue<Integer> q1 = new ArrayDeque<>();
    Queue<Integer> q2 = new ArrayDeque<>();

    public void push(int x) {
        q1.add(x);
        while (!q2.isEmpty()) {
            q1.add(q2.poll());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Removes the element on top of the stack.
    public void pop() {
        q2.poll();
    }

    // Get the top element.
    public int top() {
        return q2.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q2.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.err.println(myStack.top());
        myStack.push(3);
        System.err.println(myStack.top());
        myStack.pop();
        System.err.println(myStack.top());
    }
}
