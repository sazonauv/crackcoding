package random;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by slava on 18/01/18.
 */
public class BeautifulArrangement {

    public int countArrangement(int N) {
        HashSet<LinkedList<Integer>> arrangs = new HashSet<>();
        LinkedList<Integer> arrang = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            arrang.add(i);
        }
        arrangs.add(arrang);
        HashSet<LinkedList<Integer>> newArrangs = new HashSet<>();
        newArrangs.add(arrang);
        while (!newArrangs.isEmpty()) {
            newArrangs.clear();
            for (LinkedList<Integer> arr : arrangs) {
                for (int i=0; i<arr.size(); i++) {
                    for (int j=0; j<arr.size(); j++) {
                        if (i == j) {
                            continue;
                        }
                        // beautiful?
                        if (isBeautiful(i, arr.get(i), j, arr.get(j))) {
                            LinkedList<Integer> newArr = swap(arr, i, j);
                            if (!arrangs.contains(newArr)) {
                                newArrangs.add(newArr);
                            }
                        }
                    }
                }
            }
            arrangs.addAll(newArrangs);
        }
        return arrangs.size();
    }

    private LinkedList<Integer> swap(LinkedList<Integer> arr, int i, int j) {
        LinkedList<Integer> newArr = new LinkedList<>(arr);
        newArr.set(i, arr.get(j));
        newArr.set(j, arr.get(i));
        return newArr;
    }

    private boolean isBeautiful(int i, int vali, int j, int valj) {
        return ((vali % (j+1) == 0 || (j+1) % vali == 0)
                && (valj % (i+1) == 0 || (i+1) % valj == 0));
    }


    // recursion

    /*int count = 0;

    public int countArrangement(int N) {
        if (N == 0) return 0;
        helper(N, 1, new int[N + 1]);
        return count;
    }

    private void helper(int N, int pos, int[] used) {
        if (pos > N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                used[i] = 1;
                helper(N, pos + 1, used);
                used[i] = 0;
            }
        }
    }*/

}
