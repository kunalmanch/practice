package eight.java;

/**
 * Created by kmishra on 4/24/2016.
 */
public class LongestPalSubString {

    static String longestpalindromicSubstring(String s) {
        int len = s.length();
        boolean[][] p = new boolean[len][len];
        int maxLen = 1;
        p[len - 1][len - 1] = true;
        int st = 0, en = 0;
        for (int i = 0; i < len - 1; i++) {
            p[i][i] = true;
            st = en = i;
        }
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                p[i][i + 1] = true;
                st = i;
                en = i+1;
            }
        }
        for (int l = 3; l <= len; l++) {
            for (int i = 0; i <= len-l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    p[i][j] = p[i + 1][j - 1];
                    if (p[i][j]  && l > maxLen) {
                        st = i;
                        en = j;
                        maxLen = j - i + 1;
                    }
                } else {
                    p[i][j] = false;
                }
            }
        }
        return s.substring(st, en + 1);
    }

    public static void main(String[] args) {
        System.err.println(longestpalindromicSubstring("abcba"));
    }
}
