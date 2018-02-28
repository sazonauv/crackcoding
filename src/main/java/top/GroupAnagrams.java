package top;

import java.util.*;

/**
 * Created by slava on 28/02/18.
 */
public class GroupAnagrams {


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            List<String> anagramList = groupMap.get(sorted);
            if (anagramList == null) {
                anagramList = new ArrayList<>();
                groupMap.put(sorted, anagramList);
            }
            anagramList.add(s);
        }
        return new ArrayList<>(groupMap.values());
    }


    private boolean areAnagrams1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] chars1 = s1.toCharArray();
        Arrays.sort(chars1);
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars2);
        return chars1.equals(chars2);
    }


    private boolean areAnagrams2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        return hist(s1).equals(hist(s2));
    }


    private Map<Character, Integer> hist(String s) {
        Map<Character, Integer> hist = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            Integer count = hist.get(s.charAt(i));
            if (count == null) {
                hist.put(s.charAt(i), 1);
            } else {
                hist.put(s.charAt(i), ++count);
            }
        }
        return hist;
    }


}
