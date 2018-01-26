package ml;

import java.util.Scanner;

public class LevensteinDistance {


    public static int levenstein(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        }
        if (s1.contains(s2)) {
            return s1.length() - s2.length();
        }
        if (s2.contains(s1)) {
            return s2.length() - s1.length();
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[][] D = new int[chars1.length+1][chars2.length+1];
        for (int i=1; i<chars1.length+1; i++) {
            D[i][0] = i;
        }
        for (int j=1; j<chars2.length+1; j++) {
            D[0][j] = j;
        }
        for (int i=1; i<chars1.length+1; i++) {
            for (int j=1; j<chars2.length+1; j++) {
                int cost = (chars1[i-1] == chars2[j-1]) ? 0 : 1;
                D[i][j] = min(D[i-1][j]+1, D[i][j-1]+1, D[i-1][j-1]+cost);
            }
        }
        return D[chars1.length][chars2.length];
    }

    private static int min(int... numbers) {
        int min = numbers[0];
        for (int i=1; i<numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s1 = scan.next();
        String s2 = scan.next();
        System.out.println(levenstein(s1, s2));
    }


}
