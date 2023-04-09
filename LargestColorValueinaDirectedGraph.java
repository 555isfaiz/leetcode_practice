import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestColorValueinaDirectedGraph {
    Map<Integer, List<Integer>> m = new HashMap<>();
    int res = 0;

    boolean dfs(int cur, String colors, int[][] dp, boolean[] path, boolean[] known) {
        if (m.containsKey(cur)) {
            for (var n : m.get(cur)) {
                if (path[n])
                    return false;
                
                    path[n] = true;
                    if (!known[n])
                        if (!dfs(n, colors, dp, path, known))
                            return false;

                for (int i = 0; i < dp[n].length; i++) {
                    dp[cur][i] = Math.max(dp[cur][i], dp[n][i]);
                }

                path[n] = false;
            }
        }

        dp[cur][colors.charAt(cur) - 'a']++;
        res = Math.max(dp[cur][colors.charAt(cur) - 'a'], res);
        known[cur] = true;
        return true;
    }

    public int largestPathValue(String colors, int[][] edges) {
        if (edges.length == 0)
            return 1;
        int[][] dp = new int[colors.length()][26];
        for (var e : edges) {
            if (m.containsKey(e[0])) {
                m.get(e[0]).add(e[1]);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(e[1]);
                m.put(e[0], l);
            }
        }

        boolean[] path = new boolean[colors.length()];
        boolean[] known = new boolean[colors.length()];
        for (var k : m.keySet()) {
            if (!known[k]) {
                path[k] = true;
                if (!dfs(k, colors, dp, path, known))
                    return -1;
                path[k] = false;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LargestColorValueinaDirectedGraph l = new LargestColorValueinaDirectedGraph();
        // System.out.println(l.largestPathValue("abaca", new int[][] {{0,1},{0,2},{2,3},{3,4}}));
        System.out.println(l.largestPathValue("hhqhuqhqff", new int[][] {{0,1},{0,2},{2,3},{3,4},{3,5},{5,6},{2,7},{6,7},{7,8},{3,8},{5,8},{8,9},{3,9},{6,9}}));
    }
}
