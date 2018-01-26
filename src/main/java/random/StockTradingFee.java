package random;

import java.util.Scanner;

public class StockTradingFee {

    // input: 6
    // 1 3 2 8 4 9
    // output: 8

    public static int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] profits = new int[prices.length];
        for (int i=1; i<prices.length; i++) {
            for (int j=-1; j<i; j++) {
                int minPrice = Integer.MAX_VALUE;
                int maxProfit = 0;
                for (int k=j+1; k<=i; k++) {
                    if (minPrice > prices[k]) {
                        minPrice = prices[k];
                    }
                    if (maxProfit < prices[k] - minPrice) {
                        maxProfit = prices[k] - minPrice;
                    }
                }
                int profit = (j < 0 ? 0 : profits[j]) + (maxProfit - fee);
                if (profits[i] < profit) {
                    profits[i] = profit;
                }
            }
        }
        return profits[profits.length-1];
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int fee = 2;
        int output = maxProfit(a, fee);
        System.out.println(output);
    }



}
