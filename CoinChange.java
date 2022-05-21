import java.util.*;

// 322
public class CoinChange {
    int num = -1;
    Map<Long, Integer> cache = new HashMap<>();

    int walk(int[] coins, int amount, long current, int val) {
        if (num != -1 && val > num)
            return -1;

        if (cache.containsKey(amount - current)) {
            val += cache.get(amount - current);
            current = amount;
        }

        if (amount == current) {
            num = num == -1 ? val : val;
            return 0;
        }

        if (amount < current)
            return -1;

        for (int i = coins.length - 1; i >= 0; i--) {
            cache.put(current + coins[i], val + 1);
            int w = walk(coins, amount, current + coins[i], val + 1);
//            if (w == 0) return 0;
        }
        return -1;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        for (var i : coins) {
            cache.put((long) i, 1);
        }
        walk(coins, amount, 0, 0);
        return num;
    }

    // https://leetcode.com/problems/coin-change/discuss/77368/*Java*-Both-iterative-and-recursive-solutions-with-explanations
    public int coinChangeBetter(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount]);
    }

    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
        if(rem<0) return -1; // not valid
        if(rem==0) return 0; // completed
        if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = helper(coins, rem-coin, count);
            if(res>=0 && res < min)
                min = 1+res;
        }
        count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[rem-1];
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        System.out.println(c.coinChange(new int[]{411,412,413,414,415,416,417,418,419,420,421,422}, 9864));
    }
}
