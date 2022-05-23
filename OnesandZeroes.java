import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// 474
public class OnesandZeroes {
    int val_ = 0;
    Map<String, Integer> ones = new HashMap<>();
    Map<String, Integer> zeros = new HashMap<>();

    int walk(String[] strs, int m, int n, int val, int from, int[] ov, int[] zv) {
        int to = 0;
        int tz = 0;
        if (n > 0 && ov[n - 1] != 0) to = ov[n - 1];
        if (m > 0 && zv[m - 1] != 0) tz = zv[m - 1];
        if (to != 0 && tz != 0) return Math.max(to, tz);
        for (int i = from; i < strs.length; i++) {
            int o = 0, z = 0;
            if (!ones.containsKey(strs[i]) || !zeros.containsKey(strs[i])) {
                for (int ii = 0; ii < strs[i].length(); ii++) {
                    if (strs[i].charAt(ii) == '0') z++;
                    if (strs[i].charAt(ii) == '1') o++;
                }
                ones.put(strs[i], o);
                zeros.put(strs[i], z);
            } else {
                o = ones.get(strs[i]);
                z = zeros.get(strs[i]);
            }

            if (m - z < 0 || n - o < 0) continue;
            int v = walk(strs, m - z, n - o, val + 1, i + 1, ov, zv);
            ov[n - o - 1] = v;
            zv[m - z - 1] = v;
        }
        val_ = Math.max(val_, val);
        return val;
    }

    public int findMaxForm(String[] strs, int m, int n) {
        Arrays.sort(strs, Comparator.comparingInt(String::length));
        int[] ov = new int[n + 1];
        int[] zv = new int[m + 1];
        walk(strs, m, n, 0, 0, ov, zv);
        return val_;
    }

    // https://leetcode.com/problems/ones-and-zeroes/discuss/95811/Java-Iterative-DP-Solution-O(mn)-Space
    public int findMaxFormBetter(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String s : strs) {
            int[] count = count(s);
            for (int i=m;i>=count[0];i--)
                for (int j=n;j>=count[1];j--)
                    dp[i][j] = Math.max(1 + dp[i-count[0]][j-count[1]], dp[i][j]);
        }
        return dp[m][n];
    }

    public int[] count(String str) {
        int[] res = new int[2];
        for (int i=0;i<str.length();i++)
            res[str.charAt(i) - '0']++;
        return res;
    }

    public static void main(String[] args) {
        OnesandZeroes o = new OnesandZeroes();
        System.out.println(o.findMaxForm(new String[]{
                "11","11","0","0","10","1","1","0","11","1","0","111","11111000","0","11","000","1","1","0","00","1","101","001","000","0","00","0011","0","10000"
        }, 90, 66));
    }
}
