package random;

/**
 * Created by slava on 19/12/17.
 */
public class MatrixKthSmallest {

    public int kthSmallest(int[][] m, int k) {
        if (k == 1) {
            return m[0][0];
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<m.length; i++) {
            for (int j=0; j<m[0].length; j++) {
                if (max < m[i][j]) {
                    max = m[i][j];
                }
                if (min > m[i][j]) {
                    min = m[i][j];
                }
            }
        }
        int mid = (max + min) / 2;
        int low = min;
        int high = max;
        while (high > low) {
            int lcount = 0;
            for (int i=0; i<m.length; i++) {
                for (int j=0; j<m[0].length; j++) {
                    if (m[i][j] <= mid) {
                        lcount++;
                    }
                }
            }
            if (lcount > k) {
                high = mid;
            } else if (lcount < k) {
                low = mid + 1;
            } else {
                break;
            }
            mid = (low + high) / 2;
        }
        int res = m[0][0];
        for (int i=0; i<m.length; i++) {
            for (int j=0; j<m[0].length; j++) {
                if (res < m[i][j] && m[i][j] <= mid) {
                    res = m[i][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] m = new int[][] {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        System.out.println(new MatrixKthSmallest().kthSmallest(m, k));
    }

}
