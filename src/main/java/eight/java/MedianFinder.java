package eight.java;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by kmishra on 5/8/2016.
 */
public class MedianFinder {
    PriorityQueue<Integer> minheap = new PriorityQueue<>();
    PriorityQueue<Integer> maxheap = new PriorityQueue<>(10, new Comparator<Integer>() {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i2 - i1;
        }
    });

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (minheap.isEmpty()) maxheap.add(num);
        else if (num > minheap.peek()) minheap.add(num);
        else maxheap.add(num);

        if (maxheap.size() - minheap.size() > 1) minheap.add(maxheap.poll());
        else if (minheap.size() - maxheap.size() > 1) maxheap.add(minheap.poll());
    }

    // Returns the median of current data stream
    public double findMedian() {
        return maxheap.size() == minheap.size() ? (double)(minheap.peek() + maxheap.peek()) / 2 :
                maxheap.size() > minheap.size() ? maxheap.peek() : minheap.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(-1);
        System.err.println(mf.findMedian());
        mf.addNum(-2);
        System.err.println(mf.findMedian());
        mf.addNum(-3);
        System.err.println(mf.findMedian());
        mf.addNum(4);
        System.err.println(mf.findMedian());
        mf.addNum(5);
        System.err.println(mf.findMedian());
    }
}
