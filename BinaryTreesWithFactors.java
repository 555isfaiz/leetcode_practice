import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 823
public class BinaryTreesWithFactors {
    public int numFactoredBinaryTrees(int[] arr) {
        long val = 0;
        int MOD = 1000000007;
        long[] dp = new long[arr.length];
        Arrays.fill(dp, 1);
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0 && map.containsKey(arr[i] / arr[j])) {
                    int jj = map.get(arr[i] / arr[j]);
                    if (jj < j) continue;
                    if (dp[jj] == 1 && dp[j] == 1 && jj == j) {
                        dp[i] += 1;
                    } else if (jj == j) {
                        dp[i] += dp[jj] * dp[j];
                    } else {
                        dp[i] += dp[jj] * dp[j] * 2;
                    }
                    dp[i] = dp[i] % MOD;
                }
            }
            val += dp[i];
        }
        return (int) (val % MOD);
    }

    // https://leetcode.com/problems/binary-trees-with-factors/discuss/125794/C%2B%2BJavaPython-DP-solution
    public int numFactoredBinaryTreesBetter(int[] A) {
        long res = 0L, mod = (long)1e9 + 7;
        Arrays.sort(A);
        HashMap<Integer, Long> dp = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            dp.put(A[i], 1L);
            for (int j = 0; j < i; ++j)
                if (A[i] % A[j] == 0)
                    dp.put(A[i], (dp.get(A[i]) + dp.get(A[j]) * dp.getOrDefault(A[i] / A[j], 0L)) % mod);
            res = (res + dp.get(A[i])) % mod;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        BinaryTreesWithFactors b = new BinaryTreesWithFactors();
        System.out.println(b.numFactoredBinaryTrees(new int[] {
                45,42,2,18,23,1170,12,41,40,9,47,24,33,28,10,32,29,17,46,11,759,37,6,26,21,49,31,14,19,8,13,7,27,22,3,36,34,38,39,30,43,15,4,16,35,25,20,44,5,48
        }));
    }
}
