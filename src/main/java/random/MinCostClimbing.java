package random;

/**
 * Created by slava on 20/01/18.
 */
public class MinCostClimbing {

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        int twoStepCost = cost[0];
        int oneStepCost = cost[1];
        int currentCost;
        for (int i=2; i<cost.length; i++) {
            currentCost = cost[i] + minNumber(oneStepCost, twoStepCost);
            twoStepCost = oneStepCost;
            oneStepCost = currentCost;
        }
        return minNumber(twoStepCost, oneStepCost);
    }

    private int minNumber(int n1, int n2) {
        return n1 < n2 ? n1 : n2;
    }

}
