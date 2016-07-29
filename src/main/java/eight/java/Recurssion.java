package eight.java;

/**
 * Created by kmishra on 6/1/2016.
 */
public class Recurssion {

    /**
     *  Count of n digit numbers whose sum of digits equals to given sum
     */
    static int getNums(int sum, int n) {
        int ans = 0;
        for (int i = 1; i <= 9; i++) {
            if (sum - i >= 0) {
                ans += getNumsHelper(sum - i, n - 1);
            }
        }
        return ans;
    }

    /**
     * Below can be optimized with memoization.
     */
    private static int getNumsHelper(int sum, int n) {
        int ans = 0;
        if (n == 0) {
            return sum == 0 ? 1 : 0;
        }
        for (int i = 0; i <= 9; i++) {
            if (sum - i >= 0) {
                ans += getNumsHelper(sum - i, n - 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.err.println(getNums(2, 5));
    }
}
