/*
 * Implement a trie with insert, search, and startsWith methods using Map to store children
 * A trie node should contains the character, its children and the flag that marks if it is a leaf node.
 */

package main.java;

public class Trie {

    private static final int SIZE = 26;
    private static TrieNode root;

    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode() {
            children = new TrieNode[SIZE];
            isLeaf = false;
        }
    }

    private static void insert(String s) {
        TrieNode node = root;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isLeaf = true;
    }

    private static boolean search(String s) {
        TrieNode node = searchWord(s);
        return (node != null && node.isLeaf) ? true : false;
    }

    private static TrieNode searchWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                return null;
            else {
                node = node.children[index];
            }
        }
        return (node == root) ? null : node;

    }

    private static boolean startsWith(String s) {
        TrieNode node = searchWord(s);
        return (node != null) ? true : false;
    }

    public static void main(String[] args) {
        root = new TrieNode();
        String s1 = "care";
        String s2 = "cart";
        String s3 = "cape";

        insert(s1);
        insert(s2);
        insert(s3);

        String s4 = s1;
        System.out.println(s4 + " is present in the trie: " + search(s4) + "\n");

        String s5 = "cal";
        System.out.println(s5 + " is present in the trie: " + search(s5) + "\n");

        String s6 = "ca";
        System.out.println("The trie contains words with the prefix " + s6 + ": "+ startsWith(s6) + "\n");

        String s7 = "aa";
        System.out.println("The trie contains words with the prefix " + s7 + ": "+ startsWith(s7) + "\n");


    }
}
