package eight.java;

import java.util.*;

/**
 * Created by kmishra on 5/4/2016.
 */
public class Intervals {

    private static class Interval {
        int start; // >=0
        int end; // >=0

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

    /**
     * (1,5), (2,6), (2,9), (9,10) (11,12)
     */
    static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return intervals;
        Collections.sort(intervals, (i1, i2) -> {
            if (i1.start != i2.start) return i1.start - i2.start;
            else return i1.end - i2.end;
        });
        List<Interval> result = new ArrayList<>();
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (prev.end < curr.start) {
                result.add(prev);
            } else {
                curr = new Interval(prev.start, Math.max(prev.end, curr.end));
            }
            prev = curr;
        }
        result.add(prev);
        return result;
    }

    /**
     * Provided a list of sorted non-overlapping intervals,
     * insert and merge an interval.
     *
     * Example 1:
     * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
     *
     * Example 2:
     * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
     */
    public List<Interval> insertAndMerge(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        for (Interval interval : intervals) { //if curr interval is less than new add curr.
            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) { //if curr interval is greater than new add new.
                result.add(newInterval);
                newInterval = interval;
            } else if (interval.end >= newInterval.start) { //if curr's end is greater than new's start, merge.
                newInterval = (new Interval(Math.min(interval.start, newInterval.start), Math.max(interval.end, newInterval.end)));
            }
        }

        result.add(newInterval);

        return result;
    }

    public static void main(String[] args) {
        Interval intervals[] = new Interval[5];
        int idx = 0;
        intervals[idx++] = new Interval(1,5);
        intervals[idx++] = new Interval(2,6);
        intervals[idx++] = new Interval(2,9);
        intervals[idx++] = new Interval(9,10);
        intervals[idx++] = new Interval(11,12);
        List<Interval> intervalList = Arrays.asList(intervals);
        List<Interval> mergedList = merge(intervalList);
        for (Interval interval : mergedList) System.err.println(interval);
    }
}
