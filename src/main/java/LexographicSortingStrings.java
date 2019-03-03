/*
 * Given a set of strings, sort them in lexographical(dictionary/alphabetic) order
 */

package main.java;

public class LexographicSortingStrings {

    public static void main(String[] args) {
        root = new TrieNode();
        String[] arr = {"lexo", "champ", "zest", "pest", "pet", "peta", "zen", "pea", "peet", "peter", "zee", "san francisco", "san ramon", "santa clara", "albany", "sunnyvale", "houston", "san jose"};
        for(String word : arr)
            insert(word);
        preOrder(root);
    }

    private static final int SIZE = 27;
    private static TrieNode root;

    static class TrieNode {
        String word;
        TrieNode[] children;

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

    private static void preOrder(TrieNode root) {
        TrieNode node = root;
        for (int i = 0; i < SIZE; i++) {
            if(node.children[i] != null)
                if(node.children[i].word != null)
                    System.out.println(node.children[i].word);
                else
                    preOrder(node.children[i]);
        }
    }
}
