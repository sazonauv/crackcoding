package poc;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by slava on 07/11/17.
 */
public class UniqueInteger {

    public static int lonelyInteger(int[] a) {
        Map<Integer, Integer> hist = new HashMap<>();
        for (int i=0; i<a.length; i++) {
            Integer count = hist.get(a[i]);
            if (count == null) {
                hist.put(a[i], 1);
            } else {
                hist.put(a[i], ++count);
            }
        }
        for (Integer key : hist.keySet()) {
            if (hist.get(key) == 1) {
                return key;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        System.out.println(lonelyInteger(a));
    }

}
