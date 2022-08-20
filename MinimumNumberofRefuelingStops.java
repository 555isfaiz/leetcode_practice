import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 871
public class MinimumNumberofRefuelingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int current = 0, fuel = startFuel, count = 0, ptr = 0;
        List<int[]> l = new ArrayList<>();

        while (current + fuel < target) {
            for (; ptr < stations.length; ptr++) {
                if (stations[ptr][0] <= current + fuel) l.add(stations[ptr]);
                else break;
            }

            if (l.isEmpty()) break;

            int f = fuel, c = current;
            l.sort((a, b) -> {
                int da = f - (a[0] - c) + a[1] + a[0];
                int db = f - (b[0] - c) + b[1] + b[0];
                return db - da;
            });

            var stop = l.remove(0);
            fuel = fuel - (stop[0] - current) + stop[1];
            current = stop[0];
            count++;
        }
        return current + fuel >= target ? count : -1;
    }

    // https://leetcode.com/problems/minimum-number-of-refueling-stops/discuss/149839/DP-O(N2)-and-Priority-Queue-O(NlogN)
    public int minRefuelStopsDP(int target, int startFuel, int[][] s) {
        long[] dp = new long[s.length + 1];
        dp[0] = startFuel;
        for (int i = 0; i < s.length; ++i)
            for (int t = i; t >= 0 && dp[t] >= s[i][0]; --t)
                dp[t + 1] = Math.max(dp[t + 1], dp[t] + s[i][1]);
        for (int t = 0; t <= s.length; ++t)
            if (dp[t] >= target) return t;
        return -1;
    }

    public int minRefuelStopsPQ(int target, int cur, int[][] s) {
        Queue<Integer> pq = new PriorityQueue<>();
        int i = 0, res;
        for (res = 0; cur < target; res++) {
            while (i < s.length && s[i][0] <= cur)
                pq.offer(-s[i++][1]);
            if (pq.isEmpty()) return -1;
            cur += -pq.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        MinimumNumberofRefuelingStops m = new MinimumNumberofRefuelingStops();
        System.out.println(m.minRefuelStops(100, 50, new int[][] {
                {25, 25}, {50, 50}
        }));
    }
}
