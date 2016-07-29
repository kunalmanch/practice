package eight.java;

/**
 * Created by kmishra on 5/3/2016.
 */
public class Sorting {

    static void quickSort(int nums[], int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(nums, lo, hi);
        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    static int partition(int nums[], int lo, int hi) {
        int pivot = nums[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (nums[j] < pivot) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        int temp = nums[hi];
        nums[hi] = nums[i];
        nums[i] = temp;
        return i;
    }

    static void selectionSort(int nums[]) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    static void insertionSort(int nums[]) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int nums[] = {1,5,2,4,3, 7, 6};
//        partition(nums, 0, nums.length - 1);
//        for (int i : nums) System.err.print(i + " ");
//        quickSort(nums, 0, nums.length - 1);
//        selectionSort(nums);
        insertionSort(nums);
        for (int i : nums) System.err.print(i + " ");
    }
}
