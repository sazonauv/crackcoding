package poc;

import java.util.Arrays;

public class LongestConsecutiveSequence {

    // n*log(n)
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        Arrays.sort(nums);
        int len = 1;
        int maxLen = 1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                continue;
            }
            if (nums[i] == nums[i-1] + 1) {
                len++;
            } else {
                if (maxLen < len) {
                    maxLen = len;
                }
                len = 1;
            }
        }
        if (maxLen < len) {
            maxLen = len;
        }
        return maxLen;
    }






}
