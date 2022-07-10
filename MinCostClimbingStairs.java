// 746
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) return cost[0];
        for (int i = 1; i < cost.length; i++) {
            cost[i] = Math.min(cost[i] + cost[i - 1], i == 1 ? cost[i] : cost[i] + cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}
