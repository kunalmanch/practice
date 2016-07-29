package eight.java;

/**
 * Created by kmishra on 5/5/2016.
 */
public class Matrix {

    static int[][] sprialMatrixII(int n) {
        int a[][] = new int[n][n];
        int val = 1, i = 0, j = 0;
        int hCol = n - 1, hRow = n - 1;
        int lCol = 0, lRow = 1;
        boolean left = true,
                down = false,
                right = false,
                up = false;
        while (val <= n*n) {
            for (int aa[] : a) {
                for (int ii : aa) {
                    System.err.print(ii + "\t");
                }
                System.err.println("");
            }
            System.err.println("=================");
            a[i][j] = val++;
            if (left) {
                if (++j > hCol) {
                    down = true;
                    left = false;
                    --j;
                    ++i;
                    --hCol;
                }
            } else if (down) {
                if (++i > hRow) {
                    right = true;
                    down = false;
                    --i;
                    --j;
                    --hRow;
                }
            } else if (right) {
                if (--j < lCol) {
                    up = true;
                    right = false;
                    ++j;
                    --i;
                    ++lCol;
                }
            } else if (up){
                if (--i < lRow) {
                    left = true;
                    up = false;
                    ++i;
                    ++j;
                    ++lRow;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int a[][] = sprialMatrixII(6);
        for (int aa[] : a) {
            for (int i : aa) {
                System.err.print(i + "\t");
            }
            System.err.println("");
        }
    }
}
