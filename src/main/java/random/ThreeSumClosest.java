package random;

import java.util.Arrays;

/**
 * Created by slava on 22/01/18.
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDelta = Integer.MAX_VALUE;
        int closestSum = Integer.MAX_VALUE;
        for (int i=0; i<nums.length; i++) {
            int i1 = i;
            int i2 = i+1;
            int i3 = nums.length-1;
            while (i2 < i3) {
                int sum = nums[i1] + nums[i2] + nums[i3];
                if (sum == target) {
                    return target;
                }
                int delta = Math.abs(sum - target);
                if (minDelta > delta) {
                    minDelta = delta;
                    closestSum = sum;
                }
                if (sum < target) {
                    i2++;
                }
                if (sum > target) {
                    i3--;
                }
            }
        }
        return closestSum;
    }

}
