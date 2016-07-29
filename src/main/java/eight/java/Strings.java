package eight.java;

import java.util.*;

/**
 * Created by kmishra on 4/25/2016.
 */
public class Strings {

    static String longestCommonPrefix(String[] strings) {
        int matchEnd = Integer.MAX_VALUE;
        if (strings == null || strings.length == 0) return "";
        if (strings.length == 1) return strings[0];
        for (int i = 0; i < strings.length - 1; i++) {
            int currMatchEnd = prefixMatch(strings[i], strings[i+1]);
            if (currMatchEnd < matchEnd) {
                matchEnd = currMatchEnd;
            }
        }

        return strings[0].substring(0, matchEnd);
    }

    /**
     * helper for longestCommonPrefix().
     */
    private static int prefixMatch(String string1, String string2) {
        int minlen = string1.length() > string2.length() ? string2.length() : string1.length();
        int i;
        for (i = 0; i < minlen; i++) {
            if (string1.charAt(i) != string2.charAt(i)) break;
        }
        return i;
    }

    static int strStr(String needle, String haystack) {
        if (needle == null || haystack == null || (!needle.isEmpty() && haystack.isEmpty())) return -1;

        if (needle.length() > haystack.length()) return -1;
        if (needle.isEmpty()) return 0;
        int len = haystack.length() - needle.length();
        for (int i = 0; i <= len; i++) {
            char nChar = needle.charAt(0);
            char hChar = haystack.charAt(i);
            if (nChar == hChar) {
                boolean match = true;
                for (int j = 1, k = i + 1; j < needle.length(); j++, k++) {
                    if (needle.charAt(j) != haystack.charAt(k)) {
                        match = false;
                        break;
                    }
                    match = true;
                }
                if (match) return i;
            }
        }

        return -1;
    }

    static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        int lIdx = 0;
        List<List<String>> list = new ArrayList<>();
        for (String str : strs) {
            int hash = hash(str.toCharArray());
            Integer idx;
            if ((idx = idxMap.get(hash)) == null) {
                idx = lIdx;
                idxMap.put(hash, lIdx++);
                List<String> anagrams = new ArrayList<>();
                list.add(anagrams);
            }
            list.get(idx).add(str);
        }
        for (List<String> l : list) {
            Collections.sort(l);
        }
        return list;
    }

    private static int hash(char[] chars) {
        Arrays.sort(chars);
        int h = 0;
        for (int c : chars) {
            h = h * 29 + c;
        }
        return h;
    }

    static String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        char[] sChars = s.toCharArray();
        vowels.add('a'); vowels.add('e'); vowels.add('i'); vowels.add('o'); vowels.add('u');
        vowels.add('A'); vowels.add('E'); vowels.add('I'); vowels.add('O'); vowels.add('U');
        int i = 0;
        int j = s.length() - 1;
        boolean moveRight = true;
        int canSwap = 0;
        while (i < j) {
            if (canSwap == 2) {
                canSwap = 0;
                moveRight = true;
                char temp = sChars[i];
                sChars[i] = sChars[j];
                sChars[j] = temp;
                i++;
                j--;
            }
            if (moveRight) {
                char c = sChars[i];
                if (!vowels.contains(c)) i++;
                else {
                    moveRight = false;
                    canSwap++;
                }
            } else {
                char c = sChars[j];
                if (!vowels.contains(c)) j--;
                else {
                    canSwap++;
                }
            }
        }
        return new String(sChars);
    }

    static String reverseString(String s) {
        char[] sChars = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            char temp = sChars[l];
            sChars[l] = sChars[r];
            sChars[r] = temp;
            l++; r--;
        }
        return new String(sChars);
    }

    static int maxProduct(String[] words) {
        if (words.length == 0) return 0;
        int result = 0;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        BitSet[] bitSets = new BitSet[words.length];
        int k = 0;
        for (String word : words) {
            bitSets[k] = new BitSet();
            for (int i = 0; i < word.length(); i++) {
                bitSets[k].set(word.charAt(i));
            }
            k++;
        }

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!bitSets[i].intersects(bitSets[j])) {
                    result = Math.max(words[i].length() * words[j].length(), result);
                }
            }
        }

        return result;
    }

    static String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 2) return s;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hashMap.put(s.charAt(i), i);
        }
        ValueComparator comparator = new ValueComparator(hashMap);
        TreeMap<Character, Integer> treemap = new TreeMap<>(comparator);
        treemap.putAll(hashMap);
        int l = 0;
        char[] result = new char[treemap.size()];
        int k  = 0;
        while (!treemap.isEmpty()) {
            Map.Entry<Character, Integer> minEntry = treemap.firstEntry();
            int r = minEntry.getValue();
            char min = Character.MAX_VALUE;
            int index = 0;
            for (int i = l; i <= r; i++) {
                char c = s.charAt(i);
                if (c < min && treemap.containsKey(c)) {
                    min = c;
                    index = i;
                }
            }
            l = index + 1;
            result[k++] = min;
            treemap.remove(min);
        }
        return new String(result);
    }

    static class ValueComparator implements Comparator<Character> {

        Map<Character, Integer> map = new HashMap<>();

        public ValueComparator(Map<Character, Integer> map){
            this.map.putAll(map);
        }

        @Override
        public int compare(Character c1, Character c2) {
            return map.get(c1) - map.get(c2);
        }
    }

    static boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null || str.length() == 0 && pattern.length() == 0) return true;
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) return false;
        Map<Character, String> map = new HashMap<>();
        Set<String> valueSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            char c = pattern.charAt(i);
            if (!valueSet.contains(strs[i])) {
                if (!map.containsKey(c)) map.put(c, strs[i]);
                else return false;
                valueSet.add(strs[i]);
            }
            sb.append(map.get(c));
            sb.append(' ');
        }
        sb.setLength(sb.length() - 1);
        return str.equals(sb.toString());
    }

    static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        if (s.isEmpty() || t.isEmpty()) return true;
        HashMap<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(sc)) {
                if (map.get(sc) != tc) return false;
            } else if (set.contains(tc)) {
                return false;
            } else {
                map.put(sc, tc);
                set.add(tc);
            }
        }
        return true;
    }

    static int lengthOfLastWord(String s) {
        int l = 0;
        s = s.trim();
        for (int i = s.length() - 1; i >= 0; i--, l++) {
           if (s.charAt(i) == ' ') return l;
        }
        return l;
    }

    //abcbcad
    static int longestNonRepeatingSubString(String str) {
        HashSet<Character> hashSet = new HashSet<>();
        int ans = Integer.MIN_VALUE;
        int j = 0, i = 0;
        while (i < str.length() && j < str.length()) {
            char c = str.charAt(i);
            if (hashSet.contains(c)) {
                hashSet.remove(str.charAt(j++));
            } else {
                hashSet.add(c);
                ans = Math.max(ans, ++i - j);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strings = {"abaa", "ab", "abbaa"};
        System.err.println(longestCommonPrefix(strings));
        System.err.println(strStr("a", "a"));
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat", "duh", "ill"};
        List<List<String>> list = groupAnagrams(strs);
        for (List<String> l : list) {
            for (String s : l) {
                System.err.print(s + " ");
            }
            System.err.println("");
        }
        System.err.println(hash("and".toCharArray()));
        System.err.println(hash("dan".toCharArray()));
        System.err.println(reverseVowels("leetcode"));
        System.err.println(reverseVowels("aA"));
        System.err.println(reverseString("abc"));
        String words[] = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.err.println(maxProduct(words));
        String words2[] = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        System.err.println(maxProduct(words2));
        String words3[] = {"a", "aa", "aaa", "aaaa"};
        System.err.println(maxProduct(words3));
        System.err.println(removeDuplicateLetters("cbacdcbc"));
        System.err.println(removeDuplicateLetters("abacb"));
        System.err.println(wordPattern("abba", "dog cat cat fish"));
        System.err.println(wordPattern("abba", "dog cat cat dog"));
        System.err.println(wordPattern("abba", "dog dog dog dog"));
        System.err.println(wordPattern("aaaa", "cat cat cat cat"));
        System.err.println(isIsomorphic("abba", "abab"));
        System.err.println(isIsomorphic("paper", "title"));
        System.err.println(isIsomorphic("ab", "aa"));
        System.err.println(lengthOfLastWord("hello world"));
        System.err.println("hello   ".trim() +'|');
        System.err.println(longestNonRepeatingSubString("bcadabc"));
        System.err.println(longestNonRepeatingSubString("bbbb"));
        System.err.println(longestNonRepeatingSubString("pwwkew"));
    }
}
