package poc;

/**
 * Created by slava on 17/01/18.
 */
public class SmallestGreaterLetter {

    public char nextGreatestLetter(char[] letters, char target) {
        if (letters.length == 0) {
            return 'z';
        }
        char min = Character.MAX_VALUE;
        for (char ch : letters) {
            if (ch < min && ch > target) {
                min = ch;
            }
        }
        if (min == Character.MAX_VALUE) {
            for (char ch : letters) {
                if (ch < min) {
                    min = ch;
                }
            }
        }
        return min;
    }


}
