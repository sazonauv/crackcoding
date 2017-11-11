package poc;

import java.util.*;

/**
 * Created by slava on 11/11/17.
 */
public class LargestNonrepeatingSubstring {

    /*public static String getLargestNonrepeatingSubstringLinear(String str) {
        final char[] chars = str.toCharArray();
        Map<Character, List<Integer>> charPosMap = new HashMap<>();
        for (int i=0; i<chars.length; i++) {
            List<Integer> posList = charPosMap.get(chars[i]);
            if (posList == null) {
                posList = new ArrayList<>();
                charPosMap.put(chars[i], posList);
            }
            posList.add(i);
        }
        int maxLen = 0;
        String maxRes = "";
        for (Character ch : charPosMap.keySet()) {
            List<Integer> posList = charPosMap.get(ch);
            for (int i=1; i<posList.size(); i++) {
                int len = posList.get(i) - posList.get(i-1);
                if (maxLen < len && !containsRepeats(posList.get(i-1), posList.get(i), charPosMap)) {
                    maxLen = len;
                    maxRes = str.substring(posList.get(i-1), posList.get(i));
                }
            }
        }
        return maxRes;
    }

    private static boolean containsRepeats(Integer pos1, Integer pos2,
                                           Map<Character, List<Integer>> charPosMap) {
        for (Character ch : charPosMap.keySet()) {
            List<Integer> posList = charPosMap.get(ch);
            for (int i=1; i<posList.size(); i++) {
                if (pos1 < posList.get(i-1) && posList.get(i) < pos2) {
                    return true;
                }
            }
        }
        return false;
    }*/


    /**
     * find the largest sub-string with non-repeating characters
     * test: 3947536922032934123457663123
     * @param str
     * @return
     */
    public static String getLargestNonrepeatingSubstringQuadr(String str) {
        String res;
        String maxRes = "";
        final char[] chars = str.toCharArray();
        for (int i=0; i<chars.length; i++) {
            res = "";
            for (int j=i; j<chars.length && res.indexOf(chars[j])<0; j++) {
                res += chars[j];
            }
            if (maxRes.length() < res.length()) {
                maxRes = res;
            }
        }
        return maxRes;
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(getLargestNonrepeatingSubstringQuadr(str));
    }

}
