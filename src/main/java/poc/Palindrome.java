package poc;

/**
 * Created by slava on 07/11/17.
 */
public class Palindrome {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        final char[] chars = String.valueOf(x).toCharArray();
        for (int i=0; i<=chars.length/2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

}
