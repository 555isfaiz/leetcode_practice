import java.util.PriorityQueue;

public class SmallestNumberinInfiniteSet {
    class SmallestInfiniteSet {
        int natural_pop = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        public SmallestInfiniteSet() {
            
        }
        
        public int popSmallest() {
            if (pq.isEmpty())
                return ++natural_pop;
            else
                return pq.poll();
        }
        
        public void addBack(int num) {
            if (num > natural_pop || pq.contains(num))
                return;
            pq.offer(num);
        }
    }
}