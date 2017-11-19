package poc;

/**
 * Created by slava on 19/11/17.
 */
public class NondecreasingArrayMaker {

    public boolean checkPossibility(int[] nums) {
        int count = 0;
        int ind = 0;
        for (int i=1; i<nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                count++;
                if (count > 1) {
                    return false;
                }
                ind = i;
            }
        }
        if (count == 0) {
            return true;
        }
        if (ind == 1 || nums[ind-2] < nums[ind]) {
            nums[ind-1] = nums[ind];
        } else {
            nums[ind] = nums[ind-1];
        }
        for (int i=1; i<nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

}
