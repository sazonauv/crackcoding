package top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by slava on 26/01/18.
 */
public class TwoSum {

    // alternative O(n) solution: HashMap

    class ArrayIndexComparator implements Comparator<Integer> {
        int[] array;
        ArrayIndexComparator(int[] array) {
            this.array = array;
        }
        public int compare(Integer i, Integer j) {
            return Integer.compare(array[i], array[j]);
        }
    }

    public int[] twoSum(int[] nums, int target) {
        ArrayIndexComparator comp = new ArrayIndexComparator(nums);
        ArrayList<Integer> indexList = new ArrayList<>(nums.length);
        for (int i=0; i<nums.length; i++) {
            indexList.add(i);
        }
        Collections.sort(indexList, comp);
        int[] res = new int[2];
        int i = 0;
        int j = nums.length-1;
        while (i < j) {
            int sum = nums[indexList.get(i)] + nums[indexList.get(j)];
            if (sum == target) {
                res[0] = indexList.get(i);
                res[1] = indexList.get(j);
                return res;
            }
            if (sum > target) {
                j--;
            }
            if (sum < target) {
                i++;
            }
        }
        return null;
    }

}
