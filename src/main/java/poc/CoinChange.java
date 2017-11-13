package poc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CoinChange {

    public static long makeChange(int[] coins, int money) {
        long[] DP = new long[money + 1];
        // n == 0 case.
        DP[0] = 1;
        for(int coin : coins) {
            for(int j = coin; j < DP.length; j++) {
                DP[j] += DP[j - coin];
            }
        }
        return DP[money];
    }


    class CoinTree {

        class CoinNode {

            List<CoinNode> children;

            int coinInd;
            int coinNum;
            int remAmount;

            CoinNode(int coinInd, int coinNum, int remAmount) {
                this.coinInd = coinInd;
                this.coinNum = coinNum;
                this.remAmount = remAmount;
            }


            void branch() {
                if (remAmount <= 0 || coinInd == 0) {
                    leaves.add(this);
                    return;
                }
                int maxNum = remAmount / coins[coinInd-1];
                children = new LinkedList<>();
                for (int i=0; i<=maxNum; i++) {
                    CoinNode child = new CoinNode(coinInd-1, coinNum+i, remAmount - i*coins[coinInd-1]);
                    children.add(child);
                    child.branch();
                }
            }

        }

        int[] coins;
        int amount;

        CoinNode root;
        List<CoinNode> leaves;

        CoinTree(int[] coins, int amount) {
            this.coins = coins;
            this.amount = amount;
        }

        public void buildTree() {
            leaves = new LinkedList<>();
            root = new CoinNode(coins.length, 0, amount);
            root.branch();
        }

        public int findMinCoinNumber() {
            int minNum = Integer.MAX_VALUE;
            for(CoinNode n : leaves) {
                if (n.remAmount == 0) {
                    if (minNum > n.coinNum) {
                        minNum = n.coinNum;
                    }
                }
            }
            if (minNum == Integer.MAX_VALUE) {
                return -1;
            }
            return minNum;
        }

    }


    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        CoinTree tree = new CoinTree(coins, amount);
        tree.buildTree();
        return tree.findMinCoinNumber();
    }

    /*public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int num = 0;
        int rest = amount;
        for (int i=coins.length-1; i>=0; i--) {
            num += rest / coins[i];
            rest = rest % coins[i];
        }
        if (rest > 0) {
            return -1;
        }
        return num;
    }*/

}
