package poc;

public class StockTradingFee {

    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] profits = new int[prices.length];
        for (int i=1; i<prices.length; i++) {
            int maxProfit = 0;
            for (int j=0; j<i; j++) {
                int profit = profits[j] + (prices[i] - prices[j] - fee);
                if (maxProfit < profit) {
                    maxProfit = profit;
                }
            }
            profits[i] = maxProfit;
        }
        return profits[profits.length-1];
    }



}
