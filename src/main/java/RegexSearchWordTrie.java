/*
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or a . means it can represent any one letter.
 */

package main.java;

public class RegexSearchWordTrie {

    private static TrieNode root;

    static class TrieNode{
        TrieNode[] children;
        boolean isLeaf;
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    private static void addWord(String word){
        TrieNode node = root;
        for(int i = 0; i< word.length(); i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.isLeaf = true;
    }

    private static boolean search(String word) {
        return dfsSearch(root, word, 0);
    }

    private static boolean dfsSearch(TrieNode node, String word, int start) {
        if (node.isLeaf && start == word.length())
            return true;
        if (start >= word.length())
            return false;

        char c = word.charAt(start);if(c == '.') {
            boolean tResult = false;
            for (int j = 0; j < 26; j++) {
                if (node.children[j] != null) {
                    if (dfsSearch(node.children[j], word, start + 1)) {
                        tResult = true;
                        break;
                    }
                }
            }

            if (tResult)
                return true;
        }else {
            int index = c - 'a';

            if (node.children[index] != null) {
                return dfsSearch(node.children[index], word, start + 1);
            } else {
                return false;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        root = new TrieNode();
        String s1 = "care";
        String s2 = "cart";
        String s3 = "cape";

        addWord(s1);
        addWord(s2);
        addWord(s3);

        String s4 = "c.re";
        System.out.println(s4 + " is present in the trie: " + search(s4) + "\n");

        String s5 = "c.rd";
        System.out.println(s5 + " is present in the trie: " + search(s5) + "\n");

    }
}
