package eight.java;

/**
 * Created by kmishra on 5/11/2016.
 */
public class Booleans {

    static int bulbSwitchValidator(int n) {
        boolean bulbs[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((j + 1) % (i + 1) == 0) {
                    bulbs[j] = !bulbs[j];
                }
            }
        }
        int count = 0;
        for (boolean b : bulbs) {
            if (b) count++;
        }
        return count;
    }

    static int bulbSwitch(int n) {
        int lo = 1;
        int hi = n / 2 + 1;
        while (lo <= hi) {
            int sqrt = (lo + hi) / 2;
            long sq = Math.abs(sqrt * sqrt);
            if (sq == n || sq < n && n < (sqrt + 1) * (sqrt + 1)) {
                return sqrt;
            } else if (sq > n) {
                hi = sqrt - 1;
            } else {
                lo = sqrt + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.err.println(bulbSwitchValidator(99));
        System.err.println(bulbSwitch(64));
    }
}
