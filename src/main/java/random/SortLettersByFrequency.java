package random;

import java.util.*;

/**
 * Created by slava on 11/11/17.
 */
public class SortLettersByFrequency {

    static class LetterFrequencyComparator implements Comparator<Character> {

        private Map<Character, Integer> hist;
        private boolean asc;

        public LetterFrequencyComparator(Map<Character, Integer> hist, boolean asc) {
            this.hist = hist;
            this.asc = asc;
        }

        public List<Character> getCharacters() {
            return new ArrayList<>(hist.keySet());
        }

        @Override
        public int compare(Character ch1, Character ch2) {
            Integer count1 = hist.get(ch1);
            Integer count2 = hist.get(ch2);
            if (count1 < count2) {
                if (asc)
                    return -1;
                else
                    return 1;
            } else if (count1 > count2) {
                if (asc)
                    return 1;
                else
                    return -1;
            } else {
                if (ch1 < ch2)
                    return -1;
                else if (ch1 > ch2)
                    return 1;
                else
                    return 0;
            }
        }
    }


    /**
     * Given a word, rearrange the letters so that they appear by decreasing frequency.
     * In the case of a tie, sort the letters by increasing alphabetical order.
     * dkfgjdkfjkdhfjghdjhfgdur747djgfjheiwow9w8346582ldksjfksdhfkhskdhffajkaheiwyrteuiwukjskdhfjxhfkshdfs8e7r4765
     * @param str
     * @return
     */
    private static String sortLettersByFrequency(String str) {
        Map<Character, Integer> hist = getHistogram(str);
        LetterFrequencyComparator comp = new LetterFrequencyComparator(hist, false);
        List<Character> letters = comp.getCharacters();
        Collections.sort(letters, comp);
        String res = "";
        for (Character ch : letters) {
            int count = hist.get(ch);
            for (int i=0; i<count; i++) {
                res += ch;
            }
        }
        return res;
    }

    private static Map<Character, Integer> getHistogram(String str) {
        final char[] chars = str.toCharArray();
        Map<Character, Integer> hist = new HashMap<>();
        for (int i=0; i<chars.length; i++) {
            Integer count = hist.get(chars[i]);
            if (count == null) {
                hist.put(chars[i], 1);
            } else {
                hist.put(chars[i], ++count);
            }
        }
        return hist;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        System.out.println(sortLettersByFrequency(str));
    }



}
