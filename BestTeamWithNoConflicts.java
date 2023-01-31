import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1626
public class BestTeamWithNoConflicts {
    public int bestTeamScore(int[] scores, int[] ages) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] dp = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            // dp[i] = i == 0 ? scores[i] : (dp[i - 1] + scores[i]);
            List<Integer> l = new ArrayList<>();
            map.put(i, l);
            for (int j = i + 1; j < scores.length; j++) {
                if ((scores[i] - scores[j]) * (ages[i] - ages[j]) < 0) {
                    l.add(j);
                }
            }
        }

        for (int i = 0; i < scores.length; i++) {
            var l = map.get(i);

        }

        return dp[dp.length - 1];
    }

    // https://leetcode.com/problems/best-team-with-no-conflicts/solutions/3120873/clean-codes-full-explanation-dynamic-programming-c-java-python3/
    class Player {
        public int age;
        public int score;

        public Player(int age, int score) {
            this.age = age;
            this.score = score;
        }
    };

    class Solution {
        public int bestTeamScore(int[] scores, int[] ages) {
            final int n = scores.length;
            Player[] players = new Player[n];
            // dp[i] := max score of choosing players[0..i] w/ players[i] being selected
            int[] dp = new int[n];

            for (int i = 0; i < n; ++i)
                players[i] = new Player(ages[i], scores[i]);

            Arrays.sort(players, (a, b) -> a.age == b.age ? b.score - a.score : b.age - a.age);

            for (int i = 0; i < n; ++i) {
                // For each player, we choose it first
                dp[i] = players[i].score;
                // players[j].age >= players[i].age since we sort in descending order
                // So we only have to check that
                // players[j].score >= players[i].score
                for (int j = 0; j < i; ++j)
                    if (players[j].score >= players[i].score)
                        dp[i] = Math.max(dp[i], dp[j] + players[i].score);
            }

            return Arrays.stream(dp).max().getAsInt();
        }
    }
}