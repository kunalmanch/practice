package eight.java;


import java.util.*;

/**
 * Created by kmishra on 4/27/2016.
 */
public class DP {

    /**
     * longest palindromic sub-sequence
     * @param s
     * @return
     */
    static int lpsq(String s) {
        int len = s.length();
        int maxLen = 1;
        int p[][] = new int[len][len];
        for (int i = 0; i < len; i++) {
            p[i][i] = 1;
        }
        for (int l = 2; l <= len; l++) {
            for (int i = 0; i < len - l + 1; i++) {
                int j = l + i - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    p[i][j] = l == 2 ? 2 : p[i + 1][j - 1] + 2;
                    maxLen = Math.max(maxLen, p[i][j]);
                } else {
                    p[i][j] = Math.max(p[i + 1][j], p[i][j-1]);
                }
            }
        }
        return p[0][len - 1];
    }

    /**
     * longest common sub-sequence
     * @param s1
     * @param s2
     * @return
     */
    static int lcs(String s1, String s2) {
        int l[][] = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    l[i][j] = 1 + l[i - 1][j - 1];
                } else {
                    l[i][j] = Math.max(l[i-1][j], l[i][j - 1]);
                }
            }
        }
        return l[s1.length()][s2.length()];
    }

//    static int get(int[][] l, int x, int y) {
//        if (x < 0) return 0;
//        if (y < 0) return 0;
//        return l[x][y];
//    }

    /**
     * longest increasing sequence
     */
    static int lis(int nums[]) {
        int l[] = new int[nums.length];
        l[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    l[i] = Math.max(1 + l[j], l[i]);
                }
            }
        }
        return l[nums.length - 1];
    }

    static int coinChangeAll(int coins[], int n) {
        int c[][] = new int[n + 1][coins.length];
        for (int j = 0; j < coins.length; j++) {
            c[0][j] = 1;//no money so choose coin chnage of 0.
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < coins.length; j++) {
                int x = i - coins[j] >= 0 ? c[i - coins[j]][j] : 0;
                int y = j >= 1 ? c[i][j - 1] : 0;
                c[i][j] = x + y;
            }
        }
        return c[n][coins.length - 1];
    }

    static int coinChangeMin(int coins[], int v) {
        int c[] = new int[v + 1];
        c[0] = 0;
        for (int i = 1; i <= v; i++) {
            c[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= v; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    c[i] = Math.min(c[i], 1 + c[i - coins[j]]);
                }
            }
        }
        return c[v];
    }

    static int editDistance(String p, String t) {
        int d[][] = new int[p.length() + 1][t.length() + 1];
        int op[] = new int[4];
        int matchIdx = 0;
        int delIdx = 1;
        int insertIdx = 2;
        int replaceIdx = 3;
        for (int i  = 0; i <= p.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                int cost = Integer.MAX_VALUE;
                if (i == 0) {
                    cost = j;
                } else if (j == 0) {
                    cost = i;
                } else {
                    op[matchIdx] = p.charAt(i - 1) == t.charAt(j - 1) ? d[i - 1][j - 1] : 1 + d[i - 1][j - 1];
                    op[delIdx] = 1 + d[i - 1][j];
                    op[insertIdx] = 1 + d[i][j - 1];
                    op[replaceIdx] = 1 + d[i - 1][j - 1];
                    for (int k = 0; k < op.length; k++) {
                        cost = Math.min(cost, op[k]);
                    }
                }
                d[i][j] = cost;
            }
        }
        return d[p.length()][t.length()];
    }

    static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.equals("0")) return 0;

        int[] w = new int[s.length() + 1];
        w[0] = 1;

        //"197"
        for (int i = 1; i <= s.length(); i++) {
            if (isValid(s.substring(i - 1, i))) {
                w[i] += w[i - 1];
            }
            if (i - 2 >= 0 && isValid(s.substring(i - 2, i))) {
                w[i] += w[i - 2];
            }
        }
        return w[s.length()];
    }

    private static boolean isValid(String s) {
        if (s == null || s.isEmpty()) return false;
        int i = Integer.parseInt(s);
        return 1 <= i && i <= 26;
    }

    static int integerBreak(int n) {
        int dp[] = new int[n + 1];
        for (int i = 1; i < n; i++) {
            for (int j  = 1; j <= i; j++) {
                if (i + j > n) continue;
                dp[i + j] = Math.max(Math.max(dp[i], i) * Math.max(dp[j], j), dp[i + j]);
            }
        }
        return dp[n];
    }

    public static int numTrees(int n) {
        int[] c = new int[n + 1];
        c[0] = 1;//no tree
        c[1] = 1;//root tree

        //num of trees with root(i) = counTrees(i) * countTrees(n - 1 - i)
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                c[i] += c[j] * c[i - 1 - j];
            }
        }
        return c[n];
    }

    public static boolean subsetSum(int[] nums, int target) {
        boolean[][] s = new boolean[target + 1][nums.length + 1];

        //for target 0, there is a solution, i.e., empty set.
        for (int i = 0; i <= nums.length; i++) s[0][i] = true;

        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= nums.length; j++) {
                if (nums[j - 1] <= i) {
                    s[i][j] = s[i][j] || s[i - nums[j - 1]][j - 1];
                } else {
                    s[i][j] = s[i][j - 1];
                }
            }
        }

        System.err.print("num \t");
        for (int i : nums) System.err.print(i + "\t");
        System.err.print("\ns/i ");
        for (int i = 0; i <= nums.length; i++) System.err.print(i + "\t");
        System.err.println("\n-------------------------");
        for (int i = 0; i <= target; i++) {
            System.err.print(i + "\t");
            for (int j = 0; j <= nums.length; j++) {
                System.err.print((s[i][j] == true ? 't' : 'f') + "\t");
            }
            System.err.println("");
        }

        //logic to print a subset.
        int i = target;
        int j = nums.length;
        int sum = target;
        while (i > 0 && j > 0) {
            if (s[i][j] && !s[i][j - 1]) {
                System.err.print(nums[j - 1] + " ");
                sum -= nums[j - 1];
                i = sum;
            } else j--;
        }

        return s[target][nums.length];
    }

    /**
     * Can a frog cross the river given an initial jump size.
     * @return
     */
    public static boolean canCross(boolean[] stones, int jump) {
        if (jump >= stones.length) return true;
        if (!stones[jump]) return false;//first jump lands on water.
        // dp[i][j] denotes that frog can jump from index i to j.
        boolean[][] dp = new boolean[stones.length][stones.length];

        for (int i = 0; i < stones.length; i++) {
            for (int j = 0; j < stones.length; j++) {
                if (!stones[i] || !stones[j]) {
                    // if either stones i or stone j is removed, skip
                    continue;
                }

                if (i == 0) {
                    // if jump from position 0, can only reach jump size.
                    dp[i][j] = j == jump;
                } else {
                    int dis = j - i;
                    // if frog can jump from prev to i, then frog can jump from i to j.
                    if (0 <= i - dis && i - dis < stones.length && dp[i - dis][i])
                        dp[i][j] = true;
                    else if (0 <= i - dis - 1 && i - dis - 1 < stones.length && dp[i - dis - 1][i])
                        dp[i][j] = true;
                    else if (0 <= i - dis + 1 && i - dis + 1 < stones.length && dp[i - dis + 1][i])
                        dp[i][j] = true;
                }
                // finish calculating dp[i][j], now check termination
                if (j == stones.length - 1 && dp[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * leetcode jump game I
     */
    static boolean canJump(int[] nums) {
        int reach = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (reach == i && nums[i] == 0) return false;
            reach = Math.max(reach, i + nums[i]);
            if (reach >= nums.length - 1) return true;
        }
        return false;
    }

    /**
     * min jumps needed to cross an array.
     */
    static int minJumps(int[] arr) {
        int steps = 0;
        int reach = 0;
        int lastReach = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > lastReach) {
                steps++;
                lastReach = reach;
            }
            reach = Math.max(reach, i + arr[i]);
        }

        if (reach < arr.length - 1)
            return 0;

        return steps;
    }

    /**
     * Best time to buy and sell stocks.
     * only one transaction allowed.
     */
    static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minShare = Integer.MAX_VALUE;
        for (int price : prices) {
            maxProfit = Math.max(maxProfit, price - minShare);
            minShare = Math.min(minShare, price);
        }
        return maxProfit;
    }

    /**
     * Best time to buy and sell stocks II.
     * no constraint on transactions.
     */
    static int maxProfitII(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int[] maxProfit = new int[prices.length];
        maxProfit[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                maxProfit[i] = maxProfit[i - 1] + prices[i] - prices[i - 1];
            } else {
                maxProfit[i] = maxProfit[i - 1];
            }
        }
        return maxProfit[prices.length - 1];
    }

    /**
     * maximum sum increasing sequence
     */
    static int msis(int[] nums) {
        int[] dp = new int[nums.length];
        int max = Integer.MIN_VALUE;
        dp[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], nums[i] + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * minimum path sum
     */
    static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] += grid[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] += grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * Given a positive integer n,
     * find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
     */
    static int numSquares(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = n;
            for (int j = 0; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);//the 1 is for dp[j * j]
            }
        }
        return dp[n];
    }

    static boolean isMatch(String s, String p) {

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            dp[i][0] = p.charAt(i - 1) == '*' ? dp[i - 1][0] : false;
        }
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(j - 1) == p.charAt(i - 1) || p.charAt(i - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) != '*') {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    static int uniquePaths(int m, int n) {
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

    static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if (obstacleGrid[0][0] != 1) dp[0][0] = 1;

        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) dp[i][0] = 0;
            else dp[i][0] = dp[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) dp[0][i] = 0;
            else dp[0][i] = dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int max[] = new int[nums.length];
        int min[] = new int[nums.length];

        max[0] = min[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            } else {
                max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
            }
            result = Math.max(result, max[i]);
        }
        return result;
    }

    static int maximalSquare(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) dp[i][0] = grid[i][0] - '0';
        for (int i = 0; i < n; i++) dp[0][i] = grid[0][i] - '0';

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] - '0' == 1) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    static int maximalRectangle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] - '0' == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = i == 0 ? 1 : dp[i - 1][j] + 1;
                }
                System.err.print(dp[i][j] + " ");
            }
            System.err.println("");
        }
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            int area = Misc.histogramArea(dp[i]);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }


    static int maximalOnePath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) dp[i][0] = grid[i][0] - '0';
        for (int i = 0; i < n; i++) dp[0][i] = grid[0][i] - '0';

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] - '0' == 1) {
                    dp[i][j] = 1 + Math.max(dp[i - 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * longest increasing path in a given matrix.
     */
    static int longestIncreasingPathInMatrix(int[][] matrix) {
        int result = 1;  // Initialize result
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // Compute longest path beginning from all cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, findLongestFromACell(i, j, matrix, dp));
            }
        }

        return result;
    }

    private static int findLongestFromACell(int i, int j, int[][] matrix, int[][] dp) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }

        if (dp[i][j] != 0) return dp[i][j];

        if (i > 0 && matrix[i][j] < matrix[i - 1][j]) {
            dp[i][j] = Math.max(dp[i][j], findLongestFromACell(i - 1, j, matrix, dp));
        }

        if (i < m - 1 && matrix[i][j] < matrix[i + 1][j]) {
            dp[i][j] = Math.max(dp[i][j], findLongestFromACell(i + 1, j, matrix, dp));
        }

        if (j > 0 && matrix[i][j] < matrix[i][j - 1]) {
            dp[i][j] = Math.max(dp[i][j], findLongestFromACell(i, j - 1, matrix, dp));
        }

        if (j < n - 1 && matrix[i][j] < matrix[i][j + 1]) {
            dp[i][j] = Math.max(dp[i][j], findLongestFromACell(i, j + 1, matrix, dp));
        }

        return ++dp[i][j];
    }

    /**
     * {1,3,1,3,4}
     */
    static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int max = nums[0] > nums[1] ? nums[0] : nums[1];
        if (nums.length == 2) return max;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = max;
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp [i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    static int robII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        //not including last element.
        int[] dp1 = new int[nums.length - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1 [i - 2] + nums[i]);
        }
        //not including first element.
        int[] dp2 = new int[nums.length - 1];
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < nums.length; i++) {
            dp2[i - 1] = Math.max(dp2[i - 2], dp2[i - 3] + nums[i]);
        }

        return Math.max(dp1[nums.length - 2], dp2[nums.length - 2]);
    }

    static class Box {
        int h, w, l;

        Box(int h, int w, int l) {
            this.h = h;
            this.w = w;
            this.l = l;
        }

        int area() {
            return w * l;
        }
    }

    static int boxStacking(Box[] boxes) {
        Box[] pBoxes = new Box[boxes.length * 3];
        for (int i = 0, k = 0; i < boxes.length; i++) {
            Box box = boxes[i];
            pBoxes[k++] = box;
            pBoxes[k++] = new Box(box.l, box.h, box.w);
            pBoxes[k++] = new Box(box.w, box.l, box.h);
        }

        Arrays.sort(pBoxes, (b1, b2) -> b2.area() - b1.area());
        int[] dp = new int[pBoxes.length];
        dp[0] = pBoxes[0].h;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < pBoxes.length; i++) {
            dp[i] = pBoxes[i].h;
            for (int j = 0; j < i; j++) {
                if (pBoxes[i].w < pBoxes[j].w && pBoxes[i].l < pBoxes[j].l) {
                    dp[i] = Math.max(dp[i], dp[j] + pBoxes[i].h);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    static int knapsack01(int[] val, int[] w, int W) {
        int[][] dp = new int[W + 1][val.length + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= W; i++) {
            for (int j = 1; j <= val.length; j++) {
                if (w[j - 1] <= W) {
                    dp[i][j] = Math.max(dp[i][j], val[j - 1] + dp[W - w[j - 1]][j - 1]);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[W][val.length];
    }

    static int cutRod(int[] prices) {
        int[] dp = new int[prices.length + 1];
        for (int i = 1; i <= prices.length; i++) {
            dp[i] = prices[i - 1];
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

        return dp[dp.length - 1];
    }

    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    static int maximumLengthPairs(Pair[] pairs) {
        int[] dp = new int[pairs.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < pairs.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[j].b < pairs[i].a) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    static int longestBitonicSequence(int[] nums) {
        int[] lis = new int[nums.length];
        int[] lds = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            lis[i] = 1;
            lds[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                } else if (nums[j] > nums[i]) {
                    lds[i] = Math.max(lds[i], 1 + lds[j]);
                }
            }
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, lis[i] + lds[i] - 1);
        }
        return max;
    }

    /**
     * Optimal Cost for a BST with associated search frequency.
     */
    static int optCost(int[] freq) {
        int[][] dp = new int[freq.length][freq.length];
        // For a single key, cost is equal to frequency of the key
        for (int i = 0; i < freq.length; i++)
            dp[i][i] = freq[i];

        for (int l = 2; l <= freq.length; l++) {
            for (int i = 0; i < freq.length - l + 1; i++) {
                int j = l + i - 1;
                dp[i][j] = Short.MAX_VALUE;
                for (int r = 0; r <= j; r++) {
                    dp[i][j] = Math.min(dp[i][j], freqSum(freq, i, j) + (r > i ? dp[i][r - 1] : 0) + (r < j ? dp[r + 1][j] : 0));
                }
            }
        }
        return dp[0][freq.length - 1];
    }

    private static int freqSum(int[] freq, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += freq[k];
        }
        return sum;
    }

    static int maxEnvelopes(int[][] envelopes) {
        int dp[] = new int[envelopes.length];

        Arrays.sort(envelopes, (o1, o2) -> o1[0] - o2[0]);

        int max = 0;
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1] && envelopes[j][0] < envelopes[i][0]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * burst balloons
     */
    static int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[][] dp = new int[nums.length][nums.length];

        for (int l = 1; l <= nums.length; l++) {
            for (int i = 0; i < nums.length - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j; k++) {
                    int coins = nums[k] * (i - 1 >= 0 ? nums[i - 1] : 1) * (j + 1 < nums.length ? nums[j + 1] : 1);
                    coins += k != i ? dp[i][k - 1] : 0;
                    coins += k != j ? dp[k + 1][j] : 0;
                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }
        return dp[0][nums.length - 1];
    }

    /**
     * Dungeons game
     */
    static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int dp[][] = new int[m][n];

        dp[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? 1 : Math.abs(dungeon[m - 1][n - 1]) + 1;

        for (int i = m - 2; i >= 0; i--) dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        for (int i = n - 2; i >= 0; i--) dp[m - 1][i] = Math.max(dp[m - 1][i + 1] - dungeon[m - 1][i], 1);

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int minPoints = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(minPoints - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    /**
     * Word Break I
     */
    static boolean wordBreak(String s, Set<String> wordDict) {
        int[] pos = new int[s.length()+1];

        Arrays.fill(pos, -1);

        pos[0]=0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (wordDict.contains(sub)) {
                    pos[j] = i;
                }
            }
        }

        return pos[s.length()] != -1;
    }

    static int minCutsPalindromePartitioning(String s) {
        if (s == null || s.length() < 2) return 0;
        int[] dp = new int[s.length()];
        boolean[][] p = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) p[i][i] = true;

        //first find all palindromes.
        for (int len = 2; len <= s.length(); len++) {
            for (int i = 0; i < s.length() - len + 1; i++) {
                int j = len + i - 1;
                if (len == 2) {
                    p[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    p[i][j] = s.charAt(i) == s.charAt(j) && p[i + 1][j - 1];
                }
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (p[0][i]) {
                dp[i] = 0;
            } else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (p[j + 1][i]) {
                        dp[i] = Math.min(dp[i], 1 + dp[j]);
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }

    /**
     * distinct subsequences
     * similar to coin change problem. choose without i or i if charI == charY.
     */
    static int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                int x = dp[i - 1][j]; //not choosing i.
                int y = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1]: 0;
                dp[i][j] = x + y;
            }
        }
        return dp[s.length()][t.length()];
    }

    static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                // two empty strings have an empty string
                // as interleaving.
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                // s1 is empty.
                else if (i == 0) {
                    dp[i][j] = s2.charAt(j - 1) == s3.charAt(j - 1) ? dp[i][j - 1] : false;
                }
                // s2 is empty.
                else if (j == 0) {
                    dp[i][j] = s1.charAt(i - 1) == s3.charAt(i - 1) ? dp[i - 1][j] : false;
                }
                // Current character of s3 matches with current character of s1,
                // but doesn't match with current character of s2.
                else if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) != s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                }
                // Current character of s3 matches with current character of s2,
                // but doesn't match with current character of s1.
                else if (s1.charAt(i - 1) != s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j - 1];
                }
                // Current character of s3 matches with that of both s1 and s2.
                else if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    static List<Integer> largestDivisibleSubset(int[] nums) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (nums.length == 0) return ans;

        int[] dp = new int[nums.length];
        int[] index = new int[nums.length];

        Arrays.sort(nums);
        int maxDP = 0, maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            index[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < 1 + dp[j]) {
                    dp[i] = 1 + dp[j];
                    index[i] = j;
                }
            }
            if (maxDP < dp[i]) {
                maxDP = dp[i];
                maxIdx = i;
            }
        }

        for (int i = maxIdx; i != -1; i = index[i]) ans.addFirst(nums[i]);
        return ans;
    }

    /**
     * Buy and sell 2 stocks only
     */
    static int maxProfit2Stocks(int prices[]) {
        if (prices.length < 2) return 0;
        int[] profit = new int[prices.length];
        int maxPrice = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            profit[i] = Math.max(profit[i + 1], maxPrice - prices[i]);
        }
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profit[i] = Math.max(profit[i - 1], profit[i] + prices[i] - minPrice);
        }
        return profit[profit.length - 1];
    }

    public static void main(String[] args) {
        System.err.println(lpsq("bbababcab"));
        System.err.println(lcs("abcdgh", "aedfhr"));
        System.err.println(lcs("aggtab", "gxtxayb"));
        int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
        System.err.println(lis(nums));
        int nums1[] = {9, 10,6, 11,7, 12, 7, 19};
        System.err.println(lis(nums1));
        int nums2[] = {2,3,6,7};
        System.err.println("coin change " + coinChangeAll(nums2, 7));
        int nums3[] = {25, 10, 5};
        System.err.println(coinChangeMin(nums3, 30));
        int nums4[] = {2,3,9,1};
        System.err.println(coinChangeMin(nums4, 12));
        System.err.println(editDistance("hubby", "hobby"));
        System.err.println(numDecodings("123"));
        int nums5[] = {1,2,2,2,3,5};
        System.err.println(subsetSum(nums5, 3));
        boolean[] stones = {true, false, true, true};
        System.err.println(canCross(stones, 2));
        int arr[] = {1, 101, 2, 3, 100, 4, 5};
        System.err.println(msis(arr));
        int[][] grid = {
                {1,2,1},
                {1,3,1},
                {1,1,2}
        };
        System.err.println(minPathSum(grid));
        System.err.println(numSquares(12));
        System.err.println(numSquares(8));
        System.err.println(isMatch("bbbbba", "b*ba"));
        System.err.println(isMatch("bba", "b.."));
        System.err.println(isMatch("bba", "bc*"));
        System.err.println(isMatch("a", "a*"));
        System.err.println(uniquePaths(1,2));
        int[][] obstacleGrid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
            };
        System.err.println(uniquePathsWithObstacles(obstacleGrid));
        char[][] cGrid = {
                {'0','0','0','0'},
                {'1','1','1','1'},
                {'1','1','1','0'},
                {'0','1','0','0'}
        };
        System.err.println(maximalRectangle(cGrid));
        System.err.println(maximalSquare(cGrid));
        System.err.println("");
        char[][] cGrid2 = {
                {'0', '0', '0', '1', '1'},
                {'1', '1', '1', '0', '1'},
                {'0', '1', '1', '1', '0'},
                {'0', '0', '1', '0', '0'},
                {'1', '1', '1', '1', '1'}
        };
        System.err.println(maximalOnePath(cGrid2));
        int arr2[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.err.println("min jumps : " + minJumps(arr2));
        int[][] mat = {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };

        System.err.println(longestIncreasingPathInMatrix(mat));
        int[][] mat2 = {
                {1,2}
        };
        System.err.println(longestIncreasingPathInMatrix(mat2));
        int[][] mat3 = {
                {13,5,13,9},
                {5,0,2,9},
                {10,13,11,10},
                {0,0,13,13}
        };
        System.err.println(longestIncreasingPathInMatrix(mat3));
        int[][] mat4 = {
                {0}, {1}, {5}, {5}
        };
        System.err.println(longestIncreasingPathInMatrix(mat4));
        int[] robAr = {1,3,1,3,4};
        System.err.println(rob(robAr));
        int[] robAr2 = {2,1,1,2};
        System.err.println(rob(robAr2));
        int[] robAr3 = {1,3,1};
        System.err.println(rob(robAr3));
        int[] r = {0, 0};
        System.err.println(robII(r));
        Box boxes[] = { new Box(4, 6, 7), new Box(1, 2, 3), new Box(4, 5, 6), new Box(10, 12, 32) };
        System.err.println(boxStacking(boxes));
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int  W = 50;
        System.err.println(knapsack01(val, wt, W));
        int rod[] = {1, 5, 8, 9, 10, 17, 17, 20};
        System.err.println(cutRod(rod));
        Pair pairs[] = { new Pair(5, 24), new Pair(15, 25),
                new Pair(27, 40), new Pair(50, 60) };
        System.err.println(maximumLengthPairs(pairs));
        int bitonic[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5,
                13, 3, 11, 7, 15};
        System.err.println(longestBitonicSequence(bitonic));
        int freq[] = {34, 8, 50};
        System.err.println(optCost(freq));
        int[] jumps = {3,2,1,0,4};
        System.err.println(canJump(jumps));
        int[] jumps2 = {2,3,1,1,4};
        System.err.println(canJump(jumps2));
        int[] jumps3 = {1,1,1,0,4};
        System.err.println(canJump(jumps3));
        int[] jumps4 = {1,2};
        System.err.println(minJumps(jumps4));
        int[][] envelopes = {
                {5,4},
                {6,4},
                {6,7},
                {2,3}
        };
        System.err.println(maxEnvelopes(envelopes));
        int[] balloons = {3, 1, 5, 8};
        System.err.println(maxCoins(balloons));
        int[][] dungeon = {
                {-2, -3,   3},
                {-5, -10,  1},
                {10,  30, -5}
        };
        System.err.println(calculateMinimumHP(dungeon));
        System.err.println(minCutsPalindromePartitioning("aap"));
        System.err.println(minCutsPalindromePartitioning("ababbbabbababa"));
        System.err.println(numDistinct("rabbbit", "rabbit"));
        System.err.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.err.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        int prices[] = {2, 30, 15, 10, 8, 25, 80};
        System.err.println(maxProfit2Stocks(prices));
    }
}
