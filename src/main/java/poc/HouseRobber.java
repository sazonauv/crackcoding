package poc;

import java.util.Arrays;

/**
 * Created by slava on 19/11/17.
 */
public class HouseRobber {



    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] money = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            int max = 0;
            if (i == 2) {
                max = money[i-2];
            }
            if (i > 2) {
                max = max(money[i-2], money[i-3]);
            }
            money[i] = max + nums[i];
        }
        return max(money[nums.length-1], money[nums.length-2]);
    }

    private static int max(int n1, int n2) {
        if (n1 >= n2) {
            return n1;
        } else {
            return n2;
        }
    }


    public static void main(String[] args) {
        int n = 10;
        int[] nums = new int[n];
        for (int i=0; i<nums.length; i++) {
            nums[i] = (int) (Math.random() * 10);
        }
        Out.p("Houses: " + Arrays.toString(nums));
        Out.p(rob(nums));
    }

}
