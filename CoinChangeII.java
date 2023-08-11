import java.util.Arrays;

/**
 * CoinChangeII
 */
public class CoinChangeII {

    private int helper(int[] coins, int amount, int[] dp) {
        if (amount < 0)
            return 0;

        if (dp[amount] != -1)
            return dp[amount];

        if (amount == 0)
            return 1;

        int count = 0;
        for (int c : coins) {
            count += helper(coins, amount - c, dp);
        }
        dp[amount] = count;
        return count;
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        helper(coins, amount, dp);
        return dp[amount];
    }

    // https://leetcode.com/problems/coin-change-ii/solutions/3892702/100-dynamic-programming-video-optimal-solution/
    public class Solution {
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int j = coin; j <= amount; j++) {
                    dp[j] += dp[j - coin];
                }
            }

            return dp[amount];
        }
    }

    public static void main(String[] args) {
        CoinChangeII c = new CoinChangeII();
        System.out.println(c.change(3, new int[] {1, 2, 5}));
    }
}
