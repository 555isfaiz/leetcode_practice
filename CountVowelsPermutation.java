import java.util.Arrays;

// 1220
public class CountVowelsPermutation {
    public int countVowelPermutation(int n) {
        long[][] arr = new long[5][n];
        long MOD = 1000000007;
        //  a              e              i              o              u
        arr[0][0] = 1; arr[1][0] = 1; arr[2][0] = 1; arr[3][0] = 1; arr[4][0] = 1;
        for (int i = 0; i < n - 1; i++) {
            // a:
            arr[0][i + 1] += arr[1][i] + arr[2][i] + arr[4][i];
            arr[0][i + 1] %= MOD;
            // e:
            arr[1][i + 1] += arr[0][i] + arr[2][i];
            arr[1][i + 1] %= MOD;
            // i:
            arr[2][i + 1] += arr[1][i] + arr[3][i];
            arr[2][i + 1] %= MOD;
            // o:
            arr[3][i + 1] += arr[2][i];
            arr[3][i + 1] %= MOD;
            // u:
            arr[4][i + 1] += arr[2][i] + arr[3][i];
            arr[4][i + 1] %= MOD;
        }
        return (int) ((arr[0][n - 1] + arr[1][n - 1] + arr[2][n - 1] + arr[3][n - 1] + arr[4][n - 1]) % MOD);
    }

    // https://leetcode.com/problems/count-vowels-permutation/discuss/1315113/C%2B%2BJavaPython-Top-down-DP-Bottom-up-DP-Picture-explain-Clean-and-Concise
    int a = 0, e = 1, i = 2, o = 3, u = 4, MOD = (int) (1e9 + 7);
    public int countVowelPermutationBetter(int n) {
        long[] dp = new long[5], prevDP = new long[5];
        Arrays.fill(prevDP, 1L);
        while (n-- > 1) {
            dp[a] = (prevDP[e] + prevDP[i] + prevDP[u]) % MOD;
            dp[e] = (prevDP[a] + prevDP[i]) % MOD;
            dp[i] = (prevDP[e] + prevDP[o]) % MOD;
            dp[o] = prevDP[i];
            dp[u] = (prevDP[i] + prevDP[o]) % MOD;
            long[] tmp = dp; dp = prevDP; prevDP = tmp;
        }
        return (int) ((prevDP[a] + prevDP[e] + prevDP[i] + prevDP[o] + prevDP[u]) % MOD);
    }

    public static void main(String[] args) {
        CountVowelsPermutation c = new CountVowelsPermutation();
        System.out.println(c.countVowelPermutation(144));
    }
}
