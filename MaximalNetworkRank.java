import java.util.ArrayList;
import java.util.Arrays;

/**
 * MaximalNetworkRank
 */
public class MaximalNetworkRank {

    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads.length == 0)
            return 0;
        ArrayList<Integer>[] l = new ArrayList[n];
        for (var r : roads) {
            if (l[r[0]] == null) {
                l[r[0]] = new ArrayList<Integer>();
                l[r[0]].add(r[0]);
            }
            l[r[0]].add(r[1]);

            if (l[r[1]] == null) {
                l[r[1]] = new ArrayList<Integer>();
                l[r[1]].add(r[1]);
            }
            l[r[1]].add(r[0]);
        }

        // Arrays.sort(l, (l1, l2) -> l2.size() - l1.size());

        int max = 0;
        for (int i = 0; i < l.length; i++) {
            if (l[i] == null)
                continue;

            for (int j = i + 1; j < l.length; j++) {
                if (l[j] == null)
                    continue;
                int val = 0;
                if (l[i].contains(l[j].get(0)))
                    val = l[i].size() + l[j].size() - 3;
                else
                    val = l[i].size() + l[j].size() - 2;
                max = Math.max(val, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalNetworkRank m = new MaximalNetworkRank();
        System.out.println(m.maximalNetworkRank(8, new int[][] {{0, 1},
                {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}}));
    }
}
