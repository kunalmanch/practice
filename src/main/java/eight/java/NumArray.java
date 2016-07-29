package eight.java;

/**
 * Created by kmishra on 5/10/2016.
 */
public class NumArray {

    int nums[];
    int pre[];

    public NumArray(int[] nums) {
        this.nums = nums;
        pre = new int[nums.length];
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            pre[i] = sum;
        }
    }

    void update(int i, int val) {
        nums[i] = val;
        int sum = i == 0 ? 0 : pre[i - 1];
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            pre[j] = sum;
        }
    }

    public int sumRange(int i, int j) {
        return i == 0 ? pre[j] : pre[j] - pre[i - 1];
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4};
        NumArray numArray = new NumArray(nums);
        System.err.println(numArray.sumRange(0, 3));
        numArray.update(0,5);
        System.err.println(numArray.sumRange(0, 3));
        System.err.println(numArray.sumRange(1, 3));
        numArray.update(2, 6);
        System.err.println(numArray.sumRange(1, 3));
        int nums2[] = {-2, 0, 3, -5, 2, -1};
        numArray = new NumArray(nums2);
        System.err.println(numArray.sumRange(0,2));
    }
}
