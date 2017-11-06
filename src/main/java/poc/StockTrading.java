package poc;

import java.util.Scanner;

/**
 * Created by slava on 06/11/17.
 */
public class StockTrading {

//    Input: 7 1 5 3 6 4
//    Output: 5

    public static int maxProfit(int[] prices) {
        int maxSell = 0;
        for (int i=0; i<prices.length-1; i++) {
            for (int j=i+1; j<prices.length; j++) {
                int sell = prices[j] - prices[i];
                if (maxSell < sell) {
                    maxSell = sell;
                }
            }
        }
        return maxSell;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        int output = maxProfit(a);
        System.out.println(output);
    }

}
