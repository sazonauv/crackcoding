package random;

/**
 * Created by slava on 08/11/17.
 */
public class NextIntegerArray {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for (int i=0; i<nums1.length; i++) {
            int el = nums1[i];
            boolean found = false;
            for (int j=i+1; j<nums2.length; j++) {
                if (nums2[j] > el) {
                    res[i] = nums2[j];
                    found = true;
                    break;
                }
            }
            if (!found) {
                res[i] = -1;
            }
        }
        return res;
    }

}
