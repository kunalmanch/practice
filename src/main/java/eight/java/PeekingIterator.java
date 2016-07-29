package eight.java;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kmishra on 5/12/2016.
 */
public class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer next;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        fillNext();
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        fillNext();
        Integer ret = next;
        next = null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return next != null || iterator.hasNext();
    }

    private void fillNext() {
        if (next == null) {
            next = iterator.next();
        }
    }

    public static void main(String[] args) {
        Integer[] ints = {1,2,3,4};
        List<Integer> list = Arrays.asList(ints);
        PeekingIterator peekingIterator = new PeekingIterator(list.iterator());
        System.err.println(peekingIterator.peek());
        System.err.println(peekingIterator.next());
        System.err.println(peekingIterator.peek());
        System.err.println(peekingIterator.next());
        System.err.println(peekingIterator.peek());
        System.err.println(peekingIterator.next());
        System.err.println(peekingIterator.hasNext());
        System.err.println(peekingIterator.peek());
    }
}
