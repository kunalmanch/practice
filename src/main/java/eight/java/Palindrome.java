package eight.java;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.util.Arrays;

/**
 * Created by kmishra on 4/22/2016.
 */
public class Palindrome {

     static boolean isPalindrome(String string) {
         int len = string.length();
         int start = 0;
         int end = len - 1;
         while (start < end) {
             if (string.charAt(start++) != string.charAt(end--)) return false;
         }
         return true;
     }

    static void printAllSubStrings(String string) {
        int len = string.length();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                System.err.println(string.substring(i, j + 1));
            }
        }
    }

    static boolean isAnagram(String str1, String str2) {
        int[] freq1 = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            freq1[str1.charAt(i) - 'a'] += 1;
        }
        int[] freq2 = new int[26];
        for (int i = 0; i < str2.length(); i++) {
            freq2[str2.charAt(i) - 'a'] += 1;
        }
        for (int i  = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.err.println(isPalindrome("aba"));
        System.err.println(isPalindrome("abba"));
        System.err.println(isPalindrome("abcba"));
        System.err.println(isPalindrome("abcvba"));

        printAllSubStrings("abcba");

        System.err.println(isAnagram("aba", "baa"));
    }
}
