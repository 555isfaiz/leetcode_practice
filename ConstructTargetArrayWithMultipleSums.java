import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 1354
public class ConstructTargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        Arrays.sort(target);
        int sum = target.length;
        int ptr = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, target.length);
        while (sum < target[ptr]) {
            for (var e : map.entrySet()) {
                if (sum - e.getKey() + sum == target[ptr]) {
                    sum = target[ptr];
                    if (e.getValue() == 1) map.remove(e.getKey());
                    else map.put(e.getKey(), e.getValue() - 1);
                    break;
                }
            }
        }
        return true;
    }

    // https://leetcode.com/problems/construct-target-array-with-multiple-sums/discuss/510256/JavaC%2B%2BPython-Backtrack-OJ-is-wrong
    public boolean isPossibleBetter(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        long total = 0;
        for (int a : A) {
            total += a;
            pq.add(a);
        }
        while (true) {
            int a = pq.poll();
            total -= a;
            if (a == 1 || total == 1)
                return true;
            if (a < total || total == 0 || a % total == 0)
                return false;
            a %= total;
            total += a;
            pq.add(a);
        }
    }
}
