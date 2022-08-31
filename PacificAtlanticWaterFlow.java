import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 417
public class PacificAtlanticWaterFlow {
    int[][] dirs = new int[][] {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int[][] dp = new int[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (walk(heights, dp, i, j) == 3) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i); l.add(j);
                    res.add(l);
                }
            }
        }
        return res;
    }

    int walk(int[][] heights, int[][] dp, int x, int y) {
        if (x == 0 || y == 0) {
            dp[x][y] |= 1;   // can reach Pacific
        }

        if (x == heights.length - 1 || y == heights[0].length - 1) {
            dp[x][y] |= 2;   // can reach Atlantic
        }

        if (dp[x][y] == 3) return dp[x][y];

        for (var d : dirs) {
            if (x + d[0] < heights.length && x + d[0] >= 0
                    && y + d[1] < heights[0].length && y + d[1] >= 0
                    && heights[x + d[0]][y + d[1]] <= heights[x][y]) {
                if ((dp[x + d[0]][y + d[1]] & 4) != 0) continue;
                if (dp[x + d[0]][y + d[1]] > 0) {
                    dp[x][y] |= dp[x + d[0]][y + d[1]];
                }
                if (dp[x + d[0]][y + d[1]] < 3) {
                    dp[x][y] |= 4;
                    dp[x][y] |= walk(heights, dp, x + d[0], y + d[1]);
                    dp[x][y] &= 3;
                }
            }
        }

        return dp[x][y];
    }

    // https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/1126812/JS-Python-Java-C%2B%2B-or-Easy-DFS-Recursion-DP-Solution-w-Explanation
    class Solution {
        static void dfs(int i, int j, int w, int h, int[][] M, byte[] dp, List<List<Integer>> ans) {
            int ij = i * M[0].length + j;
            if ((dp[ij] & w) > 0 || M[i][j] < h) return;
            dp[ij] += w;
            h = M[i][j];
            if (dp[ij] == 3) ans.add(Arrays.asList(i,j));
            if (i + 1 < M.length) dfs(i+1, j, w, h, M, dp, ans);
            if (i > 0) dfs(i-1, j, w, h, M, dp, ans);
            if (j + 1 < M[0].length) dfs(i, j+1, w, h, M, dp, ans);
            if (j > 0) dfs(i, j-1, w, h, M, dp, ans);
        }
        public List<List<Integer>> pacificAtlantic(int[][] M) {
            List<List<Integer>> ans = new ArrayList<>();
            if (M.length == 0) return ans;
            int y = M.length, x = M[0].length;
            byte[] dp = new byte[x * y];
            for (int i = 0; i < x; i++) {
                dfs(0, i, 1, M[0][i], M, dp, ans);
                dfs(y-1, i, 2, M[y-1][i], M, dp, ans);
            }
            for (int i = 0; i < y; i++) {
                dfs(i, 0, 1, M[i][0], M, dp, ans);
                dfs(i, x-1, 2, M[i][x-1], M, dp, ans);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow p = new PacificAtlanticWaterFlow();
        System.out.println(p.pacificAtlantic(new int[][]{
                {8,7},
                {11,2},
                {1,13},
                {14,15},
                {0,10},
                {19,9},
                {17,14},
                {10,10},
                {5,5},
                {15,3},
                {6,10},
                {11,10},
                {4,3},
                {12,13},
                {11,7},
                {0,9},
                {13,5},
                {11,18},
                {9,19},
                {10,11}
        }));
    }
}
