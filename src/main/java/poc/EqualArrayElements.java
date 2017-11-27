package poc;

import java.util.HashMap;
import java.util.Map;

public class EqualArrayElements {

    public int minMoves2(int[] a) {
        Map<Integer, Integer> hist = new HashMap<>();
        for (int el : a) {
            Integer count = hist.get(el);
            if (count == null) {
                hist.put(el, 1);
            } else {
                hist.put(el, ++count);
            }
        }
        int maxCount = Integer.MIN_VALUE;
        for (Integer key : hist.keySet()) {
            if (maxCount < hist.get(key)) {
                maxCount = hist.get(key);
            }
        }
        int modeMean = 0;
        int modeNum = 0;
        for (Integer key : hist.keySet()) {
            if (maxCount == hist.get(key)) {
                modeMean += key;
                modeNum++;
            }
        }
        modeMean /= modeNum;
        int moves = 0;
        for (int el : a) {
            moves += Math.abs(el - modeMean);
        }
        return moves;
    }




    private static int mode(int[] a) {
        Map<Integer, Integer> hist = new HashMap<>();
        for (int el : a) {
            Integer count = hist.get(el);
            if (count == null) {
                hist.put(el, 1);
            } else {
                hist.put(el, ++count);
            }
        }
        int maxCount = Integer.MIN_VALUE;
        int mode = -1;
        for (Integer key : hist.keySet()) {
            if (maxCount < hist.get(key)) {
                maxCount = hist.get(key);
                mode = key;
            }
            if (maxCount == hist.get(key) && mode > key) {
                mode = key;
            }
        }
        return mode;
    }

}
