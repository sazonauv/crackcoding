package top;

/**
 * Created by slava on 06/02/18.
 */
public class SingleNumberInArray {

    public int singleNumberXOR(int[] nums) {
        int res = 0;
        for (int i=0; i<nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        for (int i=0; i<nums.length; i++) {
            int ind = indexOf(nums[i], max, min, nums.length);
            nums[ind] = - nums[ind];
        }
        for (int i=0; i<nums.length; i++) {
            if (nums[i] < 0) {
                return elementOf(i, max, min, nums.length);
            }
        }
        return -1;
    }

    private int indexOf(int num, int max, int min, int length) {
        return (length - 1) * (num - min) / (max - min);
    }

    private int elementOf(int index, int max, int min, int length) {
        return index * (max - min) / (length - 1) + min;
    }


}
