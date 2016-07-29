package eight.java;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by kmishra on 4/24/2016.
 */
public class Sums {

    public static void threeSum(int nums[], int idx, int sum, int target, boolean valid[], int used) {
        if (used == 3 && sum == target) {
            for (int i = 0; i < valid.length; i++) {
                if (valid[i]) {
                    System.err.print(nums[i] + " ");
                }
            }
            System.err.println("");
        } else if (used < 3 && idx < nums.length) {
            valid[idx] = true;
            threeSum(nums, idx + 1, sum + nums[idx], target, valid, used + 1);
            valid[idx] = false;
            threeSum(nums, idx + 1, sum, target, valid, used);
        }
    }

    static List<List<Integer>> threeSum2(int nums[]) {
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) map.put(nums[i], i);
        for (int i  = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int negate = 0 - (nums[i] + nums[j]);
                Integer found = map.get(negate);
                if (found == null || found < j || found == i || found == j) continue;
                int hashCode = weakHashCode(nums[i], nums[j], negate);
                if (!set.contains(hashCode)) {
                    set.add(hashCode);
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add( nums[j]);
                    temp.add(negate);
                    result.add(temp);
                }
            }
        }
        return result;
    }

    static int weakHashCode(int a, int b, int c) {
        int hash = 1;
        hash = 29 * hash + a;
        hash = 29 * hash + b;
        return 29 * hash + c;
    }
/**
 *  For example, given array S = {-1 2 1 -4}, and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
 */
    static int threeSumClosestNaive(int[] nums, int target) {
        boolean firstEntry = true;
        int sum = 0;
        for (int i  = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i == j || j == k || i == k) continue;
                    int tempSum = nums[i] + nums[j] + nums[k];
                    if (firstEntry) {
                        firstEntry = false;
                        sum = tempSum;
                    } else if (range(target, sum) > range(target, tempSum)) {
                        sum = tempSum;
                    }
                }
            }
        }
        return sum;
    }

    static int range(int target, int sum) {
        int result = 0;
        if ((target <= 0 && sum <= 0) || (target >= 0 && sum >= 0)) {
            result = Math.abs(target - sum);
        } else if (target <= 0 && sum >= 0) {
            result = sum - target;
        } else if (target >= 0 && sum <= 0) {
            result = target - sum;
        }

        return result;
    }

    static int threeSumClosestBS(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        int min = Integer.MAX_VALUE;
        for (int i  = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int tempSum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(tempSum - target);
                if (diff < min) {
                    sum = tempSum;
                    min = diff;
                }
                if (sum < target)
                    j++;
                else
                    k--;
            }
        }
        return sum;
    }

    static void threeSumSE(int nums[]) {
        Arrays.sort(nums);
        for (int i  = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) System.err.println(nums[i] + " " + nums[j] + " " + nums[k]);
                if (sum < 0)
                    j++;
                else
                    k--;
            }
        }
    }

    public static void main(String[] args) {
        int nums[] = {-1, 0, 1, 2, -1, -4};
        Arrays.sort(nums);
        threeSum(nums, 0, 0, 0, new boolean[nums.length], 0);
        System.err.println("=================");
        List<List<Integer>> result = threeSum2(nums);
        for (List<Integer> temp : result) {
            for (int a: temp) System.err.print(a + " ");
            System.err.println("");
        }
        int nums2[] = {1,1,1,0};
//        System.err.println(threeSumClosestNaive(nums2, -100));
        System.err.println(threeSumClosestBS(nums2, -100));
        int nums3[] = {-1,2,1,-4};
//        System.err.println(threeSumClosestNaive(nums3, 1));
        System.err.println(threeSumClosestBS(nums3, 1));
        int nums4[] = {0,2,1,-3};
//        System.err.println(threeSumClosestNaive(nums4, 1));
        System.err.println(threeSumClosestBS(nums4, 1));

        threeSumSE(nums);
    }
}
