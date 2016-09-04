package eight.java;

import java.util.Arrays;
import java.util.Random;

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

    static void bubbleSort(int nums[]) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                    flag = true;
                }
            }
        }
    }

    public static int kLargest(int[] a, int k) {
        return quickSelect(a, 0, a.length - 1, k - 1);
    }

    static int quickSelect(int[] a, int first, int last, int k) {
        if (first < last) {
            int pivot = qsPartition(a, first, last);
            if (pivot == k) return a[k];
            else if (pivot > k) return quickSelect(a, first, pivot - 1, k);
            else return quickSelect(a, pivot + 1, last, k);
        }
        return Integer.MIN_VALUE;
    }

    private static int qsPartition(int[] a, int first, int last) {
        int pivot = first + new Random().nextInt(last - first + 1);
        swap(a, pivot, last);
        int i = first;
        for (int j = first; j <= last; j++) {
            if (a[j] > a[last]) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, last);
        return i;
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    static void mergeSort(int a[], int[] tmp, int low, int high) {
        if (low >= high) return;
        int mid = low + ((high - low) / 2);
        mergeSort(a, tmp, low, mid);
        mergeSort(a, tmp, mid + 1, high);
        merge(a, tmp, low, mid, high);
    }

    private static void merge(int[] a, int[] tmp, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid || j <= high) {
            if (i <= mid && j <= high) {
                if (a[i] < a[j]) {
                    tmp[k] = a[i++];
                } else {
                    tmp[k] = a[j++];
                }
                k++;
            } else if (i <= mid) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        for (i = low; i <= high; i++) {
            a[i] = tmp[i];
        }
    }

    static void mergeSortStrings(String[] strings, String[] tmp, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >> 1);
        mergeSortStrings(strings, tmp, lo, mid);
        mergeSortStrings(strings, tmp, mid + 1, hi);
        mergeStrings(strings, tmp, lo, mid, hi);
    }

    static void mergeStrings(String[] a, String[] tmp, int lo, int mid, int hi) {
        if (lo >= hi) return;
        int i = lo;
        int j = mid + 1;
        int k = lo;
        while (i <= mid && j <= hi) {
            if (strCompare(a[i], a[j]) == -1) {
                tmp[k] = a[i++];
            } else {
                tmp[k] = a[j++];
            }
            k++;
        }

        while (i <= mid) {
            tmp[k++] = a[i++];
        }

        while (j <= hi) {
            tmp[k++] = a[j++];
        }

        for (i = lo; i <= hi; i++) {
            a[i] = tmp[i];
        }
    }

    static void quickSortString(String[] a, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = stringPartition(a, lo, hi);
        quickSortString(a, lo, pivot - 1);
        quickSortString(a, pivot + 1, hi);
    }

    private static int stringPartition(String[] a, int lo, int hi) {
        for (int j = lo; j <= hi; j++) {
            if (strCompare(a[j], a[hi]) == -1) {
                swap(a, lo, j);
                lo++;
            }
        }
        swap(a, lo, hi);
        return lo;
    }

    static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int strCompare(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            char s1Char = s1.charAt(i++);
            char s2Char = s2.charAt(j++);
            if (s1Char < s2Char) return -1;
            if (s1Char > s2Char) return 1;
        }

        if (s1.length() == s2.length()) return 0;
        return s1.length() > s2.length() ? 1 : -1;
    }

    public static void main(String[] args) {
        int nums[] = {1,5,2,4,3,7,6};
//        partition(nums, 0, nums.length - 1);
//        for (int i : nums) System.err.print(i + " ");
        quickSort(nums, 0, nums.length - 1);
//        selectionSort(nums);
//        insertionSort(nums);
        int[] tmp = Arrays.copyOf(nums, nums.length);
//        mergeSort(nums, tmp, 0, nums.length - 1);
        for (int i : nums) System.err.print(i + " ");
        System.err.println("");
        System.err.println(kLargest(nums, 3));

        String[] sarr = {"abc", "ab", "abcd", "a"};
        String[] tmpS = {"abc", "ab", "abcd", "a"};
//        mergeSortStrings(sarr, tmpS, 0, sarr.length - 1);
        quickSortString(sarr, 0, sarr.length - 1);
        for (String s : sarr) System.err.println(s);
        System.err.println(strCompare("a", "abc"));
    }
}
