package random;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by slava on 11/11/17.
 */
public class BalancedString {


    /**
     * test: fjg(fgf(dfg{}d[]))
     * @param str
     * @return
     */
    private static boolean isBalanced(String str) {
        char[] brackets1 = new char[] {'(', '[', '{'};
        char[] brackets2 = new char[] {')', ']', '}'};
        Map<Character, Character> bracketMap = new HashMap<>();
        for (int i=0; i<brackets1.length; i++) {
            bracketMap.put(brackets2[i], brackets1[i]);
        }
        Stack<Character> stack = new Stack<>();
        final char[] chars = str.toCharArray();
        for (int i=0; i<chars.length; i++) {
            if (isContained(chars[i], brackets1)) {
                stack.push(chars[i]);
            } else if (isContained(chars[i], brackets2)) {
                if (stack.isEmpty()) {
                    return false;
                }
                Character top = stack.pop();
                if (top != bracketMap.get(chars[i])) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }


    private static boolean isContained(char ch, char[] chars) {
        for (char c : chars) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(isBalanced(str));
    }

}
