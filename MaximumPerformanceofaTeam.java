import java.util.*;

// 1383
public class MaximumPerformanceofaTeam {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        long MOD = 1000000007;
//        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[] {speed[i], efficiency[i]});
        }

        list.sort((a, b) -> {
            if (a[1] != b[1]) return Integer.compare(b[1], a[1]);
            else return Integer.compare(b[0], a[0]);
        });

        int left = k, total_speed, min_eff;
        for (int i = n - 1; i >= 0 && k > 0; i++) {

        }

        long val = 0;
        return (int) (val % MOD);
    }

    // https://leetcode.com/problems/maximum-performance-of-a-team/discuss/2559857/JAVA-oror-Easy-Solution-With-Explanation-oror-99-Faster-Code-oror-Priority-Queue
    public int maxPerformanceBetter(int n, int[] speed, int[] efficiency, int k) {
        int[][] players = new int[n][2];
        for (int i=0; i<n; i++) {
            players[i][0] = efficiency[i];
            players[i][1] = speed[i];
        }
        // Sort the players based on efficiency in decreasing order, as for each iteration, we'll consider only players with higher efficiency.
        Arrays.sort(players, (p1, p2) -> (p2[0] - p1[0]));

        // Priority-Queue to maintain players with highest relative speeds with efficiencies greater than the one under iteration.
        PriorityQueue<Integer> speedQueue = new PriorityQueue<>(k);
        long teamPerformance = 0, teamSpeed = 0;

        for (int i=0; i<n; i++) {
            // This is because a team can have atmost `k` players.
            if (speedQueue.size() >= k) {
                teamSpeed -= speedQueue.remove();
            }
            speedQueue.add(players[i][1]);
            teamSpeed += players[i][1];

            teamPerformance = Math.max(teamPerformance, teamSpeed * players[i][0]);
        }
        return (int) (teamPerformance % 1000000007);
    }
}
