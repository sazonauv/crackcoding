package random;

import java.util.Scanner;

public class VowelsReverser {

    public static String reverseVowels(String s) {
        char[] vowels = new char[] {
                'a', 'e', 'i', 'o', 'u',
                'A', 'E', 'I', 'O', 'U',
        };
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right && left < chars.length && right >= 0) {
            while (left < right && !isVowel(chars[left], vowels)) {
                left++;
            }
            while (right > left && !isVowel(chars[right], vowels)) {
                right--;
            }
            if (left == right) {
                break;
            }
            char leftChar = chars[left];
            chars[left] = chars[right];
            chars[right] = leftChar;
            left++;
            right--;
        }
        return new String(chars);
    }


    private static boolean isVowel(char ch, char[] vowels) {
        for (int i=0; i<vowels.length; i++) {
            if (ch == vowels[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        System.out.println(reverseVowels(s));
    }

}
