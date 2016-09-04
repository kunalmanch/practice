package eight.java;

import java.util.*;

/**
 * Created by kmishra on 5/6/2016.
 */
public class Misc {

    static public int removeDuplicatesII(int[] A) {
        if (A.length <= 2)
            return A.length;

        int prev = 1; // point to previous
        int curr = 2; // point to current

        while (curr < A.length) {
            if (A[curr] == A[prev] && A[curr] == A[prev - 1]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }

        return prev + 1;
    }

    public int[] countBits(int num) {
        int bits[] = new int[num + 1];
        int p = 2;
        while (p <= num) {
            bits[p] = 1;
            p *= 2;
        }

        bits[0] = 0; bits[1] = 1;

        for (int i = 3; i <= num; i++) {
            int j = max2Factor(i);
            bits[i] = bits[j] + bits[i - j];
        }
        return bits;
    }

    private int max2Factor(int i) {
        int k = 2;
        while (k <= i) {
            k *= 2;
        }
        return k / 2;
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        class Node implements Comparable<Node> {
            int num, freq;

            public Node(int i) {
                num = i;
                freq = 0;
            }

            @Override
            public int compareTo(Node that) {
                return that.freq - this.freq;
            }
        }
        HashMap<Integer, Node>  map = new HashMap<>();
        for (int i : nums) {
            Node node;
            if ((node = map.get(i)) == null) {
                node = new Node(i);
                map.put(i, node);
            }
            ++node.freq;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            pq.add(entry.getValue());
        }

        while (k-- > 0) {
            ans.add(pq.poll().num);
        }
        return ans;
    }

    static int findDuplicate(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (int i : nums) {
            if (unique.contains(i)) return i;
            unique.add(i);
        }

        return 0;
    }

    static void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        for (int j = i; j < nums.length; j++) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j] = temp;
            }
        }
    }

    public static int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        Arrays.sort(citations);

        int result = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (result >= citations[i]) {
                return result;
            }
            result++;
        }

        return result;
    }

    /**
     * Given an array of size n, find the majority element.
     * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     */
    static int majorityElement(int[] nums) {
        int counter = 0;
        int curr = -1;
        if (nums.length < 3) return nums[0];
        for (int i : nums) {
            if (counter == 0) {
                counter = 1;
                curr = i;
            } else if (i == curr) {
                counter++;
            } else counter--;
        }
        return curr;
    }

    /**
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     * The algorithm should run in linear time and in O(1) space.
     * Uses Linear Time Majority Vote algorithm.
     */
    static List<Integer> majorityElementII(int[] nums) {
        int c1 = 0, c2 = 0;
        int n1 = 0, n2 = 0;
        boolean n1Set = false, n2Set = false;
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            if (n1Set && n1 == i) {
                c1++;
            } else if (n2Set && n2 == i) {
                c2++;
            } else if (c1 == 0) {
                c1 = 1;
                n1 = i;
                n1Set = true;
            } else if (c2 == 0) {
                c2 = 1;
                n2 = i;
                n2Set = true;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = c2 = 0;
        for (int i : nums) {
            if (n1 == i) c1++;
            else if (n2 == i) c2++;
        }
        if (c1 > nums.length/3) list.add(n1);
        if (c2 > nums.length/3) list.add(n2);
        return list;
    }

    static boolean containsDuplicate(int[] nums) {
        Set<Integer> integerSet = new HashSet<>();
        for (int i : nums) {
            if (integerSet.contains(i)) return true;
            integerSet.add(i);
        }
        return false;
    }

    /**
     * Given an array of integers and an integer k,
     * find out whether there are two distinct indices i and j in the array such that
     * nums[i] = nums[j] and the difference between i and j is at most k.
     */
    static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int prevPos = map.get(nums[i]);
                if (i - prevPos <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * Given an array of integers, find out whether there are two distinct indices i and j in the array
     * such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
     */
    static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            long cur = nums[i];
            long from = cur - t;
            long to = cur + t + 1;
            if (from <= to) {
                java.util.SortedMap<Long, Integer> subMap = map.subMap(from, to);
                for (Map.Entry<Long, Integer> entry : subMap.entrySet()) {
                    int idx = entry.getValue();
                    if (i - idx <= k) return true;
                }
            }
            map.put((long)nums[i], i);
        }
        return false;
    }

    public static int sqrt(int number) {
        double t;
        double squareRoot = number / 2;
        double diff;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
            diff = t - squareRoot;
        } while (Math.abs(diff) > 1);

        return (int)squareRoot;
    }

    public static List<Integer> grayCode(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        int m = (int)Math.pow(2, n);
        for (int i = 0; i < m; i++) {
            int temp = (i >> 1) ^ i; // generating the gray code
            arr.add(temp);
        }
        return arr;
    }

    /**
     *
     * Find a sub array of non-negative integer array which results in a particular sum.
     */
    static void findSubArray(int[] nums, int target) {
        int i = 1, start = 0;
        int sum = nums[0];
        while (start < nums.length) {
            if (sum == target) {
                System.err.println("found : " + start + " " + (i - 1));
                return;
            } else if (sum < target) {
                if (i == nums.length) {
                    System.err.println("not found");
                    return;
                }
                sum += nums[i++];
            } else {
                sum -= nums[start++];
            }
        }
    }

    /**
     * 2) Find an element in a sorted array where a[i]=i.Write all test cases for it?
     * 1 2 2 4 5 6
     */
    static void findElement(int[] nums, int el) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == el && mid == el) {
                System.err.println("found");
                return;
            }
            if (mid > el) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        System.err.println("not found");
    }
    /**
     3) If an array consists of even number of long numbers and 1 odd number of long numbers .Find the odd number.Write all test cases for it?

     4) Given three sides find whether it can form a triangle else return whether it is equilateral,isosceles or scalene.Write all test cases for it?

     5) Can you insert an element between a sorted linked list and also ensure it is sorted if only middle pointer to linked list is given?

     */


    /**
     * Longest valid parentheses "()()))()" = 4
     */
    static int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    static int histogramArea(int hist[]) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        int maxArea = 0;
        while (i < hist.length) {
            if (stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i++);
            } else {
                int tp = stack.pop();
                int tempArea = hist[tp] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                maxArea = Math.max(maxArea, tempArea);
            }
        }
        while (!stack.isEmpty()) {
            int tp = stack.pop();
            int tempArea = hist[tp] * (stack.isEmpty() ? i : i - stack.peek() - 1);
            maxArea = Math.max(maxArea, tempArea);
        }
        return maxArea;
    }

    static String numberToWords(int num) {
        if (num == 0) return "Zero";
        String[] ones = {
                "",
                "One",
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight",
                "Nine",
                "Ten",
                "Eleven",
                "Twelve",
                "Thirteen",
                "Fourteen",
                "Fifteen",
                "Sixteen",
                "Seventeen",
                "Eighteen",
                "Nineteen"
        };

        String[] tens = {
                "",
                "",
                "Twenty",
                "Thirty",
                "Forty",
                "Fifty",
                "Sixty",
                "Seventy",
                "Eighty",
                "Ninety",
        };

        if (num < 20)
            return ones[num];

        if (num < 100)
            return tens[num / 10] + (num % 10 == 0 ? "" : " " + ones[num % 10]);

        if (num < 1000)
            return ones[num / 100] + " Hundred" + (num % 100 == 0 ? "" : " " + numberToWords(num % 100));

        if (num < 1000000)
            return numberToWords(num / 1000) + " Thousand" + (num % 1000 == 0 ? "" : " " + numberToWords(num % 1000));

        if (num < 1000000000)
            return numberToWords(num / 1000000) + " Million" + (num % 1000000 == 0 ? "" : " " + numberToWords(num % 1000000));

        return numberToWords(num / 1000000000) + " Billion" + (num % 1000000000 == 0 ? "" : " " + numberToWords(num % 1000000000));
    }

    /**
     * ar1[] = {1, 12, 15, 26, 38}
     * ar2[] = {2, 13, 16, 30, 45}
     */
    static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i1 = 0, i2 = 0;
        int j1 = nums1.length - 1, j2 = nums2.length - 1;

        int len1 = getLength(i1, j1);
        int len2 = getLength(i2, j2);

        while (len1 > 2 || len2 > 2) {
            int mIdx1 = getMedianIdx(nums1, i1, j1);
            int mIdx2 = getMedianIdx(nums2, i2, j2);

            if (nums1[mIdx1] == nums2[mIdx2]) return nums1[mIdx1];

            if (nums1[mIdx1] > nums2[mIdx2]) {
                j1 = mIdx1;
                i2 = mIdx2;
            } else {
                j2 = mIdx2;
                i1 = mIdx2;
            }

            len1 = getLength(i1, j1);
            len2 = getLength(i2, j2);
        }
        int median = 0;
        if (len1 == 2 && len2 == 2) {
            median = (Math.max(nums1[i1], nums2[i2]) + Math.min(nums1[j1], nums2[j2])) / 2;
        }
        return median;
    }

    private static int getMedianIdx(int[] nums, int start, int end) {
        int len = getLength(start, end);
//        return len % 2 == 0 ? (nums[len / 2] + nums[len / 2 + 1]) / 2 : nums[len / 2];
        return start + len / 2;
    }

    private static int getLength(int start, int end) {
        return end - start + 1;
    }

    static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n >= 10) return 0;
        int total = 10;
        int cnt = 9;
        for (int i = 2; i <= n; i++) {
            cnt *= 11 - i;
            total += cnt;
        }
        return total;
    }

    static boolean canMeasureWater(int x, int y, int z) {
        return x + y == z || (x + y > z) && z % gcd(x, y) == 0;
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int low = 1, high = (num >> 1) + 1;

        while (low <= high) {
            int mid = (high + low) >> 1;
            int sq = mid * mid;
            if (sq == num) return true;
            else if (sq > num) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    static int kDifference(int[] a, int k) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int num : a) {
            int x = num + k;
            int y = num - k;
            if (!(set.contains(x) || set.contains(y))) {
                set.add(num);
            } else {
                if (set.contains(x)) count++;
                if (set.contains(y)) count++;
            }
        }
        return count;
    }

    static boolean validParenthesis(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '(':
                    stack.push(')');
                    break;
                default:
                    if (!Character.isDigit(c) && (stack.isEmpty() || stack.pop() == ')')) return false;
            }
        }
        return true;
    }

    static int maxProfit(int[] prices) {
        int maxDiff = prices[1] - prices[0];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxDiff = Math.max(prices[i] - min, maxDiff);
            min = Math.min(prices[i], min);
        }
        return maxDiff;
    }

    static int maxProfitII(int[] prices) {
        int i = 0;
        int len = prices.length;
        int maxProfit = 0;
        while (i < len - 1) {
            //find local minima
            while (i < len - 1 && prices[i + 1] <= prices[i]) {
                i++;
            }

            if (i == len - 1) {
                break;
            }
            int buy = prices[i++];

            //find local maxima
            while (i < len - 1 && prices[i + 1] >= prices[i]) {
                i++;
            }
            maxProfit += prices[i] - buy;
        }
        return maxProfit;
    }

    static int wiggleSequence(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length < 3) return 1;
        int delta = nums[1] - nums[0];
        int count = delta != 0 ? 2 : 1;
        for (int i = 2; i < nums.length; i++) {
            int newDelta = nums[i] - nums[i - 1];
            if (newDelta != 0 && newDelta * delta < 0) {
                count++;
                delta = newDelta;
            }
        }
        return count;
    }

    static String intToRoman(int num) {
        int[] nums =        {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans =   {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        while (num > 0) {
            for (int i = prev; i < nums.length; i++) {
                if (nums[i] <= num) {
                    num -= nums[i];
                    prev = i;
                    sb.append(romans[i]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    static int romanToInt(String roman) {
        char prev = ' ';
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            char curr = roman.charAt(i);
            if (curr  == 'M' && prev != 'C') result += 1000;
            else if (curr  == 'M' && prev == 'C') result += 800;
            else if (curr  == 'D' && prev != 'C') result += 500;
            else if (curr  == 'D' && prev == 'C') result += 300;
            else if (curr  == 'C' && prev != 'X') result += 100;
            else if (curr  == 'C' && prev == 'X') result += 80;
            else if (curr  == 'L' && prev != 'X') result += 50;
            else if (curr  == 'L' && prev == 'X') result += 30;
            else if (curr  == 'X' && prev != 'I') result += 10;
            else if (curr  == 'X' && prev == 'I') result += 8;
            else if (curr  == 'V' && prev != 'I') result += 5;
            else if (curr  == 'V' && prev == 'I') result += 3;
            else if (curr  == 'I') result += 1;
            prev = curr;
        }
        return result;
    }

    static int wordDistance(String[] a, String s1, String s2) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            String s = a[i];
            List<Integer> list;
            if ((list = map.get(s)) == null) {
                list = new ArrayList<>();
                map.put(s, list);
            }
            list.add(i);
        }

        List<Integer> s1Idx = map.get(s1);
        List<Integer> s2Idx = map.get(s2);

        int i = 0, j = 0;
        int dist = Integer.MAX_VALUE;
        while (i < s1Idx.size() && j < s2Idx.size()) {
            int iIdx = s1Idx.get(i);
            int jIdx = s2Idx.get(j);
            dist = Math.min(dist, Math.abs(iIdx - jIdx));
            if (iIdx < jIdx) i++;
            else j++;
        }
        return dist;
    }

    public static void main(String[] args) {
//        int a[] = {1,1,1,2,2,3,3,3};
//        int i = removeDuplicatesII(a);
//        for (int j  = 0; j < i; j++) {
//            System.err.print(a[j] + " ");
//        }
        int bits[] = new Misc().countBits(5);
        for (int i : bits) System.err.print(i + " ");
        System.err.println("");
        int nums[] = {1,1,1,2,2,3, 4,4,4,4};
        List<Integer> list = topKFrequent(nums, 2);
        for (int i : list) System.err.print(i + " ");
        int dup[] = {1,2,3,2};
        System.err.println();
        System.err.println(findDuplicate(dup));
        int nums2[] = {0, 1, 0, 3, 12};
        moveZeroes(nums2);
        for (int i : nums2) System.err.print(i + " ");
        System.err.println("");
        int nums3[] = {0, 1, 4, 3, 0};
        moveZeroes(nums3);
        for (int i : nums3) System.err.print(i + " ");
        System.err.println("");
        int nums4[] = {3, 0, 6, 1, 5};
        System.err.println(hIndex(nums4));
        int maj[] = {1,1,1,1,1,1,2,2};
        System.err.println(majorityElement(maj));
        List<Integer> list1 = majorityElementII(maj);
        for (int i : list1) System.err.print(i + " ");
        System.err.println("");
        int dup2[] = {1,2,3};
        System.err.println(containsDuplicate(dup2));
        int dup3[] = {1,2,1};
        System.err.println(containsNearbyDuplicate(dup3, 2));
        int dup4[] = {0,Integer.MAX_VALUE};
        System.err.println(containsNearbyAlmostDuplicate(dup4, 1, Integer.MAX_VALUE));
        System.err.println(Integer.MAX_VALUE);
        System.err.println(sqrt(64));
        int dup1[] = {1,1,1};
        System.err.println(removeDuplicatesII(dup1));
        int dup11[] = {1,2,2,2,3};
        System.err.println(removeDuplicatesII(dup11));
        grayCode(3);
        System.err.println(longestValidParentheses("()()()"));
        System.err.println(longestValidParentheses("(("));
        int[] arr = {1,2,3,4};
        findSubArray(arr, 10);
        int el[] = {1,2,2,4,5,6,7};
        findElement(el, 2);
        int hist[] = {6, 2, 5, 4, 5, 1, 6};
        System.err.println(histogramArea(hist));
        int el1[] = {0,2,3,4,5};
        findElement(el1, 0);
        System.err.println(numberToWords(89));
        System.err.println(numberToWords(890));
        System.err.println(numberToWords(12550));
        System.err.println(numberToWords(1234510));
        System.err.println(numberToWords(12345));
        System.err.println(numberToWords(2) + "end");
        System.err.println(longestValidParentheses(")"));
        int ar1[] = {1, 12, 15, 26, 38};
        int ar2[] = {2, 13, 17, 30, 45};
        System.err.println(findMedianSortedArrays(ar1, ar2));
        System.err.println(countNumbersWithUniqueDigits(2));
        System.err.println(gcd(2, 10));
        System.err.println(gcd(10, 2));
        System.err.println(canMeasureWater(3, 5, 4));
        System.err.println(canMeasureWater(4, 4, 4));
        System.err.println(isPerfectSquare(16));
        System.err.println(isPerfectSquare(17));
        int[] a = {5,1,5,3,4};
        System.err.println(kDifference(a, 2));
        System.err.println(validParenthesis("123(45))"));
        int arr1[] = {80, 2, 6, 3, 100};
        System.err.println(maxProfit(arr1));
        int price[] = {100, 180, 260, 310, 40, 535, 695};
        System.err.println(maxProfitII(price));
        System.err.println(countNumbersWithUniqueDigits(2));
        int[] seq = {1,7,4,9,2,5};
        System.err.println(wiggleSequence(seq));
        System.err.println(intToRoman(2014));
        System.err.println(romanToInt("MMXIV"));
        System.err.println(romanToInt("MCMIII"));

        String[] sarr = {"practice", "makes", "perfect", "coding", "makes"};
        System.err.println(wordDistance(sarr, "coding", "makes"));
    }
}
