package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by slava on 13/11/17.
 */
public class GenerateParantheses {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        long combs = (long) Math.pow(2, 2*n);
        for (int i=0; i<combs; i++) {
            char[] choices = Integer.toBinaryString(i).toCharArray();
            if (choices.length == 2*n && usesAllParentheses(choices) && isWellFormed(choices)) {
                res.add(generateString(choices));
            }
        }
        return res;
    }

    private String generateString(char[] choices) {
        String res = "";
        for (int i=0; i<choices.length; i++) {
            if (choices[i] == '1') {
                res += '(';
            } else {
                res += ')';
            }
        }
        return res;
    }

    private boolean usesAllParentheses(char[] choices) {
        int count = 0;
        for (int i=0; i<choices.length; i++) {
            if (choices[i] == '1') {
                count++;
            }
        }
        return count == choices.length/2;
    }

    private boolean isWellFormed(char[] choices) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<choices.length; i++) {
            if (choices[i] == '1') {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

}
