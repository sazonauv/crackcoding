package poc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by slava on 06/11/17.
 */
public class StringAnagrams {

    //Input: aabbccccddee accdeffgghh
    //Output: 13
    public static int numberNeeded(String first, String second) {
        // check if they are anagrams
        if (first.equals(second)) {
            return 0;
        }
        Map<Character, Integer> hist1 = getHistogram(first);
        Map<Character, Integer> hist2 = getHistogram(second);
        int number = 0;
        for (Character ch : hist1.keySet()) {
            if (!hist2.containsKey(ch)) {
                number += hist1.get(ch);
            } else {
                number += Math.abs(hist1.get(ch) - hist2.get(ch));
            }
        }
        for (Character ch : hist2.keySet()) {
            if (!hist1.containsKey(ch)) {
                number += hist2.get(ch);
            }
        }
        return number;
    }

    private static Map<Character, Integer> getHistogram(String str) {
        final char[] chars = str.toCharArray();
        Map<Character, Integer> hist = new HashMap<>();
        for (char ch : chars) {
            Integer count = hist.get(ch);
            if (count == null) {
                hist.put(ch, 1);
            } else {
                hist.put(ch, ++count);
            }
        }
        return hist;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }

}
