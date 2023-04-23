import java.util.Arrays;

public class RestoreTheArray {
    boolean numSmaller(String s, int start, String upper) {
        if (upper.length() > s.length() - start)
            return true;

        for (int i = start, j = 0; j < upper.length() && i < s.length(); i++, j++) {
            if (upper.charAt(j) > s.charAt(i))
                return true;
            else if (upper.charAt(j) < s.charAt(i))
                return false;
        }
        return true;
    }

    public int numberOfArrays(String s, int k) {
        long MOD = 1000000000 + 7;
        String upper = Integer.toString(k);
        long[] dp = new long[s.length() + 1];
        int len_cur = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            long val = 1;
            if (s.charAt(i) != '0')
                len_cur += 1;
            else {
                continue;
            }

            int len_act = s.length() - i, zeronum = 0;
            for (int j = 2; j <= upper.length() && j <= len_act; j++) {
                if (j == upper.length() && !numSmaller(s, i, upper)) {
                    break;
                }
                if (i + j - 1 < s.length() && s.charAt(i + j - 1) == '0') {
                    zeronum++;
                    if (j - zeronum < 2)
                        continue;
                }
                if (i + j < s.length() && s.charAt(i + j) == '0') {
                    // zeronum++;
                    continue;
                }
                val += dp[Math.max(len_cur - j + zeronum, 0)];
                val %= MOD;
            }
            if (len_act > upper.length() || !numSmaller(s, i, upper)) val--;
            dp[len_cur] = (dp[len_cur - 1] + val) % MOD;
        }

        if (len_cur == 1) {
            if (s.length() > upper.length() || !numSmaller(s, 0, upper))
                return 0;
        }
        return (int)dp[len_cur];
    }

    // https://leetcode.com/problems/restore-the-array/solutions/3445455/image-explanation-easiest-concise-c-java-python/
    class Solution {
        public int dfs(String s, long k, int i, int[] dp) {
            if (i == s.length()) return 1;
            if (s.charAt(i) == '0') return 0;
            if (dp[i] != -1) return dp[i];

            int ans = 0;
            long num = 0;
            for (int j = i; j < s.length(); j++) {
                num = num * 10 + s.charAt(j) - '0';
                if (num > k) break;
                ans = (ans + dfs(s, k, j + 1, dp)) % 1000000007;
            }
            return dp[i] = ans;
        }

        public int numberOfArrays(String s, int k) {
            int[] dp = new int[s.length()];
            Arrays.fill(dp, -1);
            return dfs(s, k, 0, dp);
        }
    }
    public static void main(String[] args) {
        RestoreTheArray r = new RestoreTheArray();
        System.out.println(r.numberOfArrays("2553462832281151811513004352253111", 456));
    }
}
