package main.java;
import java.util.*;

public class DuplicateRowsBinaryMatrix {

    static class TrieNode {
        boolean isLeaf;
        TrieNode[] children;
        public TrieNode() {
            children = new TrieNode[2];
        }
    }
    private static TrieNode root;

    public static void main(String[] args) {
        int[][] mat = {
                {1,0,0,1,0},
                {0,1,1,0,0},
                {1,0,0,1,0},
                {0,0,1,1,0},
                {0,1,1,0,0} };

        root = new TrieNode();
        System.out.println("Duplicate rows are: " + findDups(mat).toString());
    }

    private static List<Integer> findDups(int[][] mat) {
        if(mat == null || mat.length == 0 || mat[0].length == 0)
            return Collections.emptyList();

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < mat.length; i++) {
            if(!insert(mat[i]))
                result.add(i + 1);
        }
        return result;
    }

    private static boolean insert(int[] mat) {
        TrieNode node = root;
        for (int i = 0; i < mat.length; i++) {
            int index = mat[i];
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        if(node.isLeaf) //already leaf set, duplicate
            return false;

        return node.isLeaf = true;
    }
}
