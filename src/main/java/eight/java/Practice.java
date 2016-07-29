package eight.java;

import java.util.*;

/**
 * Created by kmishra on 6/4/2016.
 */
public class Practice {

    static String bullsAndCows(String secret, String guess) {
        if (secret.length() != guess.length()) return "0A0B";
        int cows = 0;
        int bulls = 0;

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            if (c == guess.charAt(i)) {
                cows++;
            } else {
                int freq = hashMap.containsKey(c) ? hashMap.get(c) : 0;
                hashMap.put(c, ++freq);
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                char c = guess.charAt(i);
                if (hashMap.containsKey(c)) {
                    bulls++;
                    int freq = hashMap.get(c);
                    if (--freq == 0) hashMap.remove(c);
                    else hashMap.put(c, freq);
                }
            }
        }

        return cows + "A" + bulls + "B";
    }

    /**
     * isomorphic strings
     * abba = cddc
     * abba != cdda
     */
    static boolean isomorphic(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        HashMap<Character, Character> map = new HashMap<>();
        Set<Character> mappedValues = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            char s1char = s1.charAt(i);
            char s2char = s2.charAt(i);
            if (map.containsKey(s1char)) {
                if (map.get(s1char) != s2char) return false;
            } else {
                if (mappedValues.contains(s2char)) return false;
                map.put(s1char, s2char);
                mappedValues.add(s2char);
            }
        }

        return true;
    }

    /**
     * Longest substring without repeating chars
     */
    static int lengthOfLongestSubstring(String str) {
        if (str.length() < 2) return str.length();
        Set<Character> set = new HashSet<>();
        int len = 0;
        int i = 0, j = 0;
        while (j <str.length()) {
            char c = str.charAt(j);
            if (set.contains(c)) {
                set.remove(str.charAt(i++));
            } else {
                set.add(c);
                len = Math.max(len, ++j - i);
            }
        }
        return len;
    }

    static int fibRec(int n) {
        if (n == 0 || n == 1) return 1;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    static int evalRPN(String[] postfix) {
        Deque<String> operatorStack = new ArrayDeque<>();
        Deque<Integer> operandStack = new ArrayDeque<>();
        Set<String> operators = new HashSet<>();
        operators.add("+"); operators.add("-"); operators.add("/"); operators.add("*");
        for (String token : postfix) {
            if (operators.contains(token)) {
                operatorStack.push(token);
            } else {
                operandStack.push(Integer.parseInt(token));
            }

            if (!operatorStack.isEmpty()) {
                int r = operandStack.pop();
                int l = operandStack.pop();
                int result;
                String op = operatorStack.pop();
                switch (op) {
                    case "+":
                        result = l + r;
                        break;
                    case "-":
                        result = l - r;
                        break;
                    case "*":
                        result = l * r;
                        break;
                    case "/":
                        result = l / r;
                        break;
                    default:
                        throw new RuntimeException(op + " not supported");
                }
                operandStack.push(result);
            }
        }
        return operandStack.pop();
    }

    static boolean isPalindrome(int num) {
        int div = 10;
        while (num / div >= 10) {
            div *= 10;
        }

        while (num > 0) {
            int l = num % 10;
            int r = num / div;
            if (l != r) return false;
            num = (num % div) / 10;
            div /= 100;
        }
        return true;
    }

    static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int[] min = new int[nums.length];
        int[] max = new int[nums.length];
        int result;
        min[0] = max[0] = result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max[i] = Math.max(nums[i], nums[i] * max[i - 1]);
                min[i] = Math.min(nums[i], nums[i] * min[i - 1]);
            } else {
                max[i] = Math.max(nums[i], nums[i] * min[i - 1]);
                min[i] = Math.min(nums[i], nums[i] * max[i - 1]);
            }
            result = Math.max(result, max[i]);
        }
        return result;
    }

    static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? 1 + Math.abs(dungeon[m - 1][n - 1]) : 1;

        //initialize the last row.
        for (int i = n - 2; i >= 0 ; i++) dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);
        //initialize the last column.
        for (int i = m - 2; i >= 0; i++) dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);

        for (int i = m - 2; i >= 0; i++) {
            for (int j = n - 2; j >= 0; j++) {
                int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
                dp[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    static boolean subsetSum(int sum, int[] nums) {
        boolean[][] dp = new boolean[sum + 1][nums.length + 1];

        for (int i = 0; i <= nums.length; i++) dp[0][i] = true;

        for (int i = 1; i <= sum; i++) {
            for (int j = 1; j <= nums.length; j++) {
                if (i >= nums[j - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - nums[j - 1]][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[sum][nums.length];
    }

    static int longestValidParenthesis(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int len = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    len = Math.max(len, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return len;
    }

    static int histogramArea(int[] hist) {
        int maxArea = 0, i = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (i < hist.length) {
            if (stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
                stack.push(i++);
            } else {
                int tp = stack.pop();
                int tempArea = hist[tp] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                maxArea = Math.max(tempArea, maxArea);
            }
        }

        while (!stack.isEmpty()) {
            int tp = stack.pop();
            int tempArea = hist[tp] * (stack.isEmpty() ? i : i - stack.peek() - 1);
            maxArea = Math.max(tempArea, maxArea);
        }

        return maxArea;
    }

    /**
     * remove duplicates from a list.
     */
    static Lists.ListNode removeDuplicates(Lists.ListNode listHead) {
        Set<Integer> set = new HashSet<>();
        Lists.ListNode helper = new Lists.ListNode(-1);
        helper.next = listHead;
        Lists.ListNode prev = helper, curr = helper.next;
        while (curr != null) {
            if (set.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                set.add(curr.val);
                prev = curr;
            }
            curr = curr.next;
        }
        return helper.next;
    }

    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        System.err.println(bullsAndCows(secret, guess));
        secret = "1123";
        guess = "0111";
        System.err.println(bullsAndCows(secret, guess));
        secret = "11";
        guess = "10";
        System.err.println(bullsAndCows(secret, guess));
        System.err.println(2 >> 1);
        System.err.println(2 << 3);
        System.err.println(isomorphic("abba", "cddc"));
        System.err.println(isomorphic("abba", "cdda"));
        System.err.println(isomorphic("cbba", "cddc"));
        System.err.println(lengthOfLongestSubstring("abcdbcbb"));
        System.err.println(lengthOfLongestSubstring("pwwkew"));
        System.err.println(lengthOfLongestSubstring("bbb"));
        System.err.println(lengthOfLongestSubstring("ab"));
        System.err.println(lengthOfLongestSubstring("abcabcd"));
        System.err.println(fibRec(4));

        String[] tokens = {"2", "1", "+", "3", "*"};
        System.err.println(evalRPN(tokens));
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.err.println(evalRPN(tokens2));
        System.err.println(isPalindrome(312213));
        int[] nums = {-2,-3,-4};
        System.err.println(maxProduct(nums));

        int nums5[] = {1,2,2,2,3,5};
        System.err.println(subsetSum(3, nums5));
        System.err.println(longestValidParenthesis("()()()"));

        Lists.ListNode node = new Lists.ListNode(1);
        node.next = new Lists.ListNode(2);
        node.next.next = new Lists.ListNode(3);
        node.next.next.next = new Lists.ListNode(2);
        node.next.next.next.next = new Lists.ListNode(3);
        node.next.next.next.next.next = new Lists.ListNode(4);
        Lists.ListNode head = removeDuplicates(node);
        while (head != null) {
            System.err.print(head.val + " --> ");
            head = head.next;
        }
        System.err.println("");
    }
}
