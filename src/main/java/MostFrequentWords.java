/*
 * Given an array of words find the most occurring word in it
 * Examples:
Input : arr[] = {"geeks", "for", "geeks", "a",
                "portal", "to", "learn", "can",
                "be", "computer", "science",
                 "zoom", "yup", "fire", "in",
                 "be", "data"}
Output : Geeks
"geeks" is the most frequent word as it
occurs 3 times
 */

package main.java;

public class MostFrequentWords {

    private static final int SIZE = 26;
    private static TrieNode root;
    private static String frequentWord = "";
    private static int maxCount = 0;

    static class TrieNode {
        int count;
        String word;
        TrieNode[] children;

        public TrieNode() {
            count  = 0;
            children = new TrieNode[SIZE];
        }
    }

    private static void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.word = word;
        node.count++;

    }

    private static void mostFrequent(TrieNode root) {
        TrieNode node = root;
        for(int i = 0; i < SIZE; i++) {
            if(node.children[i] != null) {
                if(node.children[i].count > maxCount){
                    maxCount = node.children[i].count;
                    frequentWord = node.children[i].word;
                }
                else
                    mostFrequent(node.children[i]);
            }
        }
    }

    public static void main(String[] args) {
        root = new TrieNode();
        String words[] = {"geeks","for","geeks","is", "a","portal","to","learn","can","be","geeks", "computer","science","zoom","yup","fire","in","be","data"};
        for(String word : words)
            insert(word);
        mostFrequent(root);
        System.out.println("The word with maximum occurence is: " + frequentWord);
    }
}
