package top;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by slava on 26/01/18.
 */
public class LongestSubstringWORepChars {

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        LinkedList<Character> buf = new LinkedList<>();
        buf.add(s.charAt(0));
        int len = 1;
        for (int i=1; i<s.length(); i++) {
            Character ch = s.charAt(i);
            int ind = buf.indexOf(ch);
            if (ind >= 0) {
                if (len < buf.size()) {
                    len = buf.size();
                }
                buf.subList(0, ind+1).clear();
            }
            buf.add(ch);
        }
        return len >= buf.size() ? len : buf.size();
    }

}
