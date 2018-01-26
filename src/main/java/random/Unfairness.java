package random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by slava on 07/11/17.
 */
public class Unfairness {

    /**
     * 1) Given acyclic graph find all the paths to a node from a parent node
     * 2) How to pick a random node with uniform probability from a binary tree?
     * 3) How to find the longest increasing sequence in an array?
     * 4) Provided a binary tree, write a function to return
     * the sum of all nodes at or below a certain specified level in the tree.
     * 5) Provided a string with any of the following characters ... ( , ) , { , } , [ , ] , ...
     * write a function to figure out if it's 'balanced'.
     * For example, "( { } )" is balanced, "( { ) }" is not.
     * 6) Given a word, rearrange the letters so that they appear by decreasing frequency.
     * In the case of a tie, sort the letters by increasing alphabetical order.
     * 7) https://leetcode.com/problems/next-greater-element-i/description/
     * 8) Given a tree that can have any number of child nodes,
     * write a function to trim the tree upto a given level k and
     * return the root where the trimmed level nodes now have
     * the sum of all their child nodes.
     * 9) Design a data structure to efficiently lookup
     * k most frequent search queries in the last 24 hours.
     * 10) Was asked to write a program in any programming language
     * to find the largest sub-string with non-repeating characters.
     *
     */


    private static int getMinUnfairness(int[] a, int k) {
        Arrays.sort(a);
        int minUnfairness = Integer.MAX_VALUE;
        for (int i=0; i<=a.length-k; i++) {
            int unfairness = a[i+k-1] - a[i];
            if (minUnfairness > unfairness) {
                minUnfairness = unfairness;
            }
        }
        return minUnfairness;
    }



    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int K = Integer.parseInt(in.readLine());
        int[] list = new int[N];

        for(int i = 0; i < N; i ++)
            list[i] = Integer.parseInt(in.readLine());

        int unfairness = getMinUnfairness(list, K);

      /*
       * Write your code here, to process numPackets N, numKids K, and the packets of candies
       * Compute the ideal value for unfairness over here
      */

        System.out.println(unfairness);
    }

}
