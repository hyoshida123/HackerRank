import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static class Trie {

        public TrieNode root = new TrieNode();

        private class TrieNode {
            HashMap<Character, TrieNode> children = new HashMap<>();
            //TrieNode[] children = new TrieNode[173];
            boolean exist = false;
        }


        public void  add_recur(TrieNode n, String s) {
            char first = s.charAt(0);
            if (!n.children.containsKey(first)) {
                TrieNode newN = new TrieNode();
                n.children.put(first, newN);
            }
            if (s.length() == 1) {
                n.children.get(first).exist = true;
                return;
            }
            String subS = s.substring(1);
            add_recur(n.children.get(first), subS);
        }


        public int find(String key) {

            TrieNode node = root;
            for (int i = 0; i < key.length(); i++) {
                char curr = key.charAt(i);
                if (!node.children.containsKey(curr)) {
                    return 0;
                }
                node = node.children.get(curr);
            }
            int count = findHelper(node, 0);
            if (node.exist) {
                return 1 + count;
            }
            return count;

        }

        private int findHelper(TrieNode n, int count) {
            for (char child : n.children.keySet()) {
                TrieNode node = n.children.get((child));

                if (node.exist == true) {
                    count++;
                }
                count += findHelper(node, 0);

            }
            return count;
        }
    }




    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        Trie trie = new Trie();

        for (int nItr = 0; nItr < n; nItr++) {
            String[] opContact = scanner.nextLine().split(" ");

            String op = opContact[0];
            String contact = opContact[1];

            if (op.equals("add")) {
                trie.add_recur(trie.root, contact);
            } else if (op.equals("find")) {
                System.out.println(trie.find(contact));
            }
        }

        scanner.close();
    }
}
