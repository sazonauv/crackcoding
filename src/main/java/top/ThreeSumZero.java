package top;

import java.util.*;

/**
 * Created by slava on 30/01/18.
 */
public class ThreeSumZero {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        loop:
        for (int i=0; i<nums.length-2; i++) {
            if (nums[i] + nums[i+1] > 0) {
                break;
            }
            int j = i+1;
            int k = nums.length-1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[j+1] > 0) {
                    break;
                }
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> triplet = new ArrayList<>(5);
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);
                    res.add(triplet);
                    if (nums[i] == 0) {
                        break loop;
                    }
                    j++;
                }
                if (sum > 0) {
                    k--;
                }
                if (sum < 0) {
                    j++;
                }
            }
        }
        return new LinkedList<>(res);
    }

}
