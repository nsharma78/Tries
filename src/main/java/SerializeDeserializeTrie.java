/*
 * Serialize and then de-serialize a trie
 */

package main.java;
import java.util.*;

public class SerializeDeserializeTrie {

    private static TrieNode root;
    private static final int SIZE = 26;
    private static final char MARKER = ')';

    static class TrieNode {
        String word;
        TrieNode[] children;
        char key;

        public TrieNode(char c) {
            key = c;
            children = new TrieNode[SIZE];
            word = null;
        }
    }

    public static void main(String[] args) {
        root = new TrieNode('.');
        String[] words = {"base", "race", "acer", "acre", "rat", "bat", "mat", "cat", "care", "bare", "rare", "dare"};
        //String[] words = {"acer", "acre"};
        System.out.println("Adding to Trie");
        for(String word: words)
            insert(word);
        System.out.println("Traversing Trie");
        traverse(root);
        System.out.println("Serializing Trie");
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        System.out.println("Serialized Trie");
        System.out.println(sb.toString());
        System.out.println("Deserializing Trie");
        TrieNode newRoot = deserialize(sb.toString());
        System.out.println("Traversing after deserializing Trie");
        traverse(newRoot);

    }

    public static void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode(c);
            node = node.children[index];
        }
        node.word = word;
    }

    public static void traverse(TrieNode root) {
        TrieNode node = root;
        for(int i = 0; i < node.children.length; i++) {
            if(node.children[i] != null) {
                if(node.children[i].word != null)
                    System.out.println(node.children[i].word);
                else
                    traverse(node.children[i]);
            }
        }
    }

    private static void serialize(TrieNode root, StringBuilder sb) {
        if(root == null)
            return;

        sb.append(root.key);
        TrieNode[] children = root.children;
        for(int i = 0; i < children.length; i++)
            serialize(children[i], sb);
        sb.append(MARKER);
    }

    private static TrieNode deserialize(String trie) {
        if(trie == null || trie.isEmpty())
            return null;

        int i = 0;
        TrieNode newRoot = new TrieNode(trie.charAt(i));
        Stack<TrieNode> stk = new Stack<>();
        stk.push(newRoot);
        int j = 0;
        StringBuilder word = new StringBuilder("");

        for(i = 1; i < trie.length(); i++) {

            char c = trie.charAt(i);
            if(c != MARKER) {
                stk.push(new TrieNode(c));
                word.append(c);
            }else {
                TrieNode n1 =stk.pop();
                if(trie.charAt(i-1) == MARKER) {
                    if(word.length() > 0)
                        word.deleteCharAt(word.length() - 1);
                }
                else {
                    n1.word = word.toString();
                    if(word.length() > 0)
                        word.deleteCharAt(word.length() - 1);
                }

                if(n1 == newRoot)
                    return n1;
                TrieNode n2 = stk.pop();
                n2.children[n1.key - 'a'] = n1;
                stk.push(n2);
            }
        }
        return newRoot;


    }
}
