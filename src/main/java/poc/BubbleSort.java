package poc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by slava on 12/11/17.
 */
public class BubbleSort {

    /**
     * test: 10
     * 1 4 2 5 3 7 8 5 6 9
     * @param a
     */
    public static void sort(double[] a) {
        for (int i=0; i<a.length; i++) {
            int swaps = 0;
            for (int j=0; j<a.length-1; j++) {
                if (a[j] < a[j+1]) {
                    double e = a[j];
                    a[j] = a[j+1];
                    a[j+1] = e;
                    swaps++;
                }
            }
            if (swaps == 0) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] a = new double[n];
        for (int i=0; i<n; i++) {
            a[i] = scanner.nextDouble();
        }
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
