/*
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words. See following examples for more details.
This is a famous Google interview question, also being asked by many other companies now a days.
Consider the following dictionary
{ i, like, sam, sung, samsung, mobile, ice,
  cream, icecream, man, go, mango}
Input:  ilike
Output: Yes
The string can be segmented as "i like".
Input:  ilikesamsung
Output: Yes
The string can be segmented as "i like samsung" or
"i like sam sung".
 */


package main.java;
import main.java.Trie.TrieNode;

public class WordBreak {

    private static TrieNode root;

    public static void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.isLeaf = true;
    }

    private static boolean searchWord(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null) {
                return false;
            }else {
                node = node.children[index];
            }
        }
        return (node == root) ? false : true;
    }

    private static boolean wordBreak(String input) {
        int size = input.length();
        if(size == 0)
            return true;
        for(int i = 0; i <= size; i++) {
            if(searchWord(input.substring(0, i)) && wordBreak(input.substring(i, size)))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        root = new TrieNode();
        String input = "icecream";
        String[] arr = {"i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "icecream", "man", "go", "mango"};
        for(String word : arr)
            insert(word);
        System.out.println("Possible: " + wordBreak(input));
    }
}
