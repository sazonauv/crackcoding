package top;

/**
 * Created by slava on 26/01/18.
 */
public class TwoArraysMedian {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 == 0) {
            if (l2 % 2 == 0) {
                return ((double)nums2[l2/2] + nums2[l2/2 + 1]) / 2;
            } else {
                return nums2[l2/2];
            }
        }
        if (l2 == 0) {
            if (l1 % 2 == 0) {
                return ((double)nums1[l1/2] + nums1[l1/2 + 1]) / 2;
            } else {
                return nums1[l1/2];
            }
        }
        int s1 = 0;
        int e1 = l1 - 1;
        int s2 = 0;
        int e2 = l2 - 1;
        int i = (s1 + e1) / 2;
        int j = (s2 + e2) / 2;
        int m = (l1 + l2) / 2;
        while (i + j != m) {
            i = (s1 + e1) / 2;
            j = (s2 + e2) / 2;
            if (nums1[i] >= nums2[j]) {
                s2 = j;
            } else {
                s1 = i;
            }
        }
        return (nums1[i] + nums2[j]) / 2;
    }

}
