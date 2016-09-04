package eight.java;

import java.util.*;

/**
 * Created by kmishra on 4/26/2016.
 */
public class BackTracking {

    static void printAllSubsets(boolean a[], int idx) {
        if (idx == a.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i]) System.err.print((i + 1) + " ");
            }
            System.err.println("");
        } else if (idx < a.length) {
            a[idx] = true;
            printAllSubsets(a, idx + 1);
            a[idx] = false;
            printAllSubsets(a, idx + 1);
        }
    }

    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        if (nums.length > 0) {
            boolean[] b = new boolean[nums.length];
            Arrays.sort(nums);
            subsetsHelper(nums, b, subsets, 0);
        }
        return subsets;
    }

    private static void subsetsHelper(int[] nums, boolean[] b, List<List<Integer>> subsets, int idx) {
        if (idx == nums.length) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (b[i]) subset.add(nums[i]);
            }
            subsets.add(subset);
        } else if (idx < nums.length) {
            b[idx] = true;
            subsetsHelper(nums, b, subsets, idx + 1);
            b[idx] = false;
            subsetsHelper(nums, b, subsets, idx + 1);
        }
    }

    /**
     * Print all subsets where subset.size == n.
     */
    static void printAllSubsetsOfN(boolean a[], int idx, int t, int n) {
        if (t == n) {
            for (int i = 0; i < a.length; i++)
                if (a[i]) System.err.print((i + 1) + " ");
            System.err.println("");
        } else if (idx < a.length) {
            a[idx] = true;
            printAllSubsetsOfN(a, idx + 1, t + 1, n);
            a[idx] = false;
            printAllSubsetsOfN(a, idx + 1, t, n);
        }
    }

    static void printPermutation(int a[], int l, int r) {
        if (l == r) {
            for (int i : a) System.err.print(i + " ");
            System.err.println("");
        } else {
            for (int i = l; i <= r; i++) {
                //swap l and i
                int temp = a[l];
                a[l] = a[i];
                a[i] = temp;
                printPermutation(a, l + 1, r);
                //swap back l and i, i.e., backtrack
                temp = a[l];
                a[l] = a[i];
                a[i] = temp;
            }
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        int nums[] = {1,2,3,4,5,6,7,8,9};
        boolean b[] = {false,false,false,false,false,false,false,false,false};
        int used = 0;
        combinationSum3Helper(nums, b, k, n, used, 0, ans, 0);
        return ans;
    }

    private static void combinationSum3Helper(int[] nums, boolean[] b, int k, int n, int used, int sum, List<List<Integer>> ans, int idx) {
        if (sum == n && k == used) {
            List<Integer> t = new ArrayList<>();
            for (int i = 0; i < b.length; i++) {
                if (b[i]) {
                    t.add(nums[i]);
                }
            }
            ans.add(t);
        } else if (idx < b.length && used < k && sum < n) {
            b[idx] = true;
            combinationSum3Helper(nums, b, k, n, used + 1, sum + nums[idx], ans, idx + 1);
            b[idx] = false;
            combinationSum3Helper(nums, b, k, n, used, sum, ans, idx + 1);
        }
    }

    static boolean canCrossRiver(boolean[] stones, int jump) {
        if (stones[jump] == false) return false;
        return canCrossRiver(stones, jump, jump);
    }

    /**
     *
     * @param stones the arrangement of stones and water, in the form of an array.
     * @param jump current jump length.
     * @param jumped total jump length so far.
     * @return
     */
    private static boolean canCrossRiver(boolean[] stones, int jump, int jumped) {
        if (jumped >= stones.length) return true;
        else {
            if (jump <= 0 || !stones[jumped])
                return false;
            else if (canCrossRiver(stones, jump, jumped + jump))
                return true;
            else if (canCrossRiver(stones, jump + 1, jumped + jump + 1))
                return true;
            else if (canCrossRiver(stones, jump - 1, jumped + jump - 1))
                return true;
        }
        return false;
    }

    static String isPossible(int a, int b, int c, int d) {
//        if (a > c || b > d) {
//            return "No";
//        } else if (a == c && b == d) {
//            return "Yes";
//        } else if (c > d) {
//            return isPossible(a, b, c - d, d);
//        }
//
//        return isPossible(a, b, c, d - c);

        while (c > a || d > b) {
            if (c > d) {
                c -= d;
            } else if (d > c) {
                d -= c;
            }
        }
        return a==c && b==d ? "Yes" : "No";
    }

    static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) return result;
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r','s'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});

        letterComboHelper(result, new StringBuilder(), 0, map, digits);
        return result;
    }

    static void letterComboHelper(List<String> result, StringBuilder sb, int idx, HashMap<Character, char[]> map, String digits) {
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }

        char ar[] = map.get(digits.charAt(idx));
        for (int i = 0; i < ar.length; i++) {
            sb.append(ar[i]);
            letterComboHelper(result, sb, idx + 1, map, digits);
            sb.deleteCharAt(sb.length() - 1);//backtracking
        }
    }

    public static void main(String[] args) {
        boolean a[] = new boolean[4];
        printAllSubsets(a, 0);
        printAllSubsetsOfN(a, 0, 0, 3);
//        int nums[] = {1,2,3};
//        printPermutation(nums, 0, nums.length - 1);
        System.err.println("combination sumn");
        List<List<Integer>> lists = combinationSum3(3,9);
        for (List<Integer> list : lists) {
            for (int i : list) System.err.print(i + " ");
            System.err.println("");
        }
        int nums[] = {4,1,0};
        List<List<Integer>> lists1 = subsets(nums);
        System.err.println(lists1.size());
        boolean[] stones = {true, true, false, true};
        System.err.println(canCrossRiver(stones, 1));
        System.err.println(isPossible(1,4,5,9));
        List<String> list = letterCombinations("23");
        for (String s :list) System.err.println(s);
    }
}
