package random;

/**
 * Created by slava on 21/01/18.
 */
public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int s = 1;
        while (nums[s] == nums[s-1] && s < nums.length-1) {
            s++;
        }
        int len = 1;
        boolean incr = nums[s] > nums[s-1];
        for (int i=s; i<nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                continue;
            }
            if (incr && nums[i] < nums[i-1]) {
                len++;
            }
            if (!incr && nums[i] > nums[i-1]) {
                len++;
            }
            incr = nums[i] > nums[i-1];
            if (i == nums.length-1) {
                len++;
            }
        }
        return len;
    }


}
