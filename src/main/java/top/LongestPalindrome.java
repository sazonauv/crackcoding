package top;

import java.util.Scanner;

/**
 * Created by slava on 29/01/18.
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        String maxRes = "" + s.charAt(0);
        int k = 1;
        int mid = s.length()/2;
        while (true) {
            k *= -1;
            int i = mid + k - (k>0 ? 1 : 0);
            if (i < 1 || i >= s.length()) {
                continue;
            }
            //
            String res = "";
            if (s.charAt(i) == s.charAt(i-1)) {
                int j = 1;
                while (i-j >= 0 && i+j-1 < s.length()) {
                    if (s.charAt(i-j) == s.charAt(i+j-1)) {
                        res = s.charAt(i-j) + res + s.charAt(i-j);
                    } else {
                        break;
                    }
                    j++;
                }
            }
            if (maxRes.length() < res.length()) {
                maxRes = res;
            }
            if (i+1 < s.length() && s.charAt(i-1) == s.charAt(i+1)) {
                res = "" + s.charAt(i);
                int j = 1;
                while (i-j >= 0 && i+j < s.length()) {
                    if (s.charAt(i-j) == s.charAt(i+j)) {
                        res = s.charAt(i-j) + res + s.charAt(i-j);
                    } else {
                        break;
                    }
                    j++;
                }
            }
            if (maxRes.length() < res.length()) {
                maxRes = res;
            }
            //
            if (maxRes.length() >= 2 * (mid - Math.abs(k) + 1) +
                    (s.length()%2 == 0 ? 0 : 1) ) {
                break;
            }
            if (k > 0) {
                k++;
            }
        }
        return maxRes;
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        System.out.println(new LongestPalindrome().longestPalindrome(s));
    }





    /*public boolean isPalindrome(int start, int end, String s) {
        int i=start;
        int j=end-1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }*/

}
