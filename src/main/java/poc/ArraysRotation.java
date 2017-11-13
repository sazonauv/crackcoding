package poc;
import java.util.Scanner;

/**
 * Created by slava on 06/11/17.
 */
public class ArraysRotation {

    public static int[] arrayLeftRotation(int[] a, int n, int k) {
        int[] output = new int[a.length];
        int shift = k % a.length;
        for (int i=shift; i<a.length; i++) {
            output[i-shift] = a[i];
        }
        for (int i=0; i<shift; i++) {
            output[a.length - shift + i] = a[i];
        }
        return output;
    }

    static int[] leftRotation(int[] a, int d) {
        int[] output = new int[a.length];
        int shift = d % a.length;
        for (int i=shift; i<a.length; i++) {
            output[i-shift] = a[i];
        }
        for (int i=0; i<shift; i++) {
            output[a.length - shift + i] = a[i];
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }

        int[] output = arrayLeftRotation(a, n, k);
        for(int i = 0; i < n; i++)
            System.out.print(output[i] + " ");

        System.out.println();

    }

}
