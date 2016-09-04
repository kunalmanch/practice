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
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        Map<Character, Character> map = new HashMap<>();
        Set<Character> valueSet = new HashSet<>();

        for (int i = 0; i < s1.length(); i++) {
            char s1Char = s1.charAt(i);
            char s2Char = s2.charAt(i);

            if (map.containsKey(s1Char)) {
                if (s2Char != map.get(s1Char)) return false;
            } else {
                if (valueSet.contains(s2Char)) return false;
                map.put(s1Char, s2Char);
                valueSet.add(s2Char);
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

    static List<List<Integer>> pascalsTriangle(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        int k = 0;
        while (k < n) {
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            for (int i = 0; i < pre.size() - 1; i++) {
                curr.add(pre.get(i) + pre.get(i + 1));
            }
            if (k != 0) curr.add(1);
            pre = curr;
            result.add(pre);
            k++;
        }
        return result;
    }

    static int findMinRotated2(int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            if (lo == hi) return a[lo];
            if (hi - lo == 1) return a[lo] < a[hi] ? lo : hi;

            if (a[lo] < a[hi]) return a[lo];

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] > a[hi]) lo = mid;
            else hi = mid;
        }
        return a[0];
    }

    static int findMaxRotated2(int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi) {
            if (lo == hi) return a[lo];
            if (hi - lo == 1) return a[lo] > a[hi] ? lo : hi;

            if (a[lo] < a[hi]) return a[hi];

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] > a[hi]) lo = mid;
            else hi = mid;
        }
        return a[0];
    }

    static int findMaxIncDec(int a[]) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            if (lo == hi - 1) return a[lo] > a[hi] ? a[lo] : a[hi];

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) return a[mid];

            if (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }


    static int binarySearchInRotatedArray(int[] a, int num) {
        int pivot = findMaxRotated2(a);
        int bs = binarySearch(a, num, 0, pivot);
        if (bs != -1) return bs;
        else return binarySearch(a, num, pivot + 1, a.length - 1);
    }

    static int binarySearch(int[] a, int num, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + ((hi - lo) >> 1);

        if (a[mid] == num) return mid;

        if (a[mid] < num) return binarySearch(a, num, mid + 1, hi);
        return binarySearch(a, num, lo, mid - 1);
    }

    static int ones(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n-1);
            count++;
        }
        return count;
    }

    static void preInToPost(int[] in, int[] pre, int[] post, int preIdx, int postIdx, int lo, int hi) {
        if (preIdx >= pre.length) return;
        int key = pre[preIdx];
        int i;
        for (i = lo; i <= hi; i++) {
            if (in[i] == key) break;
        }
        preIdx++;
        preInToPost(in, pre, post, preIdx, postIdx, lo, i - 1);
        preInToPost(in, pre, post, preIdx, postIdx, i + 1, hi);
        post[postIdx++] = key;
    }

    static BinaryTree.TreeNode bstFromPre(int[] pre, int idx, int key, int min, int max) {
        if (idx > pre.length - 1) return null;
        BinaryTree.TreeNode treeNode = null;
        if (min < key && key < max) {
            treeNode = new BinaryTree.TreeNode(key);
            idx++;
            if (idx < pre.length) {
                treeNode.left = bstFromPre(pre, idx, pre[idx], min, key);
                treeNode.right = bstFromPre(pre, idx, pre[idx], key, max);
            }

        }
        return treeNode;
    }

    static void swap(int a, int b) {//5,3
        a = a + b;//5 + 3 = 8
        b = a - b;//8 - 3 = 5
        a = a - b;//8 - 5 = 3

    }

    static int countPrimes(int n) {
        boolean[] primes = new boolean[n + 1];

//        if (n == 2) return 1;
//        if (n == 3) return 2;

        Arrays.fill(primes, true);

        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (primes[i]) count++;
        }
        return count;
    }

    static void bstToDLL(BinaryTree.TreeNode root, BinaryTree.TreeNode head, BinaryTree.TreeNode prev) {
        if (root == null) return;

        bstToDLL(root.left, head, prev);

        if (prev == null) {
            head = root;
        } else {
            prev.right = root;
            root.left = prev;
        }
        prev = root;

        bstToDLL(root.right, head, prev);
    }

    static int lcs(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    static int localMinima(int[] a) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            if (lo == hi) return a[lo];
            if (lo - hi == 1) return Math.min(a[lo], a[hi]);

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) return a[mid];

            if (a[mid] > a[mid - 1]) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    static int localMaxima(int[] a) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            if (lo == hi) return a[lo];
            if (lo - hi == 1) return Math.max(a[lo], a[hi]);

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) return a[mid];

            if (a[mid] < a[mid - 1]) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    static int earliestDuplicate(int[] a) {
        Set<Integer> set = new HashSet<>();
        int min = a.length - 1;
        for (int i = a.length - 1; i >= 0; i--) {
            if (set.contains(a[i])) {
                min = Math.min(i, min);
            } else {
                set.add(a[i]);
            }
        }
        return a[min];
    }

    static int matrixCountPath(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    static boolean binarySearchMatrix(int[][] mat, int t) {
        int m = mat.length;
        int n = mat[0].length;
        int r = 0, c = n - 1;
        while (r < m && c < n) {
            if (t == mat[r][c]) return true;
            if (t < mat[r][c]) c--;
            else r++;
        }
        return false;
    }

    static void bstToPreorderList(BinaryTree.TreeNode root, BinaryTree.TreeNode prev, BinaryTree.TreeNode head) {
        if (root == null) return;
        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        bstToPreorderList(root.left, prev, head);
        bstToPreorderList(root.right, prev, head);
    }

    static boolean compareBT(BinaryTree.TreeNode p, BinaryTree.TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return p.val == q.val && compareBT(p.left, q.left) && compareBT(p.right, q.right);
    }

    static boolean isSymmetric(BinaryTree.TreeNode p, BinaryTree.TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        boolean b1 = isSymmetric(p.left, q.right);
        boolean b2 = isSymmetric(p.right, q.left);

        return p.val == q.val && b1 && b2;
    }

    static void partitionOddEven(int a[]) {
        int i = 0;
        for (int j = i; j < a.length; j++) {
            if (a[j] % 2 == 0) {
                Sorting.swap(a, i, j);
                i++;
            }
        }
        Sorting.swap(a, i, a.length - 1);
    }

    static boolean isBalanced(BinaryTree.TreeNode root) {
        if (root == null) return true;
        int lh = BinaryTree.height(root.left);
        int rh = BinaryTree.height(root.right);

        return Math.abs(lh - rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    static int countOccInSorted(int[] a, int n) {
        int lo = 0, hi = a.length - 1;
        int l = 0;
        while (lo <= hi) {
            if (lo == hi) {
                l = lo;
                break;
            }
            if (hi - lo == 1) {
                if (a[lo] == n) l = lo;
                else if (a[hi] == n) l = hi;
                else throw new RuntimeException("number not in array");
                break;
            }

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] == n && a[mid -1] != n) {
                l = mid;
                break;
            }

            if (a[mid] == n) hi = mid;
            else lo = mid + 1;
        }

        int r = l;
        lo = l;
        hi = a.length - 1;

        while (lo <= hi) {
            if (lo == hi) {
                r = lo;
                break;
            }
            if (hi - lo == 1) {
                if (a[lo] == n) r = lo;
                else if (a[hi] == n) r = hi;
                break;
            }

            int mid = lo + ((hi - lo) >> 1);

            if (a[mid] == n && a[mid + 1] != n) {
                r = mid;
                break;
            }

            if (a[mid] == n) lo = mid;
            else hi = mid - 1;
        }
        return r - l + 1;

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
        System.err.println("================================");
        System.err.println(isomorphic("abba", "cddc"));
        System.err.println(isomorphic("abba", "cdda"));
        System.err.println(isomorphic("cbba", "cddc"));
        System.err.println("================================");
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

        List<List<Integer>> result = pascalsTriangle(5);
        System.err.println("");
        int[] incdec = {0,1,2,3,4,10,9,8};
        System.err.println(findMaxIncDec(incdec));
        int rot[] = {14,15,16,17,18,20,-2,0,1,2,3,4,5};
        System.err.println(binarySearchInRotatedArray(rot, 4));
        System.err.println(ones(6));
        System.err.println(countPrimes(4));
        System.err.println(rot[findMinRotated2(rot)]);
        System.err.println(rot[findMaxRotated2(rot)]);
        System.err.println(localMinima(incdec));
        System.err.println(localMinima(rot));
        System.err.println(localMaxima(incdec));
        System.err.println(localMaxima(rot));
        int[] dup = {10,5,6,5,6,7};
        System.err.println(earliestDuplicate(dup));
        int[][] mat = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.err.println(matrixCountPath(mat));
        System.err.println(binarySearchMatrix(mat, 6));
        int[] arr = {1,2,3,4,5,6};
        partitionOddEven(arr);
        for (int i : arr) System.err.print(i + " ");
        System.err.println("");
        int[] srted = {1,2,3,3,3,3,3,4,5};
        System.err.println(countOccInSorted(srted, 3));
    }


}
