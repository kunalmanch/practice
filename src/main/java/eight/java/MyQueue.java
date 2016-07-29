package eight.java;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by kmishra on 5/10/2016.
 */
public class MyQueue {

    Deque<Integer> s1 = new ArrayDeque<>();
    Deque<Integer> s2 = new ArrayDeque<>();

    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        fillRemovalStack();
        s2.pop();
    }

    // Get the front element.
    public int peek() {
        fillRemovalStack();
        return s2.peek();
    }

    private void fillRemovalStack() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.err.println(myQueue.peek());
        myQueue.push(3);
        System.err.println(myQueue.peek());
    }
}
