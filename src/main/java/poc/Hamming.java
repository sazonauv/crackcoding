package poc;

public class Hamming {

    public int totalHammingDistance(int[] nums) {
        int maxBinLen = 32;
        char[][] chars = new char[nums.length][maxBinLen];
        for (int i=0; i<nums.length; i++) {
            char[] charsi = Integer.toBinaryString(nums[i]).toCharArray();
            for (int j=0; j<charsi.length; j++) {
                chars[i][maxBinLen - j - 1] = charsi[charsi.length - j - 1];
            }
        }
        int hamSum = 0;
        for (int i=0; i<chars.length-1; i++) {
            for (int j=i+1; j<chars.length; j++) {
                hamSum += hamming(chars[i], chars[j]);
            }
        }
        return hamSum;
    }

    public int hamming(char[] bits1, char[] bits2) {
        if (bits1.length != bits2.length) {
            return -1;
        }
        int count = 0;
        for (int i=0; i<bits1.length; i++) {
            if (bits1[i] != bits2[i]) {
                count++;
            }
        }
        return count;
    }

}
