package ml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by slava on 07/11/17.
 */
public class StatMeanMedianMode {

    // test:
    // 10
    // 64630 11735 14216 99233 14470 4978 73429 38120 51135 67060


    private static double mean(int[] a) {
        double mean = 0;
        for (int i=0; i<a.length; i++) {
            mean += a[i];
        }
        return mean/a.length;
    }


    private static double median(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        if (b.length % 2 == 1) {
            return b[b.length/2];
        } else {
            return ((double) b[b.length/2] + b[b.length/2 - 1])/2;
        }
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


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        System.out.printf("%.1f", mean(a));
        System.out.println();
        System.out.printf("%.1f", median(a));
        System.out.println();
        System.out.println(mode(a));
    }

}
