package eight.java;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by kmishra on 5/11/2016.
 */
public class Numbers {

    static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2];
        int min = Integer.MAX_VALUE;
        for (int i  = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int tempSum = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(tempSum - target);
                if (diff == 0) return tempSum;
                if (diff < min) {
                    sum = tempSum;
                    min = diff;
                }
                if (tempSum <= target)
                    j++;
                else
                    k--;
            }
        }
        return sum;
    }

    static boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int x, y;
        x = y = Integer.MAX_VALUE;
        for (int z : nums) {
            if (x >= z) {
                x = z;
            } else if (y >= z) {
                y = z;
            } else {
                return true;
            }
        }
        return false;
    }

    static int addDigits(int num) {
        while (num > 9) {
            int q = num / 10;
            int r = num % 10;
            num = q + r;
        }
        return num;
    }

    static String getHint(String secret, String guess) {
        Map<Character, Set<Integer>> posMap = new HashMap<>();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            Set<Integer> set;
            if ((set = posMap.get(c)) == null) {
                set = new HashSet<>();
                posMap.put(c, set);
                freqMap.put(c, 0);
            }
            set.add(i);
            freqMap.put(c, (freqMap.get(c) + 1));

        }
        int bulls = 0, cows = 0;
        List<Character> cowList = new ArrayList<>();
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            Set<Integer> set = posMap.get(c);
            if (set != null) {
                int freq = freqMap.get(c);
                if (freq == 0) continue;
                if (set.contains(i)) {
                    bulls++;
                    if (freq != 0) freqMap.put(c, freq - 1);
                } else cowList.add(c);
            }
        }

        for (char c : cowList) {
            Set<Integer> set = posMap.get(c);
            if (set != null) {
                int freq = freqMap.get(c);
                if (freq == 0) continue;
                cows++;
                if (freq != 0) freqMap.put(c, freq - 1);
            }
        }

        return bulls + "A" + cows + "B";
    }

    static List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length < 2) return list;
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(nums[nums.length - 1]);
        BinaryTree.TreeNode itr;
        Integer[] result = new Integer[nums.length];
        result[nums.length - 1] = 0;
        int count;
        for (int j = nums.length - 2; j >= 0; j--) {
            itr = root;
            count = 0;
            while (true) {
                int i = nums[j];
                if (i == itr.val) {
                    itr.dup++;
                    break;
                } else if (i < itr.val) {
                    itr.leftCount++;
                    if (itr.left == null) {
                        itr.left = new BinaryTree.TreeNode(i);
                        break;
                    }
                    else itr = itr.left;
                } else {
                    count += itr.dup + itr.leftCount;
                    if (itr.right == null) {
                        itr.right = new BinaryTree.TreeNode(i);
                        break;
                    }
                    else itr = itr.right;
                }
            }
            result[j] = count;
        }
        return Arrays.asList(result);
    }

    static int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (right < nums.length) {
            if (sum >= s) {
                minLength = Math.min(minLength, right - left);
                sum -= nums[left];
                left++;
            } else {
                if (right == nums.length - 1) {
                    return minLength == Integer.MAX_VALUE ? 0 : minLength;
                }
                sum += nums[right++];
            }
        }
        return minLength;
    }

    static int smallestSubWithSum(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (right < nums.length) {
            while (sum <= s && right < nums.length)
                sum += nums[right++];

            // If current sum becomes greater than x.
            while (sum > s && left < nums.length)
            {
                // Update minimum length if needed
                if (right - left < minLength)
                    minLength = right - left;

                // remove starting elements
                sum -= nums[left++];
            }
        }
        return minLength;
    }

    static int reverse(int num) {
        long rev = 0;
        while (num != 0) {
            int r = num % 10;
            num /= 10;
            rev = 10 * rev + r;
        }
        return (int)rev;
    }

    static int myAtoi(String str) {
        if (str.length() == 0) return 0;
        String delim = "[ ]+";
        str = str.replaceAll(delim, "");
        long num = 0;
        int start = 0;
        int sign = 1;
        if (str.charAt(0) == '-') {
            start = 1;
            sign = -1;
        } else if (str.charAt(0) == '+') {
            start = 1;
        }
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isDigit(c)) return 0;
            num = num * 10 + (c - '0');
        }

        return (int)(sign * num);
    }

    static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    static int localMinima(int nums[]) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) {
            return nums[0] < nums[1] ? nums[0] : nums[0] != nums[1] ? nums[1] : -1;
        }
        int l = 0;
        int r = nums.length - 1;
        //2 3 4 6 1 7
        //6 5 4 8 1 9
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid == 0) {
                if (nums[mid] < nums[mid + 1]) return mid;
                else l = mid + 1;
            } else if (mid == nums.length - 1) {
                return nums[mid] < nums[mid - 1] ? nums[mid] : -1;
            } else if (nums[mid -1] > nums[mid] && nums[mid + 1] > nums[mid]) {
                return nums[mid];
            } else if (nums[mid - 1] < nums[mid]) { //nums[mid - 1] > nums[mid] for maxima
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    static int localMaxima(int[] nums) {
        if (nums.length == 0) return -1;

        if (nums.length == 1) return 0;
        int l = 0, r = nums.length - 1;
        if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid == 0) {
                if (nums[mid] > nums[mid + 1]) return mid;
                else l = mid + 1;
            } else if (mid == nums.length - 1) {
                return nums[mid] > nums[mid -1] ? mid : -1;
            } else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] > nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    static boolean isPalindrome(int x) {
        int div = 1;
//        while (x / div >= 10) {
//            div *= 10;
//        }

        while (div < x) {
            div *= 10;
        }
        div /= 10;

        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    static int singleNumber(int[] nums) {
        int x = 0;
        for (int i : nums)
            x ^= i;
        return x;
    }

    static void nextPermutation(int[] nums) {
        if (nums.length == 0) return;
        //find the first value that is less than its right adjacent.
        int p = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                p = i - 1;
                break;
            }
        }

        //find the first num that is greater than p.
        int q = 0;
        for (int i = nums.length - 1; i > p; i--) {
            if (nums[i] > nums[p]) {
                q = i;
                break;
            }
        }

        if(p == 0 && q == 0){
            reverse(nums, 0, nums.length-1);
            return;
        }

        //swap p and q and then reverse p + 1 ... nums.length - 1.
        int temp=nums[p];
        nums[p]=nums[q];
        nums[q]=temp;

        if(p<nums.length-1){
            reverse(nums, p+1, nums.length-1);
        }
    }

    private static void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = temp;
        }
    }

    static int nthUglyNumber(int n) {
        int[] uglies = new int[n + 1];
        uglies[0] = 1;
        int u2Idx = 0, u3Idx = 0, u5Idx = 0;
        for (int i = 1; i <= n; i++) {
            uglies[i] = Math.min(2 * uglies[u2Idx], Math.min(3 * uglies[u3Idx], 5 * uglies[u5Idx]));
            if (uglies[i] == 2 * uglies[u2Idx]) u2Idx++;
            if (uglies[i] == 3 * uglies[u3Idx]) u3Idx++;
            if (uglies[i] == 5 * uglies[u5Idx]) u5Idx++;
        }
        return uglies[n];
    }

    static int[] multiply(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

//        The below method is for arrays that have lowest significant digit at the beginning of the array.
//        for(int i = 0; i < b.length; i++) {
//            int carry = 0;
//            for(int j = 0; j < a.length; j++) {
//                int t = a[j] * b[i] + c[i + j] + carry;
//                carry = t / 10;
//                c[i + j] = t % 10;
//            }
//            if (carry > 0) {
//                c[i + a.length] = (c[i + a.length] + carry) % 10;
//            }
//        }

        for (int i = b.length - 1, k = c.length - 1; i >= 0; i--, k--) {
            int carry = 0;
            int l = k;
            for (int j = a.length - 1; j >= 0; j--, l--) {
                int t = a[j] * b[i] + c[l] + carry;
                carry = t / 10;
                c[l] = t % 10;
            }
            if (carry > 0) {
                c[l] = (c[l] + carry) % 10;
            }
        }

        return c;
    }

    static int[] add(int[] a, int[] b) {
        int[] ans = new int[1 + (a.length > b.length ? a.length : b.length)];
        int i = 0;
        int carry = 0;
        while (i < a.length && i < b.length) {
            int sum = a[i] + b[i] + carry;
            ans[i] = sum % 10;
            carry = sum / 10;
            i++;
        }

        while (i < a.length) {
            int sum = a[i] + carry;
            ans[i] = sum % 10;
            carry = sum / 10;
            i++;
        }

        while (i < b.length) {
            int sum = b[i] + carry;
            ans[i] = sum % 10;
            carry = sum / 10;
            i++;
        }

        if (carry != 0) ans[i] = carry;

        return ans;
    }

    public static int[] subtract(int[] a, int[] b) {
        int[] grt = null, less = null;
        //determine which one is greater.
        if (a.length > b.length) {
            grt = a;
            less = b;
        } else if (a.length == b.length) {
            for (int i = a.length - 1; i >= 0; i--) {
                if (a[i] == b[i]) continue;
                else {
                    if (a[i] > b[i]) {
                        grt = a;
                        less = b;
                    } else {
                        less = a;
                        grt = b;
                    }
                    break;
                }
            }
        } else {
            grt = b;
            less = a;
        }
        int c[] = new int[grt.length];
        int borrow = 0;
        int i = 0;
        while (i < less.length) {
            int x = grt[i];
            int y = less[i];
            int t = x - y + borrow;
            if (t < 0) {
                t += 10;
                borrow = -1;
            } else borrow = 0;
            c[i++] = t;
        }

        while (i < grt.length) {
            int t = grt[i] + borrow;
            if (t < 0) {
                t += 10;
                borrow = -1;
            } else borrow = 0;
            c[i++] = t;
        }
        return c;
    }

    public static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    public static final String[] tens = {
            "",        // 0
            "",        // 1
            "twenty",  // 2
            "thirty",  // 3
            "forty",   // 4
            "fifty",   // 5
            "sixty",   // 6
            "seventy", // 7
            "eighty",  // 8
            "ninety"   // 9
    };

    static String convert(int n) {

        if (n < 0) {
            return "minus " + convert(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        }

        if (n < 1000) {
            return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
        }

        if (n < 1000000) {
            return units[n / 1000] + " thousand" + ((n % 100 != 0) ? " " : "") + convert(n % 1000);
        }

        if (n < 1000000000) {
            return units[n / 1000000] + " million" + ((n % 100 != 0) ? " " : "") + convert(n % 1000000);
        }

        return units[n / 1000000000] + " billion" + ((n % 100 != 0) ? " " : "") + convert(n % 1000000000);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.err.println(increasingTriplet(nums));
        int[] nums2 = {4,3,2,1};
        System.err.println(increasingTriplet(nums2));
        System.err.println(addDigits(324));
        String secret = "1807";
        String guess = "7810";
        System.err.println(getHint(secret, guess));
        secret = "1122";
        guess = "1222";
        System.err.println(getHint(secret, guess));
        int nums1[] = {3, 2, 2, 6, 1};
        List<Integer> list = countSmaller(nums1);
        for (int i : list) System.err.print(i + " ");
        int nums3[] = {1, 4, 45, 6, 10, 19};
        System.err.println("\n" + smallestSubWithSum(53, nums3));
        System.err.println(minSubArrayLen(53, nums3));
        int nums4[] = {1, 4, 0, 0, 3, 10, 5};
        System.err.println(minSubArrayLen(7, nums4));
        System.err.println(reverse(-100));
        System.err.println(myAtoi("  010"));
        System.err.println(isPalindrome(123321));
        int[] nums5 = {2,3,4,6,1,7};
        System.err.println(localMinima(nums5));
        int[] nums6 = {5,6,2,6,7,5,7};
        System.err.println(singleNumber(nums6));
        int x = 1;
        int y = 2;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.err.println(x + " " + y);
        int[] nums7 = {3,4,3,2,1};
        System.err.println(localMaxima(nums7));
        int[] nums8 = {1,2,3};
        nextPermutation(nums8);
        for (int i : nums8) System.err.print(i + " ");
        System.err.println("");
        nextPermutation(nums8);
        for (int i : nums8) System.err.print(i + " ");
        System.err.println("");
        int nums11[] = {1,2,4,8,16,32,64,128};
        System.err.println(threeSumClosest(nums11, 82));
        System.err.println(nthUglyNumber(3));
        int a[] = {1,4};
        int b[] = {1,2};
        int product[] = multiply(a, b);
        for (int i = 0; i < a.length; i++) System.err.print(a[i]);
        System.err.print(" * ");
        for (int i = 0; i < b.length; i++) System.err.print(b[i]);
        System.err.print(" = ");
        for (int i = 0; i < product.length; i++) System.err.print(product[i]);
        int[] a1 = {3,2,3};
        int[] b1 = {7,8};
        int[] sum = add(a1, b1);
        BigInteger i1 = new BigInteger("1234561231231");
        BigInteger i2 = new BigInteger("12121212");
        BigInteger add = i1.add(i2);
        int[] a2 = {0,0,1};
        int[] b2 = {9};
        int[] sub = subtract(a2, b2);
        System.err.println("\n" + convert(19));
    }
}
