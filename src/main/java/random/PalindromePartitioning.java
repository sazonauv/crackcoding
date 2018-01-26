package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by slava on 27/11/17.
 */
public class PalindromePartitioning {

    // input: "aab"
    // output: [["a","a","b"],["aa","b"]]

    public static List<List<String>> partition(String s) {
        List<List<String>> palPartitionList = new ArrayList<>();
        final char[] chars = s.toCharArray();
        double partitions = Math.pow(2, chars.length);
        loop:
        for (long i=0; i<partitions; i++) {
            char[] bounds = Long.toBinaryString(i).toCharArray();
            if (bounds[bounds.length-1] == '1') {
                continue;
            }
            int start = 0;
            for (int b=0; b<bounds.length; b++) {
                if (bounds[b] == '1' || b == bounds.length-1) {
                    if (!isPalindrome(chars, start, chars.length-bounds.length+b)) {
                        continue loop;
                    }
                    start = chars.length-bounds.length+b+1;
                }
            }
            List<String> palPartition = new ArrayList<>();
            start = 0;
            for (int b=0; b<bounds.length; b++) {
                if (bounds[b] == '1' || b == bounds.length-1) {
                    palPartition.add(getString(chars, start, chars.length-bounds.length+b));
                    start = chars.length-bounds.length+b+1;
                }
            }
            palPartitionList.add(palPartition);
        }
        return palPartitionList;
    }


    private static String getString(char[] chars, int start, int end) {
        String s = "";
        for (int i=start; i<=end; i++) {
            s += chars[i];
        }
        return s;
    }


    private static boolean isPalindrome(char[] chars, int start, int end) {
        for (int i=0; i<=(end - start)/2; i++) {
            if (chars[start+i] != chars[end-i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        System.out.println(partition(s));
    }

}
