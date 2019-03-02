/*
 * Implement a trie with insert, search, and startsWith methods using Map to store children
 * A trie node should contains the character, its children and the flag that marks if it is a leaf node. You can use this diagram to walk though the Java solution.
 */

package main.java;
import java.util.*;

public class TrieWithMap {

    static TrieNodeWithMap root;

    static class TrieNodeWithMap {
        char elem;
        Map<Character, TrieNodeWithMap> children = new HashMap<>();
        boolean isLeaf;

        public TrieNodeWithMap() {}

        public TrieNodeWithMap(char elem) {
            this.elem = elem;
        }
    }

    private static void insert(String word) {
        if(word == null || word.isEmpty())
            return;
        Map<Character, TrieNodeWithMap> children = root.children;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNodeWithMap node;

            if(children.containsKey(c)){
                node = children.get(c);
            }else {
                node = new TrieNodeWithMap(c);
                children.put(c, node);
            }
            children = node.children;
            node.isLeaf = (i == word.length() - 1) ? true : false;
        }
    }

    private static boolean search(String word) {
        TrieNodeWithMap node = searchNode(word);
        return (node != null && node.isLeaf) ? true : false;

    }

    private static boolean startsWith(String word) {
        return (searchNode(word) != null) ? true : false;

    }

    private static TrieNodeWithMap searchNode(String word) {
        Map<Character, TrieNodeWithMap> children = root.children;
        TrieNodeWithMap node = null;

        for(int i = 0; i < word.length(); i++) {
            char child = word.charAt(i);
            if(children.containsKey(child)) {
                node = children.get(child);
                children = node.children;
            }else
                return null;
        }
        return node;
    }

    public static void main(String[] args) {
        root = new TrieNodeWithMap();

        String s1 = "car";
        String s2 = "cat";
        String s3 = "cap";

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
