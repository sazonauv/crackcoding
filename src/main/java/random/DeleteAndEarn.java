package random;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by slava on 19/12/17.
 */
public class DeleteAndEarn {

    // in: 2 8 2 9 8 9 8 8 5 5 10 2 6 6 6 9 3 10 9 3
    // out: 76

    public int deleteAndEarn(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        // sorted histogram
        int valNumber = 1;
        int cur = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i] != cur) {
                valNumber++;
                cur = nums[i];
            }
        }
        int[] vals = new int[valNumber];
        int[] counts = new int[valNumber];
        vals[0] = nums[0];
        counts[0] = 1;
        cur = nums[0];
        int j = 0;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] != cur) {
                j++;
                cur = nums[i];
                vals[j] = nums[i];
                counts[j] = 1;
            } else {
                counts[j]++;
            }
        }
        // count earned points
        int points = 0;
        int i = vals.length-1;
        while (i >= 0) {
            if (i == 0 || vals[i] - vals[i-1] > 1) {
                points += vals[i] * counts[i];
                i--;
            } else {
                int maxPoints = vals[i] * counts[i];
                if (i == 1 || vals[i-1] - vals[i-2] > 1) {
                    if (maxPoints < vals[i-1] * counts[i-1]) {
                        maxPoints = vals[i-1] * counts[i-1];
                    }
                    i-=2;
                } else {
                    if (maxPoints + vals[i-2] * counts[i-2] < vals[i-1] * counts[i-1]) {
                        maxPoints = vals[i-1] * counts[i-1];
                        i-=3;
                    } else {
                        i-=2;
                    }
                }
                points += maxPoints;
            }
        }
        return points;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(new DeleteAndEarn().deleteAndEarn(a));
    }

}
