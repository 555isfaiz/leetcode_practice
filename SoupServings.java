import java.util.HashMap;
import java.util.Map;

public class SoupServings {
    public double helper(Map<String, Double> dp, int A, int B) {
        double prob = 0.0;
        if (A != 0 && B == 0)
            return prob;

        if (dp.containsKey("" + A + B))
            return dp.get("" + A + B);

        if (A <= 100) prob += 0.25;
        else prob += 0.25 * helper(dp, A - 100, B);
        
        if (A <= 75 && B > 25) prob += 0.25;
        else if (A <= 75) prob += 0.125;
        else if (A > 75 && B > 25) prob += 0.25 * helper(dp, A - 75, B - 25);
        
        if (A <= 50 && B > 50) prob += 0.25;
        else if (A <= 50) prob += 0.125;
        else if (A > 50 && B > 50) prob += 0.25 * helper(dp, A - 50, B - 50);
        
        if (A <= 25 && B > 75) prob += 0.25;
        else if (A <= 25) prob += 0.125;
        else if (A > 25 && B > 75) prob += 0.25 * helper(dp, A - 25, B - 75);

        dp.put("" + A + B, prob);
        return prob;
    }

    public double soupServings(int n) {
        if (n == 0) return 0.5;
        if (n > 4800) return 1.0;
        Map<String, Double> dp = new HashMap<>();
        return helper(dp, n, n);
    }

    // https://leetcode.com/problems/soup-servings/solutions/3831172/video-100-soup-servings-a-dive-into-dynamic-programming-and-probability/
    // private HashMap<Pair<Integer, Integer>, Double> memo = new HashMap<>();
    // class Solution {

    //     public double soupServings(int N) {
    //         if (N > 4800) {
    //             return 1.0;
    //         }
    //         N = (N + 24) / 25;

    //         return dp(N, N);
    //     }

    //     private double dp(int a, int b) {
    //         if (a <= 0 && b <= 0) {
    //             return 0.5;
    //         }
    //         if (a <= 0) {
    //             return 1.0;
    //         }
    //         if (b <= 0) {
    //             return 0.0;
    //         }
    //         Pair<Integer, Integer> key = new Pair<>(a, b);
    //         if (memo.containsKey(key)) {
    //             return memo.get(key);
    //         }
    //         memo.put(key, 0.25 * (dp(a-4, b) + dp(a-3, b-1) + dp(a-2, b-2) + dp(a-1, b-3)));
    //         return memo.get(key);
    //     }
    // }

    public static void main(String[] args) {
        SoupServings s = new SoupServings();
        System.out.println(s.soupServings(0));
        System.out.println(s.soupServings(50));
        System.out.println(s.soupServings(100));
        System.out.println(s.soupServings(125));
    }
}
