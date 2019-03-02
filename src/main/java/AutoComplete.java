/*
 * We are given a Trie with a set of cities stored in it. Now the user types in a prefix of his search query,
 * we need to give him all recommendations to auto-complete his query based on the cities stored in the Trie.
 * For example if the Trie store {“san francisco”, “san ramon”, “santa clara”, “albany”, “sunnyvale”, "houston", "san jose"}
 * and the User types in “san ”
 * then he must be shown {“san francisco”, “san ramon”, "san jose"}.
 */
package main.java;
import java.util.*;

public class AutoComplete {

    private static final int SIZE = 27;
    private static TrieNode root;

    static class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[SIZE];
        }
    }

    private static void insert(String word) {
        if(word == null || word.isEmpty())
            return;

        TrieNode node = root;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = (c == ' ') ? 26 : c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];

        }
        node.word = word;
    }

    private static TrieNode search(String word) {
        if(word == null || word.isEmpty())
            return null;

        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = (c == ' ') ? 26 : c - 'a';
            if(node.children[index] != null)
                node = node.children[index];
            else
                return null;
        }
        return (node == root) ? null : node;
    }

    private static void traverse(TrieNode node, List<String> list) {
        for(int i = 0; i < SIZE; i++) {
            if(node.children[i] != null) {
                if(node.children[i].word != null)
                    list.add(node.children[i].word);
                traverse(node.children[i], list);
            }
        }
    }

    private static List<String> autoComplete(String prefix) {
        TrieNode node = search(prefix);
        if(node == null)
            return Collections.emptyList();

        List<String> list = new ArrayList<>();
        if(node.children != null)
            traverse(node, list);
        return list;
    }

    public static void main(String[] args) {
        root = new TrieNode();
        String[] cities = {"san francisco", "san ramon", "san carlos" , "santa clara", "albany", "sunnyvale", "houston", "san jose"};
        for(String city : cities)
            insert(city);
        System.out.println("The cities are : " + autoComplete("san ").toString());
    }
}
