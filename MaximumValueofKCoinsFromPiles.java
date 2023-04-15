import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumValueofKCoinsFromPiles {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        Queue<int[]> q = new PriorityQueue<>(
            (a,b) -> {
                if (b[0] == a[0])
                    return b[1] - a[1];
                else
                    return a[0] - b[0];
                }
        );
        int[] l = new int[piles.size() + 2];
        Arrays.fill(l, 0);
        q.add(l);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                var info = q.poll();
                if (info[0] == k) 
                    return info[1];
                for (int j = 0; j < piles.size(); j++) {
                    if (info[j + 2] >= piles.get(j).size()) continue;
                    var clone = Arrays.copyOf(info, info.length);
                    clone[0]++;
                    clone[1] += piles.get(j).get(info[j + 2]);
                    clone[j + 2]++;
                    q.offer(clone);
                }
            }
        }
        return res;
    }

    // https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/solutions/3417959/image-explanation-top-down-dp-easy-concise-c-java-python/
    class Solution {
        public int func(int i, int k, List<List<Integer>> piles, int[][] dp) {
            if (dp[i][k] > 0) return dp[i][k];
            if (i == piles.size() || k == 0) return 0;
            int res = func(i + 1, k, piles, dp), cur = 0;
            for (int j = 0; j < piles.get(i).size() && j < k; ++j) {
                cur += piles.get(i).get(j);
                res = Math.max(res, func(i + 1, k - j - 1, piles, dp) + cur);
            }
            dp[i][k] = res;
            return res;
        }
        
        public int maxValueOfCoins(List<List<Integer>> piles, int K) {
            int n = piles.size();
            int[][] dp = new int[n + 1][K + 1];
            return func(0, K, piles, dp);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        list.add(l1);
        list.add(l2);
        l1.add(1);
        l1.add(100);
        l1.add(3);
        l2.add(7);
        l2.add(8);
        l2.add(9);
        MaximumValueofKCoinsFromPiles m = new MaximumValueofKCoinsFromPiles();
        m.maxValueOfCoins(list, 2);
    }
}
