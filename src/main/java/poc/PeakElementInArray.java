package poc;

public class PeakElementInArray {

    public int findPeakElementLinear(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            if ((i == 0 || nums[i] > nums[i-1])
                    && (i == nums.length - 1 || nums[i] > nums[i+1])) {
                return i;
            }
        }
        return -1;
    }


    public int findPeakElement(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length-1;
        int i = 0;
        while (left < right) {
            i = (left + right) / 2;
            if ((i == 0 || nums[i] > nums[i-1]) &&
                    (i == nums.length-1 || nums[i] > nums[i+1])) {
                return i;
            }
            if (i > 0 && nums[i] < nums[i-1]) {
                right = i-1;
                i = i-1;
            }
            if (i < nums.length-1 && nums[i] < nums[i+1]) {
                left = i+1;
                i = i+1;
            }
        }
        return i;
    }


}
