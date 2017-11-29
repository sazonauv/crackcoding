package poc;

import java.util.Arrays;
import java.util.Comparator;

public class MedianSlidingWindow {

    static class ArrayIndexComparator implements Comparator<Integer> {

        double[] array;

        ArrayIndexComparator(double[] array) {
            this.array = array;
        }

        public int compare(Integer i, Integer j) {
            if (array[i] < array[j]) {
                return -1;
            } else if (array[i] > array[j]) {
                return 1;
            } else {
                return 0;
            }
        }

    }



    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] array = getDoubleArray(nums);
        Integer[] sortedIndex = getIndexArray(array.length);
        ArrayIndexComparator comp = new ArrayIndexComparator(array);
        Arrays.sort(sortedIndex, comp);
        double[] medians = new double[array.length-k+1];
        for (int i=0; i<medians.length; i++) {
            medians[i] = median(array, sortedIndex, i, k);
        }
        return medians;
    }

    private static double[] getDoubleArray(int[] nums) {
        double[] array = new double[nums.length];
        for (int i=0; i<nums.length;i++) {
            array[i] = nums[i];
        }
        return array;
    }

    private static Integer[] getIndexArray(int length) {
        Integer[] indexArray = new Integer[length];
        for (int i=0; i<length;i++) {
            indexArray[i] = i;
        }
        return indexArray;
    }

    private static double median(double[] nums, Integer[] sortedIndex, int start, int k) {
        if (k <= 1) {
            return nums[start];
        }
        int count = 0;
        int cur = 0;
        int prev = 0;
        for (int i=0; i<sortedIndex.length; i++) {
            if (sortedIndex[i] >= start && sortedIndex[i] < start+k) {
                count++;
                prev = cur;
                cur = i;
            }
            if (count > k/2) {
                break;
            }
        }
        if (k % 2 == 0) {
            return (nums[sortedIndex[cur]] + nums[sortedIndex[prev]]) / 2;
        } else {
            return nums[sortedIndex[cur]];
        }
    }

    public static void main(String[] args) {
        int k = 2;
        int[] array = new int[]{2147483647, 2147483647};
        Out.p(Arrays.toString(array));
//        int[] array = new int[]{1, 2, 3, 4};
//        int[] array = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        // correct: [1,-1,-1,3,5,6]
        Out.p(Arrays.toString(medianSlidingWindow(array, k)));
    }

}
