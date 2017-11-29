package poc;

import java.util.Arrays;
import java.util.Comparator;

public class MedianSlidingWindow {

    class ArrayIndexComparator implements Comparator<Integer> {

        int[] array;

        ArrayIndexComparator(int[] array) {
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



    public double[] medianSlidingWindow(int[] nums, int k) {
        Integer[] sortedIndex = getIndexArray(nums.length);
        ArrayIndexComparator comp = new ArrayIndexComparator(nums);
        Arrays.sort(sortedIndex, comp);
        double[] medians = new double[nums.length-k+1];
        for (int i=0; i<medians.length; i++) {
            medians[i] = median(nums, sortedIndex, i, k);
        }
        return medians;
    }

    private Integer[] getIndexArray(int length) {
        Integer[] indexArray = new Integer[length];
        for (int i=0; i<length;i++) {
            indexArray[i] = i;
        }
        return indexArray;
    }

    private double median(int[] nums, Integer[] sortedIndex, int start, int k) {
        if (k <= 1) {
            return nums[start];
        }
        if (k % 2 == 0) {
            return (nums[sortedIndex[start + k/2]] + nums[sortedIndex[start + k/2 - 1]]) / 2;
        } else {
            return nums[sortedIndex[start + k/2]];
        }
    }

}
