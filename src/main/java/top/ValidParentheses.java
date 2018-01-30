package top;

import java.util.Stack;

/**
 * Created by slava on 30/01/18.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.add(ch);
                continue;
            }
            if (stack.isEmpty() &&
                    (ch == ')' || ch == '}' || ch == ']')) {
                return false;
            }
            if (stack.peek() == '(' && ch == ')'
                    || stack.peek() == '{' && ch == '}'
                    || stack.peek() == '[' && ch == ']') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
