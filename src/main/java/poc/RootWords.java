package poc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by slava on 18/12/17.
 */
public class RootWords {

    class WordLengthComparator implements Comparator<String> {

        public int compare(String s1, String s2) {
            if (s1.length() < s2.length()) {
                return -1;
            } else if (s1.length() > s2.length()) {
                return 1;
            } else {
                return 0;
            }
        }

    }


    public String replaceWords(List<String> dict, String sentence) {
        Collections.sort(dict, new WordLengthComparator());
        String[] words = sentence.split(" ");
        String res = "";
        for (String word : words) {
            boolean found = false;
            for (String root : dict) {
                if (word.startsWith(root)) {
                    res += root + " ";
                    found = true;
                    break;
                }
            }
            if (!found) {
                res += word + " ";
            }
        }
        return res.trim();
    }

}
