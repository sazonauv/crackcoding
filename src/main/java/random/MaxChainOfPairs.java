package random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by slava on 13/11/17.
 */
public class MaxChainOfPairs {

    class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    class PairComparator implements Comparator<Pair> {

        public int compare(Pair p1, Pair p2) {
            if (p1.first < p2.first) {
                return -1;
            } else if (p2.first < p1.first) {
                return 1;
            } else {
                if (p1.second < p2.second) {
                    return -1;
                } else if (p1.second > p2.second) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }


    public int findLongestChain(int[][] pairs) {
        List<Pair> pairList = new ArrayList<>(pairs.length);
        for (int i=0; i<pairs.length; i++) {
            pairList.add(new Pair(pairs[i][0], pairs[i][1]));
        }
        // sort
        PairComparator comp = new PairComparator();
        Collections.sort(pairList, comp);
        // find max length
        int len = 1;
        Pair prev = pairList.get(0);
        for (int i=1; i<pairList.size(); i++) {
            if (areChain(prev, pairList.get(i))) {
                len++;
                prev = pairList.get(i);
            }
        }
        return len;
    }

    private boolean areChain(Pair p1, Pair p2) {
        if (p1.second < p2.first) {
            return true;
        }
        return false;
    }

}
