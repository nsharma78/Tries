
/*
 * Trie is an efficient information retrieval data structure.
 * In our previous post on trie we have discussed about basics of trie and how to insert and search a key in trie.
 * In this post we will discuss about displaying all of the content of a trie. That is, to display all of the keys present in the Trie.
Examples:
Input: If Trie is      root
                    /   \    \
                    t   a     b
                    |   |     |
                    h   n     y
                    |   |  \  |
                    e   s  y  e
                 /  |   |
                 i  r   w
                 |  |   |
                 r  e   e
                        |
                        r
Output: Contents of Trie:
        answer
        any
        bye
        their
        there
 */

package main.java;

public class DisplayContents {

    private static TrieNode  root;
    private static final int SIZE = 26;

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
        for(int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.word = word;
    }

    private static void displayContents(TrieNode root) {
        if(root == null)
            return;

        TrieNode node = root;
        for(int i = 0; i < SIZE; ++i) {
            if(node.children[i] != null)
                if(node.children[i].word != null)
                    System.out.println(node.children[i].word);
                displayContents(node.children[i]);
        }
    }

    public static void  main(String[] args) {
        root = new TrieNode();
        String[] words = {"answer", "any", "bye", "their", "there"};
        for(String word : words)
            insert(word);
        displayContents(root);
    }
}
