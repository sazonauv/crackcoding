package random;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slava on 17/01/18.
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        if (k <= 0) {
            return combs;
        } else
        if (k >= n) {
            List<Integer> comb = new ArrayList<>(n);
            for (int i=1; i<=n; i++) {
                comb.add(i);
            }
            combs.add(comb);
        } else
        if (k == 1) {
            for (int i=1; i<=n; i++) {
                List<Integer> comb = new ArrayList<>(2);
                comb.add(i);
                combs.add(comb);
            }
        } else {
            long combsNum = (long) Math.pow(2, n);
            for (long i=1; i<=combsNum; i++) {
                String chars = Long.toBinaryString(i);
                if (countOnes(chars) == k) {
                    List<Integer> comb = getCombination(chars, n);
                    combs.add(comb);
                }
            }
        }
        return combs;
    }

    private List<Integer> getCombination(String chars, int n) {
        List<Integer> comb = new ArrayList<>();
        for (int i=0; i<chars.length(); i++) {
            if (chars.charAt(i) == '1') {
                comb.add(n-i);
            }
        }
        return comb;
    }

    private int countOnes(String chars) {
        int count = 0;
        for (int i=0; i<chars.length(); i++) {
            if (chars.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }


}
