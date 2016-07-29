package eight.java;

/**
 * Created by kmishra on 6/10/2016.
 */
public class Greedy {

    static int canCompleteCircuit(int[] gas, int[] cost) {
        int sumRemaining = 0;
        int start = 0;
        int total = 0;

        for (int i = 0; i < gas.length; i++) {
            int remaining = gas[i] - cost[i];
            if (sumRemaining >= 0) {
                sumRemaining += remaining;
            } else {
                sumRemaining = remaining;
                start = i;
            }
            total += remaining;
        }

        return total >= 0 ? start : -1;
    }

    /**
     * 4,2,3,4,1
     */
    static int candy(int[] ratings) {
        if (ratings == null || ratings.length <= 1) return 1;
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            candies[i] = ratings[i] > ratings[i - 1] ? candies[i - 1] + 1 : 1;
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            int cur = 0;
            if (ratings[i] > ratings[i + 1]) {
                cur = candies[i + 1] + 1;
            }
            candies[i] = Math.max(cur, candies[i]);
        }

        int total = 0;
        for (int candy : candies) total += candy;
        return total;
    }

    static int minPatches(int[] nums, int n) {
        long miss = 1;
        int count = 0;
        int i = 0;

        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss = miss + nums[i++];
            } else {
                miss += miss;
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] ratings = {20,10,20,10,9};
        System.err.println(candy(ratings));
        int[] ratings2 = {2,2};
        System.err.println(candy(ratings2));
    }
}
