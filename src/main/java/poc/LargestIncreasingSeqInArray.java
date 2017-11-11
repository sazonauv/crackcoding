package poc;

import java.util.Scanner;

/**
 * Created by slava on 11/11/17.
 */
public class LargestIncreasingSeqInArray {

    /**
     * test: 3947536922032934123457663123
     * @param str
     * @return
     */
    private static String getLargestIncreasingSeqInArray(String str) {
        final char[] chars = str.toCharArray();
        String res = "" + chars[0];
        String maxRes = "";
        for (int i=1; i<chars.length; i++) {
            if (chars[i] > chars[i-1]) {
                res += chars[i];
            } else {
                if (res.length() > maxRes.length()) {
                    maxRes = res;
                }
                res = "" + chars[i];
            }
        }
        return maxRes;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(getLargestIncreasingSeqInArray(str));
    }

}
