package poc;

import java.util.Scanner;

/**
 * Created by slava on 21/12/17.
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 */
public class DuplicateFinder {

    // in: 1 3 4 2 2
    // out: 2
    public int findDuplicate(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }
        int n = 0;
        for (int i=0; i<nums.length; i++) {
            if (n < nums[i]) {
                n = nums[i];
            }
        }
        int mid = (n%2 == 0 ? n/2 : n/2+1);
        int low = 1;
        int high = n;
        int lcount = 0;
        int gcount = 0;
        while (high > low) {
            lcount = 0;
            gcount = 0;
            for (int i=0; i<nums.length; i++) {
                if (nums[i] >= low && nums[i] <= high) {
                    if (nums[i] <= mid) {
                        lcount++;
                    } else {
                        gcount++;
                    }
                }
            }
            if (lcount > gcount) {
                high = mid;
            } else {
                low = mid+1;
            }
            mid = low + (high - low) / 2;
        }
        return low;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(new DuplicateFinder().findDuplicate(a));
    }


}
