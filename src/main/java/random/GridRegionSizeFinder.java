package random;

import java.util.Scanner;

/**
 * Created by slava on 07/11/17.
 */
public class GridRegionSizeFinder {

// Input      3 3
//            1 0 1
//            0 1 0
//            1 0 1
//  Output          4

    public static int getBiggestRegion(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] regions = new int[matrix.length][matrix[0].length];
        int regionNum = 1;
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    regions[i][j] = getRegionNumber(i, j, regions);
                    if (regions[i][j] < 0) {
                        regions[i][j] = regionNum;
                        regionNum++;
                    }
                }
            }
        }
        // one more scan to cover the "X" case
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j] == 1 && i < matrix.length-1 && j > 0) {
                    if (matrix[i+1][j-1] == 1) {
                        regions[i][j] = regions[i+1][j-1];
                    }
                }
            }
        }
        int[] regionHist = new int[regionNum+1];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (regions[i][j] > 0) {
                    regionHist[regions[i][j]]++;
                }
            }
        }
        int maxRegion = 0;
        for (int i=0; i<regionHist.length; i++) {
            if (maxRegion < regionHist[i]) {
                maxRegion = regionHist[i];
            }
        }
        return maxRegion;
    }

    private static int getRegionNumber(int i, int j, int[][] regions) {
        if (j > 0 && regions[i][j-1] != 0) {
            return regions[i][j-1];
        }
        if (i > 0 && regions[i-1][j] != 0) {
            return regions[i-1][j];
        }
        if (i > 0 && j > 0 && regions[i-1][j-1] != 0) {
            return regions[i-1][j-1];
        }
        if (i > 0 && j < regions[0].length-1 && regions[i-1][j+1] != 0) {
            return regions[i-1][j+1];
        }
        if (i < regions.length-1 && regions[i+1][j] != 0) {
            return regions[i+1][j];
        }
        if (i < regions.length-1 && j > 0 && regions[i+1][j-1] != 0) {
            return regions[i+1][j-1];
        }
        return -1;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(getBiggestRegion(grid));
    }

}
