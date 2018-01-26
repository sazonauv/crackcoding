package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by slava on 16/01/18.
 */
public class DissapearedNumbers {

    // in: 4 3 2 7 8 2 3 1
    // out: 5 6
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> missed = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            int ind = Math.abs(nums[i])-1;
            if (nums[ind] > 0) {
                nums[ind] = -nums[ind];
            }
        }
        for (int i=0; i<nums.length; i++) {
            if (nums[i] > 0) {
                missed.add(i+1);
            }
        }
        return missed;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(new DissapearedNumbers().findDisappearedNumbers(a));
    }

}
