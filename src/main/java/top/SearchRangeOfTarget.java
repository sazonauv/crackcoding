package top;

/**
 * Created by slava on 13/02/18.
 */
public class SearchRangeOfTarget {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        if (nums[0] == target && nums[nums.length-1] == target) {
            return new int[] {0, nums.length-1};
        }
        // index
        int i = 0;
        int j = nums.length-1;
        int ind = 0;
        while (i < j) {
            ind = (i + j) / 2;
            if (nums[ind] == target) {
                break;
            } else if (nums[ind] > target) {
                j = ind - 1;
            } else {
                i = ind + 1;
            }
        }
        ind = (i + j) / 2;
        if (ind > 0 && nums[ind-1] == target) {
            ind--;
        }
        if (ind < nums.length-1 && nums[ind+1] == target) {
            ind++;
        }
        if (nums[ind] != target) {
            return new int[] {-1, -1};
        }
        // range
        if ((ind == 0 || nums[ind-1] != target)
                && (ind == nums.length-1 || nums[ind+1] != target)) {
            return new int[] {ind, ind};
        }
        // left
        int left = 0;
        if (ind > 0) {
            i = 0;
            j = ind - 1;
            while (i < j) {
                left = (i + j) / 2;
                if (nums[left] == target) {
                    j = left - 1;
                } else {
                    i = left + 1;
                }
            }
        }
        left = (i + j) / 2;
        if (nums[left] != target) {
            left++;
        }
        if (left > 0 && nums[left-1] == target) {
            left--;
        }
        // right
        int right = ind;
        if (ind < nums.length-1) {
            i = ind + 1;
            j = nums.length-1;
            while (i < j) {
                right = (i + j) / 2;
                if (nums[right] == target) {
                    i = right + 1;
                } else {
                    j = right - 1;
                }
            }
        }
        right = (i + j) / 2;
        if (nums[right] != target) {
            right--;
        }
        if (right < nums.length-1 && nums[right+1] == target) {
            right++;
        }
        return new int[] {left, right};
    }

}
