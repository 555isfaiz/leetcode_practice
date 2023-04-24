import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for (var s : stones) {
            pq.offer(s);
        }

        while (pq.size() > 1) {
            var s1 = pq.poll();
            var s2 = pq.poll();
            var sub = Math.abs(s1 - s2);
            if (sub != 0)
                pq.offer(sub);
        }

        return pq.isEmpty() ? 0 : pq.poll();
    }
}
