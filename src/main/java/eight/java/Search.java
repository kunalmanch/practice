package eight.java;

/**
 * Created by kmishra on 5/5/2016.
 */
public class Search {

    /**
     * Given a sorted array of integers, find the starting and ending position of a given target value.
     Your algorithm's runtime complexity must be in the order of O(log n).
     If the target is not found in the array, return [-1, -1].
     For example,
     Given [5, 7, 7, 8, 8, 10] and target value 8,
     return [3, 4].
     * @param a
     * @return
     */
    static int[] findRange(int a[], int val) {
        int s = 0, e = a.length - 1;
        int ans[] = {-1, -1};
        while (s <= e) {
            int mid = (s + e) / 2;
            if (a[mid] == val && (mid == 0 || (a[mid - 1] < a[mid]))) {
                ans[0] = mid;
                break;
            } else if ((a[mid] == val && a[mid - 1] == val) || a[mid] > val) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        s = 0;
        e = a.length - 1;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (a[mid] == val && (mid == a.length - 1 || (a[mid] < a[mid + 1]))) {
                ans[1] = mid;
                break;
            } else if ((a[mid] == val && a[mid + 1] == val) || a[mid] < val) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return ans;
    }

    static int sqrt(int x) {
        long lo = 1;
        long hi = x / 2 + 1;
        while (lo <= hi) {
            long a = (lo + hi) / 2;
            long sq = a * a;
            if (sq == x || (sq < x && x < (a + 1) * (a + 1))) return (int)a;
            if (x < sq) {
                hi = a - 1;
            } else {
                lo = a + 1;
            }
        }
        return 0;
    }

    static int firstFound(int[] nums, int val) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (hi + lo) / 2;
            if (nums[mid] == val)
                hi = mid;
            else
                lo = mid + 1;
        }
        return lo;
    }

    static int lastFound(int[] nums, int val) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (hi + lo) / 2;
            if (nums[mid] != val)
                hi = mid;
            else
                lo = mid + 1;
        }
        return nums[lo] == val ? lo : --lo;
    }

    public static void main(String[] args) {
        int nums[] = {5,7, 7, 8, 8, 8,10};
        int ans[] = findRange(nums, 8);
        System.err.println(ans[0] + " " + ans[1]);
        System.err.println(sqrt(17));
        int[] nums2= {0,1,1,1,1,1};
        System.err.println(firstFound(nums, 7));
        System.err.println(lastFound(nums, 7));
    }
}
