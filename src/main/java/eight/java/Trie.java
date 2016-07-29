package eight.java;

import java.util.HashMap;

/**
 * Created by kmishra on 5/12/2016.
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode prev = root, next;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if ((next = prev.trieNodes.get(c)) == null) {
                next = new TrieNode();
                prev.trieNodes.put(c, next);
            }
            prev = next;
        }
        prev.terminal = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode prev = root, next;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if ((next = prev.trieNodes.get(c)) == null) {
                return false;
            }
            prev = next;
        }
        return prev.terminal;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode prev = root, next;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if ((next = prev.trieNodes.get(c)) == null) {
                return false;
            }
            prev = next;
        }
        return true;
    }

    class TrieNode {
        // Initialize your data structure here.
        HashMap<Character, TrieNode> trieNodes;
        boolean terminal;
        public TrieNode() {
            trieNodes = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("foot");
        trie.insert("football");
        System.err.println(trie.search("foo"));
        System.err.println(trie.startsWith("foo"));
        System.err.println(trie.search("football"));
    }
}
