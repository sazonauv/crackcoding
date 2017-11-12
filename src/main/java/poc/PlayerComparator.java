package poc;

import java.util.Comparator;

/**
 * Created by slava on 12/11/17.
 */
public class PlayerComparator {

    class Player {
        String name;
        int score;
        Player(String name, int score){
            this.name = name;
            this.score = score;
        }
    }

    class Checker implements Comparator<Player> {

        public int compare(Player p1, Player p2) {
            if (p1.score > p2.score) {
                return -1;
            } else if (p1.score < p2.score) {
                return 1;
            } else {
                return compareNames(p1.name, p2.name);
            }
        }

        private int compareNames(String name1, String name2) {
            char[] chars1 = name1.toCharArray();
            char[] chars2 = name2.toCharArray();
            for (int i=0; i<chars1.length && i<chars2.length; i++) {
                if (chars1[i] < chars2[i]) {
                    return -1;
                } else if (chars1[i] > chars2[i]) {
                    return 1;
                }
            }
            if (chars1.length < chars2.length) {
                return -1;
            } else if (chars1.length > chars2.length) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
