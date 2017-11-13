package poc;

/**
 * Created by slava on 07/11/17.
 */
public class Palindrome {


    public int largestPalindrome(int n) {
        int maxMult = 1;
        for (int i=0; i<n; i++) {
            maxMult *= 10;
        }
        maxMult--;
        int maxPal = 0;
        for (int i=maxMult; i>0; i--) {
            for (int j=maxMult; j>0; j--) {
                int pal = i*j;
                if (isPalindrome(pal) && maxPal < pal) {
                    maxPal = pal;
                }
            }
        }
        return maxPal % 1337;
    }


    public boolean isPalindrome(long x) {
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
