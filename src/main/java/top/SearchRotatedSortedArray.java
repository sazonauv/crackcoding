package top;

import java.util.Scanner;

/**
 * Created by slava on 31/01/18.
 */
public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        // pivot
        int i = 0;
        int j = nums.length-1;
        int il = i;
        if (nums[i] > nums[j]) {
            while (i < j) {
                if (nums[i] > nums[j]) {
                    il = i;
                    i = (i + j) / 2;
                    if (i == il) {
                        break;
                    }
                } else {
                    j = i;
                    i = il;
                }
            }
            i++;
        }
        int p = i;
        i = 0;
        j = nums.length-1;
        while (i < j) {
            int mid = (i + j) / 2;
            int adjMid = getAdjustedIndex(mid, p, nums.length);
            if (nums[adjMid] == target) {
                return adjMid;
            } else if (nums[adjMid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        int adjInd = getAdjustedIndex(i, p, nums.length);
        if (nums[adjInd] == target) {
            return adjInd;
        }
        return -1;
    }

    private int getAdjustedIndex(int index, int pivot, int length) {
        if (index + pivot < length) {
            return index + pivot;
        }
        return index + pivot - length;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int target = scan.nextInt();
        int size = scan.nextInt();
        int[] array = new int[size];
        for (int i=0; i<size; i++) {
            array[i] = scan.nextInt();
        }
        System.out.println(new SearchRotatedSortedArray().search(array, target));
    }

}
