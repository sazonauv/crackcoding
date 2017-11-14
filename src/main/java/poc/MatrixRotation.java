package poc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by slava on 14/11/17.
 */
public class MatrixRotation {

    public static void rotate(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            return;
        }
        int n = matrix.length;
        if (n == 1) {
            return;
        }
        // diagonal rotation
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i + j == n-1) {
                    break;
                }
                swap(i, j, n-1-j, n-1-i, matrix);
            }
        }
        // equator rotation
        for (int i=0; i<n; i++) {
            if (i >= n/2) {
                break;
            }
            for (int j=0; j<n; j++) {
                swap(i, j, n-1-i, j, matrix);
            }
        }
    }

    private static void swap(int i1, int j1, int i2, int j2, int[][] matrix) {
        if (i1 == i2 && j1 == j2) {
            return;
        }
        int var = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = var;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] mat = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                mat[i][j] = scan.nextInt();
            }
        }
        rotate(mat);
        for (int i=0; i<n; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }


}
